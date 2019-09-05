package com.rozner.game.input;

import com.rozner.ProgramStateManager;
import com.rozner.game.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEventListeners {

    public MouseEventListeners() {
    }

    public MouseListener exitEventListener(Game game){
        MouseListener exitListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                game.stop();
                ProgramStateManager manager = ProgramStateManager.getInstance();
                manager.setRunning(false);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        return exitListener;
    }

    public MouseListener startEditorListener(Game game){
        MouseListener editorStartListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                game.stop();
                ProgramStateManager.getInstance().setProgramState(2);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        return editorStartListener;
    }
}
