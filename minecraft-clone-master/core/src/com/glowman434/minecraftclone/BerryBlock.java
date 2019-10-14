package com.glowman434.minecraftclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BerryBlock extends Block{
	public BerryBlock() {
		super(new Texture(Gdx.files.internal("texture/Berry.png")), Type.BerryBlock);
	}
}
