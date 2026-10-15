/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bomberman.jbomberman;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
/**
 *
 * @author User
 */
public class AyudaPantalla implements Screen{
    private Texture backgroundAyud;
    private GameMenu menu;
    
    Bomberman game;        
    
    public AyudaPantalla(Bomberman game){
        this.game = game;
        backgroundAyud = new Texture("backgroundAyuda.png");
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
        game.batch.draw(backgroundAyud, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
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
