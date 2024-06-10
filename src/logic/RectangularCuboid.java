package logic;

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
        System.out.println(a.getIntZ());
        setOtherItems();
    }

    private void setOtherItems() {
        setVertices(new Vertex[]{a, b, c, d, e, f, g, h});
        center = new Vertex((g.getX() + a.getX()) / 2, (g.getY() + a.getY()) / 2, (g.getZ() + a.getZ()) / 2);
        setCenter(center);
        setFaces(new Face[]{
                new Face(Color.BLUE, a, b, d), new Face(Color.BLUE, c, d, b),
                new Face(Color.RED, f, e, g), new Face(Color.RED, h, g, e),
                new Face(Color.GREEN, b, f, c), new Face(Color.GREEN, g, c, f),
                new Face(Color.YELLOW, e, a, h), new Face(Color.YELLOW, d, h, a),
                new Face(Color.ORANGE, e, f, a), new Face(Color.ORANGE, b, a, f),
                new Face(Color.CYAN, d, c, h), new Face(Color.CYAN, g, h, c)
        });
    }

    public RectangularCuboid(Vertex a, double width, double height, double depth) {
        super();
        this.a = a;
        b = new Vertex(a);
        b.translate(width, 0, 0);
        c = new Vertex(a);
        c.translate(width, height, 0);
        d = new Vertex(a);
        d.translate(0, height, 0);
        e = new Vertex(a);
        e.translate(0, 0, depth);
        f = new Vertex(a);
        f.translate(width, 0, depth);
        g = new Vertex(a);
        g.translate(width, height, depth);
        h = new Vertex(a);
        h.translate(0, height, depth);
        setOtherItems();
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
