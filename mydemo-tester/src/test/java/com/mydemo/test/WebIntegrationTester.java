package com.mydemo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest(classes = com.mydemo.Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)  
// @WebIntegrationTest  
public class WebIntegrationTester {  
  
    @Value("${local.server.port}")  
    private int port;  
  
    @Test  
    public void roleTestMothedBPage() throws Exception {  
        System.setProperty("webdriver.firefox.bin", "C:\\Mozilla Firefox\\firefox.exe");  
        FirefoxDriver webDriver = new FirefoxDriver();  
        webDriver.manage().window().maximize();  
        webDriver.get("http://127.0.0.1:" + port + "/forRoleB");  
        webDriver.findElementByName("username").sendKeys("userab");  
        webDriver.findElementByName("password").sendKeys("userab123");  
        webDriver.findElementByName("submit").click();  
        System.out.println(webDriver.getPageSource());  
    }  
  
} 