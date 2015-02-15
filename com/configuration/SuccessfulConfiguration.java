/**
  ******************************************************************************
  * @file    com/cpnfiguration/SuccessfulConfiguration.java 
  * @author  Fahmi Ghediri
  * @version V1.0
  * @date    21-Juin-2014
  * @brief   Successful configuration GUI 
  ******************************************************************************
**/








package com.configuration;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class SuccessfulConfiguration extends JPanel {

	private static final long serialVersionUID = 1L;

	public SuccessfulConfiguration() {
		setLayout(null);
		setBounds(0, 0, 700, 510);
		
		JButton btnRestartNow = new JButton("Restart Now");
		btnRestartNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("shutdown -r /t 0");
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnRestartNow.setFont(new Font("Nyala", Font.PLAIN, 16));
		btnRestartNow.setBounds(292, 426, 116, 33);
		add(btnRestartNow);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Nyala", Font.PLAIN, 11));
		label.setIcon(new ImageIcon("ressources\\finished.png"));
		label.setBounds(0, 0, 700, 510);
		add(label);

	}
	
}
