package asset.vehicle;

import asset.Asset;
import asset.AssetHelper;
import asset.particle.Particle;
import asset.particle.ParticleAnimation;
import main.SimulationMain;
import simulation.VehicleSimulation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Vehicle extends Asset {

    public boolean isMoving = true;
    public boolean collision = false;
    public int collisionBoxDefaultX, collisionBoxDefaultY;
    public final VehicleSimulation vehicleSimulation = new VehicleSimulation(this);

    public Vehicle(SimulationMain simMain) {
        super(simMain);
        AssetHelper.setupAllImages(this, "/images/vehicle/", simMain.tileSize, simMain.tileSize);
        setup();
        collisionBox = new Rectangle(0, 0, simMain.tileSize, simMain.tileSize);
    }

    public void generateSmokeParticle(Asset generator) {
        Color color = Color.darkGray;
        int size = 10;
        int speed = 1;
        int maxDuration = 144;
        Particle p1 = new Particle(simMain, generator, color, size, speed, maxDuration, -1, -1);
        Particle p2 = new Particle(simMain, generator, color, size, speed, maxDuration, 1, 1);
        Particle p3 = new Particle(simMain, generator, color, size, speed, maxDuration, 1, -1);
        Particle p4 = new Particle(simMain, generator, color, size, speed, maxDuration, -1, 1);
        Particle p5 = new Particle(simMain, generator, color, size, speed, maxDuration, 1, 0);
        Particle p6 = new Particle(simMain, generator, color, size, speed, maxDuration, 0, -1);

        simMain.engine.particleList.add(p1);
        simMain.engine.particleList.add(p2);
        simMain.engine.particleList.add(p3);
        simMain.engine.particleList.add(p4);
        simMain.engine.particleList.add(p5);
        simMain.engine.particleList.add(p6);
    }

    public void generateSmokeAnimation() {
        ParticleAnimation smokeAnimation = new ParticleAnimation(simMain, this, 500);
        simMain.engine.particleAnimationList.add(smokeAnimation);
    }





    public void update() {

        vehicleSimulation.simulate(this);

        collisionOn = false;
        simMain.engine.collisionChecker.checkTile(this);
//        simMain.engine.collisionChecker.checkObject(this, false);
        Asset hit_asset = simMain.engine.collisionChecker.vehicleCollision(this);


        if(hit_asset != null) {
            if(!particleGenerated){
                generateSmokeAnimation();
                particleGenerated = true;
            }
        }
        if(particleGenerated){
            particleLockCount++;
            if(particleLockCount > 500){
                particleLockCount = 0;
                particleGenerated = false;
                killAsset();
            }
        }
        else {
            if(!collisionOn) {
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
                    case "stop":
                        break;
                }
                spriteCounter++;
                if (spriteCounter > 24) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }
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
                g2.drawRect(screenX + collisionBox.x, screenY + collisionBox.y, collisionBox.width, collisionBox.height);
            }
            g2.drawImage(image, screenX, screenY,null);
        }
    }
}
