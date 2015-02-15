/**
  ******************************************************************************
  * @file    com/data/MacAddress.java 
  * @author  Fahmi Ghediri
  * @version V1.0
  * @date    01-July-2014
  * @brief   retrieve the machine's MAC address 
  ******************************************************************************
**/




package com.data;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MacAddress {

	private String macAddress = "";

	private void fethMacAddress() {
		InetAddress host;
		try {
			host = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(host);
			byte[] mac = network.getHardwareAddress();
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				builder.append(String.format("%02X%s", mac[i],
						(i < mac.length - 1) ? ":" : ""));
			}
			macAddress = builder.toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException ex) {
			ex.printStackTrace();
		}

	}

	public String getMacAddress() {
		fethMacAddress();
		return macAddress;
	}

}
