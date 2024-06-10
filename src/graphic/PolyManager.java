package graphic;

import logic.Face;
import logic.Polyhedron;
import logic.Vector3;
import logic.Vertex;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.*;

public class PolyManager {
    private GraphicPanel graphicPanel;
    private ArrayList<Polyhedron> polyhedra;

    public PolyManager(GraphicPanel graphicPanel) {
        this.graphicPanel = graphicPanel;
        this.polyhedra = new ArrayList<>();
    }

    public void addPolyhedron(Polyhedron polyhedron) {
        polyhedra.add(polyhedron);
    }

    public void removePolyhedron(Polyhedron polyhedron) {
        polyhedra.remove(polyhedron);
    }

    public void drawAllFaces(Graphics2D g2D) {
        double[][] zBuffer = new double[graphicPanel.getWidth()][graphicPanel.getHeight()]; // BREAKS IF GRAPHICPANEL IS TOO SMALL and other faces are outside of range size
        for (double[] doubles : zBuffer) {
            Arrays.fill(doubles, Double.POSITIVE_INFINITY);
        }
        BufferedImage drawn = new BufferedImage(graphicPanel.getWidth(), graphicPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (Polyhedron polyhedron : polyhedra) {
            for (Face face : polyhedron.getFaces()) {
                if (!face.facingCamera()) {
                    continue;
                }
                Vertex p1 = face.getP1();
                Vertex p2 = face.getP2();
                Vertex p3 = face.getP3();

                int minX = (int) ceil(min(p1.getX(), min(p2.getX(), p3.getX())));
                int minY = (int) ceil(min(p1.getY(), min(p2.getY(), p3.getY())));
                int minZ = (int) ceil(max(p1.getZ(), max(p2.getZ(), p3.getZ())));
                int maxX = (int) floor(max(p1.getX(), max(p2.getX(), p3.getX())));
                int maxY = (int) floor(max(p1.getY(), max(p2.getY(), p3.getY())));
                int maxZ = (int) floor(max(p1.getZ(), max(p2.getZ(), p3.getZ())));

                for (int i = minX; i <= maxX; i++) {
                    for (int j = minY; j <= maxY; j++) {
                        if (face.vertexInFace2D(new Vertex(i, j, 0))) {
                            Vector3 v12 = new Vector3(p1, p2);
                            Vector3 v23 = new Vector3(p2, p3);
                            Vector3 normal = Vector3.cross(v12, v23); // a, b, c as normal vector

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
        g2D.drawImage(drawn, 0, 0, null);
    }

    public void showPoints(Graphics2D g2D) {
         for (Polyhedron polyhedron : polyhedra) {
             for (Face face : polyhedron.getFaces()) {
                 for (Vertex vertex : face.getVertices()) {
                    g2D.drawString("(" + vertex.getIntX() + ", " + vertex.getIntY() + ", " + vertex.getIntZ() + ")", vertex.getIntX(), vertex.getIntY() - 10);
                 }
             }
         }
    }
}
