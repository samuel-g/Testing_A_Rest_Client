package hello;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
	
	@Autowired
	private RestTemplate restTemplate;
	private MockRestServiceServer mockServer;
	private ReadJsonUtil data = new ReadJsonUtil();




	@Test
	public void testSuccessResponse() throws IOException {
		
		
		
		//CREATE A MOCK RESPONSE --->
		mockServer = MockRestServiceServer.createServer(restTemplate);
		mockServer.expect(requestTo("http://gturnquist-quoters.cfapps.io/api/1"))
	        .andExpect(method(HttpMethod.GET))
	        .andRespond(withSuccess(ReadJsonUtil.read_JSON_File("C:\\Users\\samue\\Documents\\June_2018\\gs-consuming-rest-complete\\src\\test\\resources\\MockQuote.json"), MediaType.APPLICATION_JSON));
		String mock_quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/1", String.class);
		 
	
		//GET THE ACTUAL RESPONSE
		RestTemplate testActualRestTemplate = new RestTemplate();
		String actual_quote = testActualRestTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/1", String.class);
		System.out.println(actual_quote);
		
		
		//TEST 1: MOCK_RESPONSE VS ACTUAL RESPONSE, 2: VERIFY MOCK REQUEST PERFORMED
		assertEquals(mock_quote, actual_quote);
		mockServer.verify();
	}


	
}
