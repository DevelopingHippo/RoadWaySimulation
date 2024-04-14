package asset;

import main.SimulationMain;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Vehicle extends Asset {

    public boolean isMoving = true;
    public Vehicle(SimulationMain simMain) {
        super(simMain);
        setup();
    }

    public void setup() {
        AssetHelper.setupAllImages(this, "/images/vehicle/", simMain.tileSize, simMain.tileSize);
    }

    public void setAction() {
        actionLockCounter++;
        if(actionLockCounter == 144) {
            Random random = new Random();
            int i = random.nextInt(100)+1;
            if(i <= 25) {
                direction = "up";
            }
            else if(i <= 50) {
                direction = "down";
            }
            else if(i <= 75) {
                direction = "left";
            }
            else {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
    public void update() {
        setAction();
        switch (direction) {
            case "up":
                worldY = worldY - speed;
                break;
            case "down":
                worldY = worldY + speed;
                break;
            case "left":
                worldX = worldX - speed;
                break;
            case "right":
                worldX = worldX + speed;
                break;
        }

        spriteCounter++;
        if (spriteCounter > 24) {
//            if (spriteNum == 1) {
//                spriteNum = 1;
//            } else if (spriteNum == 2) {
//                spriteNum = 1;
//            }
            spriteCounter = 0;
        }
    }


    public void draw(Graphics2D g2) {
        int screenX = worldX - simMain.user.worldX + simMain.user.screenX;
        int screenY = worldY - simMain.user.worldY + simMain.user.screenY;

        BufferedImage image;

        if (isInUserVision()) {
            image = animation.moveAnimation();
            if(simMain.debug) {
                g2.setColor(Color.red);
                g2.drawRect(screenX, screenY, simMain.tileSize, simMain.tileSize);
            }
            g2.drawImage(image, screenX, screenY, simMain.tileSize, simMain.tileSize, null);
        }
    }



}
