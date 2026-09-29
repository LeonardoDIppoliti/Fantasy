package it.unicam.cs.mpgc.rpg126043.Main;

import it.unicam.cs.mpgc.rpg126043.Entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    private final int originaltilesize=16; // 16x16 tile
    private final int scale = 3;
    public final int tilesize=originaltilesize*scale; //48x48 tile size
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 12;
    private final int screenWidth = tilesize*maxScreenCol; //786 pixels
    private final int screenHeight = tilesize*maxScreenRow; //576 pixels

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    // Frame Per Second
    int FPS = 60;

    // set player position and speed
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        int nanoSecond = 1000000000;
        double drawInterval = (double) nanoSecond / FPS;
        double deltaTime = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer=0;
        int drawCount=0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            deltaTime += (currentTime - lastTime) / drawInterval;
            timer+=(currentTime - lastTime);
            lastTime = currentTime;

            if(deltaTime >= 1) {
                update();
                repaint();
                deltaTime --;
                drawCount++;
            }
            if(timer>= nanoSecond) {
                System.out.println("FPS" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2= (Graphics2D) g;
        player.draw(g2);
        g2.dispose();
    }
}
