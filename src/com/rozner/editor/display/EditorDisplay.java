package com.rozner.editor.display;

import com.rozner.editor.EditWorldLayout;
import com.rozner.editor.EditorHandler;
import com.rozner.editor.input.EditorListener;
import com.rozner.game.tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditorDisplay {

    private JMenuBar mb;

    private JFrame frame;
    private JPanel panel;
    private Canvas canvas;
    private String title;
    private int width, height;
    private EditorHandler editorHandler;
    private EditorTopMenu editorTopMenu;
    private EditorListener editorListener;

    public EditorDisplay(String title, int width, int height, EditorHandler editorHandler1) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.editorHandler = editorHandler1;
        editorListener = new EditorListener(editorHandler);
        createDisplay();
    }

    private void createDisplay() {
        frame = new JFrame(title);

        //build frame
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width,height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel = new JPanel();
        panel.setFocusable(false);
        panel.setPreferredSize(new Dimension(width/100*25, height));
        JButton button = new JButton("Set player spawn Location");
        button.setFocusable(false);

        JPanel texturePane = new JPanel();
        texturePane.setPreferredSize(new Dimension(width/100*20, 6700));
        texturePane.setFocusable(false);
        texturePane.setAutoscrolls(true);
        for(Tile tile : Tile.tiles){
            JLabel element = new JLabel(new ImageIcon(tile.getTexture()));
            element.setFocusable(false);
            element.addMouseListener(editorListener.getSelectTileMouseListener(tile));
            texturePane.add(element);

        }

        JScrollPane scrollPane = new JScrollPane(texturePane);
        scrollPane.setPreferredSize(new Dimension(width/100*25,500));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setFocusable(false);
        JCheckBox isSolidCheckBox = new JCheckBox("is solid");
        panel.add(scrollPane);
        panel.add(isSolidCheckBox);
        panel.add(button);
        editorTopMenu = new EditorTopMenu(editorListener);
        mb = editorTopMenu.getMenuBar();
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(500, 500));
        canvas.setFocusable(false);

        //add listeners
        canvas.addMouseListener(editorListener.getCanvasEventListener(isSolidCheckBox));
        canvas.addMouseMotionListener(editorListener.getCanvasMotionListener(isSolidCheckBox));
        canvas.addMouseListener(editorListener.getCanvasPlayerSpawnEventListener());
        button.addActionListener(editorListener.getSpawnButtonEventListener());

        //pack frame
        frame.getContentPane().add(BorderLayout.CENTER, canvas);
        frame.getContentPane().add(BorderLayout.WEST, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.pack();
    }

    public JMenuBar getMb() {
        return mb;
    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public EditorTopMenu getEditorTopMenu() {
        return editorTopMenu;
    }

    public void enableSaveMenu(boolean status){
        getMb().getMenu(0).getMenuComponents()[1].setEnabled(status);
    }

}
