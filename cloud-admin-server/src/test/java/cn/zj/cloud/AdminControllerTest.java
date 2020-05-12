package cn.zj.cloud;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.zj.cloud.admin.CloudAdminServerApplication;




@RunWith(SpringRunner.class)
@SpringBootTest(classes=CloudAdminServerApplication.class)
@WebAppConfiguration
@ContextConfiguration
@AutoConfigureMockMvc
public class AdminControllerTest {
    @Autowired
    protected WebApplicationContext context;
    @Autowired
	private MockMvc mvc;
	
    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

	@Test
	public void loginTest_OK() {
		try {
			String uriPath = "/login";
			ObjectMapper json = new ObjectMapper();
			
			Map<String, Object> reqestbody = new LinkedHashMap();
			reqestbody.put("username", "Tom");
			reqestbody.put("password", "1234567");
			String strReqBody = json.writeValueAsString(reqestbody);
			
			Map<String, Object> expected = new LinkedHashMap();
			expected.put("status", "1");
			expected.put("id", "12345");
			String strResBody = json.writeValueAsString(expected);
			
			this.mvc.perform(MockMvcRequestBuilders.post(uriPath)
					.contentType(MediaType.APPLICATION_JSON)
					.content(strReqBody))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(strResBody));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void loginTest_FAIL() {
		try {
			String uriPath = "/login";
			ObjectMapper json = new ObjectMapper();
			
			Map<String, Object> reqestbody = new LinkedHashMap();
			reqestbody.put("username", "John");
			reqestbody.put("password", "8888888");
			String strReqBody = json.writeValueAsString(reqestbody);
			
			Map<String, Object> expected = new LinkedHashMap();
			expected.put("status", "0");
			String strResBody = json.writeValueAsString(expected);
			
			this.mvc.perform(MockMvcRequestBuilders.post(uriPath)
					.contentType(MediaType.APPLICATION_JSON)
					.content(strReqBody))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(strResBody));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
    
	
	@Test
	public void queryCompanyTest() {
		try {
			String uriPath = "/company";

			this.mvc.perform(MockMvcRequestBuilders.get(uriPath)
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void queryCompanyByCompanyNameTest() {
		try {
			String uriPath = "/company/Ti";
			
			ObjectMapper json = new ObjectMapper();
			Map<String, String> expected = new LinkedHashMap<String, String>();
			expected.put("id", "8888889");
			expected.put("name", "Tian Mao");
			expected.put("code", "2196");
			expected.put("ceo", "Ma Yun");
			expected.put("boardofdirectors", "Ma Yun");
			expected.put("sectorid", "104");
			expected.put("turnover", "6987899000");
			expected.put("briefwriteup", "online fashion electronic mall");
			expected.put("status", "1");
			List<Map<String, String>> maplist = new ArrayList();
			maplist.add(expected);
			String strResBody = json.writeValueAsString(maplist);
			
			this.mvc.perform(MockMvcRequestBuilders.get(uriPath)
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
			    .andExpect(MockMvcResultMatchers.content().json(strResBody));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	@Test
	public void registCompanyTest() {
		try {
			String uriPath = "/company";
			
			ObjectMapper json = new ObjectMapper();
			Map<String, Object> reqBody = new LinkedHashMap<String, Object>();
			reqBody.put("name", "Jing Bao Da");
			reqBody.put("code", "3198");
			reqBody.put("turnover", 328600000);
			reqBody.put("ceo", "liu qiang");
			reqBody.put("boardofdirectors", "liu qiang Yun");
			reqBody.put("sectorid", "106");
			reqBody.put("briefwriteup", "online fashion golden shop");
			reqBody.put("status", "1");
			String strReqBody = json.writeValueAsString(reqBody);
			
			Map<String, Object> expected = new LinkedHashMap();
			expected.put("status", "1");
			String strResBody = json.writeValueAsString(expected);
			
			this.mvc.perform(MockMvcRequestBuilders.post(uriPath)
					.contentType(MediaType.APPLICATION_JSON)
					.content(strReqBody))
				.andExpect(MockMvcResultMatchers.status().isOk())
			    .andExpect(MockMvcResultMatchers.content().json(strResBody));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
    
	@Test
	public void updateCompanyStatusTest_OK() {
		try {
			String uriPath = "/company";
			
			ObjectMapper json = new ObjectMapper();
			Map<String, Object> reqBody = new LinkedHashMap<String, Object>();
			reqBody.put("id", "8888890");
			reqBody.put("status", "0");
			String strReqBody = json.writeValueAsString(reqBody);
			
			Map<String, Object> expected = new LinkedHashMap();
			expected.put("status", "1");
			String strResBody = json.writeValueAsString(expected);
			
			this.mvc.perform(MockMvcRequestBuilders.put(uriPath)
					.contentType(MediaType.APPLICATION_JSON)
					.content(strReqBody))
				.andExpect(MockMvcResultMatchers.status().isOk())
			    .andExpect(MockMvcResultMatchers.content().json(strResBody));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void updateCompanyStatusTest_FAIL() {
		try {
			String uriPath = "/company";
			
			ObjectMapper json = new ObjectMapper();
			Map<String, Object> reqBody = new LinkedHashMap<String, Object>();
			reqBody.put("id", "888889A");
			reqBody.put("status", "0");
			String strReqBody = json.writeValueAsString(reqBody);
			
			Map<String, Object> expected = new LinkedHashMap();
			expected.put("status", "0");
			String strResBody = json.writeValueAsString(expected);
			
			this.mvc.perform(MockMvcRequestBuilders.put(uriPath)
					.contentType(MediaType.APPLICATION_JSON)
					.content(strReqBody))
				.andExpect(MockMvcResultMatchers.status().isOk())
			    .andExpect(MockMvcResultMatchers.content().json(strResBody));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void queryStockExchangeTest_OK() {
		try {
			String uriPath = "/stockexchange";
			this.mvc.perform(MockMvcRequestBuilders.get(uriPath))
				.andExpect(MockMvcResultMatchers.status().isOk());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
