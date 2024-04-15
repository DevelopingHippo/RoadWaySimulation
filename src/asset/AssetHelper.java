package asset;

import asset.particle.ParticleAnimation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class AssetHelper {

    public static void setupAllImages(Asset asset, String filepath, int width, int height) {
        asset.up1 = setupImage(filepath + "up_1", width, height);
        asset.down1 = setupImage(filepath + "down_1", width, height);
        asset.left1 = setupImage(filepath + "left_1", width, height);
        asset.right1 = setupImage(filepath + "right_1", width, height);

        asset.up2 = setupImage(filepath + "up_2", width, height);
        asset.down2 = setupImage(filepath + "down_2", width, height);
        asset.left2 = setupImage(filepath + "left_2", width, height);
        asset.right2 = setupImage(filepath + "right_2", width, height);
    }

    public static void setupAllParticleImages(ParticleAnimation pAnimation, String filepath, int width, int height) {
        pAnimation.image1 = setupImage(filepath + "1", width, height);
        pAnimation.image2 = setupImage(filepath + "2", width, height);
        pAnimation.image3 = setupImage(filepath + "3", width, height);
        pAnimation.image4 = setupImage(filepath + "4", width, height);

        pAnimation.image5 = setupImage(filepath + "5", width, height);
        pAnimation.image6 = setupImage(filepath + "6", width, height);
        pAnimation.image7 = setupImage(filepath + "7", width, height);
        pAnimation.image8 = setupImage(filepath + "8", width, height);

        pAnimation.image9 = setupImage(filepath + "9", width, height);
        pAnimation.image10 = setupImage(filepath + "10", width, height);
        pAnimation.image11 = setupImage(filepath + "11", width, height);
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

    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        return token;
    }

}
