package com.glowman434.minecraftclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class StoneBlock extends Block {
	private static Texture t = new Texture(Gdx.files.internal("texture/Stone.PNG"));
	public StoneBlock() {
		super(t, t, t, t, t, t, Type.DirtBlock);
	}
}