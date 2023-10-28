package math;

import java.awt.*;
import java.util.Objects;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D () {
        this.x = 0.0D;
        this.y = 0.0D;
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D (double[] entries) {
        if (entries.length == 2) {
            this.x = entries[0];
            this.y = entries[1];

        } else {
            this.x = 0.0D;
            this.y = 0.0D;
            System.out.println("enter a 2D vector");
        }
    }

    public Vector2D (Vector2D vector) {
        this.x = vector.getX();
        this.y = vector.getY();
    }

    public Vector2D(Dimension size) {
        this.x = size.width;
        this.y = size.height;
    }

    public void add(Vector2D u) {
        setEntries(this.x + u.getX(), this.y + u.getY());
    }

    public Vector2D addR(Vector2D u) {
        return new Vector2D(this.x + u.getX(), this.y + u.getY());
    }

    public void addX(double dx) {
        setX(this.x + dx);
    }

    public void addY(double dy) {
        setY(this.y + dy);
    }

    public void substract(Vector2D u) {
        setEntries(this.x - u.getX(), this.y - u.getY());
    }

    public Vector2D substractR(Vector2D u) {
        return new Vector2D(this.x - u.getX(), this.y - u.getY());
    }

    public double dot(Vector2D u) {
        return this.x*u.getX() + this.y*u.getY();
    }

    public void scale(double gamma) {
        setEntries(x*gamma, y*gamma);
    }

    public void scaleX(double gamma) {
        setX(this.x * gamma);
    }

    public void scaleY(double gamma) {
        setY(this.y * gamma);
    }

    public Vector2D scaleR(double gamma) {
        return new Vector2D(this.x * gamma, this.y * gamma);
    }

    public Vector2D getUnit() {
         double len = getLength();
         if (len == 0)
             return new Vector2D();
         return new Vector2D(x/len, y/len);
    }

    public Vector2D getNormal() {
        Vector2D normal = getUnit();
        normal.rotate90();
        return normal;
    }

    public void rotate90() {
        double tmp = this.x;
        this.x = this.y * -1;
        this.y = tmp;
    }

    public Vector2D getRounded() {
        return new Vector2D(new double[]{Math.round(x), Math.round(y)});
    }

    /**
     * @param u
     * @return the angle between the given and 2D vector u
     */
    public double getAngle(Vector2D u) {
        return Math.acos( this.dot(u) / (this.getLength() * u.getLength()));
    }

    public double getPolar() {
        double length = getLength();
        double cos = x / length;
        double sin = y / length;

        if (cos < 0) {
            return Math.atan(sin / cos) + Math.PI;
        }

        else if (cos == 0) {
            if (sin > 0)
                return Math.PI/2;
            else if (sin < 0)
                return Math.PI*3/4;
            return 0;
        }
        else {
            return Math.atan(sin/cos);
        }
    }

    public Vector2D getDirecionTo(Vector2D focus) {
        Vector2D dir = new Vector2D(new double[]{focus.getX() - getX(), focus.getY() - getY()});
        return dir.getUnit();
    }

    public void setEntries(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setEntries(double[] entries) {
        setX(entries[0]);
        setY(entries[1]);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double[] getEntries() {
        return new double[]{x, y};
    }

    public int getXR() {
        return (int)Math.round(getX());
    }
    public int getYR() {
        return (int)Math.round(getY());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getLength() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public int getLengthR() {
        return (int)Math.round(getLength());
    }

    public void drawVector(Graphics g) {
        g.drawLine(0, 0, getXR(), getYR());
    }

    public void drawVector(Graphics g, Vector2D pos) {
        g.drawLine(pos.getXR(), pos.getYR(), pos.getXR()+ getXR(), pos.getYR() + getYR());
    }

    public void drawVector(Graphics g, Vector2D pos, int scale) {
        g.drawLine(pos.getXR(), pos.getYR(), (int)(pos.getXR()+ getX()*scale),
                (int)(pos.getYR() + getY()*scale));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        return Double.compare(vector2D.getX(), getX()) == 0 && Double.compare(vector2D.getY(), getY()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void add(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }
}
