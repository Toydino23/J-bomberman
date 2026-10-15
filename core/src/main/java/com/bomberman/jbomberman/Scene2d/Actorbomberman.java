package com.bomberman.jbomberman.Scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class Actorbomberman extends Actor implements Disposable{


    // Resolucion y ubicacion del bomberman
    public int X = 1185, Y = 620, resX = 1280, resY = 720;   

    public int lifebar = 3; // Vida del bomberman

    public int Bombscount = 1;

    public boolean MaxBombs = false;

    public int V = 5;
           
    // Algunos o varios posibles valores en el cual el bomberman no puede cruzar
    
    // Posicion en el eje X
    public int BlockX[] = {65, 70, 75, 80, 85, 90, 95, 100, 105, 110, 115,
                           140, 145, 150, 155, 160, 165, 170, 175, 180, 185, 190, 195, 200,
                           230, 235, 240, 245, 250, 255, 260, 265, 270, 275, 280, 285, 290,
                           310, 315, 320, 325, 330, 335, 340, 345, 350, 355, 360, 365, 370,
                           390, 395, 400, 405, 410, 415, 420, 425, 430, 435, 440, 445, 450,
                           470, 475, 480, 485, 490, 495, 500, 505, 510, 515, 520, 525, 530,
                           550, 555, 560, 565, 570, 575, 580, 585, 590, 595, 600, 605, 610,
                           640, 645, 650, 655, 660, 665, 670, 675, 680, 685, 690, 695, 700,
                           720, 725, 730, 735, 740, 745, 750, 755, 760, 765, 770, 775, 780,
                           800, 805, 810, 815, 820, 825, 830, 835, 840, 845, 850, 855, 860,
                           880, 885, 890, 895, 900, 905, 910, 915, 920, 925, 930, 935, 940,
                           965, 970, 975, 980, 985, 990, 995, 1000, 1005, 1010, 1015, 1020, 1025,
                           1050, 1055, 1060, 1065, 1070, 1075, 1080, 1085, 1090, 1095, 1100, 1105,
                           1130, 1135, 1140, 1145, 1150, 1155, 1160, 1165, 1170, 1175, 1180, 1185,

    };

    // Posicion en el eje Y
    public int BlockY[] = {600 ,595 ,590, 585 ,580, 575 ,570, 565 ,560, 555 ,550, 545 ,540, 
                           495, 490, 485, 480, 475, 470, 465, 460, 455, 450, 445, 440, 435,
                           385, 380, 375, 370, 365, 360, 355, 350, 345, 340, 335, 330, 325, 
                           275, 270, 265, 260, 255, 250, 245, 240, 235, 230, 225, 220, 215,
                           165, 160, 155, 150, 145, 140, 135, 130, 125, 120, 115, 110, 105,
    };

    public Boolean BOOL = false;

    // Textura y animacion, requerido para animar los movimientos del bomberman
    private Texture player;

    private TextureRegion[] bombermanFrames;

    private TextureRegion bombermaRegion;

    private Animation bombermanAnimation;

    public float duration = 0; // tal vez no util pero duracion de la animacion

    // Construccion, proceso completo de la creacion de la animacion del personaje
    public Actorbomberman(){

        player = new Texture("bomberman-sprite-3.png");
        
        // TextureRegion, es necesario hacer unos recorte de la imagen
        bombermaRegion = new TextureRegion(player, 0, 0, 286, 118);

        // Es necesario un arreglo bidimensional, se util para saber la cantidad de frames
        TextureRegion[][] temp = bombermaRegion.split((player.getWidth() / 4), player.getHeight()); 
        
        bombermanFrames = new TextureRegion[temp.length * temp[0].length];
        
        int indice = 0;
        for (int i = 0; i < temp.length; i++) {
           for (int j = 0; j < temp[i].length; j++) {
                bombermanFrames[indice++] = temp[i][j];
           } 
        }

        // Crea la animacion 
        bombermanAnimation = new Animation(0.3f, bombermanFrames);
   

    }

    @Override
    //Dibujo por pantalla
    public void draw(Batch batch, float parentAlpha){


        inputGame(batch);

        TextureRegion frame =  (TextureRegion) bombermanAnimation.getKeyFrame(duration+=Gdx.graphics.getDeltaTime(), true);
        
        batch.draw(frame, X, Y, 40, 55);

    }


    // Funcion de la animacion del bomberman, requerido debido a que cambia la animacion al moverse.
    private void AnimationBomberman(Texture playermv){

        TextureRegion[][] temp = TextureRegion.split(player, (player.getWidth() / 4), player.getHeight());
        
        bombermanFrames = new TextureRegion[temp.length * temp[0].length];
        
        int indice = 0;
        for (int i = 0; i < temp.length; i++) {
           for (int j = 0; j < temp[i].length; j++) {
                bombermanFrames[indice++] = temp[i][j];
           } 
        }

        bombermanAnimation = new Animation(0.3f, bombermanFrames);

    }


    // Funcion de deteccion de I/0
    private void inputGame(Batch batch){

        inputKeyboard(batch);
       


    }

    // Controles del juego
    private void inputKeyboard(Batch batch){

        boolean right = Gdx.input.isKeyPressed(Input.Keys.D)
                        || Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        boolean left = Gdx.input.isKeyPressed(Input.Keys.A) 
                        || Gdx.input.isKeyPressed(Input.Keys.LEFT);

        boolean up = Gdx.input.isKeyPressed(Input.Keys.W) 
                        || Gdx.input.isKeyPressed(Input.Keys.UP);

        boolean down = Gdx.input.isKeyPressed(Input.Keys.S) 
                        || Gdx.input.isKeyPressed(Input.Keys.DOWN);

        boolean canMove = (right != left != up != down); // Xor requerido para evitar movimientos no deseados

        if (canMove && right) {
            
            toMoveRight(batch);

        } else if (canMove && left) {
            
            toMoveLeft(batch);

        } else if (canMove && up) {
            
            toMoveUp(batch);

        } else if (canMove && down) {
            
            toMoveDown(batch);
        }
        


    }
    
    // Funciones de cuando el personaje se mueva a una direccion en espeecifico, cambia la animacion. 
    private void toMoveRight(Batch batch){


        AnimationBomberman(player = new Texture(Gdx.files.internal("bomberman-sprite-2.png")));
        
        TextureRegion frame =  (TextureRegion) bombermanAnimation.getKeyFrame(duration+=Gdx.graphics.getDeltaTime(), true);

        BOOL = false;

        if (X+V < resX - 70) {
            

            for (int j = 0; j < BlockY.length; j++) {
            
               if (BlockY[j] == Y+5) {
                
                    BOOL = true;

               }

            }

            if (!BOOL) {
               
                X+=V;

            }
            
                    
        }

        

            
        batch.draw(frame, X, Y, 40, 55);


        

    }

    private void toMoveLeft(Batch batch){

        AnimationBomberman(player = new Texture(Gdx.files.internal("bomberman-sprite-4.png")));

        TextureRegion frame =  (TextureRegion) bombermanAnimation.getKeyFrame(duration+=Gdx.graphics.getDeltaTime(), true);

        BOOL = false;

        if ((X-20) > 20) {
            

            
            for (int j = 0; j < BlockY.length; j++) {
            
               if (BlockY[j] == Y+5) {
                
                    BOOL = true;

               }

            }

            if (!BOOL) {
               
                X-=V;

            }
           
        }



            batch.draw(frame, X, Y, 40, 55);

        
    }

    private void toMoveUp(Batch batch){

        AnimationBomberman(player = new Texture(Gdx.files.internal("bomberman-sprite-1.png")));
        

        TextureRegion frame =  (TextureRegion) bombermanAnimation.getKeyFrame(duration+=Gdx.graphics.getDeltaTime(), true);

        if (Y+V < resY - 70) {
            
            for (int i = 0; i < BlockX.length; i++) {
            
               if (BlockX[i] == X+5) {
                
                    BOOL = true;

               }

            }

            if (!BOOL) {
               
                Y+=V;

            }
        }
        
        batch.draw(frame, X, Y, 40, 55);


    }

    private void toMoveDown(Batch batch){

        AnimationBomberman(player = new Texture(Gdx.files.internal("bomberman-sprite-3.png")));
        
        TextureRegion frame =  (TextureRegion) bombermanAnimation.getKeyFrame(duration+=Gdx.graphics.getDeltaTime(), true);

        BOOL = false;

        if (Y - 28  > 28) {
            
                
            for (int i = 0; i < BlockX.length; i++) {
            
               if (BlockX[i] == X+5) {
                
                    BOOL = true;

               }

            }

            if (!BOOL) {
               
                Y-=V;

            }


        }
            
            batch.draw(frame, X, Y, 40, 55);

        
    }

    
    @Override
    public void act(float delta){

       super.act(delta);        

    }

    // Obtencion de X, Y requerido para la colocacion de bombas
    @Override
    public float getX(){

        return X;

    }

    @Override
    public float getY(){

        return Y;

    }

    @Override
    public void dispose() {

        player.dispose();
    
    }
    

  public void increaseSpeed() {
   if (V < 10) {
       V = 10;
    }
}
    public void decreaseSpeed(){
   if (V > 5) {
        V = 5;
    }
}
    
    public Rectangle getBoundingBox() {
        return new Rectangle(X, Y, 40, 55);
    }
    
     public void increaseBombCount() {
        Bombscount=2;  // Incrementa la cantidad de bombas
    }
     
       public int getBombCount() {
        return Bombscount;  // Devuelve la cantidad actual de bombas
    }
       
       public void decreaseBombCount() {
        Bombscount=1;  // Decrementa la cantidad de bombas
    }
       public void increaseLifebar(){
        lifebar++;
        
     }
}
     
