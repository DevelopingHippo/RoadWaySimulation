package asset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class AssetHelper {

    public static void setupAllImages(Asset asset, String filepath, int width, int height) {
        System.out.println("AssetHelper: Setting up asset Images");
        asset.up1 = setupImage(filepath + "up1", width, height);
        asset.down1 = setupImage(filepath + "down1", width, height);
        asset.left1 = setupImage(filepath + "left1", width, height);
        asset.right1 = setupImage(filepath + "right1", width, height);
    }


    public static BufferedImage setupImage(String imagePath, int width, int height) {
        BufferedImage image = null;
        try{
            image = ImageIO.read((Objects.requireNonNull(AssetHelper.class.getResourceAsStream(imagePath + ".png"))));
            image = scaleImage(image, width, height);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public static BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }

}
