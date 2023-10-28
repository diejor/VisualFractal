package fractals;

import fractals.tools.PixelFractal;
import math.Complex;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Mandelbrot implements PixelFractal {
    private final Color colorGradient = new Color(0, 13, 33, 255);
    private final Rectangle2D coordinates = new Rectangle2D.Double(-2, -2, 5, 4);

    public Color mandelbrotIteration(Complex complex, Complex seed, Color color, int iterations) {
        double x = complex.getReal();
        double y = complex.getImaginary();
        int RADIUS = 2;
        if (x*x + y*y > RADIUS * RADIUS)
            return color;
        else if (iterations < 1)
            return Color.BLACK;
        return mandelbrotIteration(mandelbrotFunction(complex, seed), seed, color.brighter(), iterations-1);
    }

    public Complex mandelbrotFunction(Complex input, Complex seed) {
        return input.multiply(input).add(seed);
    }

    @Override
    public Color getPixelColor(Point2D coordinate, int iterations) {
        return mandelbrotIteration(
                Complex.valueOf(0),
                new Complex(coordinate.getX(),
                        coordinate.getY()),
                colorGradient,
                iterations
        );
    }

    @Override
    public Rectangle2D getCoordinates() {
        return this.coordinates;
    }
}
