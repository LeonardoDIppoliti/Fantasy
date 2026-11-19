package it.unicam.cs.mpgc.rpg126043.ui.component;
/** Single message with the elapsed time since creation. */
public class Message {
    private final String text;
    private int lifeTime;

    public Message(String text) {
        this.text = text;
    }

    public void tick(){lifeTime++;}
    public boolean isExpired(int lifeTimeFrames){
        return lifeTime>lifeTimeFrames;
    }
    public String getText(){
        return text;
    }
}
