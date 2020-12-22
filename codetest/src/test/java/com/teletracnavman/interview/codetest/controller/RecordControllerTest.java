package com.teletracnavman.interview.codetest.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.teletracnavman.interview.codetest.repository.RecordRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecordControllerTest {
	
	String testPayload="{\"RecordType\": \"xxx\","
			+ "\"DeviceId\": \"357370040159770\","
			+ "	\"EventDateTime\": \"2014-05-12T05:09:48Z\","
			+ "		\"FieldA\": 68,"
			+ "		\"FieldB\": \"xxx\","
			+ "		\"FieldC\": 123.45}";
	@Autowired                           
    private MockMvc mockMvc;  
                                                 
    @MockBean  
    private RecordRepository recordRepository;
    
    
    @Test
    public void getDeviceIdTest() throws Exception{
    	MvcResult result= this.mockMvc.perform(post("/device").header("Authorization" , "codetesttoken").contentType(MediaType.APPLICATION_JSON).
    			content(testPayload)).andReturn();
    	assertTrue(result.getResponse().getContentAsString().contains("357370040159770"));
    }
    
    @Test
    public void echoTest() throws Exception{
    	MvcResult result = this.mockMvc.perform(post("/echo").header("Authorization" , "codetesttoken").contentType(MediaType.APPLICATION_JSON).
    			content(testPayload)).andReturn();
    	assertTrue(result.getResponse().getContentAsString().contains("DeviceId"));
    }
    
  
}
