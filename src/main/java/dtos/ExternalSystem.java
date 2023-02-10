package dtos;

public class ExternalSystem {

    private String id;
    private String url;
    // TODO - for future use - each external system may return different json structure, and dedicated schema mapper can be attached to each one of them, and be converted to unified format just like Banana and Strawberry already did
    private String schemaMapJson;

    public ExternalSystem(String id, String url, String schemaMapJson) {
        this.id = id;
        this.url = url;
        this.schemaMapJson = schemaMapJson;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSchemaMapJson(String schemaMapJson) {
        this.schemaMapJson = schemaMapJson;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getSchemaMapJson() {
        return schemaMapJson;
    }

}
