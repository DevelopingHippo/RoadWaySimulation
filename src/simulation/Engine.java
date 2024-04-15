package simulation;

import asset.Asset;
import asset.particle.Particle;
import asset.particle.ParticleAnimation;
import main.SimulationMain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Engine {


    public final SimulationMain simMain;
    public CollisionChecker collisionChecker;
    public ArrayList<Particle> particleList = new ArrayList<>();
    public ArrayList<ParticleAnimation> particleAnimationList = new ArrayList<>();
    private List<Asset> proxyAssetList;

    Iterator<Particle> iterParticle;
    Iterator<ParticleAnimation> iterAnimation;
    Iterator<Asset> iterAsset;


    public Engine(SimulationMain simMain) {
        this.simMain = simMain;
    }

    public void setup() {
        collisionChecker = new CollisionChecker(simMain);

        simMain.assetManager.spawnVehicle(15, 7);
        simMain.assetManager.spawnVehicle(9, 13);
        simMain.assetManager.spawnVehicle(2, 7);
        simMain.assetManager.spawnVehicle(8, 4);
    }


    public void update() {
        simMain.user.update();

        proxyAssetList = new ArrayList<>(simMain.assetManager.allAssets);

        iterAsset = proxyAssetList.iterator();
        while (iterAsset.hasNext()) {
            Asset asset = iterAsset.next();
            if (asset != null) {
                if (asset.isAlive) {
                    asset.update();
                }
                else {
                    iterAsset.remove();
                }
            }
        }

        iterParticle = particleList.iterator();
        while (iterParticle.hasNext()) {
            Particle particle = iterParticle.next();
            if (particle != null) {
                if (particle.isAlive) {
                    particle.update();
                }
                else {
                    iterParticle.remove();
                }
            }
        }

        iterAnimation = particleAnimationList.iterator();
        while (iterAnimation.hasNext()) {
            ParticleAnimation particleA = iterAnimation.next();
            if (particleA != null) {
                if (particleA.isAlive) {
                    particleA.update();
                }
                else {
                    iterAnimation.remove();
                }
            }
        }

    }
}