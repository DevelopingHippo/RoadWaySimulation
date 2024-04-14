package renderer;

import asset.Asset;
import main.SimulationMain;

import java.awt.*;
import java.util.ArrayList;

public class SimRenderer {
    public final SimulationMain simMain;
    public ArrayList<Asset> renderEntityList = new ArrayList<>();


    public SimRenderer(SimulationMain simMain) {
        this.simMain = simMain;
    }



    public void render(Graphics2D g2) {

        simMain.tileManager.draw(g2);

        for(Asset asset : simMain.assetManager.allAssets) {
            asset.draw(g2);
        }

        g2.dispose();
    }


}
