package com.glowman434.minecraftclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TestBlock extends Block {
	private static Texture top = new Texture(Gdx.files.internal("texture/Testblock/top.PNG"));
	private static Texture front = new Texture(Gdx.files.internal("texture/Testblock/front.PNG"));
	private static Texture back = new Texture(Gdx.files.internal("texture/Testblock/back.PNG"));
	private static Texture left = new Texture(Gdx.files.internal("texture/Testblock/left.PNG"));
	private static Texture right = new Texture(Gdx.files.internal("texture/Testblock/right.PNG"));
	private static Texture bottom = new Texture(Gdx.files.internal("texture/Testblock/bottom.PNG"));
	public TestBlock() {
		super(front, back, bottom, top, left, right, Type.TestBlock);
		
	}
}
