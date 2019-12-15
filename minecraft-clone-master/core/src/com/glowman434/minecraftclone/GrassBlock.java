package com.glowman434.minecraftclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GrassBlock extends Block {
	private static Texture top = new Texture(Gdx.files.internal("texture/Grass/top.PNG"));
	private static Texture front = new Texture(Gdx.files.internal("texture/Grass/front.PNG"));
	private static Texture back = new Texture(Gdx.files.internal("texture/Grass/back.PNG"));
	private static Texture left = new Texture(Gdx.files.internal("texture/Grass/left.PNG"));
	private static Texture right = new Texture(Gdx.files.internal("texture/Grass/right.PNG"));
	private static Texture bottom = new Texture(Gdx.files.internal("texture/Grass/bottom.PNG"));
	public GrassBlock() {
		super(front, back, bottom, top, left, right, Type.GrassBlock);
		
	}
}
