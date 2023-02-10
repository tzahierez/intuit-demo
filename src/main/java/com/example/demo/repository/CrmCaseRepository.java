package com.example.demo.repository;

import com.example.demo.dtos.CrmCaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class CrmCaseRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<Map<String, Object>> findAll() {
        return jdbcTemplate.queryForList("select * from crm_case");
    }

    public void addCrmCase(CrmCaseDto crmCaseDto, String systemUrl) {

        Map<String, Object> params = new HashMap<>();
        params.put("internal_id", crmCaseDto.getInternalId());
        params.put("id", crmCaseDto.getInternalId());
        params.put("system_url", systemUrl);
        params.put("customer_id", crmCaseDto.getInternalId());
        params.put("provider", crmCaseDto.getInternalId());
        params.put("created_error_code", crmCaseDto.getInternalId());
        params.put("status", crmCaseDto.getInternalId());
        params.put("ticket_creation_date", crmCaseDto.getInternalId());
        params.put("last_modified_date", crmCaseDto.getInternalId());
        params.put("product_name", crmCaseDto.getInternalId());

        jdbcTemplate.update("INSERT INTO crm_case VALUES (?,?,?,?,?,?,?,?,?,?);",
                new Object[]{UUID.randomUUID().toString().replace("-", ""),
                        crmCaseDto.getId(),
                        systemUrl,
                        crmCaseDto.getCustomerId(),
                        crmCaseDto.getProvider(),
                        crmCaseDto.getCreatedErrorCode(),
                        crmCaseDto.getStatus(),
                        crmCaseDto.getTicketCreationDate(),
                        crmCaseDto.getLastModifiedDate(),
                        crmCaseDto.getProductName()});

        System.out.println("done inserting");

    }

}