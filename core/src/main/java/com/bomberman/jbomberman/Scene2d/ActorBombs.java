package com.bomberman.jbomberman.Scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

// Creacion de las bombas y su animacion
public class ActorBombs extends Actor implements Disposable{

    private Texture bombs;

    private TextureRegion[] bombsFrames;

    private TextureRegion bombsRegion;

    private Animation bombsAnimation;

    public float duration = 0;


    public ActorBombs(){

        bombs = new Texture("Bombs.png");
        
        bombsRegion = new TextureRegion(bombs, 0, 0, 45, 16);

        TextureRegion[][] temp = bombsRegion.split((bombs.getWidth() / 3), bombs.getHeight()); 
        
        bombsFrames = new TextureRegion[temp.length * temp[0].length];
        
        int indice = 0;
        for (int i = 0; i < temp.length; i++) {
           for (int j = 0; j < temp[i].length; j++) {
                bombsFrames[indice++] = temp[i][j];
           } 
        }

        bombsAnimation = new Animation(0.1f, bombsFrames);
   
        
    }

    @Override
    public void draw(Batch batch, float parentAlpha){

        TextureRegion frame =  (TextureRegion) bombsAnimation.getKeyFrame(duration+=Gdx.graphics.getDeltaTime(), true);
        
        batch.draw(frame, getX(), getY(), 35, 50);
    }
    

    @Override
    public void act(float delta){

        super.act(delta);

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        bombs.dispose();

    }
    
}
