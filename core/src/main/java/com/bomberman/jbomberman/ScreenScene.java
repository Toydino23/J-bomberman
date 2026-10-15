package com.bomberman.jbomberman;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import com.badlogic.gdx.utils.Array;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bomberman.jbomberman.Scene2d.ActorBackground;
import com.bomberman.jbomberman.Scene2d.ActorBombs;
import com.bomberman.jbomberman.Scene2d.ActorExplosion;
import com.bomberman.jbomberman.Scene2d.Actorbomberman;
import com.bomberman.jbomberman.Scene2d.Block;
import com.bomberman.jbomberman.Scene2d.BlockManager;
import com.bomberman.jbomberman.Scene2d.Lifebar;
import com.bomberman.jbomberman.Scene2d.PowerUp;
import com.bomberman.jbomberman.Scene2d.PowerUpManager;
import com.badlogic.gdx.Preferences;
 

// Clase donde se maneja todo el juego principal, utilizacion de Scene2d y actores.
public class ScreenScene extends PlayGameUI{

    int partidas;

    private Stage scenary; //Escenario donde se llevara a cabo el juego

    //Actores. Incluye el campo de juego y el mismo bomberman

    private Actor background, bombActor, bombActor2;
    private Actorbomberman bombermanActor;
    private ActorExplosion explosionActor, explosionActor2;
    private final PowerUpManager powerUpManager;
    private final BlockManager blockManager;
    private Array<Lifebar> lifebArray = new Array<>();
    private Array<Actor> bombAcotArray = new Array<>();

    // Needed Life Stuff
    private int Xlife = 0;
    
    //Timer stuff
    private int timer = 60;
    private float timeCount = 0;
    private boolean timeUp;
    private float elapse;
    private Label timeLabel;

    // Score stuff
    private static int scorev2 = 0;
    private Label scoreLabel;

    public GameMenu menu;

    // RunnableAction permite ejecutar bloque de codigo luego de que ocurra una sequenceAction/ParallelAction/Actions
    private RunnableAction run = new RunnableAction();
  
    private final Music song1;
        
    // Constructor 
    public ScreenScene(Bomberman game) {
        super(game);    
        this.game = game;
        
        // Load Scenary, background and Actors
        scenary = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(scenary);

        background = new ActorBackground();
        //background.addAction(Actions.hide());
        scenary.addActor(background);

        bombermanActor = new Actorbomberman();
        //bombermanActor.addAction(Actions.hide());
        scenary.addActor(bombermanActor);

        bombActor = new ActorBombs();
        bombActor2 = new ActorBombs();
        bombActor.addAction(Actions.hide());
        bombActor2.addAction(Actions.hide());
        scenary.addActor(bombActor);
        scenary.addActor(bombActor2);

        explosionActor = new ActorExplosion();
        explosionActor2 = new ActorExplosion();
        explosionActor.addAction(Actions.hide());
        explosionActor2.addAction(Actions.hide());
        scenary.addActor(explosionActor);
        scenary.addActor(explosionActor2);
        
        powerUpManager = new PowerUpManager(game.batch);
        blockManager = new BlockManager(game.batch);

        // Create the Timer text
        timeLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel.setAlignment(2);
        timeLabel.setPosition(640,710);
        timeLabel.setFontScale(2f, 2f);

        scenary.addActor(timeLabel);

	    // Create the Scorev2 text
        scoreLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel.setAlignment(2);
        scoreLabel.setPosition(1100, 710);
        scoreLabel.setFontScale(2f, 2f);

        scenary.addActor(scoreLabel);


        lifebArray.add(new Lifebar(Xlife), new Lifebar(Xlife+=50), new Lifebar(Xlife+=50));
     
        for (Lifebar lifebar : lifebArray) {
            
            scenary.addActor(lifebar);

        }

        bombAcotArray.add(new ActorBombs());
        bombAcotArray.get(bombermanActor.Bombscount - 1).addAction(Actions.moveTo(400, 665));;;

        for (Actor bombs : bombAcotArray) {
            
            scenary.addActor(bombs);
        
        }

          // Obtiene datos guardados
        Preferences prefs = Gdx.app.getPreferences("Bomberman");
        // Busca las partidas jugadas, si no tiene la coloca como 0 default
        this.partidas = prefs.getInteger("partidas",0);
        
        // Aumenta en 1 la cantidad de partidas jugadas y guarda la informacion
        partidas++;
        prefs.putInteger("partidas",partidas);
        prefs.flush();    
        
        // Musica de fondo
        song1 = Gdx.audio.newMusic(Gdx.files.internal("menu.ogg"));
        
         song1.setVolume(0.1f);
         song1.play();       
         song1.setLooping(true);
    }


    public int addScorev2(int value){

        return scorev2+=value;

    }

    public void addLifebArr(){

        if (bombermanActor.lifebar != lifebArray.size && (bombermanActor.lifebar > 0)) {
            
            lifebArray.add(new Lifebar(Xlife+=50));

            if (bombermanActor.lifebar >= 0) {
                scenary.addActor(lifebArray.get(bombermanActor.lifebar - 1));
            }
            
        
        }
  

    }

    public void addBombsArr(){

        if (bombermanActor.Bombscount == 2 && !bombermanActor.MaxBombs) {
            
            bombAcotArray.add(new ActorBombs());
            bombAcotArray.get(bombermanActor.Bombscount - 1).addAction(Actions.moveTo(440, 665));;
            scenary.addActor(bombAcotArray.get(bombermanActor.Bombscount - 1));
            bombermanActor.MaxBombs = true;

        }
        if (bombermanActor.Bombscount == 1 && bombermanActor.MaxBombs){

            bombAcotArray.get(bombermanActor.Bombscount).remove();
            bombermanActor.MaxBombs = false;

        }

    }

    // CountDown
    public void update(float delta){

        timeCount += delta;
        if (timeCount >= 1) {
            
            if (timer > 0) {
                timer--;
		        addScorev2(20);
            
            } else {

                timeUp= true;

            }


        
        timeLabel.setText("Tiempo restante: " + timer);
	    scoreLabel.setText("Score: " + scorev2);
        addLifebArr();
        addBombsArr();
        timeCount = 0;

        }

    }



    @Override
    // Renderiza o dibuja por pantalla
    public void render(float delta){

        Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Creacion del scenary
        scenary.draw();
        scenary.act();
        
        powerUpManager.render();
        powerUpManager.checkPowerUpCollected((Actorbomberman) bombermanActor);
        blockManager.render();
   
    
        // Create timer & Task
        Timer timer = new Timer();
        
        //Contador auxiliar
        elapse+= delta;
        
        Task task = new Task() {
            @Override
            public void run() {
            }
            
        };
        
        timer.schedule(task, 61); // Required
        update(delta); // CouuntDown
        
        if (elapse > 61) { //Determina cuando vovler al menu
            Preferences prefs = Gdx.app.getPreferences("Bomberman");
            this.partidas = prefs.getInteger("partidas completadas",0);
            partidas++;
            prefs.putInteger("partidas completadas",partidas);
            prefs.flush();
            game.setScreen(new GameMenu(game));
            song1.setLooping(false);
            song1.stop();
            this.dispose();
            
            
            game.setScreen(new GameMenu(game));
            song1.setLooping(false);
            song1.stop();
            this.dispose();
        }
                
        // Funcion para la colocacion de bombas
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && bombermanActor.lifebar != 0 && bombermanActor.Bombscount > 0  ) {
            
            
            

            // Funcion que se ejecuta luego de ocurrir seq
            run.setRunnable(new Runnable() {

                public void damage(){

                    // Animation's damage.
                    SequenceAction seqa = new SequenceAction();
                    for (int j = 0; j < 3; j++) {
                            
                        seqa.addAction(Actions.hide());
                        seqa.addAction(Actions.delay(0.3f));
                        seqa.addAction(Actions.show());
                        seqa.addAction(Actions.delay(0.3f));
                        bombermanActor.addAction(seqa); 

                    }

                    if (bombermanActor.lifebar > 0) {
                        
                        lifebArray.get(bombermanActor.lifebar - 1).remove();
                        Xlife-=50;
                        bombermanActor.lifebar--;
                    
                    }
                   
                    
                    
                    System.out.println(bombermanActor.lifebar);
                    // En caso de que el bomberman ya se le ha acabado la vida 
                    if (bombermanActor.lifebar == 0) {
                        
                        bombermanActor.addAction(Actions.sequence(Actions.delay(0.6f), Actions.hide()));

                    }

                }

                @Override
                // RunnableActions 
                public void run() {
                    
                    // Verificacion de posiciones sobre la explosion de la bomba
                   
                    if (bombermanActor.Bombscount == 1 || !bombermanActor.MaxBombs) {
                        for (int i = 0; i < 18; i++) {
                            if ((explosionActor.getY() - (5 * i) == bombermanActor.getY() 
                            || explosionActor.getY() + (5 * i) == bombermanActor.getY()) && explosionActor.getX() == bombermanActor.getX()) {
                                
                                damage();
        
                            }else if ((explosionActor.getX() - (5 * i) == bombermanActor.getX() 
                            || explosionActor.getX() + (5 * i) == bombermanActor.getX())  
                            && explosionActor.getY() == bombermanActor.getY()) {
                                
                                damage();
        
                            }
                            for (PowerUp powerUp : powerUpManager.getActivePowerUps()) {
                        if ((powerUp.getX() >= explosionActor.getX() - 100 && powerUp.getX() <= explosionActor.getX())
                        && (powerUp.getY() <= explosionActor.getY() + 20 && powerUp.getY() >= explosionActor.getY() - 20)) {
                            //System.out.println("has explotado un powerUp");
                            powerUp.respawn();
                        }
                        else if ((powerUp.getX() >= explosionActor.getX() && powerUp.getX() <= explosionActor.getX()+ 100)
                        && (powerUp.getY() <= explosionActor.getY() + 20 && powerUp.getY() >= explosionActor.getY() - 20)) {
                            //System.out.println("has explotado un powerUp");
                            powerUp.respawn();
                        }
                        else if((powerUp.getY() >= explosionActor.getY() - 100 && powerUp.getY() <= explosionActor.getY())
                        && (powerUp.getX() <= explosionActor.getX() + 20 && powerUp.getX() >= explosionActor.getX() - 20)) {
                             //System.out.println("has explotado un powerUp");
                            powerUp.respawn();
                        }
                         else if((powerUp.getY() >= explosionActor.getY() && powerUp.getX() <= explosionActor.getX() + 100)
                        && (powerUp.getX() <= explosionActor.getX() + 20 && powerUp.getX() >= explosionActor.getX() - 20)) {
                             //System.out.println("has explotado un powerUp");
                            powerUp.respawn();
                        }
                    }

                    // Verificar colisión con bloques
                     for (Block block : blockManager.getBlocks()) {
                         System.out.println("Block X = " + block.getX() +"block y = " + block.getY() + "Explosion = " + explosionActor.getX() + "Explosion Y = "+ explosionActor.getY());
                        if ((block.getX() >= explosionActor.getX() - 100 && block.getX() <= explosionActor.getX())
                        && (block.getY() <= explosionActor.getY() + 20 && block.getY() >= explosionActor.getY() - 20)) {
                            //System.out.println("has explotado un bloque");
                            block.respawn();
                        }
                        else if ((block.getX() >= explosionActor.getX() && block.getX() <= explosionActor.getX()+ 100)
                        && (block.getY() <= explosionActor.getY() + 20 && block.getY() >= explosionActor.getY() - 20)) {
                            //System.out.println("has explotado un bloque");
                            block.respawn();
                        }
                        else if((block.getY() >= explosionActor.getY() - 100 && block.getY() <= explosionActor.getY())
                        && (block.getX() <= explosionActor.getX() + 20 && block.getX() >= explosionActor.getX() - 20)) {
                             //System.out.println("has explotado un bloque");
                            block.respawn();
                        }
                         else if((block.getY() >= explosionActor.getY() && block.getX() <= explosionActor.getX() + 100)
                        && (block.getX() <= explosionActor.getX() + 20 && block.getX() >= explosionActor.getX() - 20)) {
                             //System.out.println("has explotado un bloque");
                            block.respawn();
                        }
                    }
                            
                        }
                        
                    }
                   

                    if (bombermanActor.Bombscount == 0) {
                        for (int i = 0; i < 18; i++) {
                            if ((explosionActor2.getX() - (5 * i) == bombermanActor.getX() 
                            || explosionActor2.getX() + (5 * i) == bombermanActor.getX())  
                            && explosionActor2.getY() == bombermanActor.getY()) {
                            
                            damage();
    
                        }else if ((explosionActor2.getY() - (5 * i) == bombermanActor.getY() 
                        || explosionActor2.getY() + (5 * i) == bombermanActor.getY()) && explosionActor2.getX() == bombermanActor.getX()) {
                            
                            damage();
                        }}
                        for (PowerUp powerUp : powerUpManager.getActivePowerUps()) {
                        if ((powerUp.getX() >= explosionActor2.getX() - 100 && powerUp.getX() <= explosionActor2.getX())
                        && (powerUp.getY() <= explosionActor2.getY() + 20 && powerUp.getY() >= explosionActor2.getY() - 20)) {
                            //System.out.println("has explotado un powerUp");
                            powerUp.respawn();
                        }
                        else if ((powerUp.getX() >= explosionActor2.getX() && powerUp.getX() <= explosionActor2.getX()+ 100)
                        && (powerUp.getY() <= explosionActor2.getY() + 20 && powerUp.getY() >= explosionActor2.getY() - 20)) {
                            //System.out.println("has explotado un powerUp");
                            powerUp.respawn();
                        }
                        else if((powerUp.getY() >= explosionActor2.getY() - 100 && powerUp.getY() <= explosionActor2.getY())
                        && (powerUp.getX() <= explosionActor2.getX() + 20 && powerUp.getX() >= explosionActor2.getX() - 20)) {
                             //System.out.println("has explotado un powerUp");
                            powerUp.respawn();
                        }
                         else if((powerUp.getY() >= explosionActor2.getY() && powerUp.getX() <= explosionActor2.getX() + 100)
                        && (powerUp.getX() <= explosionActor2.getX() + 20 && powerUp.getX() >= explosionActor2.getX() - 20)) {
                             //System.out.println("has explotado un powerUp");
                            powerUp.respawn();
                        }
                    }

                    // Verificar colisión con bloques
                     for (Block block : blockManager.getBlocks()) {
                         System.out.println("Block X = " + block.getX() +"block y = " + block.getY() + "Explosion = " + explosionActor.getX() + "Explosion Y = "+ explosionActor.getY());
                        if ((block.getX() >= explosionActor2.getX() - 100 && block.getX() <= explosionActor2.getX())
                        && (block.getY() <= explosionActor2.getY() + 20 && block.getY() >= explosionActor2.getY() - 20)) {
                            //System.out.println("has explotado un bloque");
                            block.respawn();
                        }
                        else if ((block.getX() >= explosionActor2.getX() && block.getX() <= explosionActor2.getX()+ 100)
                        && (block.getY() <= explosionActor2.getY() + 20 && block.getY() >= explosionActor2.getY() - 20)) {
                            //System.out.println("has explotado un bloque");
                            block.respawn();
                        }
                        else if((block.getY() >= explosionActor2.getY() - 100 && block.getY() <= explosionActor2.getY())
                        && (block.getX() <= explosionActor2.getX() + 20 && block.getX() >= explosionActor2.getX() - 20)) {
                             //System.out.println("has explotado un bloque");
                            block.respawn();
                        }
                         else if((block.getY() >= explosionActor2.getY() && block.getX() <= explosionActor2.getX() + 100)
                        && (block.getX() <= explosionActor2.getX() + 20 && block.getX() >= explosionActor2.getX() - 20)) {
                             //System.out.println("has explotado un bloque");
                            block.respawn();
                        }
                    }
                        
                    }
                    
                    


                   
                    

                    
                       /* Dont do it!
                        seqa.addAction(Actions.hide());
                        seqa.addAction(Actions.delay(0.1f));
                        seqa.addAction(Actions.show());
                        seqa.addAction(Actions.delay(0.1f));
                        seqa.addAction(Actions.hide());
                        seqa.addAction(Actions.delay(0.1f));
                        seqa.addAction(Actions.show());
                        seqa.addAction(Actions.delay(0.1f));
                        seqa.addAction(Actions.hide());
                        seqa.addAction(Actions.delay(0.1f));
                        seqa.addAction(Actions.show());*/

                    
 
        


                    bombermanActor.Bombscount++;
                
                      

                
                

             
                
            }});

            if (bombermanActor.Bombscount == 2) {
                
                        // Parallel's bombs
                        bombActor2.addAction(Actions.moveTo(bombermanActor.getX(), bombermanActor.getY()));
                        ParallelAction par = new SequenceAction();
                        par.addAction(Actions.show());
                        par.addAction(Actions.delay(1f));
                        par.addAction(Actions.hide());
                        
                        bombActor2.addAction(Actions.sequence(par, run));   
            
            
                            
                        
                        // Sequence's explosion
                        explosionActor2.addAction(Actions.moveTo(bombermanActor.getX(), bombermanActor.getY()));
                        SequenceAction seq2 = new SequenceAction();
                        seq2.addAction(Actions.delay(1f));
                        seq2.addAction(Actions.show());
                        seq2.addAction(Actions.delay(0.5f));
                        seq2.addAction(Actions.hide());
            
                        explosionActor2.addAction(seq2);
            }


            if (bombermanActor.Bombscount == 1) {

                    // Parallel's bombs
                    bombActor.addAction(Actions.moveTo(bombermanActor.getX(), bombermanActor.getY()));
                    ParallelAction par = new SequenceAction();
                    par.addAction(Actions.show());
                    par.addAction(Actions.delay(1f));
                    par.addAction(Actions.hide());
            
                    bombActor.addAction(Actions.sequence(par, run));   


                
            
                    // Sequence's explosion
                    explosionActor.addAction(Actions.moveTo(bombermanActor.getX(), bombermanActor.getY()));
                    SequenceAction seq2 = new SequenceAction();
                    seq2.addAction(Actions.delay(1f));
                    seq2.addAction(Actions.show());
                    seq2.addAction(Actions.delay(0.5f));
                    seq2.addAction(Actions.hide());

                    explosionActor.addAction(seq2);
                
            }

            bombermanActor.Bombscount--;

        }

    

    


    }

    @Override

    // Resize "doesnt work"
    public void resize(int width, int height){

        scenary.getViewport().update(width, height);
        background.setSize(width, height);

    }
}