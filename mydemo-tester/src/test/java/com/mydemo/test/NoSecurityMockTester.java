package com.mydemo.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest(classes=com.mydemo.Application.class)  
@WebAppConfiguration//开启Web上下文 测试  
public class NoSecurityMockTester {  
      
    @Autowired  
    private WebApplicationContext webContext;//注入WebApplicationContext   
      
    private MockMvc mockMvc;  
      
    /** 
     * setupMockMvc()方法上添加了JUnit的@Before注解，表明它应该在测试方法之前执行。 
     */  
    @Before  
    public void setupMockMvc() {  
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();  
    }  
      
    @Test  
    public void helloPage() throws Exception {  
        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))  
                .andExpect(MockMvcResultMatchers.status().isOk())  
                .andExpect(MockMvcResultMatchers.content().string("hello world"));  
    }  
      
} 