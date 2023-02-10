package dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CrmCaseDto {

    private String internalId;
    @JsonProperty("Case ID")
    private String id;
    @JsonProperty("Customer ID")
    private String customerId;
    @JsonProperty("Provider")
    private String provider;
    @JsonProperty("CREATED_ERROR_CODE")
    private String createdErrorCode;
    @JsonProperty("STATUS")
    private String status;
    @JsonProperty("TICKET_CREATION_DATE")
    private String ticketCreationDate;
    @JsonProperty("LAST_MODIFIED_DATE")
    private String lastModifiedDate;
    @JsonProperty("PRODUCT_NAME")
    private String productName;

    public CrmCaseDto(){}
    public CrmCaseDto(String internalId, String id, String customerId, String provider, String createdErrorCode, String status, String ticketCreationDate, String lastModifiedDate, String productName) {
        this.internalId = internalId;
        this.id = id;
        this.customerId = customerId;
        this.provider = provider;
        this.createdErrorCode = createdErrorCode;
        this.status = status;
        this.ticketCreationDate = ticketCreationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.productName = productName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getCreatedErrorCode() {
        return createdErrorCode;
    }

    public void setCreatedErrorCode(String createdErrorCode) {
        this.createdErrorCode = createdErrorCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public String getTicketCreationDate() {
        return ticketCreationDate;
    }

    public void setTicketCreationDate(String ticketCreationDate) {
        this.ticketCreationDate = ticketCreationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
