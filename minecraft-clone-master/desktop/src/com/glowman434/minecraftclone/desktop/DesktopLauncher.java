package com.glowman434.minecraftclone.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.glowman434.minecraftclone.MinecraftClone;


public class DesktopLauncher {
	
	public static String name;
	public static String prefix = "[DesktopLauncher] ";
	
	public static void main (String[] args) {
		try{
			System.out.println(prefix + args[0]);
			name = args[0];
		}catch(Exception e) {
			System.out.println(prefix + e);
			name = "null";
		}
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1000;
		config.height = 500;
		new LwjglApplication(new MinecraftClone(name), config);
	}
}
