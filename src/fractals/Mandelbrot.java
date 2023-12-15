package fractals;

import fractals.tools.PixelFractal;
import math.Complex;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Mandelbrot implements PixelFractal {
    private final Color colorGradient = new Color(0, 13, 33, 255);
    private final Rectangle2D coordinates = new Rectangle2D.Double(-2, -2, 5, 4);

    public Color mandelbrotIteration(double real, double imaginary, int maxIterations) {
        double smoothColor;
        double x = 0.0;
        double y = 0.0;
        double xNew, yNew;
        int iteration = 0;
        int RADIUS = 2;

        while ((x * x + y * y < RADIUS * RADIUS) && (iteration < maxIterations)) {
            xNew = x * x - y * y + real;
            yNew = 2 * x * y + imaginary;
            x = xNew;
            y = yNew;
            iteration++;
        }

        if (iteration < maxIterations) {
            // Continuous iteration count for smooth coloring
            double log_zn = Math.log(x * x + y * y) / 2;
            double nu = Math.log(log_zn / Math.log(2)) / Math.log(2);
            smoothColor = iteration + 1 - nu;
        } else {
            smoothColor = maxIterations;
        }

        return getColor(smoothColor, maxIterations);
    }

    private Color getColor(double smoothColor, int maxIterations) {
        // Modify the hue calculation for a broader range of colors
        float hue = (float) (0.5f + Math.sin(smoothColor / maxIterations * Math.PI) * 0.5f);
        float saturation = 0.6f;  // Adjust as needed for color intensity
        float brightness = 1.0f;  // Typically set to 1 for full brightness

        // Ensure points inside the set are colored black
        if (smoothColor >= maxIterations) {
            saturation = 0;
            brightness = 0;
        }

        return Color.getHSBColor(hue, saturation, brightness);
    }

    public Complex mandelbrotFunction(Complex input, Complex seed) {
        return input.multiply(input).add(seed);
    }

    @Override
    public Color getPixelColor(Point2D coordinate, int iterations) {
        return mandelbrotIteration(
                coordinate.getX(), coordinate.getY(),
                iterations
        );
    }

    @Override
    public Rectangle2D getCoordinates() {
        return this.coordinates;
    }
}
