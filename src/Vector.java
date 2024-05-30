import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class Vector {
    private double i;
    private double j;
    private double k;
    private Polygon arrowHead;

    public static Vector normalize(Vector v1, Vector v2) {
        return new Vector(
                v1.j * v2.k - v1.k * v2.j,
                v1.k * v2.i - v1.i * v2.k,
                v1.i * v2.j - v1.j * v2.i
        );
    }

    public Vector(double i, double j, double k) {
        this.i = i;
        this.j = j;
        this.k = k;
    }

    public Vector(Vertex p1, Vertex p2) {
        this.i = p2.getX() - p1.getX();
        this.j = p2.getY() - p1.getY();
        this.k = p2.getZ() - p1.getZ();
    }

    private Vector subtract(Vector other) {
        return new Vector(this.i - other.i, this.j - other.j, this.k - other.k);
    }
}
