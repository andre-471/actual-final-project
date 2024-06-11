package logic;

import java.awt.*;


public class Face {
    private Color color;
    private Vertex[] vertices;
    private Vertex p1; 
    private Vertex p2; 
    private Vertex p3;

    public Face(Color color, Vertex p1, Vertex p2, Vertex p3) {
        this.color = color;
        vertices = new Vertex[]{p1, p2, p3};
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public void rotateXAxis(double theta, double rY, double rZ) {
        for (Vertex vertex : vertices) {
            vertex.rotateXAxis(theta, rY, rZ);
        }
    }

    public void rotateYAxis(double theta, double rX, double rZ) {
        for (Vertex vertex : vertices) {
            vertex.rotateYAxis(theta, rX, rZ);
        }
    }

    public void rotateZAxis(double theta, double rX, double rY) {
        for (Vertex vertex : vertices) {
            vertex.rotateZAxis(theta, rX, rY);
        }
    }

    public void translate(double dx, double dy, double dz) {
        for (Vertex vertex : vertices) {
            vertex.translate(dx, dy, dz);
        }
    }

    public Color getColor() {
        return color;
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public Vertex getP1() {
        return p1;
    }

    public Vertex getP2() {
        return p2;
    }

    public Vertex getP3() {
        return p3;
    }

    public boolean vertexInFace2D(Vertex vertex) {
        Vector2 v12 = new Vector2(p1, p2);
        Vector2 v23 = new Vector2(p2, p3);
        Vector2 v31 = new Vector2(p3, p1);
        Vector2 v1v = new Vector2(p1, vertex);
        Vector2 v2v = new Vector2(p2, vertex);
        Vector2 v3v = new Vector2(p3, vertex);

        double c1 = Vector2.cross(v12, v1v);
        double c2 = Vector2.cross(v23, v2v);
        double c3 = Vector2.cross(v31, v3v);

        return c1 >= 0 && c2 >= 0 && c3 >= 0;
    }

    public boolean facingCamera() {
        Vector2 v12 = new Vector2(p1, p2);
        Vector2 v23 = new Vector2(p2, p3);
        Vector2 v31 = new Vector2(p3, p1);

        double c1 = Vector2.cross(v12, v23);
        double c2 = Vector2.cross(v23, v31);
        double c3 = Vector2.cross(v31, v12);

        return c1 >= 0 && c2 >= 0 && c3 >= 0;
    }
}
