/**
  ******************************************************************************
  * @file    com/data/IpAddress.java 
  * @author  Fahmi Ghediri
  * @version V1.0
  * @date    01-July-2014
  * @brief   Retrieves the machine's IP address 
  ******************************************************************************
**/






package com.data;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpAddress {

	private String address = "";

	private void fetchAddress() {
		BufferedReader buffer = null;
		try {
			URL url = new URL(
					"http://www.whatismyip.com/tools/ip-address-lookup.asp");
			InputStreamReader input = new InputStreamReader(url.openStream());
			buffer = new BufferedReader(input);
			String ip = buffer.readLine();
			Pattern pattern = Pattern
					.compile("(.*)value=\"(\\d+).(\\d+).(\\d+).(\\d+)\"(.*)");
			Matcher matcher;
			while (ip != null) {
				matcher = pattern.matcher(ip);
				if (matcher.matches()) {
					ip = matcher.group(2) + "." + matcher.group(3) + "."
							+ matcher.group(4) + "." + matcher.group(5);
					address = ip;
				}
				ip = buffer.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffer != null) {
					buffer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public String getAddress() {
		this.fetchAddress();
		return address;
	}

}
