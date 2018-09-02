package com.optile.dropbox.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.optile.dropbox.baseTest.DropboxBaseTest;
import com.optile.dropbox.pages.AccountPage;
import com.optile.dropbox.pages.HomePage;
import com.optile.dropbox.pages.LoginPage;
import com.optile.dropbox.utilities.Utilities;

public class SmokeUiTests extends DropboxBaseTest{

	private HomePage homePage;
	private LoginPage loginPage;
	private AccountPage accountPage;
	
	@Parameters({"email","password"})
	@Test
	public void userShouldLoginAndLogout(String email,String password) {
		
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		
		loginPage.login(email, password);
		homePage.logout();
		String actualUrl = driver.getCurrentUrl();
		Boolean isContainsexpectedUrl = actualUrl.contains("login?src=logout");
		Assert.assertTrue(isContainsexpectedUrl,"The user has not signed out correctly");
	}
	
	@Parameters({"email","password","receipentEmail"})
	@Test
	public void userShouldCreateSharedFolder(String email,String password,String receipentEmail) {
	
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		
		String folderName = "Test"+Utilities.generateRandomChars(5);
		loginPage.login(email,password );
		homePage.createFolder(folderName, receipentEmail);
		String actualFolderName= homePage.getCreatedFolder();
		Assert.assertEquals(actualFolderName, folderName,"The folder is not created successfully");
	}
	@Parameters({"email","password","newFirstName","newLastName"})
	@Test
	public void userShouldChangeHisName(String email,String password,String newFirstName,String newLastName) {
		
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		accountPage = new AccountPage(driver);
		
		loginPage.login(email,password);
		homePage.openUserSettings();
		String oldAccountName= accountPage.getAccountName();
		accountPage.EditName(newFirstName, newLastName);
		String newAccountName = accountPage.getAccountName();
		Assert.assertNotEquals(oldAccountName, newAccountName,"The account name has not been changed");
		Assert.assertTrue(newAccountName.contains(newFirstName),"The account name doesn't contain the first name");
		Assert.assertTrue(newLastName.contains(newLastName),"The account name doesn't contain the last name");
	}
}
