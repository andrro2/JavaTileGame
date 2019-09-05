package com.rozner.game.display;

import javax.swing.*;
import java.awt.*;

public class Display {

    private JMenuBar mb;

    private JFrame frame;
    private Canvas canvas;
    private String title;

    private int width, height;
    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay(){
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
        JMenu m11 = new JMenu("Load Map");
        JMenuItem m12 = new JMenuItem("Start Editor");
        JMenuItem m13 = new JMenuItem("Exit");
        m1.add(m11);
        m1.add(m12);
        m1.add(m13);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.pack();

    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame(){
        return frame;
    }

    public JMenuBar getMb() {
        return mb;
    }
}
