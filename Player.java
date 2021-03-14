import java.awt.*;
import java.util.ArrayList;

public class Player {

    private Position position, cameraPosition, cornerPosition;
    private Rotation cameraRotation;

    private Color color = new Color(25, 132, 209, 200);

    private World world;

    private String[] hotbar = {"Grass", "Ground", "Stone", "Gold", "Water", "Lava", "Sand", "Wood", "Grass"};
    private int selectedSlot = 1;

    public Player(String name) {
        kill(); //potentially set x instead of new pos
    }

    public void addToWorld(World w) {
        this.world = w;
    }

    public void moveX(double dist) {

        Position potentialPosition = position.copy().changeX(dist).changeX(-0.5).changeZ(-0.5);
        potentialPosition.round();

        Cube obstacle = world.getCubeAt(potentialPosition);
        if (obstacle != null) {
            return;
        } else {
            potentialPosition.changeY(PublicReference.halfCubeHeight * -2);        
            obstacle = world.getCubeAt(potentialPosition);
        } 

        if (obstacle != null) {
            if (obstacle.isWalkable()) {
                position.changeX(dist);
                cornerPosition.changeX(dist);
                cameraPosition.changeX(dist);
            }
        } else {
            return;
        }

    }

    public void moveY(double dist) {
        position.changeY(dist);
        cameraPosition.changeY(dist);
    }

    public void moveZ(double dist) {

        Position potentialPosition = position.copy().changeZ(dist).changeX(-0.5).changeZ(-0.5);
        potentialPosition.round();

        Cube obstacle = world.getCubeAt(potentialPosition);
        if (obstacle != null) {
            return;
        } else {
            potentialPosition.changeY(PublicReference.halfCubeHeight * -2);        
            obstacle = world.getCubeAt(potentialPosition);
        } 

        if (obstacle != null) {
            if (obstacle.isWalkable()) {
                position.changeZ(dist);
                cornerPosition.changeZ(dist);
                cameraPosition.changeZ(dist);
            }
        } else {
            return;
        }
        // position.changeZ(dist);
        // cameraPosition.changeZ(dist);
    }

    public void rotateX(double rad) {
        cameraRotation.changeX(rad);
    }

    public void rotateY(double rad) {
        cameraRotation.changeY(rad);
    }

    public void rotateZ(double rad) {
        cameraRotation.changeZ(rad);
    }

    public void setHotbarSlot(int a) {
        selectedSlot = a;
    }

    public String getSelectedBlockName() {
        return hotbar[selectedSlot];
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        int halfWidth = (int) (9 / (cameraPosition.getY() - position.getY()));
        g.fillRect(
        500 - halfWidth,
        375 - halfWidth,
        halfWidth * 2, halfWidth * 2
        );

        g.setColor(color);
        // int halfWidth = (int) (5 / (cameraPosition.getY() - position.getY()));
        // g.fillRect(
        // 500 - halfWidth + (int) (30 * (cameraPosition.getX() - cameraPosition.getX()) / (cameraPosition.getY() - position.getY())),
        // 375 - halfWidth + (int) (30 * (cameraPosition.getZ() - cameraPosition.getZ()) / (cameraPosition.getY() - position.getY())),
        // halfWidth * 2, halfWidth * 2
        // );

        halfWidth = (int) (5 / (cameraPosition.getY() - position.getY()));
        g.fillRect(
        500 - halfWidth,
        375 - halfWidth,
        halfWidth * 2, halfWidth * 2
        );

        halfWidth = (int) (10 / (cameraPosition.getY() - position.getY()));
        g.fillRect(
        500 - halfWidth,
        375 - halfWidth,
        halfWidth * 2, halfWidth * 2
        );
    }

    public Position getPosition() {
        return position;
    }

    public Position getCornerPosition() {
        return cornerPosition;
    }

    public Position getCameraPosition() {
        return cameraPosition;
    }

    public Rotation getCameraRotation() {
        return cameraRotation;
    }

    public void kill() {
        position = new Position(25.5, PublicReference.halfCubeHeight * 6, 25.5);
        cornerPosition = new Position(25, PublicReference.halfCubeHeight * 6, 25);
        cameraPosition = position.copy().changeY(1);
    }
}