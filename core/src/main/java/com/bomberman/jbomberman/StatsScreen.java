/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bomberman.jbomberman;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;

/**
 *
 * @author User
 */
public class StatsScreen implements Screen{
    private Texture backgroundStats;
    private GameMenu menu;
    Bomberman game;
    int partidas,completadas;
    private BitmapFont font;
    private String user;
    
    public StatsScreen(Bomberman game){

        this.game = game;
        backgroundStats = new Texture("backgroundStats.png");
        menu = new GameMenu(game);
        // Obtiene datos guardados
        Preferences prefs = Gdx.app.getPreferences("Bomberman");
        // Busca las partidas jugadas, si no tiene la coloca como 0 default
        this.partidas = prefs.getInteger("partidas",0);
        this.user = prefs.getString("usuario");
        this.completadas = prefs.getInteger("partidas completadas",0);
        
        font = new BitmapFont();
    }
    
    @Override
    public void show() {
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();                           
        
        renderbackground();
        
        GlyphLayout partidasLayout = new GlyphLayout(font,"Partidas Jugadas: "+partidas, Color.BLACK, 0, Align.left, false);
        GlyphLayout nombreLayout = new GlyphLayout(font,"Usuario: "+user, Color.BLACK, 0, Align.left, false);
        GlyphLayout CompletadasLayout = new GlyphLayout(font,"Partidas completadas: "+completadas, Color.BLACK, 0, Align.left, false);
        font.draw(game.batch, partidasLayout, 200,500);
        font.draw(game.batch, nombreLayout, 200, 520);
        font.draw(game.batch, CompletadasLayout, 200, 480);
        game.batch.end();
        
       if (Gdx.input.isTouched()){
            game.setScreen(menu);
        }
            
    }

    public void renderbackground(){
        game.batch.draw(backgroundStats, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
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
