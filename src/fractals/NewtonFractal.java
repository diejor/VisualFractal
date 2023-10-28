package fractals;

import fractals.tools.PixelFractal;
import math.Complex;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class NewtonFractal implements PixelFractal {
    private final Rectangle2D coordinates = new Rectangle2D.Double(-2, -2, 6, 4);
    private final Point2D[] roots = new Point2D.Double[]{
            new Point2D.Double(1, 0),
            new Point2D.Double(-0.5, Math.sqrt(3)/2),
            new Point2D.Double(-0.5, -Math.sqrt(3)/2)
    };

    private final Color[] rootsColor = new Color[] {
            new Color(50, 255, 0, 255),
            new Color(255, 0, 0, 255),
            new Color(0, 57, 255, 255)
    };

    private Complex derivative(Complex input) {
        return input.pow(2).multiply(3);
    }

    private Complex function(Complex input) {
        return input.pow(3).subtract(-1);
    }

    private Complex attractor(Complex input) {
        return input.subtract(function(input).divide(derivative(input)));
    }

    @Override
    public Color getPixelColor(Point2D n0, int iterations) {
        if (iterations > 1) {
            Complex n1 = attractor(new Complex(n0.getX(), n0.getY()));
            return getPixelColor(new Point2D.Double(n1.getReal(), n1.getImaginary()), iterations-1);
        }
        else {
            double d1 = n0.distanceSq(roots[0]);
            double d2 = n0.distanceSq(roots[1]);
            double d3 = n0.distanceSq(roots[2]);

            if (d1 > d2 && d1 > d3)
                return rootsColor[0];
            else if (d2 > d1 && d2 > d3)
                return rootsColor[1];
            else if (d3 > d1 && d3 > d2)
                return rootsColor[2];
            return Color.BLACK;
        }
    }

    @Override
    public Rectangle2D getCoordinates() {
        return this.coordinates;
    }
}
