package com.glowman434.minecraftclone.ui;

import javax.swing.JOptionPane;

public class ServerHost {
	
	public ServerHost(){
		
	}
	
	public String getServerHost() {
		String serverport = JOptionPane.showInputDialog(null,"Geben Sie Den Server Hostname ein", "Hostname", JOptionPane.PLAIN_MESSAGE);
		return serverport;
	}
}
