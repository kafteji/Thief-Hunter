/**
  ******************************************************************************
  * @file    com/main/Main.java 
  * @author  Fahmi Ghediri
  * @version V1.0
  * @date    03-July-2014
  * @brief   the Main class 
  ******************************************************************************
**/




package com.main;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.communication.MachineStatus;
import com.communication.SendData;
import com.configuration.ConfigurationManager;
import com.configuration.DirectoryManager;
import com.data.DataCollector;

public class Main {
	private ConfigurationManager configManager;
	private DirectoryManager directoryManager;
	private DataCollector collector;
	private SendData dataAgent;
	private MachineStatus statusChecker;
	private SystemTray Tray;
	private TrayIcon icon;
	private boolean active = true ;


	private boolean isconnectedtoInternet() {
		boolean result = false;
		try {
			URL url = new URL("http://www.google.com");
			InputStreamReader input = new InputStreamReader(url.openStream());
			result = true;
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public void Start() {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		PopupMenu popUp = new PopupMenu();
		try {
			icon = new TrayIcon(ImageIO.read(new File("ressources\\logo.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		MenuItem Status = new MenuItem("Status");
		MenuItem About = new MenuItem("About");
		MenuItem Exit = new MenuItem("Exit");
		MenuItem Help = new MenuItem("Help");

		popUp.add(Status);
		popUp.add(Help);
		popUp.add(About);
		popUp.addSeparator();
		popUp.add(Exit);
		Tray = SystemTray.getSystemTray();
		icon.setImageAutoSize(true);
		icon.setPopupMenu(popUp);

		icon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				icon.displayMessage("Thief Hunter",
						"Thief Hunter is Running and Monitoring your Device",
						TrayIcon.MessageType.INFO);
			}
		});

		Status.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				icon.displayMessage("Status", "Your Device is Protected",
						TrayIcon.MessageType.INFO);
			}
		});

		Help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								null,
								"Don't care about anyhting !! \n Just enter"
								+ " your connection details and Thief Hunter will do the rest ");
			}
		});

		About.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Thief Hunter V1.0 \n Copyright 2014 ENSI Tunisia ");
			}
		});

		Exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				active=false;
				Tray.remove(icon);
				System.exit(0);

			}
		});

		String configStatus = "";
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(new File(
					"ressources\\config.txt")));
			configStatus = buffer.readLine();
			if (configStatus == null) {
				configStatus = "";
				buffer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!configStatus.equalsIgnoreCase("ok")) {
			configManager = new ConfigurationManager();
			configManager.configure();

		} else {
			try {
				Tray.add(icon);
			} catch (AWTException e) {
				e.printStackTrace();

			}
			while (active) {
				while (!this.isconnectedtoInternet()) {
					try {
						TimeUnit.MINUTES.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				boolean isStolen=false;
				statusChecker = new MachineStatus();
				isStolen = statusChecker.verify();
				while (isStolen == false) {
					try {
						TimeUnit.MINUTES.sleep(15);
						isStolen = statusChecker.verify();
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				collector = new DataCollector();
				dataAgent = new SendData();
				while (isStolen == true) {
					collector.collect();
					while (!dataAgent.isDone()) {
						dataAgent.send();
					}
					directoryManager =  new DirectoryManager();
					directoryManager.deleteContent();
					isStolen= statusChecker.verify();
					try {
						TimeUnit.MINUTES.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					

				}

			}
		}

	}

	public static void main(String[] args) {
		Main main = new Main();
		main.Start();
	}

}
