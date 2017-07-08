package com.e2e.pageobjects;


import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.e2e.pageobjecs.Country;
import com.e2e.util.HelperUtil;



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
 
	
	@BeforeClass
	public void setUp() {
	    HelperUtil.setDriverLocation();
		driver = new ChromeDriver();
		country = new Country(driver);
	}
 
	@AfterClass
	public void afterMethod() {
		driver.close();
	}
}
