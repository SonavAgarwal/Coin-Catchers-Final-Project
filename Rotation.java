public class Rotation {
    private double x, y, z;

    public Rotation (double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double a) {
        x = a;
    }

    public void setY(double a) {
        y = a;
    }

    public void setZ(double a) {
        z = a;
    }

    public void changeX(double change) {
        x += change;
        // double y0 = y;
        // double z0 = z;
        // z = z0 * Math.cos(change) - y0 * Math.sin(change);
        // y = z0 * Math.sin(change) + y0 * Math.cos(change);
    }

    public void changeY(double change) {
        y += change;
        // double x0 = x;
        // double z0 = z;
        // x = x0 * Math.cos(change) - z0 * Math.sin(change);
        // z = x0 * Math.sin(change) + z0 * Math.cos(change);
    }

    public void changeZ(double change) {
        z += change;
        // double x0 = x;
        // double y0 = y;
        // x = x0 * Math.cos(change) + y0 * Math.sin(change);
        // y = -1 * x0 * Math.sin(change) + y0 * Math.cos(change);
    }

    @Override
    public String toString() {
        return "Rotation: " + x + ", " + y + ", " + z;
    }
}