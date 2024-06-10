package logic;

public interface Transformable {
    void rotate(double theta, double rX, double rY, double rZ);
    void rotateXAxis(double theta, double rY, double rZ);
    void rotateYAxis(double theta, double rX, double rZ);
    void rotateZAxis(double theta, double rX, double rY);
    void translate(double dx, double dy, double dz);
}
