package it.unicam.cs.mpgc.rpg126043.Entity;

import it.unicam.cs.mpgc.rpg126043.Entity_Object.Equipment;
import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;
import it.unicam.cs.mpgc.rpg126043.Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Party_Member  {
    GamePanel gP;
    String name;
    int strength;
    int maxLife;
    int dexterity;
    int attack;
    int defense;
    Equipment currentWeapon;
    Equipment currentShield;
    boolean KO;
    boolean isLeader;
    int currentLife;
    int exp;
    int nextLevelExp;
    int level;

    BufferedImage up1;
    BufferedImage down1;
    BufferedImage down2;
    BufferedImage up2;




    public Party_Member(GamePanel gP, String name, int strength, int maxLife, int dexterity,int level, Equipment weapon, Equipment shield, boolean isLeader,boolean KO) {
        this.gP=gP;
        //STATS
        this.name =name;
         this.level=level;
        this.maxLife = maxLife;
        this.currentLife = maxLife;
        this.dexterity = dexterity; // Affects damage taken and escape success
        this.strength = strength; // Affects damage dealt
         exp=0;
         nextLevelExp=40;
        this.currentWeapon = weapon;
        this.currentShield = shield;
        attack = getAttack(); // Total damage dealt (strength and attackValue of weapon)
        defense=getDefense(); //total damage taken (dexterity and defenseValue of shield)
        this.KO=KO;
        this.isLeader=isLeader;

    }

    public void getMemberImages(String ImagePath,int width,int height){
        up1=setup(ImagePath,width,height);
    }
    public void getMemberCombatImages(String ImagePath,String ImagePath2,String ImagePath3,int width,int height){
        down1=setup(ImagePath,width,height);
        down2=setup(ImagePath2,width,height);
        up2=setup(ImagePath3,width,height);
    }

    private BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath+".png")));
            image = uTool.scaleImage(image, width, height);

        }catch (Exception e){
            e.printStackTrace();
        }
        return image;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public int getStrength() {
        return strength;
    }
    public int getDexterity() {
        return dexterity;
    }
    public Entity getCurrentWeapon(){
        return currentWeapon;
    }
    public Entity getCurrentShield() {
        return currentShield;
    }
    public String getName(){
        return name;
    }
    public int getCurrentLife() {
        return currentLife;
    }
    public void setCurrentLife(int life){
        this.currentLife = life;
    }
    public int getAttack(){
        return attack = strength + currentWeapon.getAttackValue() + currentShield.getAttackValue();
    }
    public int getDefense() {
        return defense = dexterity + currentShield.getDefenseValue() + currentWeapon.getDefenseValue();
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getNextLevelExp() {
        return nextLevelExp;
    }
    public void setNextLevelExp(int nextLevelExp) {
        this.nextLevelExp = nextLevelExp;
    }
    public int getExp() {
        return exp;
    }
}
