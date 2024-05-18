import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GraphicPanel extends JPanel implements Runnable {
    private static final Random random = new Random();
    private static final int FPS = 60;
    private static final double NS = 1000000000.0;
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 1000;
    private ArrayList<Vertex> vertices;
    private Polyhedron triangle;
    private Thread thread;
    private KeyHandler keyHandler;

    public GraphicPanel() {
        vertices = new ArrayList<>();
        vertices.add(new Vertex(100, 400, 100));

        triangle = new Polyhedron(new Vertex(100, 100, 0),
                new Vertex(200, 100, 0),
                new Vertex(200, 200, 0),
                new Vertex(100, 200, 0),
                new Vertex(100, 200, 100),
                new Vertex(200, 100, 100),
                new Vertex(200, 200, 100));
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
                if (keyHandler.keyWPressed()) {
                    triangle.rotateXAxis(1, 150, 50);
                }
                if (keyHandler.keySPressed()) {
                    triangle.rotateXAxis(-1, 150, 50);
                }
                if (keyHandler.keyAPressed()) {
                    triangle.rotateYAxis(-1, 150, 50);
                }
                if (keyHandler.keyDPressed()) {
                    triangle.rotateYAxis(1, 150, 50);
                }
                if (keyHandler.keyQPressed()) {
                    triangle.rotateZAxis(-1, 150, 150);
                }
                if (keyHandler.keyEPressed()) {
                    triangle.rotateZAxis(1, 150, 150);
                }
//                triangle.rotateZAxis(1, 150, 150);

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
        triangle.draw(g2D);
    }

    private void setUpPanel() {
        this.setDoubleBuffered(true);
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.requestFocusInWindow();
        keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);
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
