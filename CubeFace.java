import java.awt.*;

public class CubeFace {
    private CubePosition centerPosition;
    public Position[] facePoints = new Position[4];
    private Color faceColor;
    private int facingDirection;
    private Cube parentCube;

    private boolean highlighted = false;

    public CubeFace(Cube par, Color col, int facingDirection, CubePosition center) {
        this.parentCube = par;
        this.centerPosition = center;
        this.faceColor = col;
        this.facingDirection = facingDirection; // 0 is y, 1 is z, 2 is x
        
        if (facingDirection == 0) {
            facePoints[0] = centerPosition.copy().changeX(-0.5).changeZ(-0.5);
            facePoints[1] = centerPosition.copy().changeX(0.5).changeZ(-0.5);
            facePoints[2] = centerPosition.copy().changeX(0.5).changeZ(0.5);
            facePoints[3] = centerPosition.copy().changeX(-0.5).changeZ(0.5);
        } else if (facingDirection == 1) {
            facePoints[0] = centerPosition.copy().changeX(-0.5).changeY(-1 * PublicReference.halfCubeHeight);
            facePoints[1] = centerPosition.copy().changeX(0.5).changeY(-1 * PublicReference.halfCubeHeight);
            facePoints[2] = centerPosition.copy().changeX(0.5).changeY(PublicReference.halfCubeHeight);
            facePoints[3] = centerPosition.copy().changeX(-0.5).changeY(PublicReference.halfCubeHeight);
        } else if (facingDirection == 2) {
            facePoints[0] = centerPosition.copy().changeY(-1 * PublicReference.halfCubeHeight).changeZ(-0.5);
            facePoints[1] = centerPosition.copy().changeY(PublicReference.halfCubeHeight).changeZ(-0.5);
            facePoints[2] = centerPosition.copy().changeY(PublicReference.halfCubeHeight).changeZ(0.5);
            facePoints[3] = centerPosition.copy().changeY(-1 * PublicReference.halfCubeHeight).changeZ(0.5);
        }

    }

    public void render(Graphics g, Position cameraPosition) {
        if (faceColor == null) return;

        int[] xPoints = new int[4];
        int[] yPoints = new int[4];

        for (int i = 0; i < 4; i++) {
            xPoints[i] = 500 + (int) (-30 * (facePoints[i].getX() - cameraPosition.getX()) / (cameraPosition.getY() - facePoints[i].getY()));
            yPoints[i] = 375 + (int) (-30 * (facePoints[i].getZ() - cameraPosition.getZ()) / (cameraPosition.getY() - facePoints[i].getY()));
        }

        if (highlighted) g.setColor(faceColor.brighter());
        else g.setColor(faceColor);
        g.fillPolygon(xPoints, yPoints, 4);
        if (PublicReference.outline) {
            g.setColor(faceColor.darker());
            g.drawPolygon(xPoints, yPoints, 4);
        }
        if (highlighted) {
            g.setColor(Color.BLACK);
            g.drawPolygon(xPoints, yPoints, 4);
        }
        highlighted = false;
        

    }

    public Color getColor() {
        return faceColor;
    }

    public Cube getCube() {
        return parentCube;
    }

    public void highlight() {
        highlighted = true;
    }

    @Override
    public int hashCode() {
        return centerPosition.hashCode();
    }

    public CubePosition getPosition() {
        return centerPosition;
    }

    public String toString() {
        return "CubeFace" + centerPosition.toString();
    }

    public int getFacingDirection() {
        return facingDirection;
    }

    public void moveX(double distance) {
        centerPosition.changeX(distance);
        for (Position p : facePoints) {
            p.changeX(distance);
        }
    }

    public void moveZ(double distance) {
        // PublicReference.getWorld().removeFace(this);
        centerPosition.changeZ(distance);
        // PublicReference.getWorld().addFace(this);
        for (Position p : facePoints) {
            p.changeZ(distance);
        }
    }

    // public String toString() {
    //     String ret = "";
    //     for (Position p : facePoints) {
    //         ret += p.toString();
    //     }
    //     return ret;
    // }
}