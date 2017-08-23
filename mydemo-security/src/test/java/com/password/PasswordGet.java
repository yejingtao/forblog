package com.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGet {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		System.out.println(encode.encode("usera123"));
		System.out.println(encode.encode("userb123"));
		System.out.println(encode.encode("userab123"));
		System.out.println(encode.encode("userc123"));
	}
}
