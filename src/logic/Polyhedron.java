package logic;

import graphic.Drawable;
import java.awt.*;

public class Polyhedron implements Drawable {
    private Face[] faces;
    private Vertex center;
    private Vertex[] vertices;

    public Polyhedron(Vertex center, Face... faces) {
        this.center = center;
        this.faces = faces;
    }
    public Polyhedron() {

    }

    @Override
    public void draw(Graphics2D g2D) {
        for (Face face : faces) {
            face.draw(g2D);
        }
        center.draw(g2D);
    }

    public void setFaces(Face[] faces) {
        this.faces = faces;
    }

    public void setCenter(Vertex center) {
        this.center = center;
    }

    public void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }

    public Face[] getFaces() {
        return faces;
    }

    public void rotateXAxis(double theta, double rY, double rZ) {
        for (Vertex vertex : vertices) {
            vertex.rotateXAxis(theta, rY, rZ);
        }
        center.rotateXAxis(theta, rY, rZ);
    }

    public void rotateYAxis(double theta, double rX, double rZ) {
        for (Vertex vertex : vertices) {
            vertex.rotateYAxis(theta, rX, rZ);
        }
        center.rotateYAxis(theta, rX, rZ);
    }

    public void rotateZAxis(double theta, double rX, double rY) {
        for (Vertex vertex : vertices) {
            vertex.rotateZAxis(theta, rX, rY);
        }
        center.rotateZAxis(theta, rX, rY);
    }

    public void translate(double dx, double dy, double dz) {
        for (Vertex vertex : vertices) {
            vertex.translate(dx, dy, dz);
        }
        center.translate(dx, dy, dz);
    }
}