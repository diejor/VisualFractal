package math;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ViewOperator {
    private Dimension winSize;
    private Rectangle2D.Double drawBox;
    private Rectangle2D.Double viewBox;
    private Vector2D zoom;
    private Vector2D translation;

    private final double ZOOM = 0.10;

    public ViewOperator() {
        this.drawBox = new Rectangle2D.Double(0, 0, 0, 0);
        this.zoom = new Vector2D(1, 1);
        this.translation = new Vector2D(0, 0);
    }

    public ViewOperator(Dimension windowSize) {
        this();
        this.winSize = windowSize;
        this.drawBox = new Rectangle2D.Double(0, 0, this.winSize.width, this.winSize.height);
        this.viewBox = new Rectangle2D.Double(0, 0, this.winSize.width, this.winSize.height);
    }

    public void zoom(double dxzoom, double dyzoom) {
        double xzoom = 1 + dxzoom;
        double yzoom = 1 + dyzoom;

        double widthScaled, heightScaled;

        widthScaled = drawBox.getWidth() * xzoom;
        heightScaled = drawBox.getHeight() * yzoom;

        Vector2D zoomFix = zoomFix(
                new Vector2D(viewBox.getCenterX(), viewBox.getCenterY()),
                new Vector2D(drawBox.getX(), drawBox.getY()),
                xzoom,
                yzoom
        );

        drawBox.setRect(
                drawBox.getX() + zoomFix.getX(),
                drawBox.getY() + zoomFix.getY(),
                widthScaled,
                heightScaled
        );
    }

    Vector2D zoomFix(Vector2D zoomCenter, Vector2D pos,double xzoom, double yzoom) {
        Vector2D relativeToDraw = zoomCenter.substractR(pos);
        Vector2D scaled = relativeToDraw.multiplyR(xzoom, yzoom);
        Vector2D fixed = relativeToDraw.substractR(scaled);
        return fixed;
    }

    public void zoomXUnits(int units) {
        double scaleXFactor = ZOOM*units;
        zoom(scaleXFactor, 1);
    }

    public void zoomYUnits(int units) {
        double scaleXFactor = ZOOM*units;
        zoom(1, scaleXFactor);
    }

    public void zoomUnits(int units) {
        double scaleFactor = ZOOM*units;
        zoom(scaleFactor, scaleFactor);
    }

    public void translation(double dx, double dy) {
        translation.add(dx, dy);
        drawBox.setRect(
                drawBox.getX() + dx,
                drawBox.getY() + dy,
                drawBox.getWidth(),
                drawBox.getHeight()
        );
    }

    public Rectangle2D getDrawBox() {
        return drawBox;
    }

    public Rectangle2D getViewBox() {
        return viewBox;
    }

    public Vector2D relativePoint(Vector2D point) {
        double x = point.getX() - drawBox.getX();
        double y = point.getY() - drawBox.getY();
        return new Vector2D(x, y);
    }

    @Override
    public String toString() {
        return "ViewOperator{" +
                ", winSize=" + winSize +
                ", viewBox=" + drawBox +
                ", zoom=" + zoom +
                ", displacement=" + drawBox.getX() + ", "+ drawBox.getY() +
                '}';
    }
}
