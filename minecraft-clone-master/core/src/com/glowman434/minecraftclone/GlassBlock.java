package com.glowman434.minecraftclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GlassBlock extends Block{
	public GlassBlock() {
		super(new Texture(Gdx.files.internal("texture/Glass.png")), Type.GlassBlock);
	}
}
