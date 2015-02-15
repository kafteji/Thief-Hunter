/**
  ******************************************************************************
  * @file    UserAccount.java 
  * @author  Fahmi Ghediri
  * @version V1.0
  * @date    01-July-2014
  * @brief   create the user account and defines its permission so the user
  *          can't access any secondary partition in the hard drive
  ******************************************************************************
**/






package com.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UserAccount {

	private String userName = System.getProperty("user.home").substring(9)+" Guest";
	private ArrayList<String> hardDrives = new ArrayList<String>();

	public void create() {

		try {
			Runtime.getRuntime().exec("net user " + getUserName() + " /add");
			getHardDrivesList();
			setPermission();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getHardDrivesList() {
		try {
			InputStream input = Runtime.getRuntime()
					.exec("wmic logicaldisk get name").getInputStream();
			InputStreamReader reader = new InputStreamReader(input);
			BufferedReader buffer = new BufferedReader(reader);
			String aux = buffer.readLine();
			buffer.readLine();
			String location = System.getenv("userprofile");
			String mainPartition = location.substring(0, 2);
			while (aux != null) {
				aux = buffer.readLine();
				if (aux == null) {
					break;
				}
				if (!(aux.equals("")) && !(aux.equals(null))
						&& !(aux.substring(0, 2).equals(mainPartition))) {
					hardDrives.add(aux.substring(0, 2) + "\\");
				}
				buffer.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setPermission() throws IOException {
		for (String aux : hardDrives) {
			Runtime.getRuntime().exec(
					"icacls " + aux + " /deny " + getUserName() + ":F /t /c");
		}
	}


	public String getUserName() {
		return userName;
	}

}
