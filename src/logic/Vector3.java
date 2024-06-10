package logic;

public class Vector3 extends Vector2 {
    public double z;

    public static Vector3 cross(Vector3 v1, Vector3 v2) {
        return new Vector3(
                v1.y * v2.z - v1.z * v2.y,
                v1.z * v2.x - v1.x * v2.z,
                v1.x * v2.y - v1.y * v2.x
        );
    }

    public static double dot(Vector3 v1, Vector3 v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public Vector3(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public Vector3(Vertex p1, Vertex p2) {
        super(p1, p2);
        this.z = p2.getZ() - p1.getZ();
    }

    public Vector3 subtract(Vector3 other) {
        return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
    }
}
