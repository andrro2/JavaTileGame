package com.rozner.editor.Listeners;

import com.rozner.ProgramStateManager;
import com.rozner.editor.Editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListeners {

    public ActionListener startGameListener(Editor editor){
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editor.stop();
                ProgramStateManager.getInstance().setProgramState(1);
            }
        };
        return listener;
    }

    public ActionListener exitButtonListener(Editor editor){
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editor.stop();
                ProgramStateManager manager = ProgramStateManager.getInstance();
                manager.setRunning(false);
            }
        };
        return listener;
    }
}
