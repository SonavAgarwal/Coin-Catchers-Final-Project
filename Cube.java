import java.awt.*;

public class Cube {
    private Position position;
    public CubeFace[] faces;
    private Color color;
    private int cubeType;
    private String name = "";
    private boolean isWalkable = true;
    private Item item;
    


    public Cube(Position p, Color col, int cubeType) {
        this.position = p;
        this.color = col;
        this.cubeType = cubeType;

        faces = new CubeFace[6];

        faces[0] = new CubeFace(this, color.brighter(), 0, new CubePosition(position.getX() + 0.5, position.getY() + PublicReference.halfCubeHeight + PublicReference.halfCubeHeight, position.getZ() + 0.5, this.cubeType)); //top
        faces[1] = new CubeFace(this, color.darker(), 1, new CubePosition(position.getX() + 0.5, position.getY() + PublicReference.halfCubeHeight, position.getZ() + 0, this.cubeType)); //front
        faces[2] = new CubeFace(this, color, 2, new CubePosition(position.getX() + 1, position.getY() + PublicReference.halfCubeHeight, position.getZ() + 0.5, this.cubeType)); //right
        faces[3] = new CubeFace(this, color, 1, new CubePosition(position.getX() + 0.5, position.getY() + PublicReference.halfCubeHeight, position.getZ() + 1, this.cubeType)); //back
        faces[4] = new CubeFace(this, color.darker(), 2, new CubePosition(position.getX() + 0, position.getY() + PublicReference.halfCubeHeight, position.getZ() + 0.5, this.cubeType)); //left 
        faces[5] = new CubeFace(this, color.darker().darker(), 0, new CubePosition(position.getX() + 0.5, position.getY() + 0, position.getZ() + 0.5, this.cubeType)); //bottom

    }

    public Cube(Position p, Color c, int cubeType, String name) {
        this.position = p;
        this.color = c;
        this.cubeType = cubeType;
        // System.out.println(name + " wants to eat you");
    }

    public Cube (Position p, int cubeType) {
        this.position = p;
        this.cubeType = cubeType;
        faces = new CubeFace[0];
    }

    public CubeFace[] getFaces() {
        // System.out.println("my faces have been gotted");
        // for (CubeFace f : faces) {
        //     System.out.println(f);
        // }
        return faces;
    }

    public String getName() {
        return name;
    }
    public void setName(String s) {
        name = s;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public void setColor(Color c) {
        this.color = c;
    }
    public Color getColor() {
        return color;
    }

    public void setWalkable(boolean b) {
        isWalkable = b;
    }

    public int getCubeType() {
        return cubeType;
    }

    public boolean hasItem() {
        if (item != null) return true;
        return false;
    }

    public Item takeItem() {
        return item;
    }

    public Position getPosition() {
        return position;
    }
}