package drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import math.*;

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

    public GraphicPanel() {
        vertices = new ArrayList<>();
        vertices.add(new Vertex(100, 400, 100));

        square = new RectangularCuboid(new Vertex(500, 500, 50), new Vertex(600, 600, -50));
        square2 = new RectangularCuboid(new Vertex(400,400,60),
                new Vertex(550, 550, -40));
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

//        square.draw(g2D);
        faceDraw(g2D);
    }

    private void faceDraw(Graphics2D graphics2D) {
        Face[] faces = square.getFaces();
        faces = Arrays.copyOf(faces, faces.length + 1);
        faces[faces.length - 1] = new Face(Color.BLACK,
                new Vertex(500, 500, 51),
                new Vertex(650, 550, 51),
                new Vertex(500, 600, 51));
        faces = concatenate(faces, square2.getFaces());

        double[][] zBuffer = new double[1000][1000];
        for (double[] doubles : zBuffer) {
            Arrays.fill(doubles, Double.NEGATIVE_INFINITY);
        }
        BufferedImage bufferedImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
        int asd = 0;
        int asd2 = 0;
        double zed = 0;

        for (int ias= 0; ias < faces.length; ias++) {
            Face face = faces[ias];
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
            int count = 0;
            for (int i = (int) minX; i <= maxX; i++) {
                for (int j = (int) minY; j <= maxY; j++) {
                    if (face.vertexInFace2D(new Vertex(i, j, 0))) {
                        Vertex[] vertices232 = face.getVertices();
                        Vertex aas = vertices232[0];
                        Vector3 as = new Vector3(vertices232[0], vertices232[1]);
                        Vector3 ad = new Vector3(vertices232[1], vertices232[2]);

                        Vector3 NORMAL = Vector3.cross(as, ad);
                        if (NORMAL.z != 0) {
                            double k = -(NORMAL.x * aas.getX() + NORMAL.y * aas.getY() + NORMAL.z * aas.getZ());

                            double z = -(NORMAL.x * i + NORMAL.y * j + k) / NORMAL.z;
//                        System.out.println("" + z + ", " + NORMAL.x + ", " + NORMAL.y + ", " + NORMAL.z);
                            if (count == 0) {
                                asd = i;
                                asd2 = j;
                                zed = z;
                                count++;
                            }
                            if (z > zBuffer[i][j]) {
                                zBuffer[i][j] = z;
                                bufferedImage.setRGB(i, j, face.getColor().getRGB());
                            }
                        }
                    }
                }
            }
//        face.draw(graphics2D);
//        Vertex[] vertices232 = face.getVertices();
//        vertices232[0].draw(graphics2D);
//        vertices232[1].draw(graphics2D);
//        vertices232[2].draw(graphics2D);
//        Vertex a = vertices232[0];
//            Vertex aas = vertices232[0];
//            Vector3 as = new Vector3(vertices232[0], vertices232[1]);
//            Vector3 ad = new Vector3(vertices232[1], vertices232[2]);
//            Vector3 NORMAL = Vector3.cross(as, ad);
//            System.out.println("a-b " + as.x + ", " + as.y + ", " + as.z);
//            System.out.println("b-c " + ad.x + ", " + ad.y + ", " + ad.z);
//            System.out.println("a " + a.getIntX() + ", " + a.getIntY() + ", " + a.getIntZ());
//            System.out.println("NORMAL " + NORMAL.x + ", " + NORMAL.y + ", " + NORMAL.z);
//            double k = -(NORMAL.x * aas.getX() + NORMAL.y * aas.getY() + NORMAL.z * aas.getZ());
//            System.out.println("k " + k);
//            double z = -(NORMAL.x * aas.getX() + NORMAL.y * aas.getY() + k) / NORMAL.z;
//            System.out.println("z " + z);
//            graphics2D.drawString("a", vertices232[0].getIntX(), vertices232[0].getIntY());
//        graphics2D.drawString("b", vertices232[1].getIntX(), vertices232[1].getIntY());
//        graphics2D.drawString("c", vertices232[2].getIntX(), vertices232[2].getIntY());
//        as.draw(graphics2D);
//        ad.draw(graphics2D);
//        NORMAL.draw(graphics2D);
        graphics2D.drawString(Double.toString(zed), asd, asd2 - 10);

        }
        graphics2D.drawImage(bufferedImage, 0, 0, null);
    }

    public <T> T[] concatenate(T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
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
