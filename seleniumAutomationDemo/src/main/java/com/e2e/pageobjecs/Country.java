package com.e2e.pageobjecs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;





public class Country {
	WebDriver driver;
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	WebElement element;
	WebElement dropdownCountry;
	List<WebElement> dropdownCountrys;
	Map <Integer, String>provinciasMap;

	String state = "";
	Select dropDown;
	public Country(WebDriver driver){
		this.driver = driver;

		driver.get("https://www.correos.go.cr/");
		provinciasMap = new HashMap<Integer, String>();
	}

	public Country(WebDriver driver,int implTimeout){
		this.driver = driver;
		driver.navigate().to("https://www.correos.go.cr/");
		driver.manage().timeouts().implicitlyWait(implTimeout, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		provinciasMap = new HashMap<Integer, String>();    
	}
	/**
	 * Function to navigate trough  web site  to get the postal code search frame
	 */
	private void redirectToPostalCodeSearchPage(){
		/*WebDriverWait wait = new WebDriverWait(driver, 20);
	   element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MenuTopContainer']/div/div/ul/li[7]/div/a/span")));*/
		element = driver.findElements(By.cssSelector("#MenuTopContainer .itemContent .menuTextContainer")).get(3);

		//Go to "codigo postal" page
		element.click();

		//Go to search 'codigo postal' page
		element = driver.findElements(By.cssSelector("#ContextMenuContainer #Menu_Contextual .itemContent a")).get(1);
		element.click();
	}

	public Map <Integer, String> getStatesList(){
		redirectToPostalCodeSearchPage();

		//Switch to frame in where the form is located 
		driver.switchTo().frame(0);
		dropDown = getSelectDropDownElement(driver,"ddl_provincia", 2); 

		//Loading map for verification
		for(WebElement optionElement:dropDown.getOptions()){
			state = optionElement.getText();
			if(!state.equalsIgnoreCase("provincia")){
				//Displaying states
				System.out.println("\n ".concat(state));
				provinciasMap.put(Integer.parseInt(optionElement.getAttribute("value")), state);
			}
		}
		//Returning to main page
		driver.switchTo().defaultContent();

		return provinciasMap;
	}

	public String getPostalCode(){
		String postalCode = null; 
		redirectToPostalCodeSearchPage();
		//Switch to frame in where the form is located 
		driver.switchTo().frame(0);


		//Get the select tag using xpath
		dropDown = this.getSelectDropDownElement(driver, "[name='ddl_provincia']", 0);

		dropDown.selectByIndex(2);

		//Selecting "cantones" or subStates
		dropDown = this.getSelectDropDownElement(driver, "//*[@id='ddl_canton']", 1); 

		//  dropDown = new Select( driver.findElement(By.xpath("//*[@id='ddl_canton']")));
		dropDown.selectByValue("211");

		//Selecting city drop down 	  
		dropDown = this.getSelectDropDownElement(driver, "ddl_distrito", 3);
		dropDown.selectByVisibleText("Zarcero");

		postalCode = driver.findElement(By.id("lbl_codigo_postal")).getText();

		//Exiting the frame
		driver.switchTo().defaultContent();

		return postalCode;
	}

	/**
	 * Function with the process of returning a selenium Select element based on the following parameters: 
	 * @param driver Browser webDriver
	 * @param locatorValue String with the location of the Select (e.g., by id, by name, attribute etc).  
	 * @param locatorType  defines the type of locator that will be used to find the element:
	 *  0: By "css" Selector,
	 *  1: By "xpath" Selector,
	 *  2: By "name" Selector,
	 *  3: By "id" Selector
	 * @return
	 */
	private Select getSelectDropDownElement(WebDriver driver, String locatorValue, int locatorType){
		Select selectElement = null;
		switch(locatorType){
		case 0: 
			selectElement =  new Select((new WebDriverWait(driver, 15))
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue))));
			break;
		case 1:	  
			selectElement = new Select((new WebDriverWait(driver, 12))
					.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue))));
			break;
		case 2:	  
			selectElement = new Select( (new WebDriverWait(driver, 12))
					.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue))));
			break;
		case 3:	
			selectElement = new Select( (new WebDriverWait(driver, 12))
					.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue))));
			break;
		}
		return selectElement;
	}
}
