/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bomberman.jbomberman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author User
 */
public class AcercaPantalla implements Screen{
    private Texture backgroundacerca;
    private GameMenu menu;
    
    Bomberman game;

    public AcercaPantalla(Bomberman game) {
        this.game = game;
        backgroundacerca = new Texture("backgroundAcerca.png");
        menu = new GameMenu(game);
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  
        game.batch.begin();                           
        
        renderbackground();
        
        game.batch.end();
        
       if (Gdx.input.isTouched()){
            game.setScreen(menu);
        }
              
    }
    
    public void renderbackground(){
        game.batch.draw(backgroundacerca, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }
    
    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
    
    
}
