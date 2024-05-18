import java.awt.*;

import static java.lang.Math.abs;

public class RectangularPrism extends Polyhedron {
    private Vertex x1;
    private Vertex x8;
    private Vertex x2;
    private Vertex x3;
    private Vertex x4;
    private Vertex x5;
    private Vertex x6;
    private Vertex x7;
    private Vertex center;


    public RectangularPrism(Vertex x1, Vertex x8) {
        super();
        this.x1 = x1;
        this.x8 = x8;
        x2 = new Vertex(x8.getX(), x1.getY(), x1.getZ());
        x3 = new Vertex(x8.getX(), x8.getY(), x1.getZ());
        x4 = new Vertex(x1.getX(), x8.getY(), x1.getZ());
        x5 = new Vertex(x8.getX(), x1.getY(), x8.getZ());
        x6 = new Vertex(x1.getX(), x1.getY(), x8.getZ());
        x7 = new Vertex(x1.getX(), x8.getY(), x8.getZ());
        setVertices(new Vertex[]{x1, x2, x3, x4, x5, x6, x7, x8});
        center = new Vertex((x8.getX() + x1.getX()) / 2, (x8.getY() + x1.getY()) / 2, (x8.getZ() + x1.getZ()) / 2);
    }

    @Override
    public void draw(Graphics2D g2D) {
        super.draw(g2D);
        g2D.drawLine(x1.get2DPoint().x, x1.get2DPoint().y, x6.get2DPoint().x, x6.get2DPoint().y);
        g2D.drawLine(x2.get2DPoint().x, x2.get2DPoint().y, x5.get2DPoint().x, x5.get2DPoint().y);
        g2D.drawLine(x3.get2DPoint().x, x3.get2DPoint().y, x8.get2DPoint().x, x8.get2DPoint().y);
        g2D.drawLine(x4.get2DPoint().x, x4.get2DPoint().y, x7.get2DPoint().x, x7.get2DPoint().y);
        g2D.drawLine(x1.get2DPoint().x, x1.get2DPoint().y, x4.get2DPoint().x, x4.get2DPoint().y);
        g2D.drawLine(x5.get2DPoint().x, x5.get2DPoint().y, x8.get2DPoint().x, x8.get2DPoint().y);

        g2D.drawLine(x2.get2DPoint().x, x2.get2DPoint().y, x7.get2DPoint().x, x7.get2DPoint().y);
        g2D.drawLine(x3.get2DPoint().x, x3.get2DPoint().y, x6.get2DPoint().x, x6.get2DPoint().y);
        g2D.drawOval((int) center.getX() - 5, (int) center.getY() - 5, 10, 10);

    }

    public Vertex getCenter() {
        return center;
    }
}
