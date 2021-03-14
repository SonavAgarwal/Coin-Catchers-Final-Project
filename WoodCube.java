import java.awt.*;

public class WoodCube extends Cube {
    public WoodCube(Position p) {
        super(p, new Color(166, 131, 81), 0);
        setName("Wood");
        setWalkable(true);
    }
}