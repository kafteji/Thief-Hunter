/**
  ******************************************************************************
  * @file    com/configuration/ConfigurationManager.java 
  * @author  Fahmi Ghediri
  * @version V1.0
  * @date    01-July-2014
  * @brief   handle the configuration operation inside the configuration package
  *          to enhance decoupling
  ******************************************************************************
**/





package com.configuration;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.apache.commons.net.ftp.FTPClient;


public class ConfigurationManager {
	private JFrame frame;
	private DirectoryManager manager;
	private UserAccount account;
	private WelcomeGUI welcomeGUI;
	private LoadingGui loading;
	private SuccessfulConfiguration success;

	public void configure() {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			frame = new JFrame("Thief Hunter");
			frame.setSize(700, 540);
			frame.setResizable(false);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation((dim.width - frame.getWidth()) / 2,
					(dim.height - frame.getHeight()) / 2);
			frame.setIconImage(ImageIO
					.read(new File("ressources\\mainlogo.png")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		JPanel panelcont = new JPanel();
		CardLayout card = new CardLayout();
		manager = new DirectoryManager();
		account = new UserAccount();
		welcomeGUI = new WelcomeGUI();
		loading = new LoadingGui();
		success = new SuccessfulConfiguration();

		panelcont.setLayout(card);
		panelcont.add(welcomeGUI, "1");
		panelcont.add(loading, "2");
		panelcont.add(success, "3");

		card.show(panelcont, "1");
		frame.add(panelcont);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		while (welcomeGUI.isStatus() == false) {
			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		card.show(panelcont, "2");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		try {
			manager.create();
			TimeUnit.SECONDS.sleep(4);
			account.create();
			this.createRemoteFolder();
			TimeUnit.SECONDS.sleep(10);

		} catch (Exception e) {
			e.printStackTrace();
		}

		card.show(panelcont, "3");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void createRemoteFolder() {
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(new File(
					"ressources\\login.txt")));
			String folderName = buffer.readLine();
			FTPClient client = new FTPClient();
			client.connect("127.0.0.1");
			client.login("user", "");
			client.makeDirectory(folderName);
			client.makeDirectory(folderName+"/"+this.getDeviceName());
			client.logout();
			client.disconnect();
			buffer.close();
			this.writeResult("ok");
		} catch (Exception e) {
			e.printStackTrace();
			this.writeResult("fail");
		}

	}

	private void writeResult(String result) {
		try {
			FileWriter writer = new FileWriter("ressources\\config.txt");
			writer.write(result);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

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
