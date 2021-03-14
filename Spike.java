import java.awt.*;

public class Spike extends Cube {

    public Spike(Position pos) {
        super(pos, new Color(209, 25, 25), 3, "Joe");
        
        setName("Spike");

        faces = new SpikeFace[6];
        
        faces[0] = new SpikeFace(this, getColor().brighter(), 0, new CubePosition(getPosition().getX() + 0.5, getPosition().getY() + PublicReference.halfCubeHeight, getPosition().getZ() + 0.5, getCubeType())); //top
        faces[1] = new SpikeFace(this, getColor().darker(), 1, new CubePosition(getPosition().getX() + 0.5, getPosition().getY() + PublicReference.halfCubeHeight * 0.5, getPosition().getZ() + 0.25, getCubeType())); //front
        faces[2] = new SpikeFace(this, getColor(), 2, new CubePosition(getPosition().getX() + 0.25, getPosition().getY() + PublicReference.halfCubeHeight * 0.5, getPosition().getZ() + 0.5, getCubeType())); //right
        faces[3] = new SpikeFace(this, getColor(), 1, new CubePosition(getPosition().getX() + 0.5, getPosition().getY() + PublicReference.halfCubeHeight * 0.5, getPosition().getZ() + 0.75, getCubeType())); //back
        faces[4] = new SpikeFace(this, getColor().darker(), 2, new CubePosition(getPosition().getX() + 0.75, getPosition().getY() + PublicReference.halfCubeHeight * 0.5, getPosition().getZ() + 0.5, getCubeType())); //left 
        faces[5] = new SpikeFace(this, getColor().darker().darker(), 0, new CubePosition(getPosition().getX() + 0.5, getPosition().getY() + 0, getPosition().getZ() + 0.5, getCubeType())); //bottom
    }
}