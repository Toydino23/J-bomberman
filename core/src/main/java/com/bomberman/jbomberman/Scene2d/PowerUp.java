package com.bomberman.jbomberman.Scene2d;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class PowerUp extends Actor{
    protected int x;
    protected int y;
    protected Texture texture;
  
    private int[] powerUpLocationsX = {40, 80, 120, 150, 205, 245, 285, 330, 370, 410, 450, 490, 530, 575, 615, 655, 695, 740, 780, 820, 860, 900, 945, 985, 1025, 1065, 1105, 1150, 1190};
    private int[] powerUpLocationsY = {55, 110, 170,225,280,335,390,450,505,560,620};
    private int[] powerUpLocationsYimpar = {55,170,280,390,505,615};
    
 public PowerUp(Texture texture) {
        this.texture = texture;
        respawn();
    }

        public void respawn(){
            int randomIndex = MathUtils.random(powerUpLocationsX.length - 1);
            // Obtén una coordenada X aleatoria
            x = powerUpLocationsX[randomIndex];

            // Obtén una coordenada Y aleatoria según si es fila par o impar
            if (randomIndex % 2 == 0) {
              y = getRandomCoordinate(powerUpLocationsY);
            } else {
              y = getRandomCoordinate(powerUpLocationsYimpar);
            }
     
        // Actualizar las coordenadas del PowerUp
        setPosition(x, y);
        
     }    
        
     private int getRandomCoordinate(int[] possibleCoordinates) {
        int randomIndex = MathUtils.random(possibleCoordinates.length - 1);
        return possibleCoordinates[randomIndex];
    }

   public abstract void applyPowerUp(Actorbomberman player);

   public void render(SpriteBatch batch) {
    batch.draw(texture, x, y, 40, 55);
    //System.out.println("Rendering power-up at: x=" + x + ", y=" + y);
}


    public Rectangle getBoundingBox() {
        return new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }
}