package asset.particle;

import animation.Animation;
import asset.Asset;
import asset.AssetHelper;
import main.SimulationMain;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ParticleAnimation {


    public Asset generator;
    public int durationLeft;
    public int durationMax;
    public boolean isAlive = true;
    public Animation animation = new Animation(this);


    public BufferedImage image1, image2, image3, image4, image5, image6, image7,image8, image9,image10,image11,image12;
    private int spriteCounter = 0;
    public int spriteNum = 1;
    public final SimulationMain simMain;

    public ParticleAnimation(SimulationMain simMain, Asset generator,int maxDuration) {
        this.generator = generator;
        this.durationMax = maxDuration;
        this.durationLeft = maxDuration;
        this.simMain = simMain;
        AssetHelper.setupAllParticleImages(this, "/images/smoke/smoke_", simMain.tileSize, simMain.tileSize);
    }

    public boolean isInUserVision() {
        return generator.worldX + simMain.tileSize > simMain.user.worldX - simMain.user.screenX &&
                generator.worldX - simMain.tileSize < simMain.user.worldX + simMain.user.screenX &&
                generator.worldY + simMain.tileSize > simMain.user.worldY - simMain.user.screenX &&
                generator.worldY - simMain.tileSize < simMain.user.worldY + simMain.user.screenY;
    }


    public void update() {

        spriteCounter++;
        durationLeft--;
        if (spriteCounter % 12 == 0) {
            spriteNum++;
            if(spriteNum == 12){
                spriteNum = 1;
                spriteCounter = 0;
            }
        }
        if(durationLeft == 0){
            isAlive = false;
        }
    }
    public void draw(Graphics2D g2) {
        int screenX = generator.worldX - simMain.user.worldX + simMain.user.screenX;
        int screenY = generator.worldY - simMain.user.worldY + simMain.user.screenY;
        BufferedImage image = animation.particleAnimation();

        if(isInUserVision()) {
            g2.drawImage(image, screenX, screenY, simMain.tileSize, simMain.tileSize, null);
        }
    }



}