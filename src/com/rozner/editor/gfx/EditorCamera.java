package com.rozner.editor.gfx;

import com.rozner.editor.EditorHandler;
import com.rozner.editor.input.EditorKeyManager;
import com.rozner.game.tile.Tile;

public class EditorCamera{

    private float xOffset, yOffset;
    private EditorHandler editorHandler;
    private EditorKeyManager editorKeyManager;
    private float x, y;

    public EditorCamera(EditorHandler editorHandler) {
        this.editorHandler = editorHandler;
        this.xOffset = 0;
        this.yOffset = 0;
        this.editorKeyManager = editorHandler.getEditor().getEditorKeyManager();


    }

    public void checkBlankSpace() {
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > editorHandler.getWorld().getWidth() * Tile.TILE_WIDTH - editorHandler.getWidth()) {
            xOffset = editorHandler.getWorld().getWidth() * Tile.TILE_WIDTH - editorHandler.getWidth();
        }
        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > editorHandler.getWorld().getHeight() * Tile.TILE_HEIGHT - editorHandler.getHeight()) {
            yOffset = editorHandler.getWorld().getHeight() * Tile.TILE_HEIGHT - editorHandler.getHeight();
        }
    }


    public void move(float xAmt, float yAmt) {
        xOffset += xAmt;
        yOffset += yAmt;
        checkBlankSpace();

    }

    public void tick(){
        if(editorHandler.getEditor().getEditorKeyManager().up){
            yOffset -= 5;
        }
        if(editorHandler.getEditor().getEditorKeyManager().down){
            yOffset += 5;
        }
        if(editorHandler.getEditor().getEditorKeyManager().left){
            xOffset -= 5;
        }
        if(editorHandler.getEditor().getEditorKeyManager().right){
            xOffset += 5;
        }
        checkBlankSpace();
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}
