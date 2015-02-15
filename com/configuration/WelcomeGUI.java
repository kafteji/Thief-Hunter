/**
  ******************************************************************************
  * @file    com/configuration/WelcomeGui.java 
  * @author  Fahmi Ghediri
  * @version V1.0
  * @date    23-Juin-2014
  * @brief   the welcome GUI 
  ******************************************************************************
**/






package com.configuration;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import com.communication.Connection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileWriter;

public class WelcomeGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField login;
	private JPasswordField password;
	private String passwordContent;
	private String loginContent;
	private Connection connectionAgent;
	private boolean status = false;

	public WelcomeGUI() {
		setLayout(null);

		JLabel lblLogin = new JLabel("login");
		lblLogin.setFont(new Font("Nyala", Font.PLAIN, 27));
		lblLogin.setBounds(175, 313, 61, 37);
		add(lblLogin);

		JLabel lblPassword = new JLabel("password");
		lblPassword.setFont(new Font("Nyala", Font.PLAIN, 27));
		lblPassword.setBounds(136, 370, 106, 37);
		add(lblPassword);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 700, 20);
		add(menuBar);

		JMenu ThiefHunterMenu = new JMenu("Thief Hunter");
		menuBar.add(ThiefHunterMenu);

		JMenuItem AboutSubMenu = new JMenuItem("About");
		AboutSubMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(null,
								"this project is made by : \n Ghediri Fahmi , Youssef Fahd ,  Degachi Souhail");

			}
		});
		ThiefHunterMenu.add(AboutSubMenu);

		JMenuItem CloseSubMenu = new JMenuItem("Close");
		CloseSubMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		ThiefHunterMenu.add(CloseSubMenu);

		JMenu Helpmenu = new JMenu("?");
		menuBar.add(Helpmenu);

		JMenuItem HelpSubMenu = new JMenuItem("Help");
		Helpmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Thief Hunter is an open source program \n"
						+ "under GNU/GPL 2.0 \n"
						+ "it tracks your stolen Device and send precious informations \n"
						+ " which can lead you to retrieve your PC again \n"
						+ "you need just to enter your username and password \n "
						+ "Thief Hunter will do the rest for you");
			}
		});
		Helpmenu.add(HelpSubMenu);

		login = new JTextField();
		login.setFont(new Font("Tahoma", Font.PLAIN, 16));
		login.setBounds(246, 318, 230, 30);
		add(login);
		login.setColumns(10);

		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 16));
		password.setBounds(246, 375, 230, 30);
		add(password);

		JButton btnConnect = new JButton("Connect");
		btnConnect.setFont(new Font("Nyala", Font.PLAIN, 22));
		btnConnect.setBounds(293, 434, 125, 42);
		btnConnect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String aux = new String(password.getPassword());
				if (login.getText().length() == 0 || aux.length() == 0) {
					JOptionPane.showMessageDialog(null,
							"Please Enter Your Connection Details");
					login.setText("");
					password.setText("");
				} else {
					loginContent = login.getText();
					passwordContent = new String(password.getPassword());
					connectionAgent = new Connection();
					connectionAgent.setLogin(loginContent);
					connectionAgent.setPassword(passwordContent);
					
					if (connectionAgent.connect() == true) {
						status = true;
						try {
							FileWriter  wr = new FileWriter("ressources\\login.txt");
							wr.write(loginContent+"\n");
							wr.write(passwordContent);
							wr.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Authentification Failed !! \n"
								+ "Please verify your internet connection. ");
						password.setText("");
					}
				}

			}
		});
		add(btnConnect);

		JPanel BackPanel = new JPanel();
		BackPanel.setBounds(0, 0, 10, 10);
		add(BackPanel);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 700, 510);
		add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("ressources\\background.png"));

	}

	public String getPasswordContent() {
		return passwordContent;
	}

	public void setPasswordContent(String passwordContent) {
		this.passwordContent = passwordContent;
	}

	public String getLoginContent() {
		return loginContent;
	}

	public void setLoginContent(String loginContent) {
		this.loginContent = loginContent;
	}

	public boolean isStatus() {
		return status;
	}
	
	public void setStatus() {
		this.status = true;
	}

}
