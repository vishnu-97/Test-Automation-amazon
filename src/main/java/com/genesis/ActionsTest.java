package com.genesis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ActionsTest {
	public WebDriver chrome() {
		System.setProperty("webdriver.chrome.driver", "Chrome Driver\\chromedriver.exe");
		
		return new ChromeDriver();
		
	}
	@Test
	public void test() throws InterruptedException {
		WebDriver webDriver=chrome();
		webDriver.get("http://amazon.com");
		Thread.sleep(3000);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		WebElement element=webDriver.findElement(By.id("twotabsearchtextbox"));	
		element.sendKeys("vishnu");
		Actions actions=new Actions(webDriver);
		Action actionbuild=actions.keyDown(Keys.SHIFT).sendKeys("vishnu").keyUp(Keys.SHIFT).build();
		actionbuild.perform();
	}
}
