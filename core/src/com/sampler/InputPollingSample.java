package com.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampler.common.SampleBase;
import com.sampler.common.SampleInfo;

public class InputPollingSample extends SampleBase {

	public static final SampleInfo SAMPLE_INFO = new SampleInfo(InputPollingSample.class);

	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	private BitmapFont font;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		camera = new OrthographicCamera();
		viewport = new FitViewport(1080,720,camera);
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width,height,true);
	}

	@Override
	public void render() {
		//clear screen
		Gdx.gl.glClearColor(0,0,0,1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		draw();

		batch.end();
	}

	private void draw(){
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();

		boolean leftPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
		boolean rightPressed = Gdx.input.isButtonPressed(Input.Buttons.RIGHT);

		font.draw(batch,
				"Mouse/Touch : x= " + mouseX + " y= " + mouseY,
				20f,720-20f);

		font.draw(batch, leftPressed ? "Left mouse button pressed" : "Left mouse button not pressed",
				20f,
				720-50f);
		font.draw(batch, rightPressed ? "Right mouse button pressed" : "Right mouse button not pressed",
				20f,
				720-80f);

		boolean wPressed = Gdx.input.isKeyPressed(Input.Keys.W);
		boolean sPressed = Gdx.input.isKeyPressed(Input.Keys.S);

		font.draw(batch, wPressed ? "W is pressed" : "W not pressed",
				20f,
				720-110f);
		font.draw(batch, sPressed ? "S is pressed" : "S not pressed",
				20f,
				720-140f);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
