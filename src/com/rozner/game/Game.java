package com.rozner.game;

import com.rozner.game.display.Display;
import com.rozner.game.gfx.Assets;
import com.rozner.game.gfx.GameCamera;
import com.rozner.game.input.KeyManager;
import com.rozner.game.input.MouseEventListeners;
import com.rozner.game.states.GameState;
import com.rozner.game.states.State;
import com.rozner.utils.FileCollector;
import com.rozner.utils.XmlEncodrerDecoder;
import com.rozner.worlds.WorldManager;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class Game implements Runnable {

    private Display display;

    private Thread thread;
    private boolean running = false;
    private BufferStrategy bs;

    private Graphics g;

    private KeyManager keyManager;
    private MouseEventListeners mouseEventListeners;
    private GameCamera gameCamera;
    private Handler handler;
    private WorldManager worldManager = WorldManager.getInstance();
    public String title;


    private int width, height;

    private State gameState;


    public Game(String title, int width, int height) {
        this.height = height;
        this.width = width;
        this.title = title;
        keyManager = new KeyManager();
        mouseEventListeners = new MouseEventListeners();

    }

    private void init() {
        worldManager.setProgramStatus(1);
        new FileCollector().loadWorlds();

        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);

        handler = new Handler(this);
        worldManager.setHandler(handler);
        gameCamera = new GameCamera(handler, 0, 0);

        gameState = new GameState(handler);
        State.setState(gameState);
        initListeners(display.getMb());

    }


    private void tick() {
        keyManager.tick();
        if (State.getState() != null)
            State.getState().tick();

    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        if (State.getState() != null)
            State.getState().render(g);

        bs.show();
        g.dispose();

    }

    public void run() {

        init();

        int fps = 60;
        long timer = 0;
        int time_slice = 1000 / 60;

        while (running) {
            timer = System.currentTimeMillis();
            if (worldManager.getCurrentWorld() != null) {
                tick();
                render();
            }
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

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
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

    public void initListeners(JMenuBar menuBar) {

        menuBar.getMenu(0).getMenuComponent(2).addMouseListener(mouseEventListeners.exitEventListener(this));
        menuBar.getMenu(0).getMenuComponent(1).addMouseListener(mouseEventListeners.startEditorListener(this));

    }

    public Thread getThread() {
        return thread;
    }

    public Display getDisplay() {
        return display;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }
}
