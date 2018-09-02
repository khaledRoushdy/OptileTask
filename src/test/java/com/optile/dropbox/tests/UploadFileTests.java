package com.optile.dropbox.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.optile.dropbox.driver.Browser;
import com.optile.dropbox.pages.LoginPage;
import com.optile.dropbox.pages.SdkHomePage;
import com.optile.dropbox.sdk.DropboxSdk;

public class UploadFileTests {

	@Parameters({"email","password","fileName"})
	@Test
	public void uploadFileTest(String email,String password,String fileName) throws DbxException, InterruptedException, IOException {
		
		Browser broswer = new Browser();
		WebDriver driver= broswer.getDriver("Chrome");
		LoginPage loginPage = new LoginPage(driver);
		SdkHomePage sdkHomePage = new SdkHomePage(driver);
		DropboxSdk dropboxSdk = new DropboxSdk();
		DbxWebAuthNoRedirect webAuth= dropboxSdk.getWebAuth();
		String url = webAuth.start();
		
		driver.get(url);
		loginPage.login(email, password);
		String code = sdkHomePage.getToken();
		String token = dropboxSdk.getDropBoxAccessToken(code, webAuth);
		dropboxSdk.uploadFile(fileName, token);
		List<String> files=dropboxSdk.getUploadedFiles(token);
		Boolean fileIsUploaded= files.contains(fileName);
		Assert.assertTrue(fileIsUploaded, "The file is not uploaded");
	}
}
