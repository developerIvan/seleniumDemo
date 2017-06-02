package com.e2e.specs;




import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import com.e2e.pageobjecs.Country;

public class CountrySpec {
	public static void main(String[] args){
		System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
		WebDriver chromeDriver = new ChromeDriver();
		Country country = new Country(chromeDriver);
		
       
		Map<Integer, String> stateMap = country.getStatesList();
		
		//Asserting all   states are found
	    Assert.assertEquals(stateMap.get(1), "San José");
	    Assert.assertEquals(stateMap.get(2), "Alajuela");
	    Assert.assertEquals(stateMap.get(3), "Cartago");
	    Assert.assertEquals(stateMap.get(4), "Heredia");
	    Assert.assertEquals(stateMap.get(5), "Guanacaste");
	    Assert.assertEquals(stateMap.get(6), "Puntarenas");
	    Assert.assertEquals(stateMap.get(7), "Limón");
	    
        String postalCode = country.getPostalCode();
        Assert.assertEquals("21101", postalCode);
		//close browser
		chromeDriver.quit();
	}
}
