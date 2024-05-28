import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class Vector implements Drawable {
    private Vertex head;
    private Vertex tail;
    private Polygon arrowHead;


    public Vector(Vertex head, Vertex tail, int arrowHeadSize) {
        this.head = head;
        this.tail = tail;
        arrowHead = new Polygon(new int[]{arrowHeadSize, -arrowHeadSize, -arrowHeadSize},
                new int[]{0, -arrowHeadSize, arrowHeadSize},
                3);
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.draw(new Line2D.Double(tail.getX(), tail.getY(), head.getX(), head.getY()));
        AffineTransform tx = AffineTransform.getTranslateInstance(head.getX(), head.getY());
        tx.rotate(Math.atan2(head.getY() - tail.getY(), head.getX() - tail.getY()));

        g2D.fill(tx.createTransformedShape(arrowHead));
    }

    public void rotateZAxis(double theta, int rX, int rY) {
        head.rotateZAxis(theta, rX, rY);
        tail.rotateZAxis(theta, rX, rY);
    }

    public void rotateXAxis(double theta, int rY, int rZ) {
        head.rotateXAxis(theta, rY, rZ);
        tail.rotateXAxis(theta, rY, rZ);
    }

    public void rotateYAxis(double theta, int rX, int rZ) {
        head.rotateYAxis(theta, rX, rZ);
        tail.rotateYAxis(theta, rX, rZ);
    }

    public Vertex getTail() {
        return tail;
    }
}
