package com.glowman434.minecraftclone.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


public class Grid{
	private final static int grid_size = 21;
	private boolean generateWorld = true;
	private String field[][][];
	private static String prefix = "[Grid] ";



	public Grid(boolean generate) {
		
		field = new String[grid_size][grid_size][grid_size];
		
		generateWorld = generate;
		if(generateWorld) {
			
			for (int i = 0; i < grid_size; i++) {
				for (int j = 0; j < grid_size; j++) {
					for (int k = 0; k < grid_size; k++) {
						field[i][j][k] = "0";
					}
				}
			}
			
			
			Random rand = new Random();
			//field = new Block[grid_size][grid_size][grid_size];
			for (int i = 0; i < grid_size; i++) {
				for (int k = 0; k < grid_size; k++) {
					field[i][0][k] = "1";
					int ran = rand.nextInt(50);
					switch(ran) {
					case 1:
						Tree(i, 1, k, grid_size);
						break;
					case 2:
						Berry(i, 1, k, grid_size);
						break;
					}
				}
			}
		}
	}
	
	public void Tree(int x, int y, int z, int size) {
		try{
			field[x][y][z] = "2";
			field[x][y+1][z] = "2";
			field[x][y+2][z] = "2";
			field[x][y+3][z] = "2";
			field[x][y+4][z] = "4";
			field[x+1][y+2][z] = "4";
			field[x+1][y+3][z] = "4";
			field[x-1][y+2][z] = "4";
			field[x-1][y+3][z] = "4";
			field[x][y+2][z+1] = "4";
			field[x][y+3][z+1] = "4";
			field[x][y+2][z-1] = "4";
			field[x][y+3][z-1] = "4";
			System.out.println(prefix + "TreeCreate " + x + "," + y + "," + z);
		}catch (Exception e) {
			System.out.println(prefix + "TreeCreat: "+e);
			try {
				field[x][y][z] = "0";
				field[x][y+1][z] = "0";
				field[x][y+2][z] = "0";
				field[x][y+3][z] = "0";
				field[x][y+4][z] = "0";
				field[x+1][y+2][z] = "0";
				field[x+1][y+3][z] = "0";
				field[x-1][y+2][z] = "0";
				field[x-1][y+3][z] = "0";
				field[x][y+2][z+1] = "0";
				field[x][y+3][z+1] = "0";
				field[x][y+2][z-1] = "0";
				field[x][y+3][z-1] = "0";
			}catch (Exception e2) {
				System.out.println(prefix + "TreeCreat: "+e2);
			}
	    }
	}
	public void Berry(int x, int y, int z, int size) {
		try {
			field[x][y][z] = "5";
			System.out.println(prefix + "BerryCreate " + x + "," + y + "," + z);
		}catch (Exception e) {
			System.out.println(prefix + "BerryCreat: "+e);
		}
	}
		
	public void save(String path) throws IOException {
		String text = "";
		for (int i = 0; i < grid_size; i++) {
			for (int j = 0; j < grid_size; j++) {
				for (int k = 0; k < grid_size; k++) {
					switch(field[i][j][k]) {
					case "1": //DirtBlock
						text = text + "1,";
						break;
					case "2": //WoodBlock
						text = text + "2,";
						break;
					case "3": //StoneBlock
						text = text + "3,";
						break;
					case "4": //LeavesBlock
						text = text + "4,";
						break;
					case "5": //BerryBlock
						text = text + "5,";
						break;
					case "6": //GlassBlock
						text = text + "6,";
						break;
					case "0":
						text = text + "0,";
						break;
					}
				}
			}
		}
		FileOutputStream stream = new FileOutputStream(path);
	    for (int i=0; i < text.length(); i++){

	    	stream.write((byte)text.charAt(i));

	      }

	    stream.close();

	    System.out.println(prefix + "Save Done");
	}
	
	public void load(String path) throws IOException {
	    int readsofar = 0;
		String text = null;
		FileReader fr = null;
		
		File file = new File(path) ;
		int len = (int)file.length() ;
		char[] buf = new char[len] ;

		fr = new FileReader(file);
		fr.read(buf, 0, len) ;
		fr.close();
		text = new String(buf);
		//System.out.println(text);
	    String[] read = text.split(",");
	    
		for (int i = 0; i < grid_size; i++) {
			for (int j = 0; j < grid_size; j++) {
				for (int k = 0; k < grid_size; k++) {
					switch(read[readsofar]) {
					case "1":
						field[i][j][k] = "1";
						break;
					case "2":
						field[i][j][k] = "2";
						break;
					case "3":
						field[i][j][k] = "3";
						break;
					case "4":
						field[i][j][k] = "4";
						break;
					case "5":
						field[i][j][k] = "5";
						break;
					case "6":
						field[i][j][k] = "6";
						break;
					case "0":
						field[i][j][k] = "0";
						break;
					}
					readsofar++;
					//System.out.println(readsofar);
					//System.out.println();
				}
			}
		}
		System.out.println(prefix + "Done Load!");
	}

	public void SetBlock(String i, int x, int y, int z) {
		field[x][y][z] = i;
	}

	public String GetWorld() {
		String text = "";
		for (int i = 0; i < grid_size; i++) {
			for (int j = 0; j < grid_size; j++) {
				for (int k = 0; k < grid_size; k++) {
					switch(field[i][j][k]) {
					case "1": //DirtBlock
						text = text + "1,";
						break;
					case "2": //WoodBlock
						text = text + "2,";
						break;
					case "3": //StoneBlock
						text = text + "3,";
						break;
					case "4": //LeavesBlock
						text = text + "4,";
						break;
					case "5": //BerryBlock
						text = text + "5,";
						break;
					case "6": //GlassBlock
						text = text + "6,";
						break;
					case "0":
						text = text + "0,";
						break;
					}
				}
			}
		}
	    System.out.println(prefix + "GetWorld Done!");
	    return text;
	}
}