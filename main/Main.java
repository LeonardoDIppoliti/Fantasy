package it.unicam.cs.mpgc.rpg126043.main;

import javax.swing.*;

public class Main{
    static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            JFrame window = new JFrame("FANTASY");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);

            GamePanel gamePanel = new GamePanel();
            window.add(gamePanel);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);

            gamePanel.setUpGame();
            gamePanel.startGame();
            gamePanel.requestFocusInWindow();
        });

    }
}
