import java.awt.*;

public class Vector {
    private double x;
    private double y;
    private double z;
    private Polygon arrowHead;

    public static Vector normalize(Vector v1, Vector v2) {
        return new Vector(
                v1.y * v2.z - v1.z * v2.y,
                v1.z * v2.x - v1.x * v2.z,
                v1.x * v2.y - v1.y * v2.x
        );
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Vertex p1, Vertex p2) {
        this.x = p2.getX() - p1.getX();
        this.y = p2.getY() - p1.getY();
        this.z = p2.getZ() - p1.getZ();
    }

    private Vector subtract(Vector other) {
        return new Vector(this.x - other.x, this.y - other.y, this.z - other.z);
    }
}
