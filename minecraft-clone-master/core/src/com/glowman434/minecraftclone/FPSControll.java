package com.glowman434.minecraftclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.glowman434.minecraftclone.Block.Type;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class FPSControll extends FirstPersonCameraController {
	
	public Block.Type currentBlock = Type.DirtBlock;

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
		case Keys.F1:
			currentBlock = Type.BerryBlock;
			break;
		case Keys.F2:
			currentBlock = Type.DirtBlock;
			break;
		case Keys.F3:
			currentBlock = Type.GlassBlock;
			break;
		case Keys.F4:
			currentBlock = Type.LeavesBlock;
			break;
		case Keys.F5:
			currentBlock = Type.StoneBlock;
			break;
		case Keys.F6:
			currentBlock = Type.WoodBlock;
			break;
		}
		System.out.println("Block select " + currentBlock);
		return super.keyDown(keycode);
	}
	
	public Type getCurrentBlock() {
		return currentBlock;
		
	}
}
