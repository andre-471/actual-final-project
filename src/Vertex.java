import java.awt.*;

public class Vertex {
    private double x;
    private double y;
    private double z;

    public Vertex(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void draw(Graphics2D g2D) {
        g2D.fillRect((int) x, (int) y, 1, 1);
    }
}
