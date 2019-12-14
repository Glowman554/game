package com.glowman434.minecraftclone.serverhandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerGetReplay {
	
	public String GetReplay(String host, int port, String msg) {
		String replay = null;
		
        try (Socket socket = new Socket(host, port)) {
 
           OutputStream output = socket.getOutputStream();
           PrintWriter writer = new PrintWriter(output, true);
 
           writer.println(msg);
 
           InputStream input = socket.getInputStream();
           BufferedReader reader = new BufferedReader(new InputStreamReader(input));
           replay = reader.readLine();
           socket.close();
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
		return replay;
    }
	
	public void SetBlock(String host, int port, int block, int x, int y, int z) {
		
		String msg = "SetBlock" + " " + block + " " + x + " " + y + " " + z + " ";
		
		GetReplay(host, port, msg);
	}
	

}
