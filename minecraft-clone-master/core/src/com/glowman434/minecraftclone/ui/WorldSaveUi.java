package com.glowman434.minecraftclone.ui;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JProgressBar;

public class WorldSaveUi extends JDialog {
	private JProgressBar progressBar;
	
	private static final long serialVersionUID = 5973378904799402918L;

	/**
	 * Create the dialog.
	 */
	public WorldSaveUi() {
		setBounds(100, 100, 277, 84);
		{
			progressBar = new JProgressBar();
			getContentPane().add(progressBar, BorderLayout.CENTER);
		}
	}
	
	public void setMax(int max) {
		progressBar.setMaximum(max);
	}
	
	public void setValue(int value) {
		progressBar.setValue(value);
	}

}
