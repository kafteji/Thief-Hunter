/**
  ******************************************************************************
  * @file    com/data/Webcam.java 
  * @author  Fahmi Ghediri
  * @version V1.0
  * @date    25-Juin-2014
  * @brief   take a webcam shot to identify the thief 3:) 
  ******************************************************************************
**/





package com.data;

import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

import com.configuration.DirectoryManager;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.VideoInputFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class WebCam implements Runnable {

	private int frameHeight = 600;
	private int frameWidth = 800;

	@Override
	public void run() {
		FrameGrabber grabber = new VideoInputFrameGrabber(0);
		grabber.setImageHeight(frameHeight);
		grabber.setImageWidth(frameWidth);
		DirectoryManager manager = new DirectoryManager();
		try {
			IplImage img = null;
			grabber.start();
			for (int i = 0; i < 7; i++) {
				img = grabber.grab();
			}
			cvSaveImage(manager.workingPath + "\\" + "webcam" + ".jpg", img);
			grabber.stop();

		} catch (Exception e) {
			e.getStackTrace();

		}

	}

}
