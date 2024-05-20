import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphicPanel extends JPanel implements Runnable {
    private static final int FPS = 60;
    private static final double NS = 1000000000.0;
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 1000;
    private ArrayList<Vertex> vertices;
    private Polyhedron something;
    private RectangularPrism square;
    private Sphere sphere;
    private Thread thread;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;

    public GraphicPanel() {
        vertices = new ArrayList<>();
        vertices.add(new Vertex(100, 400, 100));

        something = new Polyhedron(new Vertex(100, 100, 0),
                new Vertex(200, 100, 0),
                new Vertex(200, 200, 0),
                new Vertex(100, 200, 0),
                new Vertex(100, 200, 100),
                new Vertex(200, 100, 100),
                new Vertex(200, 200, 100));
        square = new RectangularPrism(new Vertex(500, 500, 50), new Vertex(600, 600, -50));

        sphere = new Sphere(new Vertex(700, 700, 0), 50);
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
//                System.out.println(mouseHandler.leftMousePressed());
                if (mouseHandler.leftMousePressedInScreen()) {
                    Dimension change = mouseHandler.getDeltaSinceLastHeld();
                    something.rotateYAxis(change.width, 150, 50);
                    something.rotateXAxis(-change.height, 150, 50);
                    square.rotateYAxis(change.width, (int) center.getX(), (int) center.getZ());
                    square.rotateXAxis(-change.height, (int) center.getY(), (int) center.getZ());
                    sphere.rotateYAxis(change.width, (int) sphere.getCenter().getX(), (int) sphere.getCenter().getZ());
                    sphere.rotateXAxis(-change.height, (int) sphere.getCenter().getY(), (int) sphere.getCenter().getZ());
                }

                if (keyHandler.keyWPressed()) {
                    something.rotateXAxis(1, 150, 50);
                    square.rotateXAxis(1, (int) center.getY(), (int) center.getZ());
                    sphere.rotateXAxis(1, (int) sphere.getCenter().getY(), (int) sphere.getCenter().getZ());
                }
                if (keyHandler.keySPressed()) {
                    something.rotateXAxis(-1, 150, 50);
                    square.rotateXAxis(-1, (int) center.getY(), (int) center.getZ());
                    sphere.rotateXAxis(-1, (int) sphere.getCenter().getY(), (int) sphere.getCenter().getZ());
                }
                if (keyHandler.keyAPressed()) {
                    something.rotateYAxis(-1, 150, 50);
                    square.rotateYAxis(-1, (int) center.getX(), (int) center.getZ());
                    sphere.rotateYAxis(-1, (int) sphere.getCenter().getX(), (int) sphere.getCenter().getZ());
                }
                if (keyHandler.keyDPressed()) {
                    something.rotateYAxis(1, 150, 50);
                    square.rotateYAxis(1, (int) center.getX(), (int) center.getZ());
                    sphere.rotateYAxis(1, (int) sphere.getCenter().getX(), (int) sphere.getCenter().getZ());
                }
                if (keyHandler.keyQPressed()) {
                    something.rotateZAxis(-1, 150, 150);
                    square.rotateZAxis(-1, (int) center.getX(), (int) center.getY());
                    sphere.rotateZAxis(-1, (int) sphere.getCenter().getX(), (int) sphere.getCenter().getY());
                }
                if (keyHandler.keyEPressed()) {
                    something.rotateZAxis(1, 150, 150);
                    square.rotateZAxis(1, (int) center.getX(), (int) center.getY());
                    sphere.rotateZAxis(1, (int) sphere.getCenter().getX(), (int) sphere.getCenter().getY());
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
        something.draw(g2D);
        square.draw(g2D);
        sphere.draw(g2D);
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
