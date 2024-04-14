package animation;

import asset.Asset;

import java.awt.image.BufferedImage;

public class Animation {


    public final Asset asset;

    public Animation(Asset asset) {
        this.asset = asset;
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
}
