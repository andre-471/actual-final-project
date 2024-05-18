import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    private boolean leftMouse;
    private boolean inScreen;
    private Point lastPoint;

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftMouse = true;
            lastPoint = e.getPoint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftMouse = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        inScreen = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        inScreen = false;
    }

    public boolean leftMousePressed() {
        return leftMouse && inScreen;
    }

    public Point getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(Point lastPoint) {
        this.lastPoint = lastPoint;
    }
}
