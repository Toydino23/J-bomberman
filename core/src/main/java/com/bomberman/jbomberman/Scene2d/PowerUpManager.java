package com.bomberman.jbomberman.Scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class PowerUpManager {
    private static final int NUM_POWER_UPS = 10;
    private PowerUp[] powerUps;
    private SpriteBatch batch;


    public PowerUpManager(SpriteBatch batch) {
        this.batch = batch;
        powerUps = new PowerUp[NUM_POWER_UPS];
        initPowerUps();
    }

    public PowerUp[] getActivePowerUps() {
        return powerUps;
    }
    

  public void initPowerUps() {
        Texture speedPowerUpTexture = new Texture("speed_powerup.png");
        Texture clogSandalsTexture = new Texture("clog_sandals.png");
        Texture extraBombTexture = new Texture("ExtraBomb_PowerUp.png");
        Texture extraHpTexture = new Texture("ExtraHp_PowerUp.png");
        Texture NoBombTexture = new Texture("-Bomb.jpg");
        Texture[] powerUpTextures = {speedPowerUpTexture, extraBombTexture, clogSandalsTexture, extraHpTexture, NoBombTexture};

    for (int i = 0; i < NUM_POWER_UPS; i++) {
        int randomIndex = MathUtils.random(powerUpTextures.length - 1);
        Texture selectedTexture = powerUpTextures[randomIndex];

        if (selectedTexture == speedPowerUpTexture) {
            powerUps[i] = new SpeedPowerUp(selectedTexture);
        } else if (selectedTexture == extraBombTexture) {
            powerUps[i] = new ExtraBombPowerUp(selectedTexture);
        } else if (selectedTexture == clogSandalsTexture) {
            powerUps[i] = new ClogSandalsPowerUp(selectedTexture);
        } else if (selectedTexture == extraHpTexture) {
            powerUps[i] = new ExtraHpPowerUp(selectedTexture);
        } else if (selectedTexture == NoBombTexture) {
            powerUps[i] = new NoBombPowerUp(selectedTexture);
        }
    }
}
    public void render() {
        batch.begin();
        for (PowerUp powerUp : powerUps) {
            powerUp.render(batch);
            
        }
        batch.end();
    }

  public void checkPowerUpCollected(Actorbomberman player) {
    for (PowerUp powerUp : powerUps) {
        if (powerUp instanceof SpeedPowerUp && powerUp.getBoundingBox().overlaps(player.getBoundingBox())) {
             Gdx.app.log("PowerUp", "Speed PowerUp collected!");
            ((SpeedPowerUp) powerUp).applyPowerUp(player);
        }
         if (powerUp instanceof NoBombPowerUp && powerUp.getBoundingBox().overlaps(player.getBoundingBox())) {
             Gdx.app.log("PowerUp", "-BombPowerUp collected!");
            ((NoBombPowerUp) powerUp).applyPowerUp(player);
         }
          if (powerUp instanceof ExtraHpPowerUp && powerUp.getBoundingBox().overlaps(player.getBoundingBox())) {
             Gdx.app.log("PowerUp", "ExtraHpPowerUp collected!");
            ((ExtraHpPowerUp) powerUp).applyPowerUp(player);
          }
           if (powerUp instanceof ExtraBombPowerUp && powerUp.getBoundingBox().overlaps(player.getBoundingBox())) {
             Gdx.app.log("PowerUp", "ExtraBombPowerUp collected!");
            ((ExtraBombPowerUp) powerUp).applyPowerUp(player);
           }
            if (powerUp instanceof ClogSandalsPowerUp && powerUp.getBoundingBox().overlaps(player.getBoundingBox())) {
             Gdx.app.log("PowerUp", "ClogSandalsPowerUp collected!");
            ((ClogSandalsPowerUp) powerUp).applyPowerUp(player);
            }
    }
  }
  
}