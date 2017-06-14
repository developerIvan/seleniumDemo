package com.e2e.specs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import com.e2e.pageobjecs.Country;

public class CountrySpec {
	public static void main(String[] args){
	    String driverPath = null;
	    Properties mainProperties = new Properties();
	    InputStream input = null;
	    String stringPath = null;
	    StringBuilder sBuilder = null;
        try{
        	
        	//Gets the current path or directory of the file or project
        	stringPath = Paths.get("").toAbsolutePath().toString();
        	System.out.println("Current relative path is: " + stringPath);
        	sBuilder = new StringBuilder(stringPath);
        	sBuilder.append("\\project.properties");
        	stringPath = sBuilder.toString();
        	System.out.println("properties location is: " + stringPath); 
          input = new FileInputStream(stringPath);
          mainProperties.load(input);
    	  driverPath = mainProperties.getProperty("chrome.driver.path");
      } catch (IOException e) {
    	  e.printStackTrace();
	  }finally{
		  if(input!=null){
			  try{
				  input.close();
			  }catch(IOException e){
				  System.out.println("Error while trying to close input ".concat(e.getMessage()));
			  }
		  }
	  }
		
  	  System.out.println("\n Chorme driver path ".concat(driverPath));
	  System.setProperty("webdriver.chrome.driver",driverPath);
	  System.out.println(System.getProperty("webdriver.chrome.driver"));
	  
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
