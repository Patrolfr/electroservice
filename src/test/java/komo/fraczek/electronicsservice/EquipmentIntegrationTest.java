package komo.fraczek.electronicsservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import komo.fraczek.electronicsservice.domain.Equipment;
import komo.fraczek.electronicsservice.domain.dto.CommentsPayload;
import komo.fraczek.electronicsservice.domain.dto.EquipmentResponse;
import komo.fraczek.electronicsservice.repository.EquipmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(secure = false)
@AutoConfigureEmbeddedDatabase(beanName = "dataSource")
public class EquipmentIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentIntegrationTest.class);

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EquipmentRepository equipmentRepository;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void contextLoads() { }

    @Test
    public void create_shouldCreateEquipment() throws Exception {
        String equipmentPayload = getRequestData("equipmentPayload.json");

        String responseContentAsString = doRequestAndVerifyStatus(HttpMethod.POST, "/equipments/", equipmentPayload, status().isCreated());
        EquipmentResponse equipmentResponse = objectMapper.readValue(responseContentAsString, EquipmentResponse.class);

        Optional<Equipment> byServiceCode = equipmentRepository.findByServiceCode(equipmentResponse.getServiceCode());
        assertTrue(byServiceCode.isPresent());

        Equipment equipment = byServiceCode.get();
        assertEquals(equipmentResponse.getName(), equipment.getName());
        assertEquals(equipmentResponse.getCategory(), equipment.getCategory().getName());
        assertEquals(equipmentResponse.getServiceStatus(), equipment.getServiceStatus());
    }

    @Test
    public void retrieveAll_shouldReturn8Equipments() throws Exception{
        String responseContentAsString = doRequestAndVerifyStatus(HttpMethod.GET, "/equipments/", "", status().isOk());

        EquipmentResponse[] equipmentResponses = objectMapper.readValue(responseContentAsString, EquipmentResponse[].class);
        assertEquals(8, equipmentResponses.length);
    }

    @Test
    public void delete_shouldDeleteEquipment() throws Exception{
        String testCode = "XYZ-123";

        assertTrue(equipmentRepository.existsByServiceCode(testCode));

        String responseContentAsString = doRequestAndVerifyStatus(HttpMethod.DELETE, "/equipments/" + testCode, "", status().isNoContent());
        assertFalse(equipmentRepository.existsByServiceCode(testCode));
    }

    @Test
    public void comment_shouldAddComments() throws Exception{
        String testCode = "ABC-123";
        String commentsPayloadString = getRequestData("commentsPayload.json");
        CommentsPayload commentsPayload = objectMapper.readValue(commentsPayloadString, CommentsPayload.class);

        String responseContentAsString = doRequestAndVerifyStatus(HttpMethod.PUT, "/equipments/" + testCode + "/comment", commentsPayloadString, status().isOk());

        EquipmentResponse equipmentResponse = objectMapper.readValue(responseContentAsString, EquipmentResponse.class);
        assertTrue(equipmentResponse.getComments().containsAll(commentsPayload.getComments()));

        Optional<Equipment> byServiceCode = equipmentRepository.findByServiceCode(testCode);
        assertTrue(byServiceCode.isPresent());

        Equipment equipment = byServiceCode.get();
        assertTrue(equipment.getComments().containsAll(commentsPayload.getComments()));

        assertEquals(equipment.getComments().size(), equipmentResponse.getComments().size());
    }

    @Test
    public void retrieveByCategory_shouldRetrieve4Equipments_forCategory_tv() throws Exception{
        String responseContentAsString = doRequestAndVerifyStatus(HttpMethod.GET, "/equipments/category/phone/", "", status().isOk());
        EquipmentResponse[] equipmentResponses = objectMapper.readValue(responseContentAsString, EquipmentResponse[].class);

        assertEquals(4, equipmentResponses.length);
    }

    @Test // security config test
    public void test_public() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/publiccontroller/")).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertEquals(contentAsString, "notSecuredTestEndpoint");
    }


    private String doRequestAndVerifyStatus(HttpMethod httpMethod, String url, String data, ResultMatcher exceptedStatus) throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.request(httpMethod, url);
        requestBuilder.content(data).headers(getRequestHeaders());

        return mockMvc.perform(requestBuilder)
                .andExpect(exceptedStatus)
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private String getRequestData(String filename) throws IOException {
        URL url = Resources.getResource(String.format("json/%s", filename));
        return Resources.toString(url, StandardCharsets.UTF_8);
    }

    private HttpHeaders getRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
