package simulation;


import asset.Asset;
import main.SimulationMain;

import java.util.Objects;

public class CollisionChecker {

    public final SimulationMain simMain;
    public CollisionChecker(SimulationMain simMain) {
        this.simMain = simMain;
    }



    // CHECKS IF ENTITY WILL RUN INTO A SOLID TILE
    public void checkTile(Asset asset) {
        int entityLeftWorldX = asset.worldX + asset.collisionBox.x;
        int entityRightWorldX = asset.worldX + asset.collisionBox.x + asset.collisionBox.width;
        int entityTopWorldY = asset.worldY + asset.collisionBox.y;
        int entityBottomWorldY = asset.worldY + asset.collisionBox.y + asset.collisionBox.height;

        int entityLeftCol = entityLeftWorldX / simMain.tileSize;
        int entityRightCol = entityRightWorldX / simMain.tileSize;
        int entityTopRow = entityTopWorldY / simMain.tileSize;
        int entityBottomRow = entityBottomWorldY / simMain.tileSize;

        int tileNum1, tileNum2;

        switch(asset.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - asset.speed) / simMain.tileSize;
                tileNum1 = simMain.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = simMain.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if(simMain.tileManager.tiles[tileNum1].collision || simMain.tileManager.tiles[tileNum2].collision) {
                    asset.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + asset.speed) / simMain.tileSize;
                tileNum1 = simMain.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = simMain.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(simMain.tileManager.tiles[tileNum1].collision || simMain.tileManager.tiles[tileNum2].collision) {
                    asset.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - asset.speed) / simMain.tileSize;
                tileNum1 = simMain.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = simMain.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if(simMain.tileManager.tiles[tileNum1].collision || simMain.tileManager.tiles[tileNum2].collision) {
                    asset.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + asset.speed) / simMain.tileSize;
                tileNum1 = simMain.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = simMain.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(simMain.tileManager.tiles[tileNum1].collision || simMain.tileManager.tiles[tileNum2].collision) {
                    asset.collisionOn = true;
                }
                break;
        }
    }


    public Asset vehicleCollision(Asset vehicle_1) {
        Asset hit_vehicle = null;
        for(Asset vehicle_2 : simMain.assetManager.allAssets) {
            if(vehicle_2 != null && !Objects.equals(vehicle_2.uuid, vehicle_1.uuid)) {
                vehicle_1.collisionBox.x = vehicle_1.worldX + vehicle_1.collisionBox.x;
                vehicle_1.collisionBox.y = vehicle_1.worldY + vehicle_1.collisionBox.y;

                vehicle_2.collisionBox.x = vehicle_2.worldX + vehicle_2.collisionBox.x;
                vehicle_2.collisionBox.y = vehicle_2.worldY + vehicle_2.collisionBox.y;

                switch(vehicle_1.direction) {
                    case "up": vehicle_1.collisionBox.y -= vehicle_1.speed; break;
                    case "down": vehicle_1.collisionBox.y += vehicle_1.speed; break;
                    case "left": vehicle_1.collisionBox.x -= vehicle_1.speed; break;
                    case "right": vehicle_1.collisionBox.x += vehicle_1.speed; break;
                }

                if(vehicle_1.collisionBox.intersects(vehicle_2.collisionBox)) {
                    vehicle_1.collisionOn = true;
                    hit_vehicle = vehicle_2;
                }

                vehicle_1.collisionBox.x = vehicle_1.collisionBoxDefaultX;
                vehicle_1.collisionBox.y = vehicle_1.collisionBoxDefaultY;
                vehicle_2.collisionBox.x = vehicle_2.collisionBoxDefaultX;
                vehicle_2.collisionBox.y = vehicle_2.collisionBoxDefaultY;
            }
        }
        return hit_vehicle;
    }

}

