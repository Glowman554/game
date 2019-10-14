package com.glowman434.minecraftclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class WoodBlock extends Block {
	public WoodBlock() {
		super(new Texture(Gdx.files.internal("texture/wood.PNG")), Type.WoodBlock);
	}
}
