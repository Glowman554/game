package com.glowman434.minecraftclone.ui;

import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

public class LoadUi extends JDialog {


	private static final long serialVersionUID = 1L;
	public LoadUi() {
		
		
	}
	
	public String chose() {
	    String userDirectory = System.getProperty("user.dir");
		JFileChooser jfc = new JFileChooser(userDirectory);
		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			return selectedFile.getAbsolutePath();
		}else {
			return null;
		}
	}

}
