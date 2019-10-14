package com.glowman434.minecraftclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class StoneBlock extends Block {
	public StoneBlock() {
		super(new Texture(Gdx.files.internal("texture/Stone.PNG")), Type.DirtBlock);
	}
}