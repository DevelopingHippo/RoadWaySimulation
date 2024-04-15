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


    public void spawnVehicle(int worldX, int worldY) {

        Vehicle vehicle = new Vehicle(simMain);
        vehicle.worldX = worldX * simMain.tileSize;
        vehicle.worldY = worldY * simMain.tileSize;
        vehicle.uuid = AssetHelper.generateUUID();
        allAssets.add(vehicle);
    }


    public void removeAsset(Asset asset_to_remove) {
        Random rand = new Random();
        allAssets.removeIf(asset -> asset.equals(asset_to_remove));

        int selection = rand.nextInt(4);

        switch (selection) {
            case 0:
                spawnVehicle(15, 7);
                break;
            case 1:
                spawnVehicle(8, 4);
                break;
            case 2:
                spawnVehicle(2, 7);
                break;
            case 3:
                spawnVehicle(9, 13);
                break;
        }
    }
}
