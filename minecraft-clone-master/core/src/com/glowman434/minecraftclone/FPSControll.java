package com.glowman434.minecraftclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;

public class FPSControll extends FirstPersonCameraController {

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
		if(keycode == Keys.ESCAPE){
			Gdx.app.exit();
		}
		return super.keyDown(keycode);
	}
}
