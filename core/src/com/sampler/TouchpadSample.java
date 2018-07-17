package com.sampler;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Logger;
import com.sampler.common.SampleBase;
import com.sampler.common.SampleInfo;

public class TouchpadSample extends SampleBase implements ApplicationListener{
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(TouchpadSample.class);
    private static final Logger log = new Logger(TouchpadSample.class.getName(), Logger.DEBUG);

    public static final float MAX_SPEED = 18f; // max player speed

    private int width = 100;
    private int height = 200; // ?? why do the dims have to be so high

    private float x;
    private float y;



    private boolean isWalking = false;
    private OrthographicCamera camera;
    private Stage stage;

    private Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin touchpadSkin;
    private Drawable touchBackground;
    private Drawable touchKnob;

    private ShapeRenderer renderer;

    private float stateTime;
    @Override
    public void create() {

        //Create camera
        float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 10f*aspectRatio, 10f); // can be used instead of a viewport

        renderer = new ShapeRenderer();
        //Create a touchpad skin
        touchpadSkin = new Skin();
        //Set background image
        touchpadSkin.add("touchBackground", new Texture("ui/touchpad/touchBackground.png"));
        //Set knob image
        touchpadSkin.add("touchKnob", new Texture("ui/touchpad/touchKnob.png"));
        //Create TouchPad Style
        touchpadStyle = new Touchpad.TouchpadStyle();
        //Create Drawable's from TouchPad skin
        touchBackground = touchpadSkin.getDrawable("touchBackground");
        touchKnob = touchpadSkin.getDrawable("touchKnob");
        //Apply the Drawables to the TouchPad Style
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;
        //Create new TouchPad with the created style
        touchpad = new Touchpad(10, touchpadStyle);
        //setBounds(x,y,width,height)
        touchpad.setBounds(15, 15, 200, 200);


        //Create a Stage and add TouchPad
     //   stage = new Stage(batch,true,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage();
        stage.addActor(touchpad);
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(255, 255,255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        stateTime += Gdx.graphics.getDeltaTime();

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLUE);

        renderer.rect(x,y,width, height);
        update();

        renderer.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    private void update(){
     float speed = 0;
        if(touchpad.isTouched()){
            isWalking = true;
            speed = MAX_SPEED;
        }
        else {
            isWalking = false;
        }

        setX(getX()+ touchpad.getKnobPercentX()  * speed);
        setY(getY()+ touchpad.getKnobPercentY()* speed);
       }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {
       renderer.dispose();
    }
}
