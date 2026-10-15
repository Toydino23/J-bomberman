package com.bomberman.jbomberman.Scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class ActorBackground extends Actor implements Disposable{
    
    //Textura del campo
    private Texture playfield;

    //Carga del archivo
    public ActorBackground(){

        playfield = new Texture("Playfield.png");


    }
    
    @Override
    public void draw(Batch batch, float parentAlpha){

        //Dibujo del campo
        batch.draw(playfield, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void act(float delta){

        super.act(delta);

    }

    @Override
    public void dispose() {
        // ESTE METODO ES IMPORTANTE PARA EVITAR SOBRECARGA HACIA LA VRAM
        playfield.dispose();
    }
    


}
