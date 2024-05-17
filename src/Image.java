import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Image {
    private BufferedImage image;
    private double theta;
    private int x;
    private int y;
    private int boundX;
    private int boundY;

    public Image(BufferedImage image, double theta, int x, int y, int boundX, int boundY) {
        this.image = image;
        this.theta = theta;
        this.x = x;
        this.y = y;
        this.boundX = boundX;
        this.boundY = boundY;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        x %= boundX + 1;
        y %= boundY + 1;
    }

    public void rotate(double dtheta) {
        theta += dtheta;
    }

    public void draw(Graphics2D g2D) {
        double midX = image.getWidth() / 2.0;
        double midY = image.getHeight() / 2.0;
        AffineTransform transform = AffineTransform.getTranslateInstance(x, y);
        transform.rotate(Math.toRadians(theta));
        transform.translate(-midX, -midY);

        g2D.drawImage(image, transform, null);
        g2D.setColor(Color.red);
        g2D.drawRect(x, y, 1, 1);
        g2D.setColor(Color.black);
    }
}
