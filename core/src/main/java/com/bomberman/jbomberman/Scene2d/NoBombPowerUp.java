package com.bomberman.jbomberman.Scene2d;

import com.badlogic.gdx.graphics.Texture;

public class NoBombPowerUp extends PowerUp {
  
    public NoBombPowerUp(Texture texture) {
        super(texture);
        setSize(40, 55);
    }

    @Override
    public void applyPowerUp(Actorbomberman player) {
       
      player.decreaseBombCount();
      respawn();
    }
}