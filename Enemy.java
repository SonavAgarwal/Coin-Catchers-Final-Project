import java.awt.*;

public class Enemy  extends Cube {

    Thread control;
    boolean isAlive = true;

    public Enemy(Position pos) {
        super(pos, new Color(150, 49, 204, 255), 3, "Joe");
        
        faces = new SpriteFace[6];
        
        faces[0] = new SpriteFace(this, getColor().brighter(), 0, new CubePosition(getPosition().getX() + 0.5, getPosition().getY() + PublicReference.halfCubeHeight + PublicReference.halfCubeHeight, getPosition().getZ() + 0.5, getCubeType())); //top
        faces[1] = new SpriteFace(this, getColor().darker(), 1, new CubePosition(getPosition().getX() + 0.5, getPosition().getY() + PublicReference.halfCubeHeight, getPosition().getZ() + 0.25, getCubeType())); //front
        faces[2] = new SpriteFace(this, getColor(), 2, new CubePosition(getPosition().getX() + 0.25, getPosition().getY() + PublicReference.halfCubeHeight, getPosition().getZ() + 0.5, getCubeType())); //right
        faces[3] = new SpriteFace(this, getColor(), 1, new CubePosition(getPosition().getX() + 0.5, getPosition().getY() + PublicReference.halfCubeHeight, getPosition().getZ() + 0.75, getCubeType())); //back
        faces[4] = new SpriteFace(this, getColor().darker(), 2, new CubePosition(getPosition().getX() + 0.75, getPosition().getY() + PublicReference.halfCubeHeight, getPosition().getZ() + 0.5, getCubeType())); //left 
        faces[5] = new SpriteFace(this, getColor().darker().darker(), 0, new CubePosition(getPosition().getX() + 0.5, getPosition().getY() + 0, getPosition().getZ() + 0.5, getCubeType())); //bottom
        
        Thread control = new Thread(new EnemyControl(this));
        control.start();
    }

    public boolean moveX(double dist) {

        Position potentialPosition = getPosition().copy().changeX(dist);
        potentialPosition.round();


        Cube obstacle = PublicReference.getWorld().getCubeAt(potentialPosition);
        if (obstacle != null) {
            return false;
        } else {
            potentialPosition.changeY(PublicReference.halfCubeHeight * -2); 
            potentialPosition.round();       
            obstacle = PublicReference.getWorld().getCubeAt(potentialPosition);
        } 

        if (obstacle != null) {
            if (obstacle.isWalkable()) {
                getPosition().changeX(dist);

                for (CubeFace f : faces) {
                    f.moveX(dist);
                }
                return true;
            }
        } else {
            return false;
        }
        return false;

    }

    public void moveY(double dist) {
        getPosition().changeY(dist);
    }

    public boolean moveZ(double dist) {

        Position potentialPosition = getPosition().copy().changeZ(dist);
        potentialPosition.round();

        Cube obstacle = PublicReference.getWorld().getCubeAt(potentialPosition);
        if (obstacle != null) {
            return false;
        } else {
            potentialPosition.changeY(PublicReference.halfCubeHeight * -2);        
            obstacle = PublicReference.getWorld().getCubeAt(potentialPosition);
        } 

        if (obstacle != null) {
            if (obstacle.isWalkable()) {
                getPosition().changeZ(dist);
                for (CubeFace f : faces) {
                    f.moveZ(dist);
                }
                return true;
            }
        } else {
            return false;
        }
        // position.changeZ(dist);
        // cameraPosition.changeZ(dist);
        return false;
    }

    public void update() {
        if (getPosition().equals(PublicReference.getPlayer().getCornerPosition())) PublicReference.getPlayer().kill();
        if (PublicReference.getWorld().hasObstacleAt(getPosition())) {
            PublicReference.getWorld().removeEnemy(this);
            isAlive = false;
        } 

    }

    public boolean isAlive() {
        return isAlive;
    }
}