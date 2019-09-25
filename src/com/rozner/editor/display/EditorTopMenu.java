package com.rozner.editor.display;

import com.rozner.editor.input.EditorListener;
import com.rozner.worlds.TestWorld;
import com.rozner.worlds.WorldManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditorTopMenu {

    private JMenuBar menuBar;
    private EditorListener editorListener;



    public EditorTopMenu(EditorListener editorListener) {
        menuBar = new JMenuBar();
        this.editorListener = editorListener;
        createTopMainMenu();
    }

    public void createTopMainMenu(){
        JMenu m1 = new JMenu("Menu");
        m1.setName("menu");
        menuBar.add(m1);
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
        m12.addActionListener((ActionEvent e) -> {
            WorldManager.getInstance().saveWorld();
            updateMenu(WorldManager.getInstance().getWorlds());
        });

        JMenu m13 = new JMenu("Load Map");
        JMenuItem m14 = new JMenuItem("Start Game");
        JMenuItem m15 = new JMenuItem("Exit");
        m1.add(m11);
        m1.add(m12);
        m1.add(m13);
        m1.add(m14);
        m1.add(m15);
    }

    public void createTopEditorMenu(){
        JMenu editorMenu = new JMenu("Editor");
        JMenuItem spawn = new JMenuItem();

    }

    public void updateMenu(List<TestWorld> worlds) {
        JMenu menu = menuBar.getMenu(0);
        Component[] components = menu.getMenuComponents();
        JMenu submenu = (JMenu) components[2];
        submenu.removeAll();
        for (TestWorld world : worlds) {
            JMenuItem item = new JMenuItem(world.getWorldName());
            item.setName(world.getWorldName());
            ActionListener action = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    WorldManager.getInstance().setCurrentWorld(world);
                }
            };
            item.addActionListener(action);
            submenu.add(item);
        }
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
