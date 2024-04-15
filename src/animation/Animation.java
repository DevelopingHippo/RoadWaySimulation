package animation;

import asset.Asset;
import asset.particle.ParticleAnimation;

import java.awt.image.BufferedImage;

public class Animation {


    public Asset asset;
    public ParticleAnimation particleAnimation;

    public Animation(Asset asset) {
        this.asset = asset;
    }

    public Animation(ParticleAnimation particleAnimation) {
        this.particleAnimation = particleAnimation;
    }

    public BufferedImage moveAnimation() {
        BufferedImage image = null;
        switch (asset.direction) {
            case "up":
                if (asset.spriteNum == 1) { image = asset.up1;}
                if (asset.spriteNum == 2) { image = asset.up2; }
                break;
            case "down":
                if(asset.spriteNum == 1) { image = asset.down1; }
                if(asset.spriteNum == 2) { image = asset.down2; }
                break;
            case "left":
                if (asset.spriteNum == 1) { image = asset.left1; }
                if (asset.spriteNum == 2) { image = asset.left2; }

                break;
            case "right":
                if (asset.spriteNum == 1) { image = asset.right1; }
                if (asset.spriteNum == 2) { image = asset.right2; }
                break;
        }
        return image;
    }


    public BufferedImage particleAnimation() {
        BufferedImage image = null;
        if (particleAnimation.spriteNum == 1) { image = particleAnimation.image1;}
        if (particleAnimation.spriteNum == 2) { image = particleAnimation.image2; }


        if(particleAnimation.spriteNum == 3) { image = particleAnimation.image3; }
        if(particleAnimation.spriteNum == 4) { image = particleAnimation.image4; }

        if (particleAnimation.spriteNum == 5) { image = particleAnimation.image5; }
        if (particleAnimation.spriteNum == 6) { image = particleAnimation.image6; }

        if (particleAnimation.spriteNum == 7) { image = particleAnimation.image7; }
        if (particleAnimation.spriteNum == 8) { image = particleAnimation.image8; }

        if (particleAnimation.spriteNum == 9) { image = particleAnimation.image9; }
        if (particleAnimation.spriteNum == 10) { image = particleAnimation.image10; }

        if (particleAnimation.spriteNum == 11) { image = particleAnimation.image11; }
        return image;
    }
}
