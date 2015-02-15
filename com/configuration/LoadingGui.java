/**
  ******************************************************************************
  * @file    com/configuration/LoadingGui.java 
  * @author  Fahmi Ghediri
  * @version V1.0
  * @date    10-May-2014
  * @brief   the Loading Gui 
  ******************************************************************************
**/






package com.configuration;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class LoadingGui extends JPanel {

	private static final long serialVersionUID = 21L;

	public LoadingGui() {
		setLayout(null);
		setBounds(0, 0, 700, 510);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(
				"ressources\\loading.gif"));
		label.setBounds(0, 0, 700, 510);
		add(label);

	}

}
