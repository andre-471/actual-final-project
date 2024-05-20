import java.awt.*;

import static java.lang.Math.*;

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

    public Point get2DPoint() {
        return new Point((int) x, (int) y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
    
    public int getIntX() {
        return (int) x;
    }
    
    public int getIntY() {
        return (int) y;
    }

    public void translate(int dx, int dy, int dz) {
        x += dx;
        y += dy;
        z += dz;
    }


    public void rotateXAxis(double theta, int rY, int rZ) {
        translate(0, -rY, -rZ);
        double thetaR = toRadians(theta);
        double yP = y * cos(thetaR) - z * sin(thetaR);
        double zP = y * sin(thetaR) + z * cos(thetaR);
        y = yP;
        z = zP;
        translate(0, rY, rZ);
    }

    public void rotateYAxis(double theta, int rX, int rZ) {
        translate(-rX, 0, -rZ);
        double thetaR = toRadians(theta);
        double xP = x * cos(thetaR) + z * sin(thetaR);
        double zP = -x * sin(thetaR) + z * cos(thetaR);
        x = xP;
        z = zP;
        translate(rX, 0, rZ);
    }

    public void rotateZAxis(double theta, int rX, int rY) {
        translate(-rX, -rY, 0);
        double thetaR = toRadians(theta);
        double xP = x * cos(thetaR) - y * sin(thetaR);
        double yP = x * sin(thetaR) + y * cos(thetaR);
        x = xP;
        y = yP;
        translate(rX, rY, 0);
    }
}
