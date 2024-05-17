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
    private ArrayList<Image> images;
    private Thread thread;

    public GraphicPanel() {images = new ArrayList<>();
        try {
            images.add(new Image(ImageIO.read(new File("resources/treasure.jpg")), 0, 100, 100, 1000, 1000));
            images.add(new Image(ImageIO.read(new File("resources/treasure.jpg")), 0, 300, 100, 1000, 1000));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

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
                images.forEach(image -> {
                    image.rotate(1);
                    image.move(0, 2);
                });
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

        images.forEach(image -> image.draw(g2D));

        g2D.setFont(new Font("Arial", Font.PLAIN, 18));
        g2D.setColor(Color.red);
        g2D.drawString("its raining treasure", 400, 100);
        g2D.setColor(Color.black);
        g2D.drawOval(380, 100 - 18, 180, 30);
        g2D.setColor(Color.green);
        g2D.draw3DRect(400, 400, 100, 100, true);
        g2D.fill3DRect(400, 400, 100, 100, true);
        g2D.setColor(Color.black);
        g2D.drawPolygon(new int[]{500, 600, 500}, new int[]{600, 500, 500}, 3);
        g2D.drawPolygon(new int[]{random.nextInt(1001), random.nextInt(1001), random.nextInt(1001), random.nextInt(1001)},
                new int[]{random.nextInt(1001), random.nextInt(1001), random.nextInt(1001), random.nextInt(1001)},
                4);
    }

    private void setUpPanel() {
        this.setDoubleBuffered(true);
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.requestFocus();
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
