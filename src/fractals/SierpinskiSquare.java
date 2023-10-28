package fractals;

import fractals.tools.FractalLogic;
import math.Vector2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SierpinskiSquare extends FractalLogic {
    public SierpinskiSquare(Dimension size) {
        super(size);
    }

    public void squares() {
        Rectangle2D win = getViewBox();
        Rectangle2D third = new Rectangle2D.Double(
                win.getX(),
                win.getY(),
                win.getWidth() / 3,
                win.getWidth() / 3
        );
        Vector2D pos = new Vector2D(win.getWidth() / 3, win.getHeight() / 3);
        squares(pos, pos, getIterations());
    }

    public void squares(Vector2D pos, Vector2D size, int it) {
        getGraphics().fillRect(
                pos.getXR(),
                pos.getYR(),
                size.getXR(),
                size.getYR()
        );
        if (it > 0) {
            double w3 = size.getX()/3.0;
            double h3 = size.getY()/3.0;

            Vector2D[] tranx = new Vector2D[8];

            double col1 = -2*w3;
            double row1 = -2*h3;
            double col3 = size.getX() + w3;
            double row3 = size.getY() + h3;
            tranx[0] = new Vector2D(col1, row1);
            tranx[1] = new Vector2D(w3, row1);
            tranx[2] = new Vector2D(col3, row1);

            tranx[3] = new Vector2D(col1, h3);
            tranx[4] = new Vector2D(col3, h3);

            tranx[5] = new Vector2D(col1, row3);
            tranx[6] = new Vector2D(w3, row3);
            tranx[7] = new Vector2D(col3, row3);

            Vector2D sizediv3 = new Vector2D(w3, h3);

            squares(pos.addR(tranx[0]), sizediv3, it-1);
            squares(pos.addR(tranx[1]), sizediv3, it-1);
            squares(pos.addR(tranx[2]), sizediv3, it-1);

            squares(pos.addR(tranx[3]), sizediv3, it-1);
            squares(pos.addR(tranx[4]), sizediv3, it-1);

            squares(pos.addR(tranx[5]), sizediv3, it-1);
            squares(pos.addR(tranx[6]), sizediv3, it-1);
            squares(pos.addR(tranx[7]), sizediv3, it-1);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        squares();
    }
}
