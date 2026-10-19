package it.unicam.cs.mpgc.rpg126043.UI;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayScreen  extends SuperScreen {

    List<String> messageList = new ArrayList<>();
    List<Integer> messageCounter = new ArrayList<>();
    int frames = 60;
    public PlayScreen(GamePanel gP) {
        super(gP);
    }
    @Override
    public void drawScreen(Graphics2D g2) {


      int  x = gP.getTileSize() / 2;
       int y = gP.getTileSize();
        g2.drawString(gP.player.getName(), x, y);
        x += gP.getTileSize() * 3;

        double oneScale = (double) gP.getTileSize() / gP.player.getMaxLife();
        double hpBarValue = oneScale * gP.player.getCurrentLife();

        y -= 15;
        int i3 = 10;
        Color color = new Color(255, 0, 30);
        g2.setColor(color);
        g2.fillRect(x, y, (int) hpBarValue, i3);


    }

    public void drawMessage(Graphics2D g2) {

        int messageX = gP.getTileSize();
        int messageY = gP.getTileSize() * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        for (int i = 0; i < messageList.size(); i++) {
            if (messageList.get(i) != null) {
                g2.setColor(Color.black);
                messageX += 2;
                messageY += 2;
                g2.drawString(messageList.get(i), messageX, messageY);
                g2.setColor(Color.white);
                g2.drawString(messageList.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1; //messageCounter++
                messageCounter.set(i, counter); // set counter to array
                messageY += 50;

                if (messageCounter.get(i) > frames * 2) {
                    messageList.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public List<String> getMessageList() {
        return messageList;
    }
    public List<Integer> getMessageCounter() {
        return messageCounter;
    }
}
