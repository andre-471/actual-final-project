import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphicPanel extends JPanel implements Runnable {
    private static final int FPS = 60;
    private static final double NS = 1000000000.0;
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 1000;
    private ArrayList<Vertex> vertices;
    private RectangularCuboid square;
    private Vector vector;
    private Thread thread;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;

    public GraphicPanel() {
        vertices = new ArrayList<>();
        vertices.add(new Vertex(100, 400, 100));

        square = new RectangularCuboid(new Vertex(500, 500, 50), new Vertex(600, 600, -50));
        vector = new Vector(new Vertex(100, 100, 0), new Vertex(0, 0, 0), 10);
        setUpPanel();
        setUpWindow();
        startThread();
    }


    @Override
    public void run() {
        long currentTime = System.nanoTime();
        long previousTime = currentTime;
        double delta = 0.0;
        double frameInterval = NS / FPS; // nanoseconds PER frame

        while (thread.isAlive()) {
            currentTime = System.nanoTime();
            delta += (currentTime - previousTime);
            previousTime = currentTime;

            if (delta >= frameInterval) {
                Vertex center = square.getCenter();
                Vertex otherCenter = vector.getTail();
                if (mouseHandler.leftMousePressedInScreen()) {
                    Dimension change = mouseHandler.getDeltaSinceLastHeld();
                    square.rotateYAxis(change.width, (int) center.getX(), (int) center.getZ());
                    square.rotateXAxis(-change.height, (int) center.getY(), (int) center.getZ());
                }

                if (keyHandler.keyWPressed()) {
                    square.rotateXAxis(1, (int) center.getY(), (int) center.getZ());
                    vector.rotateXAxis(1, otherCenter.getIntY(), otherCenter.getIntZ());
                }
                if (keyHandler.keySPressed()) {
                    square.rotateXAxis(-1, (int) center.getY(), (int) center.getZ());
                }
                if (keyHandler.keyAPressed()) {
                    square.rotateYAxis(-1, (int) center.getX(), (int) center.getZ());
                }
                if (keyHandler.keyDPressed()) {
                    square.rotateYAxis(1, (int) center.getX(), (int) center.getZ());
                }
                if (keyHandler.keyQPressed()) {
                    square.rotateZAxis(-1, (int) center.getX(), (int) center.getY());
                    vector.rotateZAxis(-1, otherCenter.getIntX(), otherCenter.getIntY());
                }
                if (keyHandler.keyEPressed()) {
                    square.rotateZAxis(1, (int) center.getX(), (int) center.getY());
                    vector.rotateZAxis(1, otherCenter.getIntX(), otherCenter.getIntY());
                }

                repaint();

                delta = 0.0;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;
        g2D.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

        vertices.forEach(vertex -> vertex.draw(g2D));
        square.draw(g2D);
        vector.draw(g2D);
    }

    private void setUpPanel() {
        this.setDoubleBuffered(true);
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.requestFocusInWindow();
        keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);
        mouseHandler = new MouseHandler(this);
        this.addMouseListener(mouseHandler);
    }

    private void setUpWindow() {
        JFrame window = new JFrame();
        window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
//        window.setResizable(false);
        window.add(this);
        window.setVisible(true);
    }

    private void startThread() {
        thread = new Thread(this);
        thread.start();
    }
}
