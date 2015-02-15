/**
  ******************************************************************************
  * @file    com/configuration/DirectoryManager.java 
  * @author  Fahmi Ghediri
  * @version V1.0
  * @date    25-Juin-2014
  * @brief   manage files and directories operations in the client machine 
  ******************************************************************************
**/





package com.configuration;

import java.io.File;

public class DirectoryManager {
	

	public DirectoryManager() {
		workingPath = System.getProperty("user.home").substring(0, 2)
				+ "\\ProgramData" + "\\" + applicationDirectoryName;
		
		directory = new File(workingPath);

	}
	private File directory;
	public String workingPath;
	private final String applicationDirectoryName = "Thief Hunter";

	public void create() {
		directory.mkdir();

	}

	public void deleteContent() {
		File[] list = directory.listFiles();
		for (File f : list) {
			f.delete();
		}

	}
}
