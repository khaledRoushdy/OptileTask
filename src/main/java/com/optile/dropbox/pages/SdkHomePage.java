package com.optile.dropbox.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.optile.dropbox.utilities.Utilities;

public class SdkHomePage {

	private By allowButtonLocator=By.cssSelector("button[name='allow_access']");
	private By codeInputLocator = By.cssSelector("div#auth-code>input");
	private WebDriver driver;
	
	public SdkHomePage(WebDriver driver){
		this.driver = driver;
	}
	
	private WebElement codeInput() {
		Utilities.waitUntilElementIsVisible(codeInputLocator,getWebdriverWait());
		return driver.findElement(codeInputLocator);
	}
	
	private WebElement allow() {
		Utilities.waitUntilElementIsVisible(allowButtonLocator,getWebdriverWait());
		return driver.findElement(allowButtonLocator);
	}
	
	
	public String getToken() throws InterruptedException {
		allow().click();
		String dataToken = codeInput().getAttribute("data-token");
		return dataToken;
	}
	
	private WebDriverWait getWebdriverWait() {
		return new WebDriverWait(driver, 30);
	}
}
