package com.genesis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

public class Utility {

	@Test
	public void test() {
		try {
			FileInputStream fis=new FileInputStream("Res/config.properties");
			Properties prop=new Properties();
			prop.load(fis);
			String val=(String) prop.getProperty("Environment");
			System.out.println(val);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
