package com.glowman434.minecraftclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class WoodBlock extends Block {
	private static Texture t = new Texture(Gdx.files.internal("texture/wood.PNG"));
	public WoodBlock() {
		super(t, t, t, t, t, t, Type.WoodBlock);
	}
}
