package it.unicam.cs.mpgc.rpg126043.entity;

import it.unicam.cs.mpgc.rpg126043.interfaces.Camera;
import it.unicam.cs.mpgc.rpg126043.interfaces.MovementCollisionService;
import it.unicam.cs.mpgc.rpg126043.interfaces.EntityContext;
import it.unicam.cs.mpgc.rpg126043.resource.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
* Base class for everything that stays in the game world:
* movement, animation, collision and drawing
*
*/
public abstract class Entity {
    protected final EntityContext entityContext;
    private static final int ANIMATION_INTERVAL = 10;
    private final int areaX =0, areaY =0, areaWidth =48, areaHeight =48;
    protected int spriteNum = 1;

    //ENTITIES ATTRIBUTES
    private final String name;
    private final int speed;
    protected Direction direction = Direction.DOWN;
    protected SpriteSet sprites;
    private int worldX, worldY;
    private Rectangle solidArea = new Rectangle(areaX, areaY, areaWidth, areaHeight);
    private boolean collisionOn;

    //COUNTER
    private int spriteCount;

    protected Entity( EntityContext entityContext, String name, int speed) {
        this.entityContext = entityContext;
        this.name = name;
        this.speed = speed;
    }

    //LOGIC SECTION
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
        MovementCollisionService collService=entityContext.getCollision();
       collService.checkTile(this);
       collService.checkNpc(this);
       collService.checkMonster(this);
       if (collService.checkPlayer(this)) onPlayerContact();
    }

    /*
    * Template Method called when an entity touches the player
    * Empty by default it's redefined by Monster, this way
    * Entity class doesn't need to know the combat section
    * */
    protected void onPlayerContact() {
    }

    protected void move() {
        worldX += direction.getDx() * speed;
        worldY += direction.getDy() * speed;
    }

    protected void animate() {
        if (++spriteCount >= ANIMATION_INTERVAL) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCount = 0;
        }
    }

    //DRAWING SECTION

    /*
    * Draws only if the entity is in the visible area (cameraView)
    * plus an extra margin of tile (for bigger sprites, like the attack ones)
     */
    public void draw(Graphics2D g2) {
        Camera camera=entityContext.getCamera();
        int tileSize = entityContext.getTileSize();
        Rectangle cameraView = camera.getViewBounds();
        cameraView.grow(tileSize,tileSize);
        if(!cameraView.intersects(new Rectangle(worldX,worldY,tileSize,tileSize))){return;}
        drawBody(g2,camera.toScreenX(worldX), camera.toScreenY(worldY));
    }

    protected void drawBody(Graphics2D g2, int screenX, int screenY) {
        int tileSize=entityContext.getTileSize();
        g2.drawImage(sprites.get(direction, spriteNum), screenX, screenY, tileSize,tileSize, null);
    }

    protected void setAlpha(Graphics2D g2, float alpha) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }

    //SPRITES SECTION
    protected BufferedImage loadImage(String path, int width, int height) {
        return SpriteLoader.loadImage(path, width, height);
    }

    protected SpriteSet loadDirectional(String prefix) {
        SpriteSet spriteSet = new SpriteSet();
        int tileSize = entityContext.getTileSize();
        for (Direction dir : Direction.values()) {
            spriteSet.set(dir, loadImage(prefix + dir.key() + "_1", tileSize, tileSize),
                             loadImage(prefix + dir.key() + "_2", tileSize, tileSize));
        }
        return spriteSet;
    }


    //COLLISION SECTION
    public void setSolidArea(int x, int y, int width, int height) {
        solidArea = new Rectangle(x, y, width, height);

    }

    public Rectangle getBounds(){
        return new Rectangle(worldX+solidArea.x,worldY+solidArea.y,solidArea.width,solidArea.height);
    }
    /*
    *Collision area after first step. Check collision BEFORE moving:
    * if the step leads to a wall or another entity, the entity will
    * stay still. It returns a copy, this way the area won't be modified
    */
    public Rectangle getBoundsAfterMove(){
        Rectangle rect = getBounds();
        rect.translate(direction.getDx()*speed,direction.getDy()*speed);
        return rect;
    }



    //ACCESS GETTER/SETTER SECTION
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
    public boolean isCollisionOn() {
        return collisionOn;
    }
    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }
    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

}

