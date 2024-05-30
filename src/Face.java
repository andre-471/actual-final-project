import java.awt.*;

public class Face implements Drawable {
    private Color color;
    private Vertex[] vertices;

    public Face (Color color, Vertex p1, Vertex p2, Vertex p3) {
        this.color = color;
        this.vertices = new Vertex[]{p1, p2, p3};
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
    }
}
