package fractals.tools;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public interface PixelFractal {
    Color getPixelColor(Point2D coordinate, int iterations);
    Rectangle2D getCoordinates();
}
