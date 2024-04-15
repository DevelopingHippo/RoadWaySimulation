package simulation;

import asset.Asset;
import asset.vehicle.Vehicle;

import java.util.Random;

public class VehicleSimulation {


    public final Asset vehicle;
    Random rand = new Random();

    public VehicleSimulation(Asset vehicle) {
        this.vehicle = vehicle;
    }



    public void simulate(Vehicle vehicle) {

        vehicle.speed = rand.nextInt(5) + 1;

        checkTilesAround(vehicle);



//        vehicle.actionLockCounter++;
//        if(vehicle.actionLockCounter == 144) {
//            Random random = new Random();
//            int i = random.nextInt(100)+1;
//            if(i <= 25) {
//                vehicle.direction = "up";
//            }
//            else if(i <= 50) {
//                vehicle.direction = "down";
//            }
//            else if(i <= 75) {
//                vehicle.direction = "left";
//            }
//            else {
//                vehicle.direction = "right";
//            }
//            vehicle.actionLockCounter = 0;
//        }


    }

    public void checkTilesAround(Vehicle vehicle) {

        int entityLeftWorldX = vehicle.worldX + vehicle.collisionBox.x;
        int entityRightWorldX = vehicle.worldX + vehicle.collisionBox.x + vehicle.collisionBox.width;
        int entityTopWorldY = vehicle.worldY + vehicle.collisionBox.y;
        int entityBottomWorldY = vehicle.worldY + vehicle.collisionBox.y + vehicle.collisionBox.height;

        int vehicleLeftCol = entityLeftWorldX / vehicle.simMain.tileSize;
        int vehicleRightCol = entityRightWorldX / vehicle.simMain.tileSize;
        int vehicleTopRow = entityTopWorldY / vehicle.simMain.tileSize;
        int vehicleBottomRow = entityBottomWorldY / vehicle.simMain.tileSize;

        int tileNum1, tileNum2;

        switch(vehicle.direction) {
            case "up":
                vehicleTopRow = (entityTopWorldY - vehicle.speed) / vehicle.simMain.tileSize;
                tileNum1 = vehicle.simMain.tileManager.mapTileNum[vehicleLeftCol][vehicleTopRow];
                tileNum2 = vehicle.simMain.tileManager.mapTileNum[vehicleRightCol][vehicleTopRow];
                if(vehicle.simMain.tileManager.tiles[tileNum1].collision || vehicle.simMain.tileManager.tiles[tileNum2].collision) {
                    vehicle.direction =  "right";
                }
                break;
            case "down":
                vehicleBottomRow = (entityBottomWorldY + vehicle.speed) / vehicle.simMain.tileSize;
                tileNum1 = vehicle.simMain.tileManager.mapTileNum[vehicleLeftCol][vehicleBottomRow];
                tileNum2 = vehicle.simMain.tileManager.mapTileNum[vehicleRightCol][vehicleBottomRow];
                if(vehicle.simMain.tileManager.tiles[tileNum1].collision || vehicle.simMain.tileManager.tiles[tileNum2].collision) {
                    vehicle.direction =  "left";
                }
                break;
            case "left":
                vehicleLeftCol = (entityLeftWorldX - vehicle.speed) / vehicle.simMain.tileSize;
                tileNum1 = vehicle.simMain.tileManager.mapTileNum[vehicleLeftCol][vehicleTopRow];
                tileNum2 = vehicle.simMain.tileManager.mapTileNum[vehicleLeftCol][vehicleBottomRow];
                if(vehicle.simMain.tileManager.tiles[tileNum1].collision || vehicle.simMain.tileManager.tiles[tileNum2].collision) {
                    vehicle.direction =  "up";
                }
                break;
            case "right":
                vehicleRightCol = (entityRightWorldX + vehicle.speed) / vehicle.simMain.tileSize;
                tileNum1 = vehicle.simMain.tileManager.mapTileNum[vehicleRightCol][vehicleTopRow];
                tileNum2 = vehicle.simMain.tileManager.mapTileNum[vehicleRightCol][vehicleBottomRow];
                if(vehicle.simMain.tileManager.tiles[tileNum1].collision || vehicle.simMain.tileManager.tiles[tileNum2].collision) {
                    vehicle.direction = "down";
                }
                break;
        }
    }

}
