import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    private GraphicPanel graphicPanel;
    private boolean leftMouse;
    private boolean inScreen;
    private Point lastHeldPos;

    public MouseHandler(GraphicPanel graphicPanel) {
        this.graphicPanel = graphicPanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftMouse = true;
            lastHeldPos = e.getPoint();
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
        lastHeldPos = e.getPoint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        inScreen = false;
    }

    public boolean leftMousePressedInScreen() {
        return leftMouse && inScreen;
    }

    public Dimension getDeltaSinceLastHeld() {
        Point currPos = graphicPanel.getMousePosition();
        int dx = currPos.x - lastHeldPos.x;
        int dy = currPos.y - lastHeldPos.y;
        lastHeldPos = currPos;

        return new Dimension(dx, dy);
    }
}
