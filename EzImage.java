import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;

public class EzImage {

    private BufferedImage image;
    private int x = 0;
    private int y = 0;

    public EzImage(String imageName) {
        try {
            image = ImageIO.read(new File("Images/" + imageName));
        } catch (Exception e) {
            Tools.print("Couldn't load image " + imageName);
        }
    }

    public EzImage(String imageName, int x, int y) {
        try {
            image = ImageIO.read(new File("images/" + imageName));
        } catch (Exception e) {
            Tools.print("Couldn't load image " + imageName);
        }

        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g, int x, int y) {
        g.drawImage(image, x, y, null);
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }

}