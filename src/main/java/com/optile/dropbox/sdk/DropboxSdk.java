package com.optile.dropbox.sdk;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.DbxWriteMode;

public class DropboxSdk {

	final static String APP_KEY = ApplicationInfo.APP_KEY;
	final static String APP_SECRET = ApplicationInfo.APP_SECRET;
	private DbxAppInfo appInfo;
	private DbxRequestConfig config;
	private DbxWebAuthNoRedirect webAuth;
	
	public DbxAppInfo getAppInfo() {
		if(appInfo == null)
			appInfo =  new DbxAppInfo(APP_KEY, APP_SECRET); 
		return appInfo;
	}
	
	public DbxRequestConfig getConfig() {
		if(config == null)
			config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
		return config;
	}
	
	public DbxWebAuthNoRedirect getWebAuth() {
		if(webAuth ==null)
			webAuth = new DbxWebAuthNoRedirect(getConfig(),getAppInfo());
		return webAuth;
	}

	public String getDropBoxAccessToken(String code, DbxWebAuthNoRedirect webAuth) throws DbxException {
		DbxAuthFinish authFinish= webAuth.finish(code);
		return authFinish.accessToken;
	}
	
	public void uploadFile(String fileName,String token) throws DbxException, IOException {
		
		DbxClient client = new DbxClient(getConfig(), token);
		File inputFile = new File(fileName);
		FileInputStream inputStream = new FileInputStream(inputFile);
		
		try {
			 client.uploadFile(fileName, DbxWriteMode.add(), inputFile.length(),
					inputStream);
		} finally {
			inputStream.close();
		}
	}
	
	public List<String> getUploadedFiles(String token) throws DbxException {
		DbxClient client = new DbxClient(getConfig(), token);
		DbxEntry.WithChildren listing = client.getMetadataWithChildren("/");
		List<String> files = new ArrayList<>();
		for (DbxEntry child : listing.children) {
			files.add(child.name);
		}
		return files;
	}
	
	
}
