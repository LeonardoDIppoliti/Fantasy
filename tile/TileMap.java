package it.unicam.cs.mpgc.rpg126043.tile;

import java.io.*;
/** Tile map loaded from a text file, with error checking. */
public class TileMap {
    private final int[][] grid;
    private final int cols;
    private final int rows;


    private TileMap(int[][] grid,int cols,int rows){
        this.grid=grid;
        this.cols=cols;
        this.rows=rows;
    }

    public static TileMap loadMap(String filePath, int cols, int rows){
        try {
            InputStream inStream =TileMap.class.getResourceAsStream(filePath);
            if(inStream==null) {throw new IllegalStateException("MAP NOT FOUND " + filePath);}
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(inStream));
            int[][] grid = new int[cols][rows];
            for (int row = 0;row<rows;row++){
                String line = buffReader.readLine();
                if(line == null){throw new IllegalStateException(filePath + "MISSING ROWS (row " + (row+1)+")");}
                String[] parts = line.trim().split("\\s+");
                if(parts.length<cols){throw new IllegalStateException(filePath + " : row " +(row+1) +" TOO SHORT" );}
                for(int col =0; col<cols;col++){
                    grid[col][row] = Integer.parseInt(parts[col]);
                }
            }
            inStream.close();
            return new TileMap(grid,cols,rows);
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

    public boolean inBounds(int col,int row){
        return col>=0 && row>=0 && col < cols && row<rows;
    }
    public int getTileIndex(int col,int row){
        return grid[col][row];
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }
}
