package com.rozner.editor;

import com.rozner.editor.gfx.EditorCamera;
import com.rozner.game.Game;
import com.rozner.game.gfx.GameCamera;
import com.rozner.game.input.KeyManager;
import com.rozner.worlds.TestWorld;
import com.rozner.worlds.World;

public class EditorHandler {

    private Editor editor;
    private TestWorld world;

    public EditorHandler(Editor editor){
        this.editor = editor;
    }

    public int getWidth(){
        return editor.getWidth();
    }

    public int getHeight(){
        return editor.getHeight();
    }

    /*public KeyManager getKeyManager(){
        return editor.getKeyManager();
    }*/

    public EditorCamera getGameCamera(){
        return editor.getEditorCamera();
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public TestWorld getWorld() {
        return world;
    }

    public void setWorld(TestWorld world) {
        this.world = world;
    }
}
