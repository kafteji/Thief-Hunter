/**
  ******************************************************************************
  * @file    com/communication/SendData.java 
  * @author  Fahmi Ghediri
  * @version V1.0
  * @date    25-Juin-2014
  * @brief   Sends the collected data to the remote srever via an FTP client 
  ******************************************************************************
**/





package com.communication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;


import java.io.InputStreamReader;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class SendData {

	private boolean Done;
	private String remoteDirectory;
	private final String path = System.getProperty("user.home").substring(0, 2)
			+ "\\ProgramData" + "\\Thief Hunter";

	public void send() {
		File directory = new File(path);
		if (directory.list().length == 0) {
			this.setDone(false);
		} else {
			FTPClient client = new FTPClient();
			try {
				client.connect("127.0.0.1");
				if (client.login("user", "")) {
					String Folder = this.getFolderName();
					client.enterLocalPassiveMode();
					File[] fileList = directory.listFiles();
					BufferedReader buffer = new BufferedReader(new FileReader(
							new File("ressources\\login.txt")));
					remoteDirectory = buffer.readLine()+"/"+this.getDeviceName();
					client.makeDirectory(remoteDirectory + "/" + Folder);
					buffer.close();
					client.setFileType(FTP.BINARY_FILE_TYPE);
					for (File f : fileList) {
						InputStream input = new FileInputStream(f);
						client.storeFile(remoteDirectory + "/" + Folder + "/"
								+ f.getName(), input);

					}

					client.logout();
					client.disconnect();
					this.setDone(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.setDone(false);
			}

		}

	}

	public boolean isDone() {
		return Done;
	}

	public void setDone(boolean done) {
		Done = done;
	}

	public String getFolderName() {
		String folder = "";
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(new File(
					"ressources\\folder.txt")));
			Integer number =Integer.parseInt(buffer.readLine())+1;
			folder=number.toString();
			FileWriter writer = new FileWriter(new File("ressources\\folder.txt"));
			writer.write(folder);
			buffer.close();
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return folder;
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
	

}
