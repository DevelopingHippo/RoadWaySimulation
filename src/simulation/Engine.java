package simulation;

import asset.Asset;
import main.SimulationMain;

public class Engine {


    public final SimulationMain simMain;

    public Engine(SimulationMain simMain) {
        this.simMain = simMain;
    }

    public void setup() {
        simMain.assetManager.spawnVehicle(10, 12);
        simMain.assetManager.spawnVehicle(11, 12);
        simMain.assetManager.spawnVehicle(12, 12);
        simMain.assetManager.spawnVehicle(13, 12);
        simMain.assetManager.spawnVehicle(14, 12);


    }


    public void update() {
        simMain.user.update();
        for(Asset asset : simMain.assetManager.allAssets) {
            asset.update();
        }
    }
}
