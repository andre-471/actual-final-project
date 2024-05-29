import java.awt.*;

public class Polyhedron implements Drawable {
    private Vertex[] vertices;
    private Edge[] edges;
    private Face[] faces;
    private Vertex center;

    public Polyhedron(Vertex[] vertices, Edge[] edges, Vertex center) {
        this.vertices = vertices;
        this.edges = edges;
        this.center = center;
    }

    public Polyhedron() {}

    protected void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }

    protected void setEdges(Edge[] edges) {
        this.edges = edges;
    }

    protected void setFaces(Face[] faces) {
        this.faces = faces;
    }

    protected void setCenter(Vertex center) {
        this.center = center;
    }

    public void draw(Graphics2D g2D) {
        for (Vertex vertex : vertices) {
            vertex.draw(g2D);
        }
        for (Face face : faces) {
            face.draw(g2D);
        }
        for (Edge edge : edges) {
            g2D.drawLine(edge.a().getIntX(),edge.a().getIntY(), edge.b().getIntX(), edge.b().getIntY());
        }
        center.draw(g2D);
    }

    public void rotateZAxis(double theta, int rX, int rY) {
        for (Vertex vertex : vertices) {
            vertex.rotateZAxis(theta, rX, rY);
        }
    }

    public void rotateXAxis(double theta, int rY, int rZ) {
        for (Vertex vertex : vertices) {
            vertex.rotateXAxis(theta, rY, rZ);
        }
    }

    public void rotateYAxis(double theta, int rX, int rZ) {
        for (Vertex vertex : vertices) {
            vertex.rotateYAxis(theta, rX, rZ);
        }
    }

    public void translate(int dx, int dy, int dz) {
        for (Vertex vertex : vertices) {
            vertex.translate(dx, dy, dz);
        }
    }
}