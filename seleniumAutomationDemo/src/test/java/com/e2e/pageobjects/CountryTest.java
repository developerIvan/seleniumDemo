package com.e2e.pageobjects;


import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.e2e.pageobjecs.Country;



public class CountryTest {

	WebDriver driver;
	Country country;
	@Test
	public void getStatestList() {
      Map<Integer, String> states = country.getStatesList();
      Assert.assertTrue(states.containsValue("Alajuela"));
	}
	
	@Test
	public void getPostalCode() {
      String postalCode = country.getPostalCode();
      Assert.assertEquals("21101", postalCode);
	}
 
	
	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		country = new Country(driver);
	}
 
	@AfterMethod
	public void afterMethod() {
		driver.close();
	}
}
