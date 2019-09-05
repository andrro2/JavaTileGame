package com.rozner;

import com.rozner.editor.Editor;
import com.rozner.game.Game;

public class ProgramStateManager implements Runnable {

    private Thread mainThread;
    private boolean running = true;
    private int programState = 1;
    private Editor editor = null;
    private Game game = null;
    private static ProgramStateManager instance;
    private ProgramStateManager(){

    }

    public static ProgramStateManager getInstance(){
        if(instance == null){
            instance = new ProgramStateManager();
        }
        return instance;
    }

    public synchronized void start(){
        mainThread = new Thread(this);
        mainThread.start();

    }


    public void run() {
        while(running){
            if(programState==1){
                if(game==null){
                    game = new Game("Game", 1280, 960);
                    game.start();
                }
                if(!game.getThread().isAlive() && game != null){
                    game.start();
                }
            }else if(programState == 2){
                if(editor == null){
                    editor = new Editor("Editor", 1280, 960);
                    editor.start();
                }
                if(!editor.getThread().isAlive() && editor != null){
                    editor.start();
                }
            }
            try {

                mainThread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stop();

    }

    public synchronized void stop(){
        if(!running)
            return;
        running = false;
    }

    public void setProgramState(int programState) {
        this.programState = programState;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
