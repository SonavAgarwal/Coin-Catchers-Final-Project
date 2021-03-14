public class Position implements Comparable<Position> {
    private double x, y, z;

    private double distanceFromCamera;

    public Position (double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        distanceFromCamera = 0;
        this.round();
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
        roundX();
    }

    public void setY(double a) {
        y = a;
        roundY();
    }

    public void setZ(double a) {
        z = a;
        roundZ();
    }

    public Position changeX(double change) {
        x += change;
        roundX();
        return this;
    }

    public Position changeY(double change) {
        y += change;
        roundY();
        return this;
    }

    public Position changeZ(double change) {
        z += change;
        roundZ();
        return this;
    }

    public void roundX() {
        x = Math.round(x * 1000) / 1000.0;
    }

    public void roundY() {
        y = Math.round(y * 1000) / 1000.0;
    }

    public void roundZ() {
        z = Math.round(z * 1000) / 1000.0;
    }

    public void round() {
        roundX();
        roundY();
        roundZ();
    }

    @Override
    public int hashCode() {
        int hCode = (int) (z * 1000000 + x * 1000 + y);
        // System.out.println("my hashcode for " + toString() + " is " + hCode);
        return hCode;
    }

    @Override
    public int compareTo(Position that) {
        // System.out.println("Comparing " + toString() + " and " + that.toString());
        if (this.getDistanceFromCamera() > that.getDistanceFromCamera()) return -1;
        if (this.getDistanceFromCamera() < that.getDistanceFromCamera()) return 1;
        // if (this.equals(that)) return 0;
        // return 1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        Position that = (Position) o;
        // System.out.println("Equaling " + toString() + " and " + that.toString());
        if (this.getX() == that.getX() && this.getY() == that.getY() && this.getZ() == that.getZ()) return true;
        return false;
    }

    @Override
    public String toString() {
        // return "" + distanceFromCamera;
        return "Position: " + x + ", " + y + ", " + z;
    }

    public double getDistanceFrom(Position p) {
        double xDist = this.getX() - p.getX();
        double yDist = this.getY() - p.getY();
        double zDist = this.getZ() - p.getZ();
        return Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist);
    }

    public void setDistanceFromCamera(Position cameraPosition) {
        distanceFromCamera = getDistanceFrom(cameraPosition);
    }

    public double getDistanceFromCamera() {
        return distanceFromCamera;
    }

    public Position copy() {
        return new Position(x, y, z);
    }

    public CubePosition createCubePosition(int ct) {
        return new CubePosition(x, y, z, ct);
    }
}