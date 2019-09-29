package com.genesis;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.Test;

public class HeadlessDriver {

	WebDriver webDriver;
	@Test
	public void headlessBrowser() {
		webDriver= new HtmlUnitDriver();
		webDriver.get("https://google.com");
		String title=webDriver.getTitle(); 
		System.err.println(title);
	}
}
