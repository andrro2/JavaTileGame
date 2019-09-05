package com.rozner.editor;

import com.rozner.editor.display.Display;
import com.rozner.utils.FileCollector;
import com.rozner.worlds.TestWorld;
import com.rozner.worlds.World;
import com.rozner.worlds.WorldManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileFilter;

public class Editor implements Runnable{

    private Thread thread;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;
    private Display display;
    public String title;
    private int width, height;
    private WorldManager worldManager;


    public Editor(String title, int width, int height){
        this.height = height;
        this.width = width;
        this.title = title;
        worldManager = WorldManager.getInstance();
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
        display = new Display(title, width, height);
        display.updateMenu(worldManager.getWorlds());
        initMouseEventListeners();
    }

    public void tick(){
        if(worldManager.getCurrentWorld() != null){
            display.enableSaveMenu(true);
        }

    }

    public void render(){

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
        display.getFrame().dispose();
        /*try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }


    private void initMouseEventListeners() {
    }

    public Thread getThread() {
        return thread;
    }
}
