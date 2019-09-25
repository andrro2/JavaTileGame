package com.rozner.editor.input;

import com.rozner.editor.EditWorldLayout;
import com.rozner.editor.EditorHandler;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class EditorListener {

    private EditorHandler editorHandler;
    private static boolean canvasEventListenerToggle = true;
    private static boolean canvasPlayerSpawnEventListenerToggle = false;

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

    private void setCanvasEventListenerToggle(boolean canvasEventListenerToggle) {
        EditorListener.canvasEventListenerToggle = canvasEventListenerToggle;
    }

    public void toggleCanvasEventListeners(){
        if(canvasEventListenerToggle){
            canvasEventListenerToggle = false;
        }else{
            canvasEventListenerToggle = true;
        }
    }

    public ActionListener getSpawnButtonEventListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvasEventListenerToggle = false;
                canvasPlayerSpawnEventListenerToggle = true;
            }
        };
    }

    public MouseListener getCanvasPlayerSpawnEventListener(){
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(canvasPlayerSpawnEventListenerToggle) {
                    float xPos = e.getX() + editorHandler.getGameCamera().getxOffset();
                    float yPos = e.getY() + editorHandler.getGameCamera().getyOffset();
                    editorHandler.getWorld().setPlayerSpawnX((int) xPos);
                    editorHandler.getWorld().setPlayerSpawnY((int) yPos);
                    canvasPlayerSpawnEventListenerToggle = false;
                    canvasEventListenerToggle = true;
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
}
