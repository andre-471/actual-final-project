import java.awt.*;

public class Polyhedron {
    private Vertex[] vertices;

    public Polyhedron(Vertex... vertices) {
        this.vertices = vertices;
    }

    public void draw(Graphics2D g2D) {
        for (int i = 0; i < vertices.length; i++) {
            vertices[i].draw(g2D);
            Point a = vertices[i].get2DPoint();
            Point b = vertices[(i + 1) % vertices.length].get2DPoint();

            g2D.drawLine(a.x, a.y, b.x, b.y);
        }
    }

    public void rotateZAxis(int theta, int rX, int rY) {
        for (Vertex vertex : vertices) {
            vertex.rotateZAxis(theta, rX, rY);
        }
    }

    public void rotateXAxis(int theta, int rY, int rZ) {
        for (Vertex vertex : vertices) {
            vertex.rotateXAxis(theta, rY, rZ);
        }
    }

    public void rotateYAxis(int theta, int rX, int rZ) {
        for (Vertex vertex : vertices) {
            vertex.rotateYAxis(theta, rX, rZ);
        }
    }
}