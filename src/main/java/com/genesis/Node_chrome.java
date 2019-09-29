package com.genesis;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class Node_chrome {

	WebDriver driver;
	String nodeurl;
	
	@Test
	public void setupChrome() throws MalformedURLException {
		nodeurl="http://192.168.43.22:47688/wd/hub";
		DesiredCapabilities capabilities=DesiredCapabilities.chrome();
		driver=new RemoteWebDriver(new URL(nodeurl),capabilities);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.get("https://google.com");
	}
}
