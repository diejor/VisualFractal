package fractals;

import fractals.tools.FractalLogic;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SierpinskiTriangle extends FractalLogic {
    public SierpinskiTriangle(Dimension size) {
        super(size);
    }

    public void sierpinskiTriangle() {
        Rectangle2D viewBox = getViewBox();
        int x = round(viewBox.getX());
        int y = round(viewBox.getY());
        double w = viewBox.getWidth();
        double h = viewBox.getHeight();
        double wh = w*0.5;
        double hh = h*0.5;
        getGraphics().drawPolygon(
                new int[]{x, round(w) + x, round(wh) + x},
                new int[]{round(h) + y, round(h) + y, y},
                3
        );
        sierpinskiTriangle(wh*0.5 + x, hh + y, wh, hh, getIterations()-1);
    }

    public void sierpinskiTriangle(double x, double y, double w, double h, int it) {
        if (it > 0) {
            double wh = w*0.5;
            double hh = h*0.5;
            double whh = wh*0.5;
            getGraphics().drawPolygon(
                    new int[]{round(x), round(x+w), round(x+wh)},
                    new int[]{round(y), round(y), round(y+h)},
                    3
            );

            sierpinskiTriangle(x-whh, y+hh, wh, hh,  it-1);
            sierpinskiTriangle(x+whh, y-hh, wh, hh, it-1);
            sierpinskiTriangle(x+wh+whh, y+hh, wh, hh, it-1);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        sierpinskiTriangle();
    }

}
