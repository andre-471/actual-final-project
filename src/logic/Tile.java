package logic;

import java.awt.*;

import static java.lang.Math.abs;

public class Tile extends Polyhedron {
    private Vertex a;
    private Vertex b;
    private Vertex c;
    private Vertex d;
    private Vertex e;
    private Vertex f;
    private Vertex g;
    private Vertex h;
    private boolean hasBomb;
    private int numOfBombNear;


    public Tile(Vertex a, Vertex g) {
        super();
        this.a = a;
        this.g = g;
        b = new Vertex(g.getX(), a.getY(), a.getZ());
        c = new Vertex(g.getX(), g.getY(), a.getZ());
        d = new Vertex(a.getX(), g.getY(), a.getZ());
        e = new Vertex(a.getX(), a.getY(), g.getZ());
        f = new Vertex(g.getX(), a.getY(), g.getZ());
        h = new Vertex(a.getX(), g.getY(), g.getZ());
        setOtherItems();
        hasBomb = Math.random() >= 0.8;
        numOfBombNear = 0;
    }

    public Tile(Vertex a, double width, double height, double depth) {
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
        hasBomb = Math.random() >= 0.8;
        numOfBombNear = 0;
    }

    private void setOtherItems() {
        setVertices(new Vertex[]{a, b, c, d, e, f, g, h});
        Vertex center = new Vertex((g.getX() + a.getX()) / 2, (g.getY() + a.getY()) / 2, (g.getZ() + a.getZ()) / 2);
        setCenter(center);
        setFaces(new Face[]{
                new Face(Color.LIGHT_GRAY, a, b, d), new Face(Color.LIGHT_GRAY, c, d, b),
                new Face(Color.GRAY, f, e, g), new Face(Color.GRAY, h, g, e),
                new Face(Color.LIGHT_GRAY, b, f, c), new Face(Color.LIGHT_GRAY, g, c, f),
                new Face(Color.GRAY, e, a, h), new Face(Color.GRAY, d, h, a),
                new Face(Color.LIGHT_GRAY, e, f, a), new Face(Color.LIGHT_GRAY, b, a, f),
                new Face(Color.GRAY, d, c, h), new Face(Color.GRAY, g, h, c)
        });
    }

    public void setNumOfBombNear(int numOfBombNear) {
        this.numOfBombNear = numOfBombNear;
    }
}
