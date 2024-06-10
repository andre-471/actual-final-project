package graphic;

import java.awt.*;

public class Camera {
    private GraphicPanel graphicPanel;
    private double thetaX;
    private double thetaY;
    private double thetaZ;
    private double x;
    private double y;
    private double z;

    public Camera(GraphicPanel graphicPanel) {
        this.graphicPanel = graphicPanel;
        thetaX = 0.0;
        thetaY = 0.0;
        thetaZ = 0.0;
        x = 0.0;
        y = 0.0;
        z = 0.0;
    }

    public void draw(Graphics2D g2D) {

    }
}
