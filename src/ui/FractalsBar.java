package ui;

import fractals.*;
import fractals.tools.FractalHandler;

import javax.swing.*;

public class FractalsBar extends JMenuBar {
    FractalGraphics display;

    public FractalsBar(FractalPanel panel) {
        this.display = panel.getFractalDisplay();
        JMenu menu = new JMenu("Fractals");
        JMenuItem triangle = new JMenuItem("Sierpinski Triangle");
        JMenuItem square = new JMenuItem("Sierpinski Square");
        JMenuItem mandelbrot = new JMenuItem("Mandelbrot set");
        JMenuItem newton = new JMenuItem("Newton Fractal");
        JMenuItem increment = new JMenuItem("Increment Iterations");
        JMenuItem decrement = new JMenuItem("Decrement Iterations");

        JMenuItem stretchx = new JMenuItem("Vertical Expansion");
        JMenuItem compressx = new JMenuItem("Vertical Compression");
        JMenuItem stretchy = new JMenuItem("Horizontal Expansion");
        JMenuItem compressy = new JMenuItem("Horizonral Compression");


        menu.add(triangle);
        menu.add(square);
        menu.add(mandelbrot);
        menu.add(newton);

        this.add(menu);
        this.add(increment);
        this.add(decrement);

//        this.add(stretchx);
//        this.add(compressx);
//        this.add(stretchy);
//        this.add(compressy);

        compressx.addActionListener(e -> {
            this.display.getFractal().zoomYUnits(-5);
            this.display.repaint();
        });
        stretchx.addActionListener(e -> {
            this.display.getFractal().zoomYUnits(5);
            this.display.repaint();
        });
        compressy.addActionListener(e -> {
            this.display.getFractal().zoomXUnits(-5);
            this.display.repaint();
        });
        stretchy.addActionListener(e -> {
            this.display.getFractal().zoomXUnits(5);
            this.display.repaint();
        });

        triangle.addActionListener(e -> {
            this.display.setFractal(new SierpinskiTriangle(panel.getSize()));
            this.display.repaint();
        });

        square.addActionListener(e -> {
            this.display.setFractal(new SierpinskiSquare(panel.getSize()));
            this.display.repaint();
        });

        mandelbrot.addActionListener(e -> {
            this.display.setFractal(new FractalHandler(new Mandelbrot(), panel.getSize()));
            this.display.repaint();
        });

        newton.addActionListener(e -> {
            this.display.setFractal(new FractalHandler(new NewtonFractal(), panel.getSize()));
            this.display.repaint();
        });

        increment.addActionListener(e -> {
            this.display.getFractal().incrementIterations();
            this.display.repaint();
        });

        decrement.addActionListener(e -> {
            this.display.getFractal().decrementIterations();
            this.display.repaint();
        });
    }
}
