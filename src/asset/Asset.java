package asset;

import animation.Animation;
import main.SimulationMain;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Asset {

    public final SimulationMain simMain;
    public BufferedImage idle, up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public int worldX, worldY;
    public String direction = "down";
    public int speed = 1;
    public Animation animation = new Animation(this);
    public String uuid;
    public boolean isMoving = false;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int actionLockCounter = 0;

    public int particlesGenerated = 0;
    public int maxParticles = 10;
    public int particleLockCount = 0;
    public boolean particleGenerated = false;

    public boolean collisionOn = false;
    public boolean collision = false;
    public Rectangle collisionBox = new Rectangle(0, 0, 48, 48);
    public int collisionBoxDefaultX, collisionBoxDefaultY;

    public Asset(SimulationMain simMain) {
        this.simMain = simMain;
    }

    public void setAction(){

    }

    public void generateParticle(Asset generator, Asset target) {

    }


    public boolean isInUserVision() {
        return worldX + simMain.tileSize > simMain.user.worldX - simMain.user.screenX &&
                worldX - simMain.tileSize < simMain.user.worldX + simMain.user.screenX &&
                worldY + simMain.tileSize > simMain.user.worldY - simMain.user.screenX &&
                worldY - simMain.tileSize < simMain.user.worldY + simMain.user.screenY;
    }


    public void setup() {

    }

    public void update() {

        System.out.println("UPDATE FAILED");

////        if (isAlive) {
//            setAction();
////            collisionOn = false;
////            cardinal.collisionChecker.checkTile(this);
////            cardinal.collisionChecker.checkObject(this, false);
////            cardinal.collisionChecker.checkEntity(this);
////            if (!collisionOn) {
//                switch (direction) {
//                    case "up":
//                        worldY = worldY - speed;
//                        break;
//                    case "down":
//                        worldY = worldY + speed;
//                        break;
//                    case "left":
//                        worldX = worldX - speed;
//                        break;
//                    case "right":
//                        worldX = worldX + speed;
//                        break;
////                }
//            }
//
////        }
    }


    public void draw(Graphics2D g2){
        System.out.println("DRAW FAILED");
    }


}
