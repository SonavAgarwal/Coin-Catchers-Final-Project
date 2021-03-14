import java.awt.*;

public class SpriteFace extends CubeFace {

    public SpriteFace(Cube par, Color col, int facingDirection, CubePosition center) {
        super(par, col, facingDirection, center);
        
        if (getFacingDirection() == 0) {
            facePoints[0] = getPosition().copy().changeX(-0.25).changeZ(-0.25);
            facePoints[1] = getPosition().copy().changeX(0.25).changeZ(-0.25);
            facePoints[2] = getPosition().copy().changeX(0.25).changeZ(0.25);
            facePoints[3] = getPosition().copy().changeX(-0.25).changeZ(0.25);
        } else if (getFacingDirection() == 1) {
            facePoints[0] = getPosition().copy().changeX(-0.25).changeY(-1 * PublicReference.halfCubeHeight);
            facePoints[1] = getPosition().copy().changeX(0.25).changeY(-1 * PublicReference.halfCubeHeight);
            facePoints[2] = getPosition().copy().changeX(0.25).changeY(PublicReference.halfCubeHeight);
            facePoints[3] = getPosition().copy().changeX(-0.25).changeY(PublicReference.halfCubeHeight);
        } else if (getFacingDirection() == 2) {
            facePoints[0] = getPosition().copy().changeY(-1 * PublicReference.halfCubeHeight).changeZ(-0.25);
            facePoints[1] = getPosition().copy().changeY(PublicReference.halfCubeHeight).changeZ(-0.25);
            facePoints[2] = getPosition().copy().changeY(PublicReference.halfCubeHeight).changeZ(0.25);
            facePoints[3] = getPosition().copy().changeY(-1 * PublicReference.halfCubeHeight).changeZ(0.25);
        }

    }
}