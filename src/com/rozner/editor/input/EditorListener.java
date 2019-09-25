package com.rozner.editor.input;

import com.rozner.editor.EditWorldLayout;
import com.rozner.editor.EditorHandler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

public class EditorListener {

    private EditorHandler editorHandler;
    private static boolean canvasEventListenerToggle = true;

    public EditorListener(EditorHandler editorHandler) {
        this.editorHandler = editorHandler;
    }

    public MouseListener getCanvasEventListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (canvasEventListenerToggle) {
                    float xPos = e.getX() + editorHandler.getGameCamera().getxOffset();
                    float yPos = e.getY() + editorHandler.getGameCamera().getyOffset();
                    new EditWorldLayout().replaceTileInPosition((int) xPos, (int) yPos);
                }
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
    }

    public MouseMotionListener getCanvasMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(canvasEventListenerToggle) {
                    float xPos = e.getX() + editorHandler.getGameCamera().getxOffset();
                    float yPos = e.getY() + editorHandler.getGameCamera().getyOffset();
                    new EditWorldLayout().replaceTileInPosition((int) xPos, (int) yPos);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        };
    }

    public void toggleCanvasEventListeners(){
        if(canvasEventListenerToggle){
            canvasEventListenerToggle = false;
        }else{
            canvasEventListenerToggle = true;
        }
    }


}
