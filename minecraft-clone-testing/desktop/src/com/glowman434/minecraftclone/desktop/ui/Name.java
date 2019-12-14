package com.glowman434.minecraftclone.desktop.ui;

import javax.swing.JOptionPane;

public class Name {
	
	public Name(){
		
	}
	
	public String getUserName() {
		String username = JOptionPane.showInputDialog(null,"Geben Sie Ihren username ein", "Ihr username", JOptionPane.PLAIN_MESSAGE);
		return username;
	}
}
