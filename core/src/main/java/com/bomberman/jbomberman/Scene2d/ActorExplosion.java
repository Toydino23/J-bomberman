package com.bomberman.jbomberman.Scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

// Creacion de la explosion y su animacion
public class ActorExplosion extends Actor implements Disposable{


    private Texture explosion;

    private TextureRegion[] explosionFrames;

    private TextureRegion explosionRegion;

    private Animation explosionAnimation;

    public float duration = 0;

    public ActorExplosion(){

        explosion = new Texture(Gdx.files.internal("Explosion.png"));

        TextureRegion [][] temp = TextureRegion.split(explosion, explosion.getWidth() / 2 , explosion.getHeight() / 2);

        explosionFrames = new TextureRegion[2 * 2];
        int indice = 0;
        for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				explosionFrames[indice++] = temp[i][j];
			}
		}
        
        explosionAnimation = new Animation<>(0.1f, explosionFrames);


    }

    @Override
    public void draw(Batch batch, float parentAlpha){

        TextureRegion frame =  (TextureRegion) explosionAnimation.getKeyFrame(duration+=Gdx.graphics.getDeltaTime(), true);
        
        batch.draw(frame, getX() - 80 , getY() - 70, 200, 200);

    }

    @Override
    public void act(float delta){

        super.act(delta);

    }

    


    @Override
    public void dispose() {
        
        explosion.dispose();
    }
    
}
