package com.optile.dropbox.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.optile.dropbox.driver.Browser;

public class Utilities {

	public static String generateRandomChars(int length) {
		String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
		}

		return sb.toString();
	}
	
	public WebDriverWait getWebdriver(WebDriver driver) {
		return new WebDriverWait(driver, 30);
	}

	public static void waitUntilElementIsVisible(By locator,WebDriverWait wait) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public static Map<String, String> getApplicationData() {
		Map<String,String> applicationData = new HashMap<String, String>();
		Properties prop = new Properties();
		InputStream input = null;
		String url = null;
		String browserName = null;
		try {

			input = new FileInputStream("src/main/resources/conf/application.properties");

			prop.load(input);

			// get the property value and print it out
			url = prop.getProperty("url");
			browserName = prop.getProperty("browser");
			applicationData.put("Browser", browserName);
			applicationData.put("Url", url);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return applicationData;
	}
}
