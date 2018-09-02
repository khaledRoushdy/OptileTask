package com.optile.dropbox.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private By emailTextBoxLocator=By.cssSelector("input[name='login_email']");
	private By passwordTextBoxLocator=By.cssSelector("input[name='login_password']");
	private By signInButtonLocator=By.cssSelector("button[type='submit']");
	private WebDriver driver;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
	}
	
	private WebElement email() {
		return driver.findElement(emailTextBoxLocator);
	}
	
	private WebElement password() {
		return driver.findElement(passwordTextBoxLocator);
	}
	
	private WebElement signIn() {
		return driver.findElement(signInButtonLocator);
	}
	
	public void login(String email,String password) {
		
		email().sendKeys(email);
		password().sendKeys(password);
		signIn().click();
	}
}
