package com.rozner.editor;

import com.rozner.editor.Listeners.ActionListeners;
import com.rozner.editor.display.EditorDisplay;
import com.rozner.editor.gfx.EditorCamera;
import com.rozner.editor.input.EditorKeyManager;
import com.rozner.utils.FileCollector;
import com.rozner.worlds.WorldManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class Editor implements Runnable{

    private Thread thread;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;
    private EditorDisplay editorDisplay;
    public String title;
    private int width, height;
    private WorldManager worldManager;
    private ActionListeners actionListeners;
    private EditorCamera editorCamera;
    private EditorHandler editorHandler;
    private EditorKeyManager editorKeyManager;


    public Editor(String title, int width, int height){
        this.height = height;
        this.width = width;
        this.title = title;
        worldManager = WorldManager.getInstance();
        actionListeners = new ActionListeners();
        editorKeyManager = new EditorKeyManager();

    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }


    private void init() {
        FileCollector collector = new FileCollector();
        collector.loadWorlds();
        worldManager.setProgramStatus(2);
        editorHandler = new EditorHandler(this);
        editorHandler.setWorld(worldManager.getCurrentWorld());
        worldManager.setEditorHandler(editorHandler);
        editorDisplay = new EditorDisplay(title, width, height, editorHandler);
        editorDisplay.getEditorTopMenu().updateMenu(worldManager.getWorlds());
        initMouseEventListeners();
        editorCamera = new EditorCamera(editorHandler);
        editorDisplay.getFrame().addKeyListener(editorKeyManager);
    }

    public void tick(){
        if(worldManager.getCurrentWorld() != null){
            editorDisplay.enableSaveMenu(true);
        }
        editorKeyManager.tick();
        editorCamera.tick();

    }

    private void render() {
        bs = editorDisplay.getCanvas().getBufferStrategy();
        if (bs == null) {
            editorDisplay.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        if(worldManager.getCurrentWorld() != null)
            worldManager.render(g);

        bs.show();
        g.dispose();

    }


    public void run() {
        init();

        long timer = 0;
        int time_slice = 1000 / 60;

        while (running) {
            timer = System.currentTimeMillis();
            tick();
            render();
            try {
                if ((System.currentTimeMillis() - timer) < time_slice) {

                    thread.sleep(time_slice - (System.currentTimeMillis() - timer));

                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        stop();


    }

    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        editorDisplay.getFrame().dispose();
        /*try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }


    private void initMouseEventListeners() {
        JMenuItem startGameButton = (JMenuItem) editorDisplay.getMb().getMenu(0).getMenuComponent(3);
        startGameButton.addActionListener(actionListeners.startGameListener(this));
        JMenuItem exitButton = (JMenuItem) editorDisplay.getMb().getMenu(0).getMenuComponent(4);
        exitButton.addActionListener(actionListeners.exitButtonListener(this));
    }

    public Thread getThread() {
        return thread;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EditorCamera getEditorCamera() {
        return editorCamera;
    }

    public EditorHandler getEditorHandler() {
        return editorHandler;
    }

    public EditorKeyManager getEditorKeyManager() {
        return editorKeyManager;
    }
}
