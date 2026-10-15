package com.bomberman.jbomberman.Scene2d;

import com.badlogic.gdx.graphics.Texture;

public class SpeedPowerUp extends PowerUp {
    private static final float WIDTH = 40f;
    private static final float HEIGHT = 55f;

   public SpeedPowerUp(Texture texture) {
        super(texture);
        setSize(40, 55);
    }
   
    @Override
    public void applyPowerUp(Actorbomberman player) {
        // Aplicar el efecto de aumento de velocidad al jugador
        player.increaseSpeed();
        respawn();
    }
    
   
}
