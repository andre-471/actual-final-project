import java.awt.*;
import java.awt.geom.Rectangle2D;

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
        setEdges(new Edge[]{new Edge(a, b), new Edge(b, c), new Edge(c, d), new Edge(d, a), new Edge(e, f), new Edge(f, g), new Edge(g, h), new Edge(h, e), new Edge(a, e), new Edge(b, f), new Edge(c, g), new Edge(d, h)});
        setFaces(new Face[]{
                new Face(Color.BLUE, a, b, d), new Face(Color.BLUE, c, d, b),
                new Face(Color.RED, e, f, h), new Face(Color.RED, g, h, f),
                new Face(Color.GREEN, b, f, c), new Face(Color.GREEN, g, c, f),
                new Face(Color.YELLOW, e, a, h), new Face(Color.YELLOW, d, h, a),
                new Face(Color.ORANGE, e, f, h), new Face(Color.ORANGE, g, h, f),
                new Face(Color.CYAN, d, c, h), new Face(Color.CYAN, g, h, c)
        });
    }

    @Override
    public void draw(Graphics2D g2D) {
        super.draw(g2D);
        g2D.drawString("A", a.getIntX(), a.getIntY());
        g2D.drawString("B", b.getIntX(), b.getIntY());
        g2D.drawString("C", c.getIntX(), c.getIntY());
        g2D.drawString("D", d.getIntX(), d.getIntY());
        g2D.drawString("E", e.getIntX(), e.getIntY());
        g2D.drawString("F", f.getIntX(), f.getIntY());
        g2D.drawString("G", g.getIntX(), g.getIntY());
        g2D.drawString("H", h.getIntX(), h.getIntY());
    }

    public Vertex getCenter() {
        return center;
    }
}
