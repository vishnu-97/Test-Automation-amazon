package com.genesis;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestAnnotations {
	@BeforeTest
	void method() {
		System.out.println("@BeforeTest");
	}
	@AfterTest
	void method1() {
		System.out.println("@AfterTest");
	}
	@AfterMethod
	void method2() {
		System.out.println("@AfterMethod");
	}
	@BeforeMethod
	void method3() {
		System.out.println("@BeforeMethod");
		
	}
	@AfterSuite
	void method4() {
		System.out.println("@AfterSuite");
	}
	@BeforeSuite
	void method5() {
		System.out.println("@BeforeSuite");
		
	}
	@Test(priority = 1)
	void method6() {
		System.out.println("HELLO6");
	}
	@Test(priority = 2)
	void method7() {
		System.out.println("HELLO7");
	}
	@Test
	void method8() {
		System.out.println("HELLO8");
	}
}
