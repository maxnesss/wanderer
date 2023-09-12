package org.gfa.wanderer;

import org.gfa.wanderer.character.GameCharacter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Stats extends JComponent implements KeyListener {

    private GameCharacter hero;


    public Stats(GameCharacter hero) {
        setPreferredSize(new Dimension(720, 20));

        setVisible(true);
        this.hero = hero;


    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawString("Player Max level " + hero.getLevel() + ", HP " + hero.getHp() + " out of " + hero.getMaxHp() + ",  Strike power "
                + hero.getSp() + ", Defence power: " + hero.getDp() + ", Has key: " + hero.isHasKey(), 0, 10);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        repaint();

    }
}
