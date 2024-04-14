package asset;

import main.SimulationMain;

import java.util.ArrayList;

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
        allAssets.add(vehicle);
    }


}
