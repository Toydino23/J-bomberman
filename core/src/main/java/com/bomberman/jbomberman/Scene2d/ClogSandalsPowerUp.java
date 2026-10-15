package com.bomberman.jbomberman.Scene2d;

import com.badlogic.gdx.graphics.Texture;

public class ClogSandalsPowerUp extends PowerUp {
   

    public ClogSandalsPowerUp(Texture texture){
        super(texture);
        setSize(40, 55);
    }

    @Override
    public void applyPowerUp(Actorbomberman player) {
        // Aplica el debuff reduciendo la velocidad del jugador
       player.decreaseSpeed(); 
       respawn();
    }
}