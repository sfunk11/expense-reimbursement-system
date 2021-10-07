package com.revature.project1.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.revature.project1.main.LogDriver;

public class ExpenseDBConnection {

	ClassLoader cl = getClass().getClassLoader();
	InputStream is;
	Properties p= new Properties();
	
	public ExpenseDBConnection() {
		is = cl.getResourceAsStream("connection.properties");
		try {
			p.load(is);
		}catch(IOException e) {
			LogDriver.log.error(e);
			
		}
		
		
	}
}
