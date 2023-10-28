package fractals.tools;

import java.awt.*;

public interface PrintableFractal {
    void paint(Graphics g);
    void setIterations(int iterations);
    void incrementIterations();
    void decrementIterations();
    void zoomUnits(int units);
    void zoomXUnits(int units);
    void zoomYUnits(int units);
    void translate(double dx, double dy);
}
