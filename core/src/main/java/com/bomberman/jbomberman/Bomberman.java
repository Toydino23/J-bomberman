package com.bomberman.jbomberman;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Bomberman extends Game {
    
    public SpriteBatch batch;
    
    public PlayGameUI table;
    public PlayGameUI player;
    public PlayGameUI bombs;
    public PlayGameUI scene;
    public GameMenu menu;
    
    Sound bomba;
    
    // funcion main, algunas variables han sido puesta en anotacion debido ha que hubieron cambios.
    @Override
    public void create() {

        batch = new SpriteBatch();
        
        bomba = Gdx.audio.newSound(Gdx.files.internal("bombagaru.mp3"));
        bomba.play(0.5f);
        //table = new ScreenTable(this); 
        //player = new ScreenBomberman(this);
        //scene = new ScreenScene(this);
        menu = new GameMenu(this);
        
        setScreen(menu);
     

    }




}