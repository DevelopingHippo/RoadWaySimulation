package world;

import asset.AssetHelper;
import main.SimulationMain;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class TileManager {
    public final SimulationMain simMain;
    public HashMap<String, Integer> tileList;
    public Tile[] tiles;
    public int[][] mapTileNum;
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();


    public TileManager(SimulationMain simMain) {
        this.simMain = simMain;
        InputStream is = getClass().getResourceAsStream("/Tiles/Data/tiledata.txt");
        assert is != null;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while((line = br.readLine()) != null) {
                fileNames.add(line);
                collisionStatus.add(br.readLine());
            }
            br.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        tiles = new Tile[fileNames.size()];
        tileList = new HashMap<>();
        is = getClass().getResourceAsStream("/Maps/world.txt");
        br = new BufferedReader(new InputStreamReader(is));
        try {
            line = br.readLine();
            String[] maxTile = line.split(" ");
            simMain.world.maxWorldCol = maxTile.length;
            simMain.world.maxWorldRow = maxTile.length;
            mapTileNum = new int[simMain.world.maxWorldCol][simMain.world.maxWorldRow];
            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        getTileImage();
        loadMap("world");
    }

    public void getTileImage() {
        for(int i = 0; i < fileNames.size(); i++) {
            String fileName;
            boolean collision = false;
            fileName = fileNames.get(i);
            if(collisionStatus.get(i).equals("true")){
                collision = true;
            }
            setup(i, fileName, collision);
        }
    }

    public void setup(int i, String name, boolean collision) {
        try {
            String filePath = "/Tiles/Images/" + name;
            Tile tile = new Tile();
            tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(filePath)));
            tile.image = AssetHelper.scaleImage(tile.image, simMain.tileSize, simMain.tileSize);

            if(name.contains("street")) {
                tile.road = true;
                if(name.contains("left")){
                    tile.leftRoad = true;
                }
            }
            tile.name = name;
            tile.collision = collision;
            tiles[i] = tile;
            tileList.put(name, i);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String map) {
        map = "/Maps/" + map + ".txt";
        try {
            InputStream is = getClass().getResourceAsStream(map);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < simMain.world.maxWorldCol && row < simMain.world.maxWorldRow) {
                String line = br.readLine();
                while (col < simMain.world.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int tileType = (Integer.parseInt(numbers[col]));
                    mapTileNum[col][row] = tileType;
                    col++;
                }
                if (col == simMain.world.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < simMain.world.maxWorldCol && worldRow < simMain.world.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * simMain.tileSize;
            int worldY = worldRow * simMain.tileSize;
            int screenX = worldX - simMain.user.worldX + simMain.user.screenX;
            int screenY = worldY - simMain.user.worldY + simMain.user.screenY;

            if (worldX + simMain.tileSize > simMain.user.worldX - simMain.user.screenX &&
                    worldX - simMain.tileSize < simMain.user.worldX + simMain.user.screenX &&
                    worldY + simMain.tileSize > simMain.user.worldY - simMain.user.screenX &&
                    worldY - simMain.tileSize < simMain.user.worldY + simMain.user.screenY) {
                g2.drawImage(tiles[tileNum].image, screenX, screenY, simMain.tileSize, simMain.tileSize,null);
            }
            worldCol++;
            if (worldCol == simMain.world.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
            if(tiles[tileNum].debug) {
                g2.setColor(Color.red);
                g2.drawRect(screenX, screenY, simMain.tileSize, simMain.tileSize);
            }
        }
    }
}
