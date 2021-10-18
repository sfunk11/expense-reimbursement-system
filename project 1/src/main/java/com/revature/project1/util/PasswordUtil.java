package com.revature.project1.util;

import java.security.NoSuchAlgorithmException;

import com.revature.project1.main.LogDriver;

import java.security.MessageDigest;  

public class PasswordUtil {

	private String hashPassword;
	
	public String encryptPassword(String password) {
		try {
			System.out.println("in hash function");
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(password.getBytes());
			byte[] bytes = m.digest();
			
			StringBuilder s = new StringBuilder();
			
			for (int i = 0; i <bytes.length; i++ ) {
				s.append(Integer.toString((bytes[i] & 0xff)+ 0x100, 16).substring(1));
			}
			hashPassword = s.toString();
		}catch(NoSuchAlgorithmException e){
			LogDriver.log.error(e);
			e.printStackTrace();
			
		}
		return hashPassword;
	}
	
	
}
