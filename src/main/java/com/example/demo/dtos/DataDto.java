package com.example.demo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataDto {

    public DataDto() {}

    public DataDto(CrmCaseDto[] crmCaseDto) {
        this.crmCaseDto = crmCaseDto;
    }

    @JsonProperty("data")
    private CrmCaseDto[] crmCaseDto;

    public CrmCaseDto[] getCrmCaseDtos() {
        return crmCaseDto;
    }

    public void setCrmCaseDtos(CrmCaseDto[] crmCaseDto) {
        this.crmCaseDto = crmCaseDto;
    }
}
