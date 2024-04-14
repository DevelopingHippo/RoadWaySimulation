package user;

import main.SimulationMain;

public class User {

    public final SimulationMain simMain;
    public int worldX;
    public int worldY;
    public int screenX;
    public int screenY;
    public int defaultWorldX = 12;
    public int defaultWorldY = 13;
    public String direction = "up";
    public boolean isMoving = false;
    public int speed = 2;

    public User(SimulationMain simMain) {
        this.simMain = simMain;
        screenX = simMain.screenWidth / 2 - (simMain.tileSize / 2);
        screenY = simMain.screenHeight / 2 - (simMain.tileSize / 2);
        worldX = simMain.tileSize * defaultWorldX;
        worldY = simMain.tileSize * defaultWorldY;
    }

    public void update(){


            if (simMain.userControls.upPressed || simMain.userControls.downPressed || simMain.userControls.leftPressed || simMain.userControls.rightPressed || simMain.userControls.enterPressed) {

                if (simMain.userControls.upPressed) {
                    direction = "up";
                    isMoving = true;
                }
                if (simMain.userControls.downPressed) {
                    direction = "down";
                    isMoving = true;
                }
                if (simMain.userControls.leftPressed) {
                    direction = "left";
                    isMoving = true;
                }
                if (simMain.userControls.rightPressed) {
                    direction = "right";
                    isMoving = true;
                }

                switch (direction) {
                    case "up": worldY = worldY - speed; break;
                    case "down": worldY = worldY + speed; break;
                    case "left": worldX = worldX - speed; break;
                    case "right": worldX = worldX + speed; break;
                }
            }
    }

}
