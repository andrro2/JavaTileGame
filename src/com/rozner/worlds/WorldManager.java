package com.rozner.worlds;

import com.rozner.game.Handler;
import com.rozner.game.tile.Tile;
import com.rozner.utils.XmlEncodrerDecoder;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WorldManager {

    private final String worldDirectory = "res/worlds/";
    private static WorldManager instance = null;
    private List<TestWorld> worlds;
    private TestWorld currentWorld = null;
    private Handler handler;


    private WorldManager() {
        worlds = new ArrayList<>();
    }

    public static WorldManager getInstance() {
        if (instance == null) {
            instance = new WorldManager();
        }
        return instance;
    }

    public void tick(){

    }

    public void render(Graphics g) {
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(currentWorld.getWidth(), (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(currentWorld.getHeight(), (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));


            }
        }

    }

    public void saveWorld(){
        XmlEncodrerDecoder decoder = new XmlEncodrerDecoder();
        String path = worldDirectory + currentWorld.getWorldName() + ".xml";
        decoder.DecodeObjectToXml(path, currentWorld);
    }

    public TestWorld loadWorld(String path){
        XmlEncodrerDecoder encoder = new XmlEncodrerDecoder();
        TestWorld world = encoder.deserializeFromXML(path);
        System.out.println(world.getWorldName());
        return  world;
    }

    public void addToWorldsList(TestWorld world){
        worlds.add(world);
    }

    public List<TestWorld> getWorlds() {
        return worlds;
    }

    public void setWorlds(List<TestWorld> worlds) {
        this.worlds = worlds;
    }

    public TestWorld getCurrentWorld() {
        /*if(currentWorld == null){
            currentWorld = worlds.get(0);

        }*/
        return currentWorld;
    }

    public void setCurrentWorld(TestWorld currentWorld) {
        this.currentWorld = currentWorld;
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= currentWorld.getWidth() || y >= currentWorld.getHeight())
            return Tile.grassTile;


        Tile tile = Tile.tiles[currentWorld.getWorldLayout()[x][y]];
        if (tile == null) {
            return Tile.grassTile;
        }

        return tile;
    }
}


