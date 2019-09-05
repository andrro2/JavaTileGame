package com.rozner.editor;

import com.rozner.game.Game;
import com.rozner.game.gfx.GameCamera;
import com.rozner.game.input.KeyManager;
import com.rozner.worlds.World;

public class EditorHandler {

    private Game editor;
    private World world;

    public EditorHandler(Game game){
        this.editor = game;
    }

    public int getWidth(){
        return editor.getWidth();
    }

    public int getHeight(){
        return editor.getHeight();
    }

    public KeyManager getKeyManager(){
        return editor.getKeyManager();
    }

    public GameCamera getGameCamera(){
        return editor.getGameCamera();
    }

    public Game getEditor() {
        return editor;
    }

    public void setEditor(Game editor) {
        this.editor = editor;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
