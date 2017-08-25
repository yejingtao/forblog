package com.mydemo.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest(classes = com.mydemo.Application.class)  
@WebAppConfiguration // 开启Web上下文 测试  
public class WithSecurityMockTester {  
  
    @Autowired  
    private WebApplicationContext webContext;// 注入WebApplicationContext  
  
    private MockMvc mockMvc;  
  
    /** 
     * setupMockMvc()方法上添加了JUnit的@Before注解，表明它应该在测试方法之前执行。 
     */  
    @Before  
    public void setupMockMvc() {  
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).apply(springSecurity()).build();  
    }  
  
    @Test  
    public void helloPage() throws Exception {  
        mockMvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string("hello world"));  
    }  
  
    /** 
     * 没有权限会被重定向到登陆页面 
     *  
     * @throws Exception 
     */  
    @Test  
    public void roleTestMothedAPage() throws Exception {  
        mockMvc.perform(get("/forRoleA")).andExpect(status().is3xxRedirection())  
                .andExpect(header().string("Location", "http://localhost/login"));  
    }  
  
    @Test  
    @WithMockUser(username = "userb", password = "userb123", authorities = "authority_b")  
    public void roleTestMothedBPage() throws Exception {  
        mockMvc.perform(get("/forRoleB")).andExpect(status().isOk()).andExpect(content().string("Role B Successful"));  
    }  
  
    @Test  
    @WithUserDetails(value = "userab")  
    public void roleTestMothedABPage() throws Exception {  
        mockMvc.perform(get("/forRoleB")).andExpect(status().isOk()).andExpect(content().string("Role B Successful"));  
        mockMvc.perform(get("/forRoleA")).andExpect(status().isOk()).andExpect(content().string("Role A Successful"));  
    }  
  
}  