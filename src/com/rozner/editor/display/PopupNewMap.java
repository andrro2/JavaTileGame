package com.rozner.editor.display;

import com.rozner.worlds.TestWorld;
import com.rozner.worlds.WorldManager;

import javax.swing.*;

public class PopupNewMap {
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    JTextField nameField = new JTextField(20);
    JTextField xField = new JTextField(3);
    JTextField yField = new JTextField(3);

    public PopupNewMap(){
        panel.add(new JLabel("Map name"));
        panel.add(nameField);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(new JLabel("Width"));
        panel.add(xField);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(new JLabel("Height"));
        panel.add(yField);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Please Enter Name, X and Y Values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            TestWorld world = new TestWorld(nameField.getText(), Integer.parseInt(xField.getText()), Integer.parseInt(yField.getText()));
            WorldManager.getInstance().setCurrentWorld(world);
            WorldManager.getInstance().saveWorld();

        }

        //Object result = JOptionPane.showInputDialog(frame, "Name of the new map");
        //System.out.println(String.valueOf(result));
        //TestWorld newWorld = new TestWorld(String.valueOf(result));

    }
}
