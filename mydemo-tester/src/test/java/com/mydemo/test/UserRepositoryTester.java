package com.mydemo.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mydemo.dao.UserRepository;
import com.mydemo.entity.SysUser;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest(classes=com.mydemo.Application.class)  
//@ContextConfiguration(classes=com.mydemo.Application.class)//此注解只适用不依赖于环境配置的单元测试  
public class UserRepositoryTester {  
      
    @Autowired  
    private UserRepository userRepository;  
      
    @Test  
    public void serviceTest() {  
        SysUser user = new SysUser();  
        user.setUserName("fortest");  
        user.setPassword((new BCryptPasswordEncoder()).encode("password123"));  
        SysUser findingUser = userRepository.findByUserName(user.getUserName());  
        Assert.assertNull("User fortest shoud be null", findingUser);  
          
        userRepository.save(user);  
        findingUser = userRepository.findByUserName(user.getUserName());  
        Assert.assertNotNull("User fortest shoud be added", findingUser);  
          
        userRepository.delete(findingUser.getId());  
        findingUser = userRepository.findByUserName(user.getUserName());  
        Assert.assertNull("User fortest shoud be deleted", findingUser);  
    }  
      
}  