import java.awt.*;

public class GoldCube extends Cube {
    public GoldCube(Position p) {
        super(p, new Color(245, 241, 5), 0);
        setName("Gold");
        setWalkable(true);
    }
}