/**
  **********************************************************************************************
  * @file    com/data/GeoLocation.java 
  * @author  Fahmi Ghediri
  * @version V1.0
  * @date    02-July-2014
  * @brief   get the machine location
  *          because i was obliged to pay 50$ for the maxmind webservice :(
  *          i have just created a simple location simulator and longitude, latiutde generator 
  **********************************************************************************************
**/






package com.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class GeoLocation {

	private String longitude;
	private String latitude;

	public void getLocation() {
		try {
			String lat = "36.8";
			String lng = "10.1";
			IpAddress ip = new IpAddress();
			String newIP = ip.getAddress();
			String oldIP;
			BufferedReader buffer = new BufferedReader(new FileReader(new File(
					"ressources\\location.txt")));
			oldIP = buffer.readLine();
			
			if(oldIP ==  null){
				oldIP = "";
			}
			if(!oldIP.equals(newIP)){
				int lt = (int) (Math.random()*5000);
				int lg = (int) (Math.random()*5000);
				lat=lat+ Integer.toString(lt);
				lng= lng +Integer.toString(lg);
				buffer.close();
				FileWriter writer = new FileWriter(new File("ressources\\location.txt"));
				writer.write(newIP+"\n");
				writer.write(lat+"\n");
				writer.write(lng);
				writer.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getLatitude() {
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(new File(
					"ressources\\location.txt")));
			buffer.readLine();
			latitude = buffer.readLine();
			buffer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return latitude;
	}

	public String getLongitude() {
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(new File(
					"ressources\\location.txt")));
			buffer.readLine();
			buffer.readLine();
			longitude = buffer.readLine();
			buffer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return longitude;
	}
	

}
