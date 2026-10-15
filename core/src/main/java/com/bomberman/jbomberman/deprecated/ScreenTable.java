package com.bomberman.jbomberman.deprecated;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.bomberman.jbomberman.Bomberman;
import com.bomberman.jbomberman.PlayGameUI;

public class ScreenTable extends PlayGameUI {

    private Texture table;


    public ScreenTable(Bomberman game) {
        super(game);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void show(){

        table = new Texture("Playfield.png");

    }

    @Override
    public void render(float delta){

        game.batch.begin();

        game.batch.draw(table, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        game.batch.end();

    }

}
