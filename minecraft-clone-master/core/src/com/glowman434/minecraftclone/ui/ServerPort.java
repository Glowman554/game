package com.glowman434.minecraftclone.ui;

import javax.swing.JOptionPane;

public class ServerPort {
	
	public ServerPort(){
		
	}
	
	public String getServerPort() {
		String port = JOptionPane.showInputDialog(null,"Geben Sie den Server Port Ein", "Server Port", JOptionPane.PLAIN_MESSAGE);
		return port;
	}
}
