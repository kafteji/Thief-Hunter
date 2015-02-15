/**
  ******************************************************************************
  * @file    com/configuration/UserAccount.java 
  * @author  Fahmi Ghediri
  * @version V1.0
  * @date    25-Juin-2014
  * @brief   Create an uopen user account to let the thief login to the machine  
  ******************************************************************************
**/


package com.configuration;


import java.io.File;
import java.io.IOException;


public class UserAccount {

	public void create() {

		try {
			File program =  new File("ressources/permission.exe");
			Runtime.getRuntime().exec("explorer "+"\""+program.getAbsolutePath()+"\"");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
