package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Road Way Simulation");

        SimulationMain sim = new SimulationMain();
        window.add(sim);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        sim.setup();
        sim.startSimThread();
    }
}
