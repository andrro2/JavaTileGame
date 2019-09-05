package com.rozner.editor.display;

import com.rozner.worlds.TestWorld;
import com.rozner.worlds.WorldManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Display {

    private JMenuBar mb;

    private JFrame frame;

    private Canvas canvas;
    private String title;
    private int width, height;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay() {
        frame = new JFrame(title);

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        mb = new JMenuBar();
        JMenu m1 = new JMenu("Menu");
        m1.setName("menu");
        mb.add(m1);
        JMenuItem m11 = new JMenuItem("New map");
        m11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PopupNewMap popup = new PopupNewMap();
                updateMenu(WorldManager.getInstance().getWorlds());
            }
        });

        JMenuItem m12 = new JMenuItem("Save Map");
        m12.setEnabled(false);
        m12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("huha");
                WorldManager.getInstance().saveWorld();
                updateMenu(WorldManager.getInstance().getWorlds());
            }
        });

        JMenu m13 = new JMenu("Load Map");
        JMenuItem m14 = new JMenuItem("Start Game");
        JMenuItem m15 = new JMenuItem("Exit");
        m1.add(m11);
        m1.add(m12);
        m1.add(m13);
        m1.add(m14);
        m1.add(m15);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        frame.add(canvas);
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

    public void updateMenu(List<TestWorld> worlds) {
        JMenu menu = mb.getMenu(0);
        Component[] components = menu.getMenuComponents();
        JMenu submenu = (JMenu) components[2];
        submenu.removeAll();
        for (TestWorld world : worlds) {
            JMenuItem item = new JMenuItem(world.getWorldName());
            item.setName(world.getWorldName());
            ActionListener action = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("World "+world.getWorldName()+" setted");
                    WorldManager.getInstance().setCurrentWorld(world);
                }
            };
            item.addActionListener(action);
            submenu.add(item);
        }
    }

    public void enableSaveMenu(boolean status){
        getMb().getMenu(0).getMenuComponents()[1].setEnabled(status);
    }

}
