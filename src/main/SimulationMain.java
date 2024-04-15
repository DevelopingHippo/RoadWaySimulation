package main;

import asset.AssetManager;
import user.User;
import world.World;
import world.TileManager;
import renderer.SimRenderer;
import simulation.Engine;
import user.UserControls;

import javax.swing.*;
import java.awt.*;

public class SimulationMain extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16px x 16px tile (Character/Map/Textures)
    final int scale = 4; // Scales tile size * scale
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public int maxScreenCol = 24;
    public int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol; // 768 Pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 Pixels


    public Thread simThread;
    public Engine engine = new Engine(this);
    public SimRenderer renderer = new SimRenderer(this);
    public final int fps = 144;
    public final AssetManager assetManager = new AssetManager(this);
    public World world = new World(this);
    public final TileManager tileManager = new TileManager(this);
    public User user = new User(this);
    public boolean debug = false;



    public UserControls userControls = new UserControls(this);


    public SimulationMain() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.decode("#3B8FCA"));
        this.setDoubleBuffered(true);

        this.addKeyListener(userControls);
        this.setFocusable(true);
    }

    public void setup() {
        engine.setup();
//        engine.playMusic("backgroundMusic");
//        engine.music.setVolume(-40.0f);
//        engine.playEnvironment("Forest_Day");
//        engine.environmentSound.setVolume(-40.0f);
    }
    public void startSimThread() {
        simThread = new Thread(this);
        simThread.start();
    }


    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        //assetSetter.setupAllObjects();

        while(simThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if(delta >= 1) {
                engine.update();
                repaint();
                delta--;
            }
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        renderer.render(g2);
    }


}
