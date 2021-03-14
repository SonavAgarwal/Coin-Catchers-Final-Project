public class CubePosition extends Position implements Comparable<Position> {
    private int cubeType = 0;
    public CubePosition(double x, double y, double z, int cubeType) {
        super(x, y, z);
        this.cubeType = cubeType;
    }

    public int getCubeType() {
        return cubeType;
    }

    public void setCubeType(int ct) {
        cubeType = ct;
    }

    @Override
    public int hashCode() {
        int hCode = (int) (getZ() * 1000000 + getX() * 1000 + getY()) + cubeType * 1000000000;
        return hCode;
    }

    public CubePosition copy() {
        return new CubePosition(getX(), getY(), getZ(), cubeType);
    }

    @Override
    public int compareTo(Position t) {
        CubePosition that = (CubePosition) t;
        if (that == null) System.out.println("thats null");
        // System.out.println("Comparing " + toString() + " and " + that.toString());
        // if (that.getCubeType() == null) System.out.println("y r they null" + that.getPosition());
        if (this.getDistanceFromCamera() > that.getDistanceFromCamera()) return -1;
        else if (this.getDistanceFromCamera() < that.getDistanceFromCamera()) return 1;
        else if (this.getCubeType() > that.getCubeType()) return 1 * -1;
        else if (this.getCubeType() < that.getCubeType()) return -1 * -1;
        // if (this.equals(that)) return 0;
        // return 1;
        return 0;
    }
}