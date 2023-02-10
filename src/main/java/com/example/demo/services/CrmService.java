package com.example.demo.services;

import com.example.demo.clients.ExternalSystemClient;
import com.example.demo.repository.CrmCaseRepository;
import com.example.demo.repository.ExternalSystemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.CrmCaseDto;
import dtos.DataDto;
import dtos.ExternalSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CrmService {

    @Autowired
    ExternalSystemClient externalSystemClient;

    @Autowired
    ExternalSystemRepository externalSystemRepository;
    @Autowired
    CrmCaseRepository crmCaseRepository;

    private ObjectMapper objectMapper = new ObjectMapper();


    public CrmCaseDto getCrmCaseDto() {
        return new CrmCaseDto(UUID.randomUUID().toString(), "a", "a", "a", "a", "a", "a", "a", "v");
    }


    public String addExternalSystem(String url) {
        String uuid = UUID.randomUUID().toString();
        externalSystemRepository.addExternalSystem(new ExternalSystem(uuid, url, ""));
        return uuid;
    }

    public List<Map<String, Object>> listAllExternalSystems() {

        List<Map<String, Object>> systems = externalSystemRepository.findAll();
        return systems;
    }

    public List<Map<String, Object>> onDemandAggregationAccordingExternalSystems() {

        // TODO - add functionality of avoiding the action if previous aggregation been done in the last 15 minutes. can be checked by dedicated database field, or latest updated DB (new) column update time comparing to now
        List<Map<String, Object>> externalSystemsList = externalSystemRepository.findAll();
        // TODO - parallelism (for better performance) can be considered start
        for (Map<String, Object> externalSystem : externalSystemsList) {
            String url = "http://" + externalSystem.get("EXTERNAL_SYSTEM_URL");
            System.out.println("querying url " + url);
            try {
                String urlContent = externalSystemClient.readStringFromURL(url);
                //urlContents.add(urlContent);
                mergeResultInDb(urlContent, url);
            } catch (IOException e) {
                System.out.println("failed updating from " + url);
            }
        }
        // TODO - parallelism can be considered end
        return findAllCrmCases();
    }


    // TODO - this is not real merge. only aggregation. yet when the data is filtered out, it can be filtered as merge. according to definition, most likely better be merged during insertion, or while listing in BE, or even in FE as a compromise
    private DataDto mergeResultInDb(String urlContent, String systemUrl) throws JsonProcessingException {

        DataDto crmCaseDtos = objectMapper.readValue(urlContent, DataDto.class);

        for (CrmCaseDto freshCrmCaseDtoFromSystem : crmCaseDtos.getCrmCaseDtos()) {
            crmCaseRepository.addCrmCase(freshCrmCaseDtoFromSystem, systemUrl);
            System.out.println("done adding " + systemUrl + " external systems data");
        }
        System.out.println("done adding all external systems data");
        return crmCaseDtos;
    }

    public List<Map<String, Object>> findAllCrmCases() {
        List<Map<String, Object>> crmCases = crmCaseRepository.findAll();
        return crmCases;
    }
}
