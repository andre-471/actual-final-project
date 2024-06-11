package graphic;

import javax.swing.*;
import java.awt.*;

import logic.*;

public class GraphicPanel extends JPanel implements Runnable {
    private static final int FPS = 60;
    private static final double NS = 1000000000.0;
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 1000;
//    private
    private Tile square;
    private Tile square2;
    private Thread thread;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;
    private TileManager tileManager;

    public GraphicPanel() {
        setUpPanel();
        setUpOthers();
        setUpWindow();
        tileManager.generateMap(3);
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
                tileManager.update();

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

        tileManager.drawAllFaces(g2D);
    }

    private void setUpOthers() {
        tileManager = new TileManager(this, keyHandler, mouseHandler, 50);
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
        window.setResizable(false);
        window.add(this);
        window.setVisible(true);
    }

    private void startThread() {
        thread = new Thread(this);
        thread.start();
    }
}
