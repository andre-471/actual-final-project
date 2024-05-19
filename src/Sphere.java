import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.QuadCurve2D;

public class Sphere extends Polyhedron {
    private Vertex center;
    private int radius;
    private Vertex x1, x2;
    private Vertex y1, y2;
    private Vertex z1, z2;

    public Sphere(Vertex center, int radius) {
        super();
        this.center = center;
        this.radius = radius;
        x1 = new Vertex(center.getX() - radius, center.getY(), center.getZ());
        x2 = new Vertex(center.getX() + radius, center.getY(), center.getZ());
        y1 = new Vertex(center.getX(), center.getY() - radius, center.getZ());
        y2 = new Vertex(center.getX(), center.getY() + radius, center.getZ());
        z1 = new Vertex(center.getX(), center.getY(), center.getZ() - radius);
        z2 = new Vertex(center.getX(), center.getY(), center.getZ() + radius);

        setVertices(new Vertex[]{x1, x2, y1, y2, z1, z2});
    }

    @Override
    public void draw(Graphics2D g2D) {
        super.draw(g2D);

        g2D.drawArc(x1.get2DPoint().x, y1.get2DPoint().y, radius, radius, 180, 90);
    }

    public Vertex getCenter() {
        return center;
    }
}
