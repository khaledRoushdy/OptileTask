package com.optile.dropbox.baseTest;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.optile.dropbox.driver.Browser;
import com.optile.dropbox.utilities.Utilities;

public class DropboxBaseTest {

	protected WebDriver driver;
	private String browserName;
	private String url;
	private Browser browser = new Browser();

	@BeforeMethod
	public void setup() {
		Map<String, String> applicationData = Utilities.getApplicationData();
		browserName = applicationData.get("Browser");
		url = applicationData.get("Url");
		driver = browser.getDriver(browserName);
		driver.get(url);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
