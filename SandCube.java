import java.awt.*;

public class SandCube extends Cube {
    public SandCube(Position p) {
        super(p, new Color(255, 220, 122), 0);
        setName("Sand");
        setWalkable(true);
    }
}