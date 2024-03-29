package com.glowman434.minecraftclone;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.glowman434.minecraftclone.serverhandler.ServerGetReplay;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
	
	public String host = null;
	public int port = 0;
	public boolean online = false;

	public FPSControll camera_controller;
	public Environment environment;
	public ModelBatch model_batch;
	public SpriteBatch sprite_batch;
	public Stage stage;
	public PerspectiveCamera camera;
	public Grid grid;
	public Texture crosshair;
	private BitmapFont font;
	public Button place;
	public Button destroy;
	public Button forward;
	public Button backward;
	private Vector3 tmp = new Vector3();
	
	public MinecraftClone(String name) {
		username = name;
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
        final Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        stage = new Stage(new ScreenViewport());
        place = new TextButton("Place Block",skin,"colored");
        place.setPosition(Gdx.graphics.getWidth()-350,Gdx.graphics.getHeight()-100);
        place.setTransform(true);
        place.scaleBy(1.2f);
		place.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				grid.editBoxByRayCast(camera.position, camera.direction, currentBlock, online, host, port);

				return true;
			}
		});
        stage.addActor(place);

		destroy = new TextButton("Destroy Block",skin,"colored");
		destroy.setPosition(Gdx.graphics.getWidth()-700,Gdx.graphics.getHeight()-100);
		destroy.setTransform(true);
		destroy.scaleBy(1.2f);
		destroy.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				grid.editBoxByRayCast(camera.position, camera.direction, null, online, host, port);
				return true;
			}
		});
		stage.addActor(destroy);

		forward = new TextButton("Forward",skin,"colored");
		forward.setPosition(200,200);
		forward.setTransform(true);
		forward.scaleBy(1.2f);
		forward.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                tmp.set(camera.direction).nor().scl(1);
                camera.position.add(tmp);
				return true;
			}
		});

		stage.addActor(forward);

		backward = new TextButton("Backward",skin,"colored");
		backward.setPosition(200,100);
		backward.setTransform(true);
		backward.scaleBy(1.2f);
		backward.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				tmp.set(camera.direction).nor().scl(-1);
				camera.position.add(tmp);
				return true;
			}
		});

		stage.addActor(backward);

		camera = new PerspectiveCamera(field_of_view, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0, 10f, 10f);
		camera.near = camera_near;
		camera.far = camera_far;
		camera.update();
		
		crosshair = new Texture(Gdx.files.internal("interface/Crosshair.png"));
		grid = new Grid(true);

		camera_controller = new FPSControll(camera) {
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				if (button == 0) {
					//grid.editBoxByRayCast(camera.position, camera.direction, currentBlock, online, host, port);
				} else if (button == 1) {
					//grid.editBoxByRayCast(camera.position, camera.direction, null, online, host, port);
				}
				return super.touchDown(screenX, screenY, pointer, button);
			}
		};
		camera_controller.setDegreesPerPixel(camera_degrees_per_pixel);
		camera_controller.setVelocity(camera_velocity);

		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(camera_controller);
		inputMultiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(inputMultiplexer);
		//Gdx.input.setInputProcessor(camera_controller);

		//Gdx.input.setInputProcessor(stage);
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
		case GrassBlock:
			nameBlock = "GrassBlock";
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
		font.draw(sprite_batch, (int)frameRate + " FPS", 50, Gdx.graphics.getHeight() - 3);
		font.draw(sprite_batch, username, 50, Gdx.graphics.getHeight() - 27);
		font.draw(sprite_batch, nameBlock, 50, Gdx.graphics.getHeight() - 56);
		sprite_batch.draw(crosshair, crosshair_x, crosshair_y, crosshair_size, crosshair_size);
		sprite_batch.end();
        stage.act();
        stage.draw();
		boolean bol = camera_controller.getSave();
		boolean bol2 = camera_controller.getLoad();
		boolean bol3 = camera_controller.getOnline();
		if(bol) {
			try {
				SimpleDateFormat date=new SimpleDateFormat("yyyy.MM.dd.HH-mm-ss");
				String savedate=date.format(new Date());
				System.out.println(savedate);
				grid.save(savedate + ".msave");
				camera_controller.delSaveBol();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(bol2) {
			//LoadUi ui = new LoadUi();
			//String path = ui.chose();
			try {
				grid.load("");
			} catch (IOException e) {
				e.printStackTrace();
				Gdx.app.exit();
			}
			camera_controller.delLoadBol();
			
		}
		
		if(bol3) {
			//ServerHost gethost = new ServerHost();
			//ServerPort getport = new ServerPort();
			ServerGetReplay r = new ServerGetReplay();
			
			//host = gethost.getServerHost();
			//port = Integer.parseInt(getport.getServerPort());
			r.GetReplay(host, port, "LoginPlayer " + username);
			String world = r.GetReplay(host, port, "GetWorld");
			grid.loadStr(world);
			
			online = true;
			
			camera_controller.delOnlineBol();
		}
		
		if(online) {
			ServerGetReplay r = new ServerGetReplay();
			String temp = r.GetReplay(host, port, "GetEvent");
			
			if(temp.contains("SetBlock")) {
				//System.out.println(temp);
				String[] t = temp.split(" ");
				grid.SetBlock(t[1], Integer.parseInt(t[2]), Integer.parseInt(t[3]), Integer.parseInt(t[4]));
			}
		}
	}

	@Override
	public void dispose() {
		model_batch.dispose();
		grid.dispose();
		font.dispose();
		stage.dispose();
	}
}
