package com.bomberman.jbomberman.Scene2d;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class ExtraBombPowerUp extends PowerUp {
    

    public ExtraBombPowerUp(Texture texture) {
        super(texture);
       setSize(40, 55);
    }

    @Override
    public void applyPowerUp(Actorbomberman player) {
        // Aplicar el efecto de obtener una bomba adicional al jugador
         player.increaseBombCount();
         respawn();
    }

 
}