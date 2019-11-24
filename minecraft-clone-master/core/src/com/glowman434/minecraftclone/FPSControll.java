package com.glowman434.minecraftclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.glowman434.minecraftclone.Block.Type;

public class FPSControll extends FirstPersonCameraController {
	
	public Block.Type currentBlock = Type.DirtBlock;
	private String prefix = "[FPSControll] ";
	private boolean save = false;
	private boolean load = false;

	public FPSControll(Camera camera) {
		super(camera);
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		touchDragged(screenX, screenY, 0);
		return super.mouseMoved(screenX, screenY);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		switch(keycode) {
		case Keys.ESCAPE:
			Gdx.app.exit();
			break;
		case Keys.NUM_1:
			currentBlock = Type.BerryBlock;
			System.out.println(prefix + "Block select " + currentBlock);
			break;
		case Keys.NUM_2:
			currentBlock = Type.DirtBlock;
			System.out.println(prefix + "Block select " + currentBlock);
			break;
		case Keys.NUM_3:
			currentBlock = Type.GlassBlock;
			System.out.println(prefix + "Block select " + currentBlock);
			break;
		case Keys.NUM_4:
			currentBlock = Type.LeavesBlock;
			System.out.println(prefix + "Block select " + currentBlock);
			break;
		case Keys.NUM_5:
			currentBlock = Type.StoneBlock;
			System.out.println(prefix + "Block select " + currentBlock);
			break;
		case Keys.NUM_6:
			currentBlock = Type.WoodBlock;
			System.out.println(prefix + "Block select " + currentBlock);
			break;
		case Keys.F1:
			save = true;
			break;
		case Keys.F2:
			load = true;
			break;
		}
		return super.keyDown(keycode);
	}
	
	public Type getCurrentBlock() {
		return currentBlock;
		
	}
	
	public boolean getSave() {
		return save;
		
	}
	
	public boolean getLoad() {
		return load;
		
	}
	
	public void delSaveBol(){
		save = false;
	}
	
	public void delLoadBol(){
		load = false;
	}
}
