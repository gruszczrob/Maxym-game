package com.company;

import com.company.classes.CharacterClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameField extends JPanel {
    private Team team;
    private CharacterClass[] players;
    public GameField(Team team) {
        this.team = team;
        this.players = team.getTeamMembers();

        setFocusable(true);
        addKeyListener(new FieldKeyListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (CharacterClass player : players) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
            g.drawString("healthPoints: " + player.getHealthPoints(), player.getX(), player.getY());
        }
    }

    public class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            for (CharacterClass player : players) {

                if (key == player.leftKey) {
                    player.left();
                    //player.setX(player.getX() - 40);
                }
                if (key == player.rightKey) {
                    //player.setX(player.getX() + 40);
                    player.right();
                }
                if (key == player.upKey) {
                    //player.setY(player.getY() - 40);
                    player.up();
                }
                if (key == player.downKey) {
                    //player.setY(player.getY() + 40);
                    player.down();
                }
                if (key == player.leftAttackKey) {
                    //player.setAttackLeftImage();
                    player.leftAttack();
                }
                if (key == KeyEvent.VK_O) {
                    player.setAttackLeftImage();

                    //timer
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    player.setBaseImage();
                                    repaint();
                                }
                            }, 200
                    );
                }
                if (key == player.rightAttackKey) {
                    player.rightAttack();
                    //player.setAttackRightImage();
                }
                if (key == KeyEvent.VK_P) {
                    player.setAttackRightImage();

                    //timer
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    player.setBaseImage();
                                    repaint();
                                }
                            }, 200
                    );
                }
            }
            repaint();
        }
    }
}
