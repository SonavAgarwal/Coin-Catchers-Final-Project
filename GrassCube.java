import java.awt.*;

public class GrassCube extends Cube {
    public GrassCube(Position p) {
        super(p, new Color(50, 168, 82), 0);
        setName("Grass");
        setWalkable(true);
    }
}