package renderer;

import asset.Asset;
import asset.particle.Particle;
import asset.particle.ParticleAnimation;
import main.SimulationMain;
import world.Tile;

import java.awt.*;
import java.util.ArrayList;

public class SimRenderer {
    public final SimulationMain simMain;
    public ArrayList<Asset> renderEntityList = new ArrayList<>();
    public ArrayList<Particle> renderParticleList = new ArrayList<>();
    public ArrayList<ParticleAnimation> renderParticleAnimationList = new ArrayList<>();
    public ArrayList<Tile> debugTileList = new ArrayList<>();



    public SimRenderer(SimulationMain simMain) {
        this.simMain = simMain;
    }



    public void render(Graphics2D g2) {
        simMain.tileManager.draw(g2);
        for(Asset asset : simMain.assetManager.allAssets) {
            if(asset != null){
                renderEntityList.add(asset);
            }
        }

        for(Particle particle : simMain.engine.particleList) {
            if(particle != null){
                renderParticleList.add(particle);
            }
        }

        for(ParticleAnimation particle : simMain.engine.particleAnimationList) {
            if(particle != null){
                renderParticleAnimationList.add(particle);
            }
        }

        for(Asset asset : simMain.renderer.renderEntityList){
            asset.draw(g2);
        }
        for(Particle particle : renderParticleList){
            particle.draw(g2);
        }
        for(ParticleAnimation particle : renderParticleAnimationList){
            particle.draw(g2);
        }

        renderParticleList.clear();
        renderEntityList.clear();
        renderParticleAnimationList.clear();
        g2.dispose();
    }


}
