package math;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class MathOperator {
    private Rectangle2D coordinates;
    private Vector2D coordScale;
    private Vector2D coordOffset;

    public MathOperator(Rectangle2D coordinates, Rectangle2D window) {
        this.coordinates = coordinates;
        coordScale = new Vector2D(
                this.coordinates.getWidth()/window.getWidth(),
                this.coordinates.getHeight()/window.getHeight()
        );
        coordOffset = new Vector2D(
                -this.coordinates.getWidth()/2,
                -this.coordinates.getHeight()/2
        );
    }

    public Point2D getCoordinate(Vector2D pixel) {
        double x = pixel.getX() * coordScale.getX() + coordOffset.getX();
        double y = pixel.getY() * coordScale.getY() + coordOffset.getY();
        return new Point2D.Double(x, y);
    }
}
