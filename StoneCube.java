import java.awt.*;

public class StoneCube extends Cube {
    public StoneCube(Position p) {
        super(p, new Color(128, 132, 135), 0);
        setName("Stone");
        setWalkable(true);
    }
}