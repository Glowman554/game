package com.glowman434.minecraftclone;

import java.io.IOException;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;

public class MinecraftClone extends ApplicationAdapter{
	public final float field_of_view = 67;
	public final float camera_near = 1;
	public final float camera_far = 300;
	public final float camera_velocity = 40;
	public final float camera_degrees_per_pixel = 0.08f;
	public final float crosshair_size = 25;
	public int frameRate;
	public String username;
	public Block.Type currentBlock;
	public String nameBlock;
	private String prefix = "[MinecraftClone] ";
	private boolean load = false;
	private String pathLoad = null;

	public FPSControll camera_controller;
	public Environment environment;
	public ModelBatch model_batch;
	public SpriteBatch sprite_batch;
	public PerspectiveCamera camera;
	public Grid grid;
	public Texture crosshair;
	private BitmapFont font;
	
	public MinecraftClone(String name, String path) {
		username = name;
		if(path == "null") {
			load = false;
		}else {
			load = true;
			pathLoad = path;
		}
	}

	@Override
	public void create() {
		model_batch = new ModelBatch();
		sprite_batch = new SpriteBatch();

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.5f, 0.5f, 0.5f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.5f));
		environment.add(new DirectionalLight().set(0.2f, 0.2f, 0.2f, 1f, 0.8f, 0.5f));
		
		font = new BitmapFont(Gdx.files.internal("font/Calibri.fnt"),false);

		camera = new PerspectiveCamera(field_of_view, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0, 10f, 10f);
		camera.near = camera_near;
		camera.far = camera_far;
		camera.update();
		
		crosshair = new Texture(Gdx.files.internal("interface/Crosshair.png"));
		if(load) {
			try {
				grid = new Grid(false);
				Grid.load(pathLoad);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			grid = new Grid(true);
		}

		camera_controller = new FPSControll(camera) {
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				if (button == 0) {
					grid.editBoxByRayCast(camera.position, camera.direction, currentBlock);
				} else if (button == 1) {
					grid.editBoxByRayCast(camera.position, camera.direction, null);
				}
				return super.touchDown(screenX, screenY, pointer, button);
			}
		};
		camera_controller.setDegreesPerPixel(camera_degrees_per_pixel);
		camera_controller.setVelocity(camera_velocity);
		Gdx.input.setInputProcessor(camera_controller);
		Gdx.input.setCursorCatched(true);
		System.out.println(prefix + "Done Create");
	}

	@Override
	public void render() {
		currentBlock = camera_controller.getCurrentBlock();
		switch(currentBlock) {
		case DirtBlock:
			nameBlock = "DirtBlock";
			break;
		case WoodBlock:
			nameBlock = "WoodBlock";
			break;
		case BerryBlock:
			nameBlock = "BerryBlock";
			break;
		case LeavesBlock:
			nameBlock = "LeavesBlock";
			break;
		case GlassBlock:
			nameBlock = "GlassBlock";
			break;
		case StoneBlock:
			nameBlock = "StoneBlock";
			break;
		}
		
		
		frameRate = Gdx.graphics.getFramesPerSecond();
		camera_controller.update();
		Gdx.gl.glClearColor(0.5f, 0.8f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		model_batch.begin(camera);
		grid.renderGrid(model_batch, environment);
		model_batch.end();

		float crosshair_x = (Gdx.graphics.getWidth() - crosshair_size) / 2;
		float crosshair_y = (Gdx.graphics.getHeight() - crosshair_size) / 2;
		sprite_batch.begin();
		//System.out.println(frameRate);
		font.draw(sprite_batch, (int)frameRate + " FPS", 10, Gdx.graphics.getHeight() - 3);
		font.draw(sprite_batch, username, 10, Gdx.graphics.getHeight() - 27);
		font.draw(sprite_batch, nameBlock, 10, Gdx.graphics.getHeight() - 56);
		sprite_batch.draw(crosshair, crosshair_x, crosshair_y, crosshair_size, crosshair_size);
		sprite_batch.end();
		boolean bol = camera_controller.getSave();
		if(bol) {
			try {
				grid.save("default.msave");
				camera_controller.delSaveBol();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void dispose() {
		model_batch.dispose();
		grid.dispose();
		font.dispose();
	}
}
