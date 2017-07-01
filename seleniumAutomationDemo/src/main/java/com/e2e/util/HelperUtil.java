/**
 * 
 */
package com.e2e.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Contains functions with the purposes to help other components
 * @author Ivan
 *
 */
public class HelperUtil {
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	/**
	 * 
	 */
	public static void setDriverLocation(){
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
	        	sBuilder.append(getPropertiesFilesLocation());
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
	}
	
	/**
	 * @return the properties file location sub child directory 
	 * depending on current OS
	 */
	private static String getPropertiesFilesLocation(){
		String pathAppender;
		System.out.println("\n current os is:".concat(OS) );
		switch(OS.substring(0, 2)){
		case "win":
			
			pathAppender = "\\project.properties";
			break;
		case "nix":
			pathAppender = "//project.properties";
			break;
		case "nux":
			pathAppender = "//project.properties";
			break;
		case "aix":
			pathAppender = "//project.properties";
			break;
		default:
			pathAppender = "/project.properties";
			break;
		}
		return pathAppender;
	}
}
