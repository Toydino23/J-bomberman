package com.bomberman.jbomberman.deprecated;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bomberman.jbomberman.Bomberman;
import com.bomberman.jbomberman.PlayGameUI;

public class ScreenBomberman extends PlayGameUI{

    public int X = 100, Y = 100;    

    private Texture player;

    private TextureRegion[] bombermanFrames;

    private TextureRegion bombermaRegion;

    private Animation bombermanAnimation;

    public float duration = 0, durationbombs = 0;

    public ScreenBomberman(Bomberman game) {
        super(game);
        
    }

    @Override
    public void show(){

        player = new Texture("bomberman-sprite-3.png");
        
        bombermaRegion = new TextureRegion(player, 0, 0, 286, 118);

        TextureRegion[][] temp = bombermaRegion.split((player.getWidth() / 4), player.getHeight()); 
        
        bombermanFrames = new TextureRegion[temp.length * temp[0].length];
        
        int indice = 0;
        for (int i = 0; i < temp.length; i++) {
           for (int j = 0; j < temp[i].length; j++) {
                bombermanFrames[indice++] = temp[i][j];
           } 
        }

        bombermanAnimation = new Animation(0.1f, bombermanFrames);
    }

    @Override
    public void render(float delta){

        inputGame();
        
        

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);

        
        

        TextureRegion frame =  (TextureRegion) bombermanAnimation.getKeyFrame(duration+=delta, true);
         
        game.batch.begin();

            game.batch.draw(frame, X, Y, 40, 55);

        game.batch.end();
        
    }


    private void AnimationBomberman(Texture playermv){

        TextureRegion[][] temp = TextureRegion.split(player, (player.getWidth() / 4), player.getHeight());
        
        bombermanFrames = new TextureRegion[temp.length * temp[0].length];
        
        int indice = 0;
        for (int i = 0; i < temp.length; i++) {
           for (int j = 0; j < temp[i].length; j++) {
                bombermanFrames[indice++] = temp[i][j];
           } 
        }

        bombermanAnimation = new Animation(0.1f, bombermanFrames);

    }


    private void inputGame(){

        inputKeyboard();
       


    }

    private void inputKeyboard(){

        boolean right = Gdx.input.isKeyPressed(Input.Keys.D)
                        || Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        boolean left = Gdx.input.isKeyPressed(Input.Keys.A) 
                        || Gdx.input.isKeyPressed(Input.Keys.LEFT);

        boolean up = Gdx.input.isKeyPressed(Input.Keys.W) 
                        || Gdx.input.isKeyPressed(Input.Keys.UP);

        boolean down = Gdx.input.isKeyPressed(Input.Keys.S) 
                        || Gdx.input.isKeyPressed(Input.Keys.DOWN);

        boolean canMove = (right != left != up != down);

        if (canMove && right) {
            
            toMoveRight();

        } else if (canMove && left) {
            
            toMoveLeft();

        } else if (canMove && up) {
            
            toMoveUp();

        } else if (canMove && down) {
            
            toMoveDown();
        }
        


    }
    
    private void toMoveRight(){


        AnimationBomberman(player = new Texture(Gdx.files.internal("bomberman-sprite-2.png")));
        
        TextureRegion frame =  (TextureRegion) bombermanAnimation.getKeyFrame(duration+=Gdx.graphics.getDeltaTime(), true);

        game.batch.begin();

            X = X + 10;
            game.batch.draw(frame, X, Y, 40, 55);

        game.batch.end();
        

    }

    private void toMoveLeft(){

        AnimationBomberman(player = new Texture(Gdx.files.internal("bomberman-sprite-4.png")));
        

        TextureRegion frame =  (TextureRegion) bombermanAnimation.getKeyFrame(duration+=Gdx.graphics.getDeltaTime(), true);

        game.batch.begin();

            X = X - 10;
            game.batch.draw(frame, X, Y, 40, 55);

        game.batch.end();
    }

    private void toMoveUp(){

        AnimationBomberman(player = new Texture(Gdx.files.internal("bomberman-sprite-1.png")));
        

        TextureRegion frame =  (TextureRegion) bombermanAnimation.getKeyFrame(duration+=Gdx.graphics.getDeltaTime(), true);

        game.batch.begin();

            Y = Y + 5;
            game.batch.draw(frame, X, Y, 40, 55);

        game.batch.end();

    }

    private void toMoveDown(){

        AnimationBomberman(player = new Texture(Gdx.files.internal("bomberman-sprite-3.png")));
        

        TextureRegion frame =  (TextureRegion) bombermanAnimation.getKeyFrame(duration+=Gdx.graphics.getDeltaTime(), true);

        game.batch.begin();

            Y = Y - 5;
            game.batch.draw(frame, X, Y, 40, 55);

        game.batch.end();
    }

}
