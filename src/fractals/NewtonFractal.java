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
            new Color(53, 166, 23, 255),
            new Color(162, 26, 26, 255),
            new Color(34, 50, 141, 255)
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

    private double[] multiply(double x1, double y1, double x2, double y2) {
        double real = x1 * x2 - y1 * y2;
        double imaginary = x1 * y2 + y1 * x2;
        return new double[]{real, imaginary};
    }

    private double[] divide(double x1, double y1, double x2, double y2) {
        double[] conjugate = {x2, -y2};
        double[] numerator = multiply(x1, y1, conjugate[0], conjugate[1]);
        double denominator = x2 * x2 + y2 * y2;
        return new double[]{numerator[0] / denominator, numerator[1] / denominator};
    }

    private double[] pow(double x, double y, int power) {
        double[] result = {x, y};
        for (int i = 1; i < power; i++) {
            result = multiply(result[0], result[1], x, y);
        }
        return result;
    }

    private double[] derivative(double x, double y) {
        // (x + yi)^2 = (x^2 - y^2) + 2xyi
        double real = x * x - y * y;
        double imaginary = 2 * x * y;

        // Multiply by 3
        real *= 3;
        imaginary *= 3;

        return new double[]{real, imaginary};
    }

    private double[] function(double x, double y) {
        // (x + yi)^3
        double[] cubed = pow(x, y, 3);

        // Subtract -1 (i.e., Add 1 to the real part)
        cubed[0] += 1;

        return cubed;
    }

    private double[] attractor(double x, double y) {
        double[] funcResult = function(x, y);
        double[] derivResult = derivative(x, y);
        double[] funcDivDeriv = divide(funcResult[0], funcResult[1], derivResult[0], derivResult[1]);

        // Subtract funcDivDeriv from (x, y)
        return new double[]{x - funcDivDeriv[0], y - funcDivDeriv[1]};
    }

    @Override
    public Color getPixelColor(Point2D n0, int iterations) {
        double x = n0.getX();
        double y = n0.getY();

        for (int i = 0; i < iterations; i++) {
            double[] result = attractor(x, y);
            x = result[0];
            y = result[1];
        }

        n0 = new Point2D.Double(x, y);

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

    @Override
    public Rectangle2D getCoordinates() {
        return this.coordinates;
    }
}