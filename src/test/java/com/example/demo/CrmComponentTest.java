package com.example.demo;

import com.example.demo.clients.ExternalSystemClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-componentTests.properties")

public class CrmComponentTest {

    // json taken from task definition
    private static final String JSON_EXAMPLE = "{ \"data\": [\n" +
                "{\n" +
                " \"Case ID\": 2,\n" +
                " \"Customer ID\": 790521,\n" +
                " \"Provider\": 26241,\n" +
                " \"CREATED_ERROR_CODE\": 0,\n" +
                " \"STATUS\": \"Closed\",\n" +
                " \"TICKET_CREATION_DATE\": \"3/4/2019 9:30\",\n" +
                " \"LAST_MODIFIED_DATE\": \"3/5/2019 2:47\",\n" +
                " \"PRODUCT_NAME\": \"BLUE\"\n" +
                "},\n" +
                "{\n" +
                " \"Case ID\": 3,\n" +
                " \"Customer ID\": 738081,\n" +
                " \"Provider\": 1211,\n" +
                " \"CREATED_ERROR_CODE\": 101,\n" +
                " \"STATUS\": \"Closed\",\n" +
                " \"TICKET_CREATION_DATE\": \"2/5/2019 0:30\",\n" +
                " \"LAST_MODIFIED_DATE\": \"2/10/2019 1:52\",\n" +
                " \"PRODUCT_NAME\": \"BLUE\"\n" +
                "}\n" +
                " ]\n" +
                "}\n";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ExternalSystemClient mockExternalSystemClient;


    @Test
    public void triggerUpdateAccordingMockedExternalSystemsAPI() throws Exception {

        addSystem("systemUrl1");
        addSystem("systemUrl2");
        // TODO - teardown or mock adding systems. otherwise may infect future tests, since db state remains during component test

        mvc.perform(MockMvcRequestBuilders
                        .get("/crm/list_external_systems", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));

        when(mockExternalSystemClient.readStringFromURL("http://systemUrl1")).thenReturn(JSON_EXAMPLE);
        when(mockExternalSystemClient.readStringFromURL("http://systemUrl2")).thenReturn(JSON_EXAMPLE);

        // testing the on-demand-aggregation
        mvc.perform(MockMvcRequestBuilders
                        .get("/crm/aggregate_data_from_external_systems", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(4)));

        // 4 crm cases should be returned now. there are 2 external systems, and each one of them returns 2 cases
        mvc.perform(MockMvcRequestBuilders
                        .get("/crm/find_all_crm_cases", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(4)));
    }

    private void addSystem(String systemUrl) throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/crm/add_external_system/" + systemUrl, 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
