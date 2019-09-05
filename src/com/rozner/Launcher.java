package com.rozner;

import com.rozner.ProgramStateManager;

public class Launcher {

    public static void main(String[] args) {
        /*Game game = new Game("Game", 1280, 960);
        game.start();*/
        ProgramStateManager manager = ProgramStateManager.getInstance();
        manager.start();
    }
}
