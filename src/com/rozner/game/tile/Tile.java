package com.rozner.game.tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    //Statics

    public static Tile[] tiles = new Tile[1065];
    public static  Tile grassTile = new GrassTile(0);
    public static Tile currentTile;


    // Class

    protected BufferedImage texture;
    protected final int id;

    public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick(){

    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);

    }

    public boolean isSolid(){
        return false;
    }

    public int getId(){
        return id;
    }

    public BufferedImage getTexture() {
        return texture;
    }
}
