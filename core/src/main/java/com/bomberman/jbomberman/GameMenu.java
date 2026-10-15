/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bomberman.jbomberman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;


/**
 *
 * @author User
 */
public class GameMenu implements Screen,TextInputListener{
    
    //Constantes de altua y ancho de los botones
    private static final int playbutton_ancho = 400;
    private static final int playbutton_alto = 150;    
        
    Bomberman game;
    
    private String nombre;
    String text;
    //Tecturas de botones y fondo
    private Texture playtButtonActive;
    private Texture playButtonInactive;
    private Texture backgroundMenu;
    private Texture ayudaButtonActive;
    private Texture ayudaButtonInactive;
    private Texture statButtonActive;
    private Texture statButtonInactive;
    private Texture acercaButtonActive;
    private Texture acercaButtonInactive;
    
    //Obtejos de panatallas
    private ScreenScene scene;    
    private AyudaPantalla ayuda;
    private StatsScreen stats;
    private AcercaPantalla acerca;
    
    //Musica y efectos de sonido
    Music song1;    
    Sound OK;
    Sound gaaru;
    Sound Okcat;
    
    public GameMenu (Bomberman game){
        this.game = game;
        //Colocar valores a las texturas
        backgroundMenu = new Texture("backgroundMenu.png");
        playtButtonActive = new Texture("JUGARactive.png");
        playButtonInactive = new Texture("JUGARinactive.png");  
        ayudaButtonActive = new Texture("AYUDAactive.png");
        ayudaButtonInactive = new Texture("AYUDAinactive.png");
        statButtonActive = new Texture("STATSactive.png");
        statButtonInactive = new Texture("STATSinactive.png");
        acercaButtonActive = new Texture("ACERCAactive.png");
        acercaButtonInactive = new Texture("ACERCAinactive.png");
        
        Preferences prefs = Gdx.app.getPreferences("Bomberman");
        this.nombre = prefs.getString("usuario","prueba");  
                prefs.putString("usuario",nombre);
                prefs.flush();
                     
        
        //Colocar valores a la musica y efectos      
        song1 = Gdx.audio.newMusic(Gdx.files.internal("menu.ogg"));
        OK = Gdx.audio.newSound(Gdx.files.internal("OK.mp3"));
        gaaru = Gdx.audio.newSound(Gdx.files.internal("BOMBAGAARRU.mp3"));
        Okcat = Gdx.audio.newSound(Gdx.files.internal("OK NYA.mp3"));
       
            song1.setVolume(0.1f);
            song1.play();       
            song1.setLooping(true);
        
    }
    
    @Override
    public void show() {

    }
            
    
    @Override
    public void render(float f) { 
        
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();                                  
        
        renderbackground();        
                      
        //boton de inicio de juego
        if(Gdx.input.getX()>800 && Gdx.input.getX()<1200 && Gdx.input.getY()>69 && Gdx.input.getY()<219){
        game.batch.draw(playButtonInactive, 800, 500,playbutton_ancho,playbutton_alto);
        if(Gdx.input.isTouched()){             
            gaaru.play(0.5f);
            song1.setLooping(false);
            song1.stop();
            scene = new ScreenScene(game);
            game.setScreen((scene));
            this.dispose();
        }
        } else {
        game.batch.draw(playtButtonActive,800,500,playbutton_ancho,playbutton_alto);
        }
        
        //boton de ayuda
        if(Gdx.input.getX()>800 && Gdx.input.getX()<1200 && Gdx.input.getY()>230 && Gdx.input.getY()<380){
        game.batch.draw(ayudaButtonInactive, 800, 340,playbutton_ancho,playbutton_alto);
        if(Gdx.input.isTouched()){     
            ayuda = new AyudaPantalla(game);
            OK.play(0.5f);
            song1.stop();
            game.setScreen((ayuda));
            this.dispose();
        }
        } else {
        game.batch.draw(ayudaButtonActive,800,340,playbutton_ancho,playbutton_alto);
        }
        
        //boton de estadisticas
        if(Gdx.input.getX()>800 && Gdx.input.getX()<1200 && Gdx.input.getY()>389 && Gdx.input.getY()<539){
        game.batch.draw(statButtonInactive, 800, 180,playbutton_ancho,playbutton_alto);
        if(Gdx.input.isTouched()){     
            stats = new StatsScreen(game);
            OK.play(0.5f);
            song1.stop();
            game.setScreen((stats));
            this.dispose();
        }
        } else {
        game.batch.draw(statButtonActive,800,180,playbutton_ancho,playbutton_alto);
        }
        
        //boton de acerca de
        if(Gdx.input.getX()>800 && Gdx.input.getX()<1200 && Gdx.input.getY()>550 && Gdx.input.getY()<704){
        game.batch.draw(acercaButtonInactive, 800, 20,playbutton_ancho,playbutton_alto);
        if(Gdx.input.isTouched()){     
            acerca = new AcercaPantalla(game);
            OK.play(0.5f);
            song1.stop();
            game.setScreen((acerca));
            this.dispose();
        }
        } else {
        game.batch.draw(acercaButtonActive,800,20,playbutton_ancho,playbutton_alto);
        }
        
        //for a friend :)
        if(Gdx.input.getX()>150 && Gdx.input.getX()<380 && Gdx.input.getY()>0 && Gdx.input.getY()<250){
        if(Gdx.input.isTouched()){     
            backgroundMenu = new Texture("Friend.png");
            Okcat.play(0.5f);
        }        
        }
        
        game.batch.end();

    }

    public void renderbackground(){
        game.batch.draw(backgroundMenu, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
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

    @Override
    public void input(String string) {
        this.text=text;
    }

    @Override
    public void canceled() {
     text="default";
    }

}
