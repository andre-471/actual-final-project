package logic;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Vector2 {
    // dont do this its bad
    public double x;
    public double y;
    public Vertex p1;
    public Vertex p2;
    private Polygon arrowHead;

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
        arrowHead = new Polygon(new int[]{5, -5, -5},
                new int[]{0, -5, 5},
                3);
    }

    public Vector2(Vertex p1, Vertex p2) {
        this.x = p2.getX() - p1.getX();
        this.y = p2.getY() - p1.getY();
        this.p1 = p1;
        this.p2 = p2;
        arrowHead = new Polygon(new int[]{5, -5, -5},
                new int[]{0, -5, 5},
                3);
    }

    public Vector2 subtract(Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    public void draw(Graphics2D g2D) {
        g2D.drawLine(p1.getIntX(), p1.getIntY(), p2.getIntX(), p2.getIntY());
        AffineTransform tx = AffineTransform.getTranslateInstance(p2.getX(), p2.getY());
        tx.rotate(Math.atan2(p2.getY() - p1.getY(), p2.getX() - p1.getX()));

        g2D.fill(tx.createTransformedShape(arrowHead));
    }
}