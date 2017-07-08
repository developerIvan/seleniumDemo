package com.e2e.specs;


import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import com.e2e.pageobjecs.Country;
import com.e2e.util.HelperUtil;

public class CountrySpec {
	public static void main(String[] args){

      HelperUtil.setDriverLocation();  
	  WebDriver chromeDriver = new ChromeDriver();
	  Country country = new Country(chromeDriver);
    	Map<Integer, String> stateMap = country.getStatesList();

		//Asserting all   states are found
		Assert.assertEquals(stateMap.get(1), HelperUtil.getExpectedProvinceOne());
		Assert.assertEquals(stateMap.get(2), HelperUtil.getExpectedProvinceTwo());
		Assert.assertEquals(stateMap.get(3), HelperUtil.getExpectedProvinceThree());
		Assert.assertEquals(stateMap.get(4), HelperUtil.getExpectedProvinceFour());
		Assert.assertEquals(stateMap.get(5), HelperUtil.getExpectedProvinceFive());
		Assert.assertEquals(stateMap.get(6), HelperUtil.getExpectedProvinceSix());
		Assert.assertEquals(stateMap.get(7), HelperUtil.getExpectedProvinceSeven());

		String postalCode = country.getPostalCode();
		Assert.assertEquals(HelperUtil.getExpectedPostalCode(), postalCode);
		//close browser
		chromeDriver.quit();
	}
}
