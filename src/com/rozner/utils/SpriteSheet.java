package com.rozner.utils;

import com.rozner.game.tile.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;

    }

    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x,y,width,height);
    }

    public List<BufferedImage> getAllTextures(){
        List<BufferedImage> textures = new ArrayList<>();
        for(int height = 0; height<sheet.getHeight(); height += Tile.TILE_HEIGHT){
            for (int width = 0; width<sheet.getWidth(); width += Tile.TILE_WIDTH){
                textures.add(sheet.getSubimage(width, height, Tile.TILE_WIDTH, Tile.TILE_HEIGHT));
            }
        }
        return textures;
    }
}
