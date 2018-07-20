package com.jxjr.utility;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpFun {

	/**
	 * 请求
	 * 
	 * @param address
	 * @param strJson
	 * */
	public static String doPost(String address, String strJson) {
		try {
			URL my_url = new URL(address);
			HttpURLConnection connection = (HttpURLConnection) my_url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(5000);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.connect();
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			byte[] content = strJson.getBytes("utf-8");
			out.write(content, 0, content.length);
			out.flush();
			out.close();

			StringBuilder builder = new StringBuilder(256);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			int value = 0;
			
			// reads to the end of the stream
			while ((value = reader.read()) != -1) {
				builder.append((char) value);
				// prints character
			}
			reader.close();
			connection.disconnect();
			return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String doPost(String address, File file) {
		try {
			URL my_url = new URL(address);
			HttpURLConnection connection = (HttpURLConnection) my_url
					.openConnection();
			connection.setConnectTimeout(30000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "image/png");
			connection.connect();

			FileInputStream fis = new FileInputStream(file);
			BufferedOutputStream out = new BufferedOutputStream(
					connection.getOutputStream());

			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				out.write(b, 0, n);
			}
			fis.close();
			out.flush();
			out.close();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			StringBuilder builder = new StringBuilder(256);
			String s;
			while ((s = reader.readLine()) != null) {
				builder.append(s);
			}
			reader.close();
			connection.disconnect();
			return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	String readResponse(HttpURLConnection connection)
			throws UnsupportedEncodingException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), "utf-8"));
		StringBuilder builder = new StringBuilder(256);
		String s;
		while ((s = reader.readLine()) != null) {
			builder.append(s);
		}
		reader.close();
		connection.disconnect();
		return reader.toString();
	}
}