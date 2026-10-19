package it.unicam.cs.mpgc.rpg126043.Entity;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;
import it.unicam.cs.mpgc.rpg126043.Main.UtilityTool;
import it.unicam.cs.mpgc.rpg126043.UI.UI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Objects;

public abstract class Entity {
    protected final GamePanel gP;
    private final int animationInterval = 10;
    protected int spriteNum = 1;
    private int solidAreaDefaultX, solidAreaDefaultY;

    //ENTITIES ATTRIBUTES
    private String name;
    private int speed;
    protected Direction direction = Direction.DOWN;
    protected SpriteSet sprites;
    private int worldX, worldY;
    private Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    private boolean collisionOn;

    //COUNTER
    private int spriteCount;

    protected Entity(GamePanel gP, String name, int speed) {
        this.gP = gP;
        this.name = name;
        this.speed = speed;
    }


    public void update() {
        setAction();
        collisionOn = false;
        checkCollision();
        if (!collisionOn) move();
        animate();

    }

    protected void setAction() {
    }

    protected void checkCollision() {

        gP.cChecker.checkTile(this);
        gP.cChecker.checkEntity(this, gP.npc);
        gP.cChecker.checkEntity(this, gP.monster);
        if (gP.cChecker.checkPlayer(this)) onPlayerContact();
    }

    protected void onPlayerContact() {
    }

    protected void move() {
        worldX += direction.getdX() * speed;
        worldY += direction.getdY() * speed;
    }

    protected void animate() {
        if (++spriteCount >= animationInterval) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCount = 0;
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = getWorldX()- gP.player.getWorldX() + gP.player.getScreenX();
        int screenY = getWorldY() - gP.player.getWorldY() + gP.player.getScreenY();
        if (isOnScreen(screenX, screenY)) {
            drawBody(g2, screenX, screenY);
        }
    }

    private boolean isOnScreen(int screenX, int screenY) {
        int tileSize = gP.getTileSize();
        return screenX + tileSize > 0 && screenX - tileSize < gP.getScreenWidth()
                && screenY + tileSize > 0 && screenY - tileSize < gP.getScreenHeight();
    }

    protected void drawBody(Graphics2D g2, int screenX, int screenY) {
        g2.drawImage(sprites.get(direction, spriteNum), screenX, screenY, gP.getTileSize(), gP.getTileSize(), null);
    }

    protected void setAlpha(Graphics2D g2, float alpha) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }

    protected BufferedImage loadImage(String path, int width, int height) {
        try {
            InputStream iS = getClass().getResourceAsStream(path + ".png");
            if (iS == null) {
                throw new IllegalStateException("SPRITE" + path + " NOT FOUND");
            }
            return new UtilityTool().scaleImage(ImageIO.read(iS), width, height);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    protected SpriteSet loadDirectional(String prefix) {
        SpriteSet spriteSet = new SpriteSet();
        int tileSize = gP.getTileSize();
        for (Direction d : Direction.values()) {
            spriteSet.set(d, loadImage(prefix + d.key() + "_1", tileSize, tileSize),
                    loadImage(prefix + d.key() + "_2", tileSize, tileSize));
        }
        return spriteSet;
    }

    public void setSolidArea(int x, int y, int width, int height) {
        solidArea = new Rectangle(x, y, width, height);
        solidAreaDefaultX = x;
        solidAreaDefaultY = y;

    }

    public String getName() {
        return name;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int X) {
       this.worldX = X;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int Y) {
        this.worldY = Y;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }
    public int getSpeed() {
        return speed;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

}

