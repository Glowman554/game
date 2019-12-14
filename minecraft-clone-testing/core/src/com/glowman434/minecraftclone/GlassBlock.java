package com.glowman434.minecraftclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GlassBlock extends Block{
	private static Texture t = new Texture(Gdx.files.internal("texture/Glass.PNG"));
	public GlassBlock() {
		super(t, t, t, t, t, t, Type.GlassBlock);
	}
}
