package com.rozner.game.gfx;

import com.rozner.game.tile.Tile;
import com.rozner.utils.ImageLoader;
import com.rozner.utils.SpriteSheet;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage grass;
    public static BufferedImage[] playerStillLeft = new BufferedImage[5];
    public static BufferedImage[] playerStillRight = new BufferedImage[5];
    public static BufferedImage[] playerMoveAnimationLeft = new BufferedImage[8];
    public static BufferedImage[] playerMoveAnimationRight = new BufferedImage[8];

    private static final int tileWidth = 32, tileHeight = 32;
    private static final int playerWidth = 96, playerHeight = 96;


    public static void init(){

        SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tiles.png"));
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));


        for(int i = 0; i<=4; i++){
            playerStillRight[i] = playerSheet.crop(playerWidth*i,0, playerWidth, playerHeight);
        }

        for(int i = 0; i<=4; i++){
            playerStillLeft[i] = playerSheet.crop(playerWidth*i,playerHeight*10, playerWidth, playerHeight);
        }

        for(int i = 0; i<=7;i++){
            playerMoveAnimationLeft[i] = playerSheet.crop(playerWidth*i, playerHeight*11,playerWidth,playerHeight);
        }

        for(int i = 0; i<=7;i++){
            playerMoveAnimationRight[i] = playerSheet.crop(playerWidth*i, playerHeight,playerWidth,playerHeight);
        }


        grass = tileSheet.crop(0,0,tileWidth, tileHeight);
        int counter = 1;
        System.out.println(tileSheet.getAllTextures().size());
        for(BufferedImage subimage : tileSheet.getAllTextures()){
            new Tile(subimage, counter);
            counter++;
        }



    }

}
