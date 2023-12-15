package fractals;

import fractals.tools.FractalLogic;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class SierpinskiTriangle extends FractalLogic {
    public SierpinskiTriangle(Dimension size) {
        super(size);
    }

    public void sierpinskiTriangle() {
        Rectangle2D drawBox = getViewOperator().getDrawBox();
        int startx = round(drawBox.getX());
        int starty = round(drawBox.getY());
        double width = drawBox.getWidth();
        double height = drawBox.getHeight();
        double half_width = width*0.5;
        double half_height = height*0.5;
        getGraphics().drawPolygon(
                new int[]{startx, round(width) + startx, round(half_width) + startx},
                new int[]{round(height) + starty, round(height) + starty, starty},
                3
        );
        sierpinskiTriangle(half_width*0.5 + startx, half_height + starty, half_width, half_height, getIterations()-1);
    }

    public void sierpinskiTriangle(double start, double y, double w, double h, int it) {
        if (it > 0) {
            double wh = w*0.5;
            double hh = h*0.5;
            double whh = wh*0.5;
            getGraphics().drawPolygon(
                    new int[]{round(start), round(start+w), round(start+wh)},
                    new int[]{round(y), round(y), round(y+h)},
                    3
            );

            sierpinskiTriangle(start-whh, y+hh, wh, hh,  it-1);
            sierpinskiTriangle(start+whh, y-hh, wh, hh, it-1);
            sierpinskiTriangle(start+wh+whh, y+hh, wh, hh, it-1);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        sierpinskiTriangle();
    }
}
