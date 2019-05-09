package komo.fraczek.electronicsservice;

import com.google.common.io.Resources;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(secure = false)
@AutoConfigureEmbeddedDatabase(beanName = "dataSource")
public class IntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(IntegrationTest.class);


    @Autowired
    MockMvc mockMvc;

    @Test
    public void contextLoads(){
        logger.debug(">>>>>>>");
    }

    @Test
    public void test_public() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/publiccontroller/")).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        logger.debug("contentAsString:  " + contentAsString);
        logger.debug(mvcResult.getResponse().getStatus() + "");
    }

    @Test
    public void test_create() throws Exception{
        String payload = getRequestData("equipmentPayload.json");
        logger.debug("payload: " + payload);

        String s = doRequest(HttpMethod.POST, "/equipments/", payload);
        logger.debug("doRequest: " + s);


//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/equipments/").content(payload)).andReturn();
//        String contentAsString = mvcResult.getResponse().getContentAsString();
//        logger.debug("contentAsString" + contentAsString);
//        logger.debug(mvcResult.getResponse().getStatus() + " ");
    }



//    protected String doRequest(HttpMethod httpMethod, String url, String data, ResultMatcher exceptedStatus) throws Exception {
    protected String doRequest(HttpMethod httpMethod, String url, String data) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.request(httpMethod, url);
        requestBuilder.content(data).headers(getRequestHeaders());

        return mockMvc.perform(requestBuilder)
//                .andExpect(exceptedStatus)
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
