package fractals.tools;

import math.MathOperator;
import math.Vector2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static java.lang.Math.floor;

public class FractalHandler extends FractalLogic {
    private MathOperator coordinateOperator;
    private PixelFractal fractal;

    public FractalHandler(PixelFractal fractal, Dimension size) {
        super(size);
        this.fractal = fractal;
    }

    private void paintFractal() {
        Rectangle2D viewBox = getViewOperator().getViewBox();
        Rectangle2D drawBox = getViewOperator().getDrawBox();

        int xstart = round(drawBox.getX());
        int ystart = round(drawBox.getY());
        int honzPixels = round(drawBox.getWidth()) + xstart;
        int vertPixels = round(drawBox.getHeight()) + ystart;

        BufferedImage pixels = new BufferedImage(
                honzPixels,
                vertPixels,
                BufferedImage.TYPE_INT_ARGB
        );

        for (int xdraw = xstart; xdraw < honzPixels; xdraw++) {
            for (int ydraw = ystart; ydraw < vertPixels; ydraw++) {
                Vector2D drawPixel = new Vector2D(xdraw, ydraw);
                Vector2D viewPixel = getViewOperator().relativePoint(drawPixel);
                Point2D orderedPair = coordinateOperator.getCoordinate(viewPixel);
                Color color = fractal.getPixelColor(orderedPair, getIterations());
                pixels.setRGB(xdraw, ydraw, color.getRGB());
            }
        }

        getGraphics().drawImage(pixels, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.coordinateOperator = new MathOperator(fractal.getCoordinates(), getViewBox());
        paintFractal();
    }
}
