package it.unicam.cs.mpgc.rpg126043.Tile;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;
import it.unicam.cs.mpgc.rpg126043.Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gP;
    public Tile[] tile;
    public int[][] mapTileNum;
    public TileManager(GamePanel gP) {
        this.gP = gP;
        tile = new Tile[10];
        mapTileNum = new int[gP.maxWorldCol][gP.maxWorldRow];
        getTileImage();
        loadMap("/Maps/world01.txt");
    }
    public void getTileImage(){
            setup(0,"002",false);
            setup(2,"018",true);
            setup(4,"016",true);
            setup(5,"003",false);

    }

    public void setup(int index,String imageNum,boolean collision){
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/" + imageNum +".png")));
            tile[index].image = uTool.scaleImage(tile[index].image, gP.getTileSize(), gP.getTileSize());
            tile[index].collision=collision;

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try {
            InputStream iS = getClass().getResourceAsStream(filePath);
            BufferedReader bR = new BufferedReader(new InputStreamReader(iS));

            int col=0;
            int row=0;
            while(col< gP.maxWorldCol && row< gP.maxWorldRow){
                String line = bR.readLine();
                while(col< gP.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gP.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            bR.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
    int worldCol = 0;
    int worldRow = 0;


        while (worldCol<gP.maxWorldCol && worldRow<gP.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            //MOVING CAMERA
            int worldX=worldCol* gP.getTileSize();
            int worldY=worldRow* gP.getTileSize();
            int screenX= worldX-gP.player.getWorldX()+gP.player.getScreenX();
            int screenY= worldY-gP.player.getWorldY()+gP.player.getScreenY();

            //IMPROVING RENDERING
            if(worldX + gP.getTileSize() > gP.player.getWorldX() - gP.player.getScreenX() &&
               worldX - gP.getTileSize() < gP.player.getWorldX() + gP.player.getScreenX() &&
               worldY + gP.getTileSize() > gP.player.getWorldY() - gP.player.getScreenY() &&
               worldY - gP.getTileSize() < gP.player.getWorldY() + gP.player.getScreenY()
            ){
                g2.drawImage(tile[tileNum].image,screenX,screenY,null);
            }
            worldCol++;

            if(worldCol==gP.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
