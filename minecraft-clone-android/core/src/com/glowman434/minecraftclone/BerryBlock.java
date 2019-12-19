package com.glowman434.minecraftclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BerryBlock extends Block{
	private static Texture t = new Texture(Gdx.files.internal("texture/Berry.png"));
	public BerryBlock() {
		super(t, t, t, t, t, t, Type.BerryBlock);
	}
}
