package com.glowman434.minecraftclone.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ServerMain extends Thread {
	public static Grid world = new Grid(true);
	private static String prefix = "[ServerMain] ";
	static String Event = null;
	public static HashMap<String, String> PlayersLogin = new HashMap<String, String>();
	public static int port = 0;
	
	
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
				  case "players":
					  System.out.println(prefix + PlayersLogin);
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
    	if (args.length < 1) {
    		port = 90;
    	}else {
    		port = Integer.parseInt(args[0]);
    	}

        
 
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
			  	case "SaveWorld":
			  		world.save("world.msave");
			  		break;
			  	case "LoadWorld":
			  		world.load("world.msave");
			  		break;
			  	case "ExitServer":
			  		System.exit(0);
			  		break;
			  	case "GetPlayers":
			  		writer.println(prefix + PlayersLogin);
			  		break;
                }
                
                if(text.contains("SetBlock")) {
                	//System.out.println(text);
                	String[] temp = text.split(" ");
                	Event = text;
                	world.SetBlock(temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
                	
                }
                
                if(text.contains("LoginPlayer")) {
    				SimpleDateFormat date=new SimpleDateFormat("HH-mm-ss");
    				String logindate = date.format(new Date());
    				String[] temp = text.split(" ");
    				PlayersLogin.put(temp[1], logindate);
    				System.out.println(prefix + temp[1] + " Joinet the game!");
                }
 
                socket.close();
            }
 
        } catch (IOException ex) {
            System.out.println(prefix + "Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
