package GFX;

import javax.swing.*;
import java.awt.*;

public class Draw {
    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    public Draw(String title, int width, int height) {
        this.setTitle(title);
        this.setWidth(width);
        this.setHeight(height);

        draw();
    }

    public void draw(){
        this.setFrame(new JFrame(this.getTitle()));
        this.getFrame().setSize(this.getWidth(),this.getHeight());
        this.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getFrame().setResizable(false);
        this.getFrame().setLocationRelativeTo(null);
        this.getFrame().setVisible(true);

        this.setCanvas(new Canvas());
        Dimension size = new Dimension(this.getWidth(),this.getHeight());
        this.getCanvas().setPreferredSize(size);
        this.getCanvas().setMaximumSize(size);
        this.getCanvas().setMinimumSize(size);

        this.getFrame().add(this.getCanvas());
        this.getFrame().pack();
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
