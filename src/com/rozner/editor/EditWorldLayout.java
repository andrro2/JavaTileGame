package com.rozner.editor;

import com.rozner.game.tile.Tile;
import com.rozner.worlds.WorldManager;

public class EditWorldLayout {

    private WorldManager worldManager = WorldManager.getInstance();

    public void replaceTileInPosition(int x, int y, boolean isSolid) {
        System.out.println(isSolid);
        if (worldManager.getCurrentWorld() != null) {
            int yPosition = y / Tile.TILE_WIDTH;
            int xPosition = x / Tile.TILE_WIDTH;
            if (xPosition > worldManager.getCurrentWorld().getWidth() || yPosition > worldManager.getCurrentWorld().getHeight()) {
                return;
            }
            int[][] layout = worldManager.getCurrentWorld().getWorldLayout();
            boolean[][] solidTiles = worldManager.getCurrentWorld().getSolidTiles();

            if (Tile.currentTile != null) {
                layout[xPosition][yPosition] = Tile.currentTile.getId();
                solidTiles[xPosition][yPosition] = isSolid;
            }
            worldManager.getCurrentWorld().setWorldLayout(layout);
        }
    }
}
