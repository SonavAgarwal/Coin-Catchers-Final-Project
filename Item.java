import java.awt.*;

public class Item extends CubeFace {
    private String texture;
    private boolean pickedUp = false;
    private EzImage image;

    public Item(String t, CubePosition p) {
        super(null, Color.YELLOW, 0, p);
        texture = t;
        image = new EzImage(texture);
    }

    @Override
    public void render(Graphics g, Position cameraPosition) {
        // Position cameraPosition = PublicReference.getPlayer().getCameraPosition();

        int xPos = 500 + (int) (-30 * (getPosition().getX() - cameraPosition.getX()) / (cameraPosition.getY() - getPosition().getY())) - 10;
        int yPos = 375 + (int) (-30 * (getPosition().getZ() - cameraPosition.getZ()) / (cameraPosition.getY() - getPosition().getY())) - 10;

        image.draw(g, xPos, yPos);
        // g.setColor(Color.BLACK);
        // g.drawString(position.toString(), xPos, yPos);
    }
}