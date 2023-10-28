package ui;

import fractals.tools.FractalLogic;
import fractals.tools.PrintableFractal;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;

public class FractalGraphics extends JPanel {
    private PrintableFractal currentFractal;
    private MouseEvent start;

    public FractalGraphics(Dimension size) {
        this.currentFractal = new FractalLogic(size);
        this.setBackground(new Color(169, 169, 169));

        this.addMouseWheelListener(e -> {
            this.currentFractal.zoomUnits(e.getWheelRotation()*-1);
            repaint();
        });

        this.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                start = e;
            }
        });

        this.addMouseMotionListener(new MouseInputAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentFractal.translate(
                        e.getX()-start.getX(),
                        e.getY()-start.getY()
                );
                start = e;
                repaint();
            }
        });
    }



    public void setFractal(PrintableFractal fractal) {
        this.currentFractal = fractal;
    }

    public PrintableFractal getFractal() {
        return currentFractal;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.currentFractal.paint(g);
    }
}
