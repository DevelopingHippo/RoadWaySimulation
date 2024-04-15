package simulation;

import asset.Asset;

import java.util.Random;

public class VehicleSimulation {


    public final Asset vehicle;

    public VehicleSimulation(Asset vehicle) {
        this.vehicle = vehicle;
    }



    public void simulate() {

        vehicle.actionLockCounter++;
        if(vehicle.actionLockCounter == 144) {
            Random random = new Random();
            int i = random.nextInt(100)+1;
            if(i <= 25) {
                vehicle.direction = "up";
            }
            else if(i <= 50) {
                vehicle.direction = "down";
            }
            else if(i <= 75) {
                vehicle.direction = "left";
            }
            else {
                vehicle.direction = "right";
            }
            vehicle.actionLockCounter = 0;
        }


    }

}
