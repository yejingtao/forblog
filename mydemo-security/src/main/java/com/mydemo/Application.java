package com.mydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mydemo.service.MybatisUserDetailsService;

@SpringBootApplication
@MapperScan("com.mydemo.dao")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Configuration
	@EnableWebSecurity
	protected static class webSecurityConfig extends WebSecurityConfigurerAdapter {
		@Autowired
		private MybatisUserDetailsService userDetailsService;// 这个名字没得挑，userDetailsService是自定义鉴权的名字。

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http.authorizeRequests().antMatchers("/", "/hello").permitAll()
					// .antMatchers("/forRoleA/**").access("hasAuthority('authority_a')")
					// .antMatchers("/forRoleB/**").access("hasAuthority('authority_b')")
					// .antMatchers("/forRoleA/**").hasAuthority("authority_a")
					// .antMatchers("/forRoleB/**").hasAuthority("authority_b")
					.and().formLogin();
			/**
			 * .loginPage("/login") .failureUrl("/login?error") .permitAll() //登录页面用户任意访问
			 * 
			 * .and() .logout().permitAll(); //注销行为任意访问
			 */
		}

		// 验证时给密码加密，因为数据库里的也是BCryptPasswordEncoder加密的
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		}

		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
	}

}
