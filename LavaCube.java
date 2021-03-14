import java.awt.*;

public class LavaCube extends Cube {
    public LavaCube(Position p) {
        super(p, new Color(255, 95, 15, 200), 1);
        setName("Lava");
        setWalkable(false);
    }
}