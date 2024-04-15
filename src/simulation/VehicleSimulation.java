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
        vehicle.speed = rand.nextInt(4) + 1;

        checkTileRight();
        if(vehicle.parked){
            leaveParking(vehicle);
            inStartingTile(vehicle);
        }
        else {
            drivingOnTheRight(vehicle);
//            if(vehicle.isDrivingLeft){
//                drivingOnTheLeft(vehicle);
//            }
//            else {
//                drivingOnTheRight(vehicle);
//            }
        }
    }

    public void drivingOnTheLeft(Vehicle vehicle){
        int vehicleLeftWorldX = vehicle.worldX + vehicle.collisionBox.x;
        int entityRightWorldX = vehicle.worldX + vehicle.collisionBox.x + vehicle.collisionBox.width;
        int entityTopWorldY = vehicle.worldY + vehicle.collisionBox.y;
        int entityBottomWorldY = vehicle.worldY + vehicle.collisionBox.y + vehicle.collisionBox.height;


        int vehicleLeftCol = vehicleLeftWorldX / vehicle.simMain.tileSize;
        int vehicleRightCol = entityRightWorldX / vehicle.simMain.tileSize;
        int vehicleTopRow = entityTopWorldY / vehicle.simMain.tileSize;
        int vehicleBottomRow = entityBottomWorldY / vehicle.simMain.tileSize;

        int tileNum1, tileNum2;

        switch(vehicle.direction) {
            case "up":
                vehicleTopRow = (entityTopWorldY - vehicle.speed) / vehicle.simMain.tileSize;
                tileNum1 = vehicle.simMain.tileManager.mapTileNum[vehicleLeftCol][vehicleTopRow];
                tileNum2 = vehicle.simMain.tileManager.mapTileNum[vehicleRightCol][vehicleTopRow];

                if(!vehicle.simMain.tileManager.tiles[tileNum1].leftRoad || !vehicle.simMain.tileManager.tiles[tileNum2].leftRoad) {
                    vehicle.direction =  "right";
                }
                break;

            case "right":
                vehicleRightCol = (entityRightWorldX + vehicle.speed) / vehicle.simMain.tileSize;
                tileNum1 = vehicle.simMain.tileManager.mapTileNum[vehicleRightCol][vehicleTopRow];
                tileNum2 = vehicle.simMain.tileManager.mapTileNum[vehicleRightCol][vehicleBottomRow];

                if(!vehicle.simMain.tileManager.tiles[tileNum1].leftRoad || !vehicle.simMain.tileManager.tiles[tileNum2].leftRoad) {
                    vehicle.direction =  "down";
                }

                break;
            case "down":
                vehicleBottomRow = (entityBottomWorldY + vehicle.speed) / vehicle.simMain.tileSize;
                tileNum1 = vehicle.simMain.tileManager.mapTileNum[vehicleLeftCol][vehicleBottomRow];
                tileNum2 = vehicle.simMain.tileManager.mapTileNum[vehicleRightCol][vehicleBottomRow];

                if(!vehicle.simMain.tileManager.tiles[tileNum1].leftRoad || !vehicle.simMain.tileManager.tiles[tileNum2].leftRoad){
                    vehicle.direction = "left";
                }

                break;
            case "left":
                vehicleLeftCol = (vehicleLeftWorldX - vehicle.speed) / vehicle.simMain.tileSize;
                tileNum1 = vehicle.simMain.tileManager.mapTileNum[vehicleLeftCol][vehicleTopRow];
                tileNum2 = vehicle.simMain.tileManager.mapTileNum[vehicleLeftCol][vehicleBottomRow];

                if(!vehicle.simMain.tileManager.tiles[tileNum1].leftRoad || !vehicle.simMain.tileManager.tiles[tileNum2].leftRoad) {
                        vehicle.direction =  "up";
                }

                break;
        }
    }

    public void checkTileUp(){

    }
    public void checkTileDown() {

    }
    public void checkTileLeft() {

    }
    public void checkTileRight() {

        int entityRightWorldX = vehicle.worldX + vehicle.collisionBox.x + vehicle.collisionBox.width;
        int vehicleRightCol = entityRightWorldX / vehicle.simMain.tileSize;

        int entityTopWorldY = vehicle.worldY + vehicle.collisionBox.y;
        int vehicleTopRow = entityTopWorldY / vehicle.simMain.tileSize;

        int tileNum = vehicle.simMain.tileManager.mapTileNum[vehicleRightCol][vehicleTopRow];
    }



    public void drivingOnTheRight(Vehicle vehicle) {

        int entityLeftWorldX = vehicle.worldX + vehicle.collisionBox.x;
        int entityRightWorldX = vehicle.worldX + vehicle.collisionBox.x + vehicle.collisionBox.width;
        int entityTopWorldY = vehicle.worldY + vehicle.collisionBox.y;
        int entityBottomWorldY = vehicle.worldY + vehicle.collisionBox.y + vehicle.collisionBox.height;

        int vehicleLeftCol = entityLeftWorldX / vehicle.simMain.tileSize;
        int vehicleRightCol = entityRightWorldX / vehicle.simMain.tileSize;
        int vehicleTopRow = entityTopWorldY / vehicle.simMain.tileSize;
        int vehicleBottomRow = entityBottomWorldY / vehicle.simMain.tileSize;

        int tileNum1, tileNum2;

        switch (vehicle.direction) {
            case "up":
                vehicleTopRow = (entityTopWorldY - vehicle.speed) / vehicle.simMain.tileSize;
                tileNum1 = vehicle.simMain.tileManager.mapTileNum[vehicleLeftCol][vehicleTopRow];
                tileNum2 = vehicle.simMain.tileManager.mapTileNum[vehicleRightCol][vehicleTopRow];
                if (vehicle.simMain.tileManager.tiles[tileNum1].leftRoad || vehicle.simMain.tileManager.tiles[tileNum2].leftRoad) {
                    vehicle.direction = "left";
                }
                break;
            case "left":
                vehicleLeftCol = (entityLeftWorldX - vehicle.speed) / vehicle.simMain.tileSize;
                tileNum1 = vehicle.simMain.tileManager.mapTileNum[vehicleLeftCol][vehicleTopRow];
                tileNum2 = vehicle.simMain.tileManager.mapTileNum[vehicleLeftCol][vehicleBottomRow];
                if (vehicle.simMain.tileManager.tiles[tileNum1].leftRoad || vehicle.simMain.tileManager.tiles[tileNum2].leftRoad) {
                    vehicle.direction = "down";
                }
                break;
            case "down":
                vehicleBottomRow = (entityBottomWorldY + vehicle.speed) / vehicle.simMain.tileSize;
                tileNum1 = vehicle.simMain.tileManager.mapTileNum[vehicleLeftCol][vehicleBottomRow];
                tileNum2 = vehicle.simMain.tileManager.mapTileNum[vehicleRightCol][vehicleBottomRow];
                if (vehicle.simMain.tileManager.tiles[tileNum1].leftRoad || vehicle.simMain.tileManager.tiles[tileNum2].leftRoad) {
                    vehicle.direction = "right";
                }
                break;
            case "right":
                vehicleRightCol = (entityRightWorldX + vehicle.speed) / vehicle.simMain.tileSize;
                tileNum1 = vehicle.simMain.tileManager.mapTileNum[vehicleRightCol][vehicleTopRow];
                tileNum2 = vehicle.simMain.tileManager.mapTileNum[vehicleRightCol][vehicleBottomRow];
                if (vehicle.simMain.tileManager.tiles[tileNum1].leftRoad || vehicle.simMain.tileManager.tiles[tileNum2].leftRoad) {
                    vehicle.direction = "up";
                }
                break;
        }
    }

    public void inStartingTile(Vehicle vehicle) {
        vehicle.collisionBox.x = vehicle.worldX + vehicle.collisionBox.x;
        vehicle.collisionBox.y = vehicle.worldY + vehicle.collisionBox.y;

        int defaultX = vehicle.startingTileBox.x;
        int defaultY = vehicle.startingTileBox.y;

        vehicle.startingTileBox.x = vehicle.startingTileX + vehicle.startingTileBox.x;
        vehicle.startingTileBox.y = vehicle.startingTileY + vehicle.startingTileBox.y;

        switch(vehicle.direction) {
            case "up": vehicle.collisionBox.y -= vehicle.speed; break;
            case "down": vehicle.collisionBox.y += vehicle.speed; break;
            case "left": vehicle.collisionBox.x -= vehicle.speed; break;
            case "right": vehicle.collisionBox.x += vehicle.speed; break;
        }
        vehicle.parked = vehicle.collisionBox.intersects(vehicle.startingTileBox);
        vehicle.collisionBox.x = vehicle.collisionBoxDefaultX;
        vehicle.collisionBox.y = vehicle.collisionBoxDefaultY;

        vehicle.startingTileBox.x = defaultX;
        vehicle.startingTileBox.y = defaultY;
    }

    public void leaveParking(Vehicle vehicle) {
        int entityLeftWorldX = vehicle.worldX + vehicle.collisionBox.x;
        int entityRightWorldX = vehicle.worldX + vehicle.collisionBox.x + vehicle.collisionBox.width;
        int entityTopWorldY = vehicle.worldY + vehicle.collisionBox.y;
        int entityBottomWorldY = vehicle.worldY + vehicle.collisionBox.y + vehicle.collisionBox.height;

        int vehicleLeftCol = entityLeftWorldX / vehicle.simMain.tileSize;
        int vehicleRightCol = entityRightWorldX / vehicle.simMain.tileSize;
        int vehicleTopRow = entityTopWorldY / vehicle.simMain.tileSize;
        int vehicleBottomRow = entityBottomWorldY / vehicle.simMain.tileSize;

        int tileNum1, tileNum2;
        vehicle.direction = "right";

        switch(vehicle.direction) {
            case "up":
                vehicleTopRow = (entityTopWorldY - vehicle.speed) / vehicle.simMain.tileSize;
                tileNum1 = vehicle.simMain.tileManager.mapTileNum[vehicleLeftCol][vehicleTopRow];
                tileNum2 = vehicle.simMain.tileManager.mapTileNum[vehicleRightCol][vehicleTopRow];

                if(vehicle.simMain.tileManager.tiles[tileNum1].collision || vehicle.simMain.tileManager.tiles[tileNum2].collision) {
                        vehicle.direction =  "right";
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

        }
    }




}
