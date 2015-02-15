/**
  ******************************************************************************
  * @file    com/data/Screenshot.java 
  * @author  Fahmi Ghediri
  * @version V1.0
  * @date    25-Juin-2014
  * @brief   take a screenshot to the thief workspace 
  ******************************************************************************
**/




package com.data;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import com.configuration.DirectoryManager;

import javax.imageio.ImageIO;

public class ScreenShot {

	public void Take() throws Exception {
		Robot robot = new Robot();
		DirectoryManager manager = new DirectoryManager();
		BufferedImage screenShot = robot.createScreenCapture(new Rectangle(
				Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(screenShot, "JPG", new File(manager.workingPath + "\\"
				+ "screenshot" + ".jpg"));

	}

}
