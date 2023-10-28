package math;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ViewOperator {
    private Dimension winSize;
    private Rectangle2D.Double viewBox;
    private Vector2D zoom;
    private Vector2D translation;

    private final double ZOOM = 0.10;

    public ViewOperator() {
        this.viewBox = new Rectangle2D.Double(0, 0, 0, 0);
        this.zoom = new Vector2D(1, 1);
        this.translation = new Vector2D(0, 0);
    }

    public ViewOperator(Dimension windowSize) {
        this();
        this.winSize = windowSize;
        this.viewBox = new Rectangle2D.Double(0, 0, this.winSize.width, this.winSize.height);
    }

    public void zoom(double dxzoom, double dyzoom) {
        double xzoom = 1 + dxzoom;
        double yzoom = 1 + dyzoom;

        double widthScaled = viewBox.getWidth() * xzoom;
        double heightScaled = viewBox.getHeight() * yzoom;

        Vector2D zoomTranslation = new Vector2D(
                -widthScaled*0.5 + viewBox.getX() + viewBox.getWidth()*0.5,
                -heightScaled*0.5 + viewBox.getY() + viewBox.getHeight()*0.5
        );

        viewBox.setRect(
                zoomTranslation.getX(),
                zoomTranslation.getY(),
                widthScaled,
                heightScaled
        );
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
//        viewBox.setRect(
//                translation.getX(),
//                translation.getY(),
//                viewBox.getWidth(),
//                viewBox.getHeight()
//        );
    }

    public Rectangle2D getViewBox() {
        return viewBox;
    }

    public Rectangle2D getDrawBox() {
        Rectangle2D windowBox = new Rectangle2D.Double(
                0,
                0,
                winSize.width,
                winSize.height
        );
        Rectangle2D viewBox = getViewBox();
        return viewBox.createIntersection(windowBox);
    }

    public Vector2D relativePoint(Vector2D point) {
        double x = point.getX() - viewBox.getX();
        double y = point.getY() - viewBox.getY();
        return new Vector2D(x, y);
    }

    @Override
    public String toString() {
        return "ViewOperator{" +
                ", winSize=" + winSize +
                ", viewBox=" + viewBox +
                ", zoom=" + zoom +
                ", displacement=" + viewBox.getX() + ", "+ viewBox.getY() +
                '}';
    }
}
