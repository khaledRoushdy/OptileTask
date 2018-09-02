package com.optile.dropbox.pages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

import org.testng.annotations.Test;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.DbxWriteMode;

public class TestSdk {

	public static void main(String[] args) throws IOException, DbxException {

		final String APP_KEY = "7xfq7xcbtbnvbgw";
		final String APP_SECRET = "wdxolxm1yxb71l4";

		DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

		DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
		DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);

		String authorizeUrl = webAuth.start();
		String code = null ;
//		String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
		DbxAuthFinish authFinish = webAuth.finish(code);
		String accessToken = authFinish.accessToken;

		DbxClient client = new DbxClient(config, accessToken);
		String clientinfo = client.getAccountInfo().displayName;

		File inputFile = new File("working-draft.txt");
		FileInputStream inputStream = new FileInputStream(inputFile);
		try {
			DbxEntry.File uploadedFile = client.uploadFile("/magnum-opus.txt", DbxWriteMode.add(), inputFile.length(),
					inputStream);
			System.out.println("Uploaded: " + uploadedFile.toString());
		} finally {
			inputStream.close();
		}

		DbxEntry.WithChildren listing = client.getMetadataWithChildren("/");
		System.out.println("Files in the root path:");
		for (DbxEntry child : listing.children) {
			System.out.println("	" + child.name + ": " + child.toString());
		}

		FileOutputStream outputStream = new FileOutputStream("magnum-opus.txt");
		try {
			DbxEntry.File downloadedFile = client.getFile("/magnum-opus.txt", null, outputStream);
			System.out.println("Metadata: " + downloadedFile.toString());
		} finally {
			outputStream.close();
		}
	}
}
