package com.example.demo.repository;

import com.example.demo.dtos.ExternalSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ExternalSystemRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public ExternalSystem findById(long id) {
        return jdbcTemplate.queryForObject("select * from external_systems where id=?", new BeanPropertyRowMapper<>(ExternalSystem.class), id);
    }

    public void addExternalSystem(ExternalSystem externalSystem) {
        jdbcTemplate.update("INSERT INTO external_systems VALUES (?,?);", new Object[]{externalSystem.getId(), externalSystem.getUrl()});
    }

    public List<Map<String, Object>> findAll() {
        return jdbcTemplate.queryForList("select * from external_systems");
    }
}