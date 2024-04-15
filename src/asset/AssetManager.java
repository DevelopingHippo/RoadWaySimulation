package asset;

import asset.vehicle.Vehicle;
import main.SimulationMain;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class AssetManager {

    public final SimulationMain simMain;
    public ArrayList<Asset> allAssets = new ArrayList<>();

    public AssetManager(SimulationMain simulationMain) {
        this.simMain = simulationMain;
    }


    public void spawnVehicle(int worldX, int worldY, boolean isDrivingLeft) {
        Vehicle vehicle = new Vehicle(simMain);
        vehicle.isDrivingLeft = isDrivingLeft;
        vehicle.worldX = worldX * simMain.tileSize;
        vehicle.worldY = worldY * simMain.tileSize;
        vehicle.startingTileX = worldX * simMain.tileSize;
        vehicle.startingTileY = worldY * simMain.tileSize;
        vehicle.uuid = AssetHelper.generateUUID();
        allAssets.add(vehicle);
    }


    public void removeAsset(Asset asset_to_remove) {
        Random rand = new Random();
        allAssets.removeIf(asset -> asset.equals(asset_to_remove));

        int selection = rand.nextInt(3);

        switch (selection) {
            case 0:
                spawnVehicle(11, 10, true);
                break;
            case 1:
                spawnVehicle(22, 15, false);
                break;
            case 2:
                spawnVehicle(31, 10, true);
                break;
        }
    }
}
