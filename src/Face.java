import java.awt.*;
import java.awt.image.BufferedImage;

public class Face implements Drawable {
    private Color color;
    private Vertex[] vertices;

    public Face (Color color, Vertex p1, Vertex p2, Vertex p3) {
        this.color = color;
        this.vertices = new Vertex[]{p1, p2, p3};
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

    @Override
    public void draw(Graphics2D g2D) {
        Polygon polygon = new Polygon();
        for (Vertex vertex : vertices) {
            polygon.addPoint(vertex.getIntX(), vertex.getIntY());
        }
        g2D.setColor(color);
        g2D.fillPolygon(polygon);
        g2D.setColor(Color.BLACK);
        g2D.drawPolygon(polygon);

    }
}
