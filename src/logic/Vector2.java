package logic;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Vector2 {
    // dont do this its bad
    public double x;
    public double y;
    public Vertex p1;
    public Vertex p2;

    public static double cross(Vector2 v1, Vector2 v2) {
        return v1.x * v2.y - v1.y * v2.x;
    }

    public static double dot(Vector2 v1, Vector2 v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
        this.p1 = new Vertex(100, 100, 0);
        this.p2 = new Vertex(x + 100, y + 100, 0);
    }

    public Vector2(Vertex p1, Vertex p2) {
        this.x = p2.getX() - p1.getX();
        this.y = p2.getY() - p1.getY();
        this.p1 = p1;
        this.p2 = p2;
    }

    public Vector2 subtract(Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }
}