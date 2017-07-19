package com.e2e.grid;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.e2e.pageobjecs.Country;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
public class MultiBrowserTest {

	private WebDriver driver;
	private DesiredCapabilities cap;
	private String node;
	private Country country;
	private final String IEXPLORER = "internet explorer";
	protected ThreadLocal<RemoteWebDriver> threadDriver = null;

	/**
	 * load all the required elements for the browsers 
	 * @param browser browser type (e.g., chrome, firefox, ie , opera etc=
	 * @param browserNode url for the remote computer or webdriver 
	 * @throws MalformedURLException
	 */
	@Parameters({"browser" ,"browserNode"})
	@BeforeClass
	public void initBrowserDriver(String browser , String browserNode) throws MalformedURLException{
		node = browserNode;
		switch(browser){
		case "firefox":
			System.out.println("Using firefox browser");
			cap = DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			/*driver = new RemoteWebDriver(new URL(node), cap);
			country = new Country(driver);*/
			break;
		case "chrome":
			System.out.println("Using google browser");
			cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
            
			break;
		case "ie":
			System.out.println("Using ie browser");
			
			cap = DesiredCapabilities.internetExplorer();
			cap.setBrowserName(IEXPLORER);
			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			break;
		default:
			throw new IllegalArgumentException("The Browser Type is Undefined");
		}
		
		driver = new RemoteWebDriver(new URL(node), cap);
        //Ensuring when ie is the browser, the system will max it window´s resolution for the selectors 
		if(!cap.getBrowserName().equals(IEXPLORER)){
		  country = new Country(driver);
		}else{
			country = new Country(driver, 20);
		}
	}

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

	@AfterClass
	public void closeBrowser(){
		driver.quit();
	}
}
