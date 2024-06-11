package graphic;

import logic.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.*;

public class TileManager {
    private GraphicPanel graphicPanel;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;
    private Tile[][][] tiles;
    private int gap;
    private Vertex center;

    public TileManager(GraphicPanel graphicPanel, KeyHandler keyHandler, MouseHandler mouseHandler, int gap) {
        this.graphicPanel = graphicPanel;
        this.keyHandler = keyHandler;
        this.mouseHandler = mouseHandler;
        this.tiles = new Tile[0][0][0];
        this.gap = gap;
    }

    public void generateMap(int width) {
        this.tiles = new Tile[width][width][width];
        int totalWidth = Math.min(graphicPanel.getWidth(), graphicPanel.getHeight()) - 400;
        double widthPerTile = (double) (totalWidth - gap * (width - 1)) / width;
        center = new Vertex((double) graphicPanel.getWidth() / 2, (double) graphicPanel.getHeight() / 2, 0);
        Vertex topLeft = new Vertex(center);
        topLeft.translate((double) -totalWidth / 2, (double) -totalWidth / 2, (double) -totalWidth / 2);

        for (int z = 0; z < tiles.length; z++) {
            for (int y = 0; y < tiles[z].length; y++) {
                for (int x = 0; x < tiles[z][y].length; x++) {
                    tiles[z][y][x] = new Tile(new Vertex(topLeft.getX() + (x * (widthPerTile + gap)), topLeft.getY() + (y * (widthPerTile + gap)), topLeft.getZ() + (z * (widthPerTile + gap))), widthPerTile, widthPerTile, widthPerTile);
                }
            }
        }
    }

    public void drawAllFaces(Graphics2D g2D) {
        double[][] zBuffer = new double[graphicPanel.getWidth()][graphicPanel.getHeight()]; // BREAKS IF GRAPHICPANEL IS TOO SMALL and other faces are outside of range size
        for (double[] doubles : zBuffer) {
            Arrays.fill(doubles, Double.POSITIVE_INFINITY);
        }
        BufferedImage drawn = new BufferedImage(graphicPanel.getWidth(), graphicPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (Tile[][] tile2D : tiles) {
            for (Tile[] tile1D : tile2D) {
                for (Tile tile : tile1D) {
                    for (Face face : tile.getFaces()) {
                        if (!face.facingCamera()) {
                            continue;
                        }
                        Vertex p1 = face.getP1();
                        Vertex p2 = face.getP2();
                        Vertex p3 = face.getP3();

                        int minX = (int) floor(min(p1.getX(), min(p2.getX(), p3.getX())));
                        int minY = (int) floor(min(p1.getY(), min(p2.getY(), p3.getY())));
                        int maxX = (int) ceil(max(p1.getX(), max(p2.getX(), p3.getX())));
                        int maxY = (int) ceil(max(p1.getY(), max(p2.getY(), p3.getY())));

                        for (int i = minX; i <= maxX; i++) {
                            for (int j = minY; j <= maxY; j++) {
                                if (face.vertexInFace2D(new Vertex(i, j, 0))) {
                                    Vector3 v12 = new Vector3(p1, p2);
                                    Vector3 v23 = new Vector3(p2, p3);
                                    Vector3 normal = Vector3.cross(v12, v23); // a, b, c as normal vector

                                    if (i < zBuffer.length && j < zBuffer[0].length && i >= 0 && j >= 0) {
                                        if (normal.z != 0) {
                                            double k = -(normal.x * p1.getX() + normal.y * p1.getY() + normal.z * p1.getZ()); // solving for k in ax + by + cz + k = 0
                                            double z = -(normal.x * i + normal.y * j + k) / normal.z; // solving for z

                                            if (z < zBuffer[i][j]) { // if z value of specific pixel is less than the last pixel iterated over
                                                zBuffer[i][j] = z;
                                                drawn.setRGB(i, j, face.getColor().getRGB());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        g2D.drawImage(drawn, 0, 0, null);

    }

    public void update() {
        boolean up = keyHandler.upArrowPressed();
        boolean down = keyHandler.downArrowPressed();
        boolean left = keyHandler.leftArrowPressed();
        boolean right = keyHandler.rightArrowPressed();
        Dimension change = mouseHandler.getDeltaSinceLastHeld();
        if (up) {
            center.translate(0, -2, 0);
        }
        if (down) {
            center.translate(0, 2, 0);
        }
        if (left) {
            center.translate(-2, 0, 0);
        }
        if (right) {
            center.translate(2, 0, 0);
        }
        for (Tile[][] tile2D : tiles) {
            for (Tile[] tile1D : tile2D) {
                for (Tile tile : tile1D) {
                    tile.rotateYAxis(-change.width, center.getX(), center.getZ());
                    tile.rotateXAxis(change.height, center.getY(), center.getZ());

                    if (keyHandler.keyWPressed()) {
                        tile.rotateXAxis(-1, center.getY(), center.getZ());
                    }
                    if (keyHandler.keySPressed()) {
                        tile.rotateXAxis(1, center.getY(), center.getZ());
                    }
                    if (keyHandler.keyAPressed()) {
                        tile.rotateYAxis(1, center.getX(), center.getZ());
                    }
                    if (keyHandler.keyDPressed()) {
                        tile.rotateYAxis(-1, center.getX(), center.getZ());
                    }
                    if (keyHandler.keyQPressed()) {
                        tile.rotateZAxis(-1, center.getX(), center.getY());
                    }
                    if (keyHandler.keyEPressed()) {
                        tile.rotateZAxis(1, center.getX(), center.getY());
                    }
                    if (up) {
                        tile.translate(0, -2, 0);
                    }
                    if (down) {
                        tile.translate(0, 2, 0);
                    }
                    if (left) {

                        tile.translate(-2, 0, 0);
                    }
                    if (right) {

                        tile.translate(2, 0, 0);
                    }
                }
            }
        }
    }

    public void showPoints(Graphics2D g2D) {
        for (Tile[][] tile2D : tiles) {
            for (Tile[] tile1D : tile2D) {
                for (Tile tile : tile1D) {
                    for (Face face : tile.getFaces()) {
                        for (Vertex vertex : face.getVertices()) {
                            g2D.drawString("(" + vertex.getIntX() + ", " + vertex.getIntY() + ", " + vertex.getIntZ() + ")", vertex.getIntX(), vertex.getIntY() - 10);
                        }
                    }
                }
            }
        }
    }
}
