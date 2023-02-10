package com.example.demo.controllers;

import com.example.demo.services.CrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/crm")
public class CrmController {

    @Autowired
    private CrmService crmService;

    // TODO - should be post, with admin level authorization
    // admin API - enable adding external systems such as Banana and Strawberry
    @GetMapping("/add_external_system/{url}")
    public String addExternalSystem(@PathVariable String url) {

        return crmService.addExternalSystem(url);

    }

    // TODO - should be with admin level authorization (?)
    // admin API - enable adding external systems such as Banana and Strawberry
    @GetMapping("/list_external_systems")
    public List<Map<String, Object>> addExternalSystem() {

        return crmService.listAllExternalSystems();

    }

    // API for enabling on demand aggregation triggered by UI (or externally), unlike the scheduled job that calls the service internally
    // TODO - replace with post
    @GetMapping("/aggregate_data_from_external_systems")
    public List<Map<String, Object>> updateAccordingExternalSystems() {

        return crmService.onDemandAggregationAccordingExternalSystems();

    }

    // API for UI presentation
    // TODO - filtering param should be added. in the meantime the front end can filter the structured data it gets
    @GetMapping("/find_all_crm_cases")
    public List<Map<String, Object>> findAllCrmCases() {

        return crmService.findAllCrmCases();

    }

}
