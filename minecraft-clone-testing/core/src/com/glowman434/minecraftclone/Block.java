package com.glowman434.minecraftclone;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;

public class Block implements Disposable {
	private static ModelBuilder modelBuilder = new ModelBuilder();
	private Model model;
	private ModelInstance instance;
	
	public Block(Texture front, Texture back, Texture bottom, Texture top, Texture left, Texture right, Type type) {
		int attr = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates;
		modelBuilder.begin();
		modelBuilder.part("front", GL20.GL_TRIANGLES, attr, new Material(TextureAttribute.createDiffuse(front)))
	    .rect(-2f,-2f,-2f, -2f,2f,-2f,  2f,2f,-2, 2f,-2f,-2f, 0,0,-1);
	modelBuilder.part("back", GL20.GL_TRIANGLES, attr, new Material(TextureAttribute.createDiffuse(back)))
	    .rect(-2f,2f,2f, -2f,-2f,2f,  2f,-2f,2f, 2f,2f,2f, 0,0,1);
	modelBuilder.part("bottom", GL20.GL_TRIANGLES, attr, new Material(TextureAttribute.createDiffuse(bottom)))
	    .rect(-2f,-2f,2f, -2f,-2f,-2f,  2f,-2f,-2f, 2f,-2f,2f, 0,-1,0);
	modelBuilder.part("top", GL20.GL_TRIANGLES, attr, new Material(TextureAttribute.createDiffuse(top)))
	    .rect(-2f,2f,-2f, -2f,2f,2f,  2f,2f,2f, 2f,2f,-2f, 0,1,0);
	modelBuilder.part("left", GL20.GL_TRIANGLES, attr, new Material(TextureAttribute.createDiffuse(left)))
	    .rect(-2f,-2f,2f, -2f,2f,2f,  -2f,2f,-2f, -2f,-2f,-2f, -1,0,0);
	modelBuilder.part("right", GL20.GL_TRIANGLES, attr, new Material(TextureAttribute.createDiffuse(right)))
	    .rect(2f,-2f,-2f, 2f,2f,-2f,  2f,2f,2f, 2f,-2f,2f, 1,0,0);
		model = modelBuilder.end();
		
		instance = new ModelInstance(model);
	}
	
	public void setPosition(float x, float y, float z){
		instance.transform = new Matrix4().translate(x, y, z);
	}
	
	public Vector3 getPosition(){
		float x = instance.transform.getValues()[Matrix4.M03];
		float y = instance.transform.getValues()[Matrix4.M13];
		float z = instance.transform.getValues()[Matrix4.M23];
		return new Vector3(x, y, z);
	}
	

	public Model getModel() {
		return model;
	}

	public ModelInstance getInstance() {
		return instance;
	}

	public void dispose() {
		model.dispose();
	}
	
	public enum Type{
		DirtBlock,
		WoodBlock,
		StoneBlock,
		LeavesBlock,
		BerryBlock, 
		GlassBlock,
		GrassBlock;
	}
}
