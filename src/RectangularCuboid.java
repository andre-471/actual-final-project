import java.awt.*;

import static java.lang.Math.abs;

public class RectangularCuboid extends Polyhedron {
    private Vertex a;
    private Vertex b;
    private Vertex c;
    private Vertex d;
    private Vertex e;
    private Vertex f;
    private Vertex g;
    private Vertex h;
    private Vertex center;


    public RectangularCuboid(Vertex a, Vertex g) {
        super();
        this.a = a;
        this.g = g;
        b = new Vertex(g.getX(), a.getY(), a.getZ());
        c = new Vertex(g.getX(), g.getY(), a.getZ());
        d = new Vertex(a.getX(), g.getY(), a.getZ());
        e = new Vertex(a.getX(), a.getY(), g.getZ());
        f = new Vertex(g.getX(), a.getY(), g.getZ());
        h = new Vertex(a.getX(), g.getY(), g.getZ());
        setVertices(new Vertex[]{a, b, c, d, e, f, g, h});
        center = new Vertex((g.getX() + a.getX()) / 2, (g.getY() + a.getY()) / 2, (g.getZ() + a.getZ()) / 2);
        setCenter(center);
        setEdges(new Edge[]{new Edge(a, b), new Edge(b, c), new Edge(c, d), new Edge(d, a), new Edge(e, f), new Edge(f, g), new Edge(g, h), new Edge(h, e)});
    }

    @Override
    public void draw(Graphics2D g2D) {
        super.draw(g2D);
        g2D.drawLine(a.getIntX(), a.getIntY(), f.getIntX(), f.getIntY());
        g2D.drawLine(b.getIntX(), b.getIntY(), e.getIntX(), e.getIntY());
        g2D.drawLine(c.getIntX(), c.getIntY(), g.getIntX(), g.getIntY());
        g2D.drawLine(d.getIntX(), d.getIntY(), g.getIntX(), g.getIntY());
        g2D.drawLine(a.getIntX(), a.getIntY(), d.getIntX(), d.getIntY());
        g2D.drawLine(e.getIntX(), e.getIntY(), g.getIntX(), g.getIntY());

        g2D.drawLine(b.getIntX(), b.getIntY(), g.getIntX(), g.getIntY());
        g2D.drawLine(c.getIntX(), c.getIntY(), f.getIntX(), f.getIntY());
        g2D.drawOval(center.getIntX() - 5, center.getIntY() - 5, 10, 10);
    }

    public Vertex getCenter() {
        return center;
    }
}
