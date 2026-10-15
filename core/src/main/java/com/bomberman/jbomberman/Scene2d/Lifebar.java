package com.bomberman.jbomberman.Scene2d;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class Lifebar extends Actor implements Disposable{

    // Textura de la vida
    private Texture lifebar;
    private int X = 0;

    public Lifebar(int X){

        lifebar = new Texture("Lifebomber.png");
        this.X = X;

    }

    @Override
    public void draw(Batch batch, float parentAlpha){

        //Dibujo de la vida
        batch.draw(lifebar,20 + X, 660, 70, 70);
    }

    @Override
    public void act(float delta){

        super.act(delta);

    }

    @Override
    public void dispose() {
        // ESTE METODO ES IMPORTANTE PARA EVITAR SOBRECARGA HACIA LA VRAM
        lifebar.dispose();
    }
    

}

