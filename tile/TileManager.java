package it.unicam.cs.mpgc.rpg126043.tile;


import it.unicam.cs.mpgc.rpg126043.interfaces.Camera;
import it.unicam.cs.mpgc.rpg126043.resource.SpriteLoader;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/** Manages tiles: loads them, responds if a cell is solid, and draws only visible ones. */
public class TileManager {
    private static final String MAP_FILE_PATH = "/Maps/world01.txt";
    private static final List<TileDef> TILE_DEFINITIONS =List.of(
            new TileDef(0,"002",false),
            new TileDef(2,"018",true),
            new TileDef(4,"016",true),
            new TileDef(5,"003",false)
    );
    private final Map<Integer,Tile> tiles=new HashMap<>();
    private final TileMap map;
    private final int tileSize;

    public TileManager(int tileSize,int cols,int rows) {
     this.tileSize=tileSize;
     for (TileDef tileDef: TILE_DEFINITIONS){
         tiles.put(tileDef.index(),new Tile(
                 SpriteLoader.loadImage("/Tiles/"+tileDef.image(),tileSize,tileSize),
                 tileDef.collision()));
         }
     this.map=TileMap.loadMap(MAP_FILE_PATH,cols,rows);
    }

    public boolean isSolid(int col, int row){
        if(!map.inBounds(col, row)){return true;}

        Tile tile=tiles.get(map.getTileIndex(col,row));
        return tile==null||tile.collision();

    }

    /*
    *Draws only tiles within the visible area.
    *  An unknown index is skipped in the drawing but counts as a wall in isSolid()
    * */
    public void draw(Graphics2D g2,Camera camera){
        Rectangle cameraView = camera.getViewBounds();
        int firstCol=Math.max(0,Math.floorDiv(cameraView.x, tileSize));
        int firstRow=Math.max(0,Math.floorDiv(cameraView.y, tileSize));
        int lastCol = Math.min(map.getCols()-1,Math.floorDiv(cameraView.x+cameraView.width,tileSize));
        int lastRow = Math.min(map.getRows()-1,Math.floorDiv(cameraView.y+cameraView.height,tileSize));

        for(int col=firstCol;col<=lastCol;col++){
            for(int row=firstRow;row<=lastRow;row++){
                Tile tile=tiles.get(map.getTileIndex(col,row));
                if(tile==null){continue;}
                g2.drawImage(tile.image(),
                             camera.toScreenX(col * tileSize),
                             camera.toScreenY(row*tileSize),null);
            }
        }
    }
}
