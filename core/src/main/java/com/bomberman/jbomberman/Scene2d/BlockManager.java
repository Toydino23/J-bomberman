package com.bomberman.jbomberman.Scene2d;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class BlockManager {

    private Array<Block> blocks;
    private SpriteBatch batch;
    
    public BlockManager(SpriteBatch batch) {
        this.batch = batch;
        blocks = new Array<>();
        generateBlocks();
    }

    private void generateBlocks() {
        Texture blockTexture = new Texture("Block.png");
        // Genera bloques en posiciones aleatorias usando las coordenadas ya definidas
        int numBlocks = 20; // Cambia según sea necesario
        for (int i = 0; i < numBlocks; i++) {
            Block block = new Block(blockTexture);
            blocks.add(block);
        }
    }

    public void render() {      
        batch.begin();
        for (Block block : blocks) {
            block.render(batch); 
        }
        batch.end();
    }

    public Array<Block> getBlocks() {
        return blocks;
    }

      public boolean checkCollision(Actorbomberman player) {
        for (Block block : blocks) {
            if (player.getBoundingBox().overlaps(block.getBoundingBox())) {
                // Colisión detectada
                return true;
            }
        }
        return false;
    }
    
}