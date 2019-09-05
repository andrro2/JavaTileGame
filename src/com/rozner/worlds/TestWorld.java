package com.rozner.worlds;

import com.rozner.game.gfx.Assets;
import com.rozner.game.tile.Tile;

import java.util.List;

public class TestWorld {

    private int width, height;
    private String worldName;
    private String worldPath;
    private String tileTexturePAth;
    private int playerSpawnX, playerSpawnY;
    private List<Tile> tiles;
    private int[][] worldLayout;

    private Tile baseTile = Tile.grassTile;


    public TestWorld(){

    }

    public TestWorld(String worldName, int width, int height){
        this.worldName = worldName;
        this.height = height;
        this.width = width;
        worldLayout = new int[height][width];
    }



    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public String getWorldPath() {
        return worldPath;
    }

    public void setWorldPath(String worldPath) {
        this.worldPath = worldPath;
    }

    public String getTileTexturePAth() {
        return tileTexturePAth;
    }

    public void setTileTexturePAth(String tileTexturePAth) {
        this.tileTexturePAth = tileTexturePAth;
    }

    public int getPlayerSpawnX() {
        return playerSpawnX;
    }

    public void setPlayerSpawnX(int playerSpawnX) {
        this.playerSpawnX = playerSpawnX;
    }

    public int getPlayerSpawnY() {
        return playerSpawnY;
    }

    public void setPlayerSpawnY(int playerSpawnY) {
        this.playerSpawnY = playerSpawnY;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public int[][] getWorldLayout() {
        return worldLayout;
    }

    public void setWorldLayout(int[][] worldLayout) {
        this.worldLayout = worldLayout;
    }

    public Tile getBaseTile() {
        return baseTile;
    }

    public void setBaseTile(Tile baseTile) {
        this.baseTile = baseTile;
    }
}
