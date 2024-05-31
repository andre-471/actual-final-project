import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class GraphicPanel extends JPanel implements Runnable {
    private static final int FPS = 60;
    private static final double NS = 1000000000.0;
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 1000;
    private ArrayList<Vertex> vertices;
    private RectangularCuboid square;
    private Thread thread;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;

    public GraphicPanel() {
        vertices = new ArrayList<>();
        vertices.add(new Vertex(100, 400, 100));

        square = new RectangularCuboid(new Vertex(500, 500, 50), new Vertex(600, 600, -50));
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
                if (mouseHandler.leftMousePressedInScreen()) {
                    Dimension change = mouseHandler.getDeltaSinceLastHeld();
                    square.rotateYAxis(change.width, (int) center.getX(), (int) center.getZ());
                    square.rotateXAxis(-change.height, (int) center.getY(), (int) center.getZ());
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

        square.draw(g2D);
    }

    private void faceDraw() {
       Face[] faces = square.getFaces();

       double[][] zBuffer = new double[1000][1000];
        for (double[] doubles : zBuffer) {
            Arrays.fill(doubles, Double.NEGATIVE_INFINITY);
        }
        BufferedImage bufferedImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_4BYTE_ABGR);



        for (Face face : faces) {
            double minX = Double.POSITIVE_INFINITY;
            double minY = Double.POSITIVE_INFINITY;
            double maxX = Double.NEGATIVE_INFINITY;
            double maxY = Double.NEGATIVE_INFINITY;

            for (Vertex vertex : face.getVertices()) {
                minX = Math.min(minX, vertex.getX());
                minY = Math.min(minY, vertex.getY());
                maxX = Math.max(maxX, vertex.getX());
                maxY = Math.max(maxY, vertex.getY());
            }
            minX = Math.floor(minX);
            minY = Math.floor(minY);
            maxX = Math.ceil(maxX);
            maxY = Math.ceil(maxY);
            for (int i = (int) minX; i <= maxX; i++) {
                bufferedImage.setRGB(i, minY, color.getRGB());
                bufferedImage.setRGB(i, maxY, color.getRGB());
            }
            for (int i = (int) minY; i <= maxY; i++) {
                bufferedImage.setRGB(minX, i, color.getRGB());
                bufferedImage.setRGB(maxX, i, color.getRGB());
            }
        }
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
