/**
  ******************************************************************************
  * @file    com/communication/MachineStatus.java 
  * @author  Fahmi Ghediri
  * @version V1.0
  * @date    25-Juin-2014
  * @brief   Checks the machine's status if it's stolen or not
  ******************************************************************************
**/





package com.communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.data.MacAddress;

public class MachineStatus {
	private String mac;
	
	public void setMac() {
		MacAddress address =  new MacAddress();
		mac=address.getMacAddress();
	}
	
	public boolean verify(){
		Boolean status=false;
		setMac();
		try {
			URL url = new URL("http://localhost:8080/WebApplication1/webresources/generic/etatPC&"+mac);
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
