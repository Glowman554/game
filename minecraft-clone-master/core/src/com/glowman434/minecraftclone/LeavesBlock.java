package com.glowman434.minecraftclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class LeavesBlock extends Block{
	public LeavesBlock() {
		super(new Texture(Gdx.files.internal("texture/Leaves.PNG")), Type.LeavesBlock);
	}
}
