package com.glowman434.minecraftclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class DirtBlock extends Block {
	public DirtBlock() {
		super(new Texture(Gdx.files.internal("texture/Dirt.PNG")), Type.DirtBlock);
	}
}
