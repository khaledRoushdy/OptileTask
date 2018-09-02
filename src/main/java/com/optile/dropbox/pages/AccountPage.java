package com.optile.dropbox.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.optile.dropbox.utilities.Utilities;

public class AccountPage {

	private By editNameLocator = By.xpath("(//div[@class='account-key-value-block__links'])[2]/button");
	private By firstNameLocator = By.name("fname");
	private By lastNameLocator = By.name("lname");
	private By changeNameLocator = By.cssSelector("div.db-modal-buttons>button.button-primary");
	private By accountNameLocator = By.cssSelector("div.account-page-module.account-key-value-block.preference-name-row>span.account-key-value-block__value");
	private WebDriver driver;
	
	public AccountPage(WebDriver driver){
		this.driver = driver;
	}
	
	private WebElement editName() {
		Utilities.waitUntilElementIsVisible(editNameLocator,getWebdriverWait());
		return driver.findElement(editNameLocator);
	}
	
	private WebElement firstName() {
		Utilities.waitUntilElementIsVisible(firstNameLocator,getWebdriverWait());
		return driver.findElement(firstNameLocator);
	}
	
	private WebElement lastName() {
		return driver.findElement(lastNameLocator);
	}
	
	private WebElement changeName() {
		return driver.findElement(changeNameLocator);
	}

	private WebElement accountName() {
		Utilities.waitUntilElementIsVisible(accountNameLocator,getWebdriverWait());
		return driver.findElement(accountNameLocator);
	}
	
	public void EditName(String firstName,String lastName) {
		editName().click();
		firstName().clear();
		firstName().sendKeys(firstName);
		lastName().clear();
		lastName().sendKeys(lastName);
		changeName().click();
	}
	
	public String getAccountName() {
		return accountName().getText();
	}
	
	private WebDriverWait getWebdriverWait() {
		return new WebDriverWait(driver, 30);
	}
}
