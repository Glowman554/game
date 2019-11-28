package com.glowman434.minecraftclone.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServerMain extends Thread {
	public static Grid world = new Grid(true);
	private static String prefix = "[ServerMain] ";
	static String Event = null;
	
	
	  public void run() {
		  try {
			  while(true) {
				  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				  String command = reader.readLine();
				  
				  switch(command) {
				  case "save":
					  world.save("world.msave");
					  break;
				  case "load":
					  world.load("world.msave");
					  break;
				  case "exit":
					  System.exit(0);
					  break;
				  
				  default:
					  System.out.println(prefix + "Command not found!");
					  break;
				  }
				  
			  }
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	  }
	

    public static void main(String[] args) {
    	Random ran = new Random();
        int port = ran.nextInt(2000);
    	//int port = 200;
        
 
        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println(prefix + "Server is listening on port " + port);
            ServerMain console = new ServerMain();
            console.start();
            while (true) {
                Socket socket = serverSocket.accept();
                //System.out.println("New client connected");
 
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
 
 
                String text;
                text = reader.readLine();
                
                switch(text) {
                case "GetWorld":
                	writer.println(world.GetWorld());
                	break;
                case "GetEvent":
                	writer.println(Event);
                	break;
                }
                
                if(text.contains("SetBlock")) {
                	//System.out.println(text);
                	String[] temp = text.split(" ");
                	Event = text;
                	world.SetBlock(temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
                	
                }
 
                socket.close();
            }
 
        } catch (IOException ex) {
            System.out.println(prefix + "Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
