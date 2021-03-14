import java.awt.*;

public class WaterCube extends Cube {
    public WaterCube(Position p) {
        super(p, new Color(100, 100, 255, 150), 1);
        setName("Water");
        setWalkable(false);
    }
}