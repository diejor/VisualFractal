package fractals.tools;

import math.ViewOperator;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class FractalLogic implements PrintableFractal {
    private Graphics2D g2d;
    private ViewOperator viewOp;
    private int iterations;

    public FractalLogic(Dimension size) {
        this.iterations = 1;
        this.viewOp = new ViewOperator(size);
    }

    @Override
    public void setIterations(int iterations) {
        if (iterations > 0)
            this.iterations = iterations;
    }

    public int getIterations() {
        return iterations;
    }

    @Override
    public void incrementIterations() {
        iterations++;
    }

    @Override
    public void decrementIterations() {
        if (iterations > 0)
            iterations--;
    }

    /**
     * calls the ViewOperator to increment the scale factor to zoom in or out the Graphics
     * @param units if the units are negative, the zoom will be decremented and incremented when positive units
     */
    @Override
    public void zoomUnits(int units) {
        viewOp.zoomUnits(units);
    }

    @Override
    public void zoomXUnits(int units) {
        viewOp.zoomXUnits(units);
    }

    @Override
    public void zoomYUnits(int units) {
        viewOp.zoomYUnits(units);
    }

    @Override
    public void translate(double dx, double dy) {
        viewOp.translation(dx, dy);
    }

    public void setGraphics(Graphics g2d) {
        this.g2d = (Graphics2D) g2d;
    }

    public Graphics2D getGraphics() {
        return g2d;
    }

    public int round(double u) {
        return (int)Math.round(u);
    }

    public Rectangle2D getViewBox() {
        return viewOp.getViewBox();
    }

    public ViewOperator getViewOperator() {
        return viewOp;
    }

    @Override
    public void paint(Graphics g) {
        setGraphics(g);
    }
}
