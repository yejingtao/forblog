package com.mydemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mydemo.dao.AuthorityRepository;
import com.mydemo.dao.UserRepository;
import com.mydemo.entity.SysAuthority;
import com.mydemo.entity.SysUser;

@Service
public class MybatisUserDetailsService implements UserDetailsService{
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		//先判断登陆用户是否存在
		SysUser user = userRepository.findByUserName(userName);
		if(user==null) {
			throw new UsernameNotFoundException("Cannot find user by username: "+userName);
		}
		//登录用户存在的情况加载用户的权限
		/**
		SecurityUser securityUser = new SecurityUser(user);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return securityUser;
        */
		
		List<SysAuthority> roleList = authorityRepository.findByUserId(user.getId());
		List<GrantedAuthority> grantedAuthorities = new ArrayList <GrantedAuthority>();
		if(roleList!=null && roleList.size()>0) {
			roleList.forEach(r->grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));
		}
		return new User(user.getUserName(),user.getPassword(),grantedAuthorities);
	}

}
