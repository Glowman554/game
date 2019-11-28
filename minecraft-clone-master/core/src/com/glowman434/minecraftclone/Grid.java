package com.glowman434.minecraftclone;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.glowman434.minecraftclone.BerryBlock;
import com.glowman434.minecraftclone.Block;
import com.glowman434.minecraftclone.DirtBlock;
import com.glowman434.minecraftclone.GlassBlock;
import com.glowman434.minecraftclone.LeavesBlock;
import com.glowman434.minecraftclone.StoneBlock;
import com.glowman434.minecraftclone.WoodBlock;
import com.glowman434.minecraftclone.serverhandler.ServerGetReplay;
import com.glowman434.minecraftclone.ui.ProgreassBarUi;
import com.glowman434.minecraftclone.ui.WorldGenerateUi;
import com.glowman434.minecraftclone.ui.WorldSaveUi;

public class Grid extends JPanel implements Disposable  {
	private static final long serialVersionUID = -2420530188123215913L;
	private final static int grid_size = 21;
	private final static float field_size = 5;
	private boolean generateWorld = true;
	private static Block field[][][];
	private static String prefix = "[Grid] ";
    private Sound wood = Gdx.audio.newSound(Gdx.files.internal("sound/wood.ogg"));
    private Sound stone = Gdx.audio.newSound(Gdx.files.internal("sound/stone.ogg"));
    private Sound grass = Gdx.audio.newSound(Gdx.files.internal("sound/grass.ogg"));
    private Sound leave = Gdx.audio.newSound(Gdx.files.internal("sound/leave.ogg"));
    private Sound berry = Gdx.audio.newSound(Gdx.files.internal("sound/berry.ogg"));
	private ServerGetReplay r = new ServerGetReplay();


	public Grid(boolean generate) {
		generateWorld = generate;
		field = new Block[grid_size][grid_size][grid_size];
		if(generateWorld) {
			Random rand = new Random();
			WorldGenerateUi dialog = new WorldGenerateUi();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);		
			dialog.setMax(grid_size - 1);
			//field = new Block[grid_size][grid_size][grid_size];
			for (int i = 0; i < grid_size; i++) {
				for (int k = 0; k < grid_size; k++) {
					field[i][0][k] = new DirtBlock();
					int ran = rand.nextInt(50);
					switch(ran) {
					case 1:
						Tree(i, 1, k, grid_size);
						break;
					case 2:
						Berry(i, 1, k, grid_size);
						break;
					}
					dialog.setValue(i);
				}
			}
			dialog.setVisible(false);
			updatePosition();
		}
	}


	public static void updatePosition() {
		for (int i = 0; i < grid_size; i++) {
			for (int j = 0; j < grid_size; j++) {
				for (int k = 0; k < grid_size; k++) {
					float x = i * field_size;
					float y = j * field_size;
					float z = k * field_size;
					if (field[i][j][k] != null) {
						field[i][j][k].setPosition(x, y, z);
						//System.out.println(i + "," + j + "," + k);
					}
				}
			}
		}
	}

	public void renderGrid(ModelBatch batch, Environment environment) {
		for (int i = 0; i < grid_size; i++) {
			for (int j = 0; j < grid_size; j++) {
				for (int k = 0; k < grid_size; k++) {
					if (field[i][j][k] != null) {
						batch.render(field[i][j][k].getInstance(), environment);
					}
				}
			}
		}
	}

	@Override
	public void dispose() {
		wood.dispose();
		stone.dispose();
		grass.dispose();
		leave.dispose();
		berry.dispose();
		for (int i = 0; i < grid_size; i++) {
			for (int j = 0; j < grid_size; j++) {
				for (int k = 0; k < grid_size; k++) {
					if (field[i][j][k] != null) {
						field[i][j][k].dispose();
					}
				}
			}
		}
	}

	public void editBoxByRayCast(Vector3 start_point, Vector3 direction, Block.Type type, boolean online, String host, int port) {
		int last_point_x = 0;
		int last_point_y = 0;
		int last_point_z = 0;

		for (int i = 1; i < grid_size * 2; i++) {
			Vector3 tmp_start = new Vector3(start_point);
			Vector3 tmp_direction = new Vector3(direction);
			tmp_direction.nor();
			tmp_direction.scl(i);
			Vector3 line = tmp_start.add(tmp_direction);
			// scale to grid wolrd
			line.scl(1 / field_size);
			int x = Math.round(line.x);
			int y = Math.round(line.y);
			int z = Math.round(line.z);

			if (x > (grid_size - 1) || y > (grid_size - 1) || z > (grid_size - 1) || x < 0 || y < 0 || z < 0) {
				break;
			}

			if (field[x][y][z] != null) {
				if (type == null) {
					if (field[x][y][z] != null) {
						field[x][y][z].dispose();
						field[x][y][z] = null;
						r.SetBlock(host, port, 0, x, y, z);
						updatePosition();
					}
				}else {
					switch(type) {
					case DirtBlock:
						field[last_point_x][last_point_y][last_point_z] = new DirtBlock();
						if(online) r.SetBlock(host, port, 1, last_point_x, last_point_y, last_point_z);
						grass.play();
						updatePosition();
						break;
					case WoodBlock:
						field[last_point_x][last_point_y][last_point_z] = new WoodBlock();
						if(online) r.SetBlock(host, port, 2, last_point_x, last_point_y, last_point_z);
						wood.play();
						updatePosition();
						break;
					case BerryBlock:
						field[last_point_x][last_point_y][last_point_z] = new BerryBlock();
						if(online) r.SetBlock(host, port, 5, last_point_x, last_point_y, last_point_z);
						berry.play();
						updatePosition();
						break;
					case LeavesBlock:
						field[last_point_x][last_point_y][last_point_z] = new LeavesBlock();
						if(online) r.SetBlock(host, port, 4, last_point_x, last_point_y, last_point_z);
						leave.play();
						updatePosition();
						break;
					case GlassBlock:
						field[last_point_x][last_point_y][last_point_z] = new GlassBlock();
						if(online) r.SetBlock(host, port, 6, last_point_x, last_point_y, last_point_z);
						updatePosition();
						break;
					case StoneBlock:
						field[last_point_x][last_point_y][last_point_z] = new StoneBlock();
						if(online) r.SetBlock(host, port, 3, last_point_x, last_point_y, last_point_z);
						stone.play();
						updatePosition();
						break;
					}
				}
				break;
			}

			last_point_x = x;
			last_point_y = y;
			last_point_z = z;
		}
	}

	public boolean hittingBox(Vector3 point) {
		point.scl(1 / field_size);
		int x = Math.round(point.x);
		int y = Math.round(point.y);
		int z = Math.round(point.z);
		
		if(field[x][y][z] != null){
			return true;
		} else {
			return false;
		}
	}
	public void Tree(int x, int y, int z, int size) {
		try{
			field[x][y][z] = new WoodBlock();
			field[x][y+1][z] = new WoodBlock();
			field[x][y+2][z] = new WoodBlock();
			field[x][y+3][z] = new WoodBlock();
			field[x][y+4][z] = new LeavesBlock();
			field[x+1][y+2][z] = new LeavesBlock();
			field[x+1][y+3][z] = new LeavesBlock();
			field[x-1][y+2][z] = new LeavesBlock();
			field[x-1][y+3][z] = new LeavesBlock();
			field[x][y+2][z+1] = new LeavesBlock();
			field[x][y+3][z+1] = new LeavesBlock();
			field[x][y+2][z-1] = new LeavesBlock();
			field[x][y+3][z-1] = new LeavesBlock();
			System.out.println(prefix + "TreeCreate " + x + "," + y + "," + z);
			updatePosition();
		}catch (Exception e) {
			System.out.println(prefix + "TreeCreat: "+e);
			try {
				field[x][y][z] = null;
				field[x][y+1][z] = null;
				field[x][y+2][z] = null;
				field[x][y+3][z] = null;
				field[x][y+4][z] = null;
				field[x+1][y+2][z] = null;
				field[x+1][y+3][z] = null;
				field[x-1][y+2][z] = null;
				field[x-1][y+3][z] = null;
				field[x][y+2][z+1] = null;
				field[x][y+3][z+1] = null;
				field[x][y+2][z-1] = null;
				field[x][y+3][z-1] = null;
				updatePosition();
			}catch (Exception e2) {
				System.out.println(prefix + "TreeCreat: "+e2);
			}
	    }
	}
	public void Berry(int x, int y, int z, int size) {
		try {
			field[x][y][z] = new BerryBlock();
			System.out.println(prefix + "BerryCreate " + x + "," + y + "," + z);
			updatePosition();
		}catch (Exception e) {
			System.out.println(prefix + "BerryCreat: "+e);
		}
	}
	
	public String getBlock(int x, int y, int z){
		try {
			String[] block = field[x][y][z].toString().split("@");
			//System.out.println(block[0]);
			return block[0];
		}catch(Exception e) {
			return "null";
		}
		
	}
	
	public void save(String path) throws IOException {
		String text = "";
		WorldSaveUi dialog = new WorldSaveUi();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);		
		dialog.setMax(grid_size - 1);
		for (int i = 0; i < grid_size; i++) {
			for (int j = 0; j < grid_size; j++) {
				for (int k = 0; k < grid_size; k++) {
					switch(getBlock(i, j, k)) {
					case "com.glowman434.minecraftclone.DirtBlock":
						text = text + "1,";
						break;
					case "com.glowman434.minecraftclone.WoodBlock":
						text = text + "2,";
						break;
					case "com.glowman434.minecraftclone.StoneBlock":
						text = text + "3,";
						break;
					case "com.glowman434.minecraftclone.LeavesBlock":
						text = text + "4,";
						break;
					case "com.glowman434.minecraftclone.BerryBlock":
						text = text + "5,";
						break;
					case "com.glowman434.minecraftclone.GlassBlock":
						text = text + "6,";
						break;
					case "null":
						text = text + "0,";
						break;
					}
					dialog.setValue(i);
				}
			}
		}
		FileOutputStream stream = new FileOutputStream(path);
	    for (int i=0; i < text.length(); i++){

	    	stream.write((byte)text.charAt(i));

	      }

	    stream.close();
	    dialog.setVisible(false);

	    System.out.println(prefix + "Save Done");
	}
	
	public void load(String path) throws IOException {
	    int readsofar = 0;
		String text = null;
		ProgreassBarUi dialog = new ProgreassBarUi();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);		
		dialog.setMax(100);
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
	    
	    dialog.setMax(read.length);
		for (int i = 0; i < grid_size; i++) {
			for (int j = 0; j < grid_size; j++) {
				for (int k = 0; k < grid_size; k++) {
					switch(read[readsofar]) {
					case "1":
						field[i][j][k] = new DirtBlock();
						break;
					case "2":
						field[i][j][k] = new WoodBlock();
						break;
					case "3":
						field[i][j][k] = new StoneBlock();
						break;
					case "4":
						field[i][j][k] = new LeavesBlock();
						break;
					case "5":
						field[i][j][k] = new BerryBlock();
						break;
					case "6":
						field[i][j][k] = new GlassBlock();
						break;
					case "0":
						field[i][j][k] = null;
						break;
					}
					updatePosition();
					readsofar++;
					dialog.setValue(readsofar);
					//System.out.println(readsofar);
					//System.out.println();
				}
			}
		}
		dialog.setVisible(false);
		System.out.println(prefix + "Done Load!");
	}
	
	public void loadStr(String str) {
	    int readsofar = 0;
		ProgreassBarUi dialog = new ProgreassBarUi();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);		
		dialog.setMax(100);
		;
		//System.out.println(text);
	    String[] read = str.split(",");
	    
	    dialog.setMax(read.length);
		for (int i = 0; i < grid_size; i++) {
			for (int j = 0; j < grid_size; j++) {
				for (int k = 0; k < grid_size; k++) {
					switch(read[readsofar]) {
					case "1":
						field[i][j][k] = new DirtBlock();
						break;
					case "2":
						field[i][j][k] = new WoodBlock();
						break;
					case "3":
						field[i][j][k] = new StoneBlock();
						break;
					case "4":
						field[i][j][k] = new LeavesBlock();
						break;
					case "5":
						field[i][j][k] = new BerryBlock();
						break;
					case "6":
						field[i][j][k] = new GlassBlock();
						break;
					case "0":
						field[i][j][k] = null;
						break;
					}
					updatePosition();
					readsofar++;
					dialog.setValue(readsofar);
					//System.out.println(readsofar);
					//System.out.println();
				}
			}
		}
		dialog.setVisible(false);
		System.out.println(prefix + "Done Load!");
	}

	public void SetBlock(String block, int i, int j, int k) {
		switch(block) {
		case "1":
			field[i][j][k] = new DirtBlock();
			break;
		case "2":
			field[i][j][k] = new WoodBlock();
			break;
		case "3":
			field[i][j][k] = new StoneBlock();
			break;
		case "4":
			field[i][j][k] = new LeavesBlock();
			break;
		case "5":
			field[i][j][k] = new BerryBlock();
			break;
		case "6":
			field[i][j][k] = new GlassBlock();
			break;
		case "0":
			field[i][j][k] = null;
			break;
		}
		updatePosition();
	}

}