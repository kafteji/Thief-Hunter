/**
  ******************************************************************************
  * @file    com/communication/Connection.java 
  * @author  Fahmi Ghediri
  * @version V1.0
  * @date    23-Juin-2014
  * @brief   Connect to the main server to validate user's credentials 
  ******************************************************************************
**/



package com.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import com.data.MacAddress;

public class Connection {
	private String serverURL;
	private String login;
	private String password;
	private String hostName;
	private String mac;

	public void setLogin(String login) {
		this.login = login;
	}

	public void setHostName() {
		this.hostName = getDeviceName();
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String getDeviceName() {
		String name = "";
		try {
			InputStream input = Runtime.getRuntime().exec("hostname")
					.getInputStream();
			InputStreamReader reader = new InputStreamReader(input);
			BufferedReader buffer = new BufferedReader(reader);
			name = buffer.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}

	public void setMac() {
		MacAddress address = new MacAddress();
		String macad = address.getMacAddress();
		this.mac = macad;
	}

	public void setServerURL() {

		this.serverURL = "http://localhost:8080/WebApplication1/webresources/generic/inscription&"
				+ login + "&" + password + "&" + mac + "&" + hostName;
	}

	public boolean connect() {
		Boolean status = false;
		setMac();
		setHostName();
		setServerURL();
		try {
			URL url = new URL(serverURL);
			InputStreamReader input = new InputStreamReader(url.openStream());
			BufferedReader buffer = new BufferedReader(input);
			String response = buffer.readLine();
			if (response != null) {
				status = Boolean.valueOf(response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
