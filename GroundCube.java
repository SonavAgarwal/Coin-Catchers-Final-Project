import java.awt.*;

public class GroundCube extends Cube {
    public GroundCube(Position p) {
        super(p, new Color(87, 56, 6), 0);
        setName("Ground");
        setWalkable(true);
    }
}