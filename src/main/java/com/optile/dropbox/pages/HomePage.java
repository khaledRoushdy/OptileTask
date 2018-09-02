package com.optile.dropbox.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.optile.dropbox.utilities.Utilities;

public class HomePage {
	
	private By accountLocator = By.cssSelector("div.account-menu.mc-popover >button");
	private By signoutLocator = By.linkText("Sign out");
	private By settingsLocator = By.linkText("Settings");
	private By newSharedFolderLocator = By.cssSelector("li.mc-tertiary-list-element> button");
	private By nextButtonLocator = By.cssSelector("button.c-btn.c-btn--primary");
	private By folderNameTextboxLocator = By.id("unified-share-modal-title");
	private By emailTextboxLocator = By.cssSelector("div.token-container > textarea");
	private By createdFolderLocator = By.cssSelector("nav#path-breadcrumbs > span:nth-child(2)");
	private By shareButtonLocator = By.cssSelector("div.u-l-fr>button");
	private WebDriver driver;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
	}
	
	private WebElement account() {
		Utilities.waitUntilElementIsVisible(accountLocator,getWebdriverWait());
		return driver.findElement(accountLocator);
	}
	
	private WebElement signOut() {
		Utilities.waitUntilElementIsVisible(signoutLocator,getWebdriverWait());
		return driver.findElement(signoutLocator);
	}
	
	private WebElement settings() {
		Utilities.waitUntilElementIsVisible(settingsLocator,getWebdriverWait());
		return driver.findElement(settingsLocator);
	}
	
	private WebElement newSharedFolder() {
		Utilities.waitUntilElementIsVisible(newSharedFolderLocator,getWebdriverWait());
		return driver.findElement(newSharedFolderLocator);
	}
	
	private WebElement next() {
		Utilities.waitUntilElementIsVisible(nextButtonLocator,getWebdriverWait());
		return driver.findElement(nextButtonLocator);
	}
	
	private WebElement folderName() {
		Utilities.waitUntilElementIsVisible(folderNameTextboxLocator,getWebdriverWait());
		return driver.findElement(folderNameTextboxLocator);
	}
	
	private WebElement email() {
		Utilities.waitUntilElementIsVisible(emailTextboxLocator,getWebdriverWait());
		return driver.findElement(emailTextboxLocator);
	}
	
	private WebElement createdFolder() {
		Utilities.waitUntilElementIsVisible(createdFolderLocator,getWebdriverWait());
		return driver.findElement(createdFolderLocator);
	}
	
	private WebElement share() {
		Utilities.waitUntilElementIsVisible(shareButtonLocator,getWebdriverWait());
		return driver.findElement(shareButtonLocator);
	}
	
	public void logout() {
		account().click();
		signOut().click();
	}
	
	public void openUserSettings() {
		account().click();
		settings().click();
	}
	
	public void createFolder(String folderName,String email) {
		newSharedFolder().click();
		next().click();
		folderName().sendKeys(folderName);
		email().sendKeys(email);
		share().click();
	}
	
	public String getCreatedFolder() {
		return createdFolder().getText();
	}
	
	private WebDriverWait getWebdriverWait() {
		return new WebDriverWait(driver, 30);
	}


}
