package simulation;

import asset.Asset;
import asset.particle.Particle;
import asset.particle.ParticleAnimation;
import main.SimulationMain;

import java.util.ArrayList;
import java.util.Iterator;

public class Engine {


    public final SimulationMain simMain;
    public CollisionChecker collisionChecker;
    public ArrayList<Particle> particleList = new ArrayList<>();
    Iterator<Particle> iter;
    Iterator<ParticleAnimation> iterAnimation;
    public ArrayList<ParticleAnimation> particleAnimationList = new ArrayList<>();


    public Engine(SimulationMain simMain) {
        this.simMain = simMain;
    }

    public void setup() {
        collisionChecker = new CollisionChecker(simMain);

        simMain.assetManager.spawnVehicle(8, 11);
        simMain.assetManager.spawnVehicle(10, 11);
        simMain.assetManager.spawnVehicle(12, 11);
        simMain.assetManager.spawnVehicle(14, 11);
        simMain.assetManager.spawnVehicle(16, 11);
    }


    public void update() {
        simMain.user.update();
        for (Asset asset : simMain.assetManager.allAssets) {
            asset.update();
        }

        iter = particleList.iterator();
        while (iter.hasNext()) {
            Particle particle = iter.next();
            if (particle != null) {
                if (particle.isAlive) {
                    particle.update();
                }
                else {
                    iter.remove();
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