package asset.particle;

import asset.Asset;
import main.SimulationMain;

import java.awt.*;

public class Particle {

    private final SimulationMain simMain;
    public Asset generator;
    public Color color;
    public int xd;
    public int yd;
    public int size;
    public int durationMax;
    public int durationLeft;
    public int worldX;
    public int worldY;
    public boolean isAlive;
    public int speed;



    public Particle(SimulationMain simMain, Asset generator, Color color, int size, int speed, int maxDuration, int xd, int yd) {
        this.simMain = simMain;
        this.generator = generator;
        this.color = color;
        this.size = size;
        this.xd = xd;
        this.yd = yd;
        worldX = generator.worldX;
        worldY = generator.worldY;
        isAlive = true;
        this.durationMax = maxDuration;
        this.durationLeft = maxDuration;
        this.speed = speed;
    }


    public void update() {
        durationLeft--;

        worldX += xd*speed;
        worldY += yd*speed;

        if(durationLeft == 0){
            isAlive = false;
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - simMain.user.worldX + simMain.user.screenX;
        int screenY = worldY - simMain.user.worldY + simMain.user.screenY;

        g2.setColor(color);
        g2.fillRect(screenX, screenY, size, size);
    }
}
