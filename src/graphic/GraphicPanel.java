package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import logic.*;
import logic.Face;

public class GraphicPanel extends JPanel implements Runnable {
    private static final int FPS = 60;
    private static final double NS = 1000000000.0;
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 1000;
    private ArrayList<Vertex> vertices;
    private RectangularCuboid square;
    private RectangularCuboid square2;
    private Thread thread;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;
    private PolyManager polyManager;

    public GraphicPanel() {
        vertices = new ArrayList<>();
        vertices.add(new Vertex(100, 400, 100));

        square = new RectangularCuboid(new Vertex(500, 500, 50), new Vertex(600, 600, -50));
        square2 = new RectangularCuboid(new Vertex(400,400,60),
                new Vertex(550, 550, -40));
        setUpOthers();
        setUpPanel();
        setUpWindow();
        polyManager.addPolyhedron(square);
        polyManager.addPolyhedron(square2);
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
                if (mouseHandler.leftMousePressedInScreen()) {
                    Dimension change = mouseHandler.getDeltaSinceLastHeld();
                    square.rotateYAxis(change.width, (int) center.getX(), (int) center.getZ());
                    square.rotateXAxis(-change.height, (int) center.getY(), (int) center.getZ());
                    square2.rotateYAxis(change.width, (int) square2.getCenter().getX(), (int) square2.getCenter().getZ());
                    square2.rotateXAxis(-change.height, (int) square2.getCenter().getY(), (int) square2.getCenter().getZ());
                }

                if (keyHandler.keyWPressed()) {
                    square.rotateXAxis(1, (int) center.getY(), (int) center.getZ());
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
                }
                if (keyHandler.keyEPressed()) {
                    square.rotateZAxis(1, (int) center.getX(), (int) center.getY());
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

//        square.draw(g2D);
        polyManager.drawAllFaces(g2D);
        polyManager.showPoints(g2D);
    }

    private void setUpOthers() {
        polyManager = new PolyManager(this);
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
