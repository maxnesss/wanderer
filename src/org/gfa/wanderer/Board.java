package org.gfa.wanderer;

import org.gfa.wanderer.background.Background;
import org.gfa.wanderer.character.Boss;
import org.gfa.wanderer.character.GameCharacter;
import org.gfa.wanderer.character.Hero;
import org.gfa.wanderer.character.SkeletonHandlerer;
import org.gfa.wanderer.background.Title;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JComponent implements KeyListener {

    public final int WIDTHSCREEN = 720;
    public final int HEIGHTSCREEN = 720;
    private Background background;
    private GameCharacter hero;
    private GameCharacter boss;
    private SkeletonHandlerer skeletonHandlerer;


    public Board(GameCharacter hero, Background background, GameCharacter boss, SkeletonHandlerer skeletonHandlerer) {
        // set the size of your draw board
        setPreferredSize(new Dimension(WIDTHSCREEN, HEIGHTSCREEN));
        setVisible(true);
        this.background = background;
        this.hero = hero;
        this.boss = boss;
        this.skeletonHandlerer = skeletonHandlerer;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        //bgr
        background.drawBackground(graphics);

        // hero
        hero.draw(graphics);

        // skeletons
        for (GameCharacter skeleton : skeletonHandlerer.getListOfSkeletons()) {
            skeleton.draw(graphics);
        }
        // boss
        boss.draw(graphics);
    }

    public static void main(String[] args) {
        // Here is how you set up a new window and adding our board to it
        JFrame frame = new JFrame("Max's RPG Game");


        Background background = new Background();

        GameCharacter hero;
        GameCharacter boss;
        hero = new Hero();
        boss = new Boss();

        SkeletonHandlerer skeletons = new SkeletonHandlerer();

        background.getTitles()[boss.getImgX() / 72][boss.getImgY() / 72].addToCharacterList(boss);
        background.getTitles()[hero.getImgX() / 72][hero.getImgY() / 72].addToCharacterList(hero);
        for (GameCharacter sketon : skeletons.getListOfSkeletons()) {
            background.getTitles()[sketon.getImgX() / 72][sketon.getImgY() / 72].addToCharacterList(sketon);
        }


        Board board = new Board(hero, background, boss, skeletons);
        Stats stats = new Stats(hero);
        Panel panel = new Panel();
        panel.add(board);
        panel.add(stats);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        // key event listener
        frame.addKeyListener(board);
        frame.addKeyListener(stats);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // When the up or down keys hit, we change the position of our box
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            hero.lookUp();
            if (hero.getImgY() > 0) {
                if (background.canMoveto(hero.getImgX(), hero.getImgY() - 72) && background.getTitles()[hero.getImgX() / 72][hero.getImgY() / 72].getCharacterList().size() < 2) {
                    Title tile = background.getTitles()[hero.getImgX() / 72][hero.getImgY() / 72];
                    tile.removeFromCharacterList(hero);
                    hero.moveUp();
                    tile = background.getTitles()[hero.getImgX() / 72][hero.getImgY() / 72];
                    tile.addToCharacterList(hero);
                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            hero.lookDown();
            if (hero.getImgY() < WIDTHSCREEN - 72) {
                if (background.canMoveto(hero.getImgX(), hero.getImgY() + 72) && background.getTitles()[hero.getImgX() / 72][hero.getImgY() / 72].getCharacterList().size() < 2) {
                    background.getTitles()[hero.getImgX() / 72][hero.getImgY() / 72].removeFromCharacterList(hero);
                    hero.moveDown();
                    background.getTitles()[hero.getImgX() / 72][hero.getImgY() / 72].addToCharacterList(hero);
                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            hero.lookRight();
            if (hero.getImgX() < HEIGHTSCREEN - 72) {
                if (background.canMoveto(hero.getImgX() + 72, hero.getImgY()) && background.getTitles()[hero.getImgX() / 72][hero.getImgY() / 72].getCharacterList().size() < 2) {
                    background.getTitles()[hero.getImgX() / 72][hero.getImgY() / 72].removeFromCharacterList(hero);
                    hero.moveRight();
                    background.getTitles()[hero.getImgX() / 72][hero.getImgY() / 72].addToCharacterList(hero);
                }

            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            hero.lookLeft();
            if (hero.getImgX() > 0) {
                if (background.canMoveto(hero.getImgX() - 72, hero.getImgY()) && background.getTitles()[hero.getImgX() / 72][hero.getImgY() / 72].getCharacterList().size() < 2) {

                    background.getTitles()[hero.getImgX() / 72][hero.getImgY() / 72].removeFromCharacterList(hero);

                    hero.moveLeft();
                    background.getTitles()[hero.getImgX() / 72][hero.getImgY() / 72].addToCharacterList(hero);

                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (background.getTitles()[hero.getImgX() / 72][hero.getImgY() / 72].getCharacterList().size() > 1) {

                GameCharacter attacker = background.getTitles()[hero.getImgX() / 72][hero.getImgY() / 72].getCharacterList().get(1);
                GameCharacter defender = background.getTitles()[hero.getImgX() / 72][hero.getImgY() / 72].getCharacterList().get(0);

                background.getTitles()[hero.getImgX() / 72][hero.getImgY() / 72].getCharacterList().remove(hero.fight(attacker, defender));
            }
        }

        // redraw after new event
        repaint();

    }

}
