package com.bomberman.jbomberman.Scene2d;

import com.badlogic.gdx.graphics.Texture;

public class ExtraHpPowerUp extends PowerUp {
 
 public ExtraHpPowerUp(Texture texture) {
        super(texture);
        setSize(40, 55);
    }

    @Override
    public void applyPowerUp(Actorbomberman player) {
        // Lógica para aplicar el power-up de vida extra al jugador
        player.increaseLifebar();
        respawn();
    }
}