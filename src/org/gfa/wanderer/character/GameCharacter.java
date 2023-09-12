package org.gfa.wanderer.character;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class GameCharacter {
    private BufferedImage image;
    private int imgX;
    private int imgY;
    private boolean hasKey;
    private int hp;
    private int maxHp;
    private int dp;
    private int sp;
    private int level = 1;


    public GameCharacter(String imagePath, int imgX, int imgY, boolean hasKey) {
        // works for hero
        if (this instanceof Hero) {
            this.hp = 20 + (3 * dice());
            this.maxHp = this.hp;
            this.dp = 2 * dice();
            this.sp = 5 + dice();
        }
        if (this instanceof Skeleton) {
            this.hp = 2 * level * dice();
            this.dp = level / 2 * dice();
            this.sp = level * dice();
        }
        if (this instanceof Boss) {
            this.hp = 2 * level * (dice() + dice());
            this.dp = (level / 2 * dice()) + (dice() / 2);
            this.sp = (level * dice()) + level;
        }
        this.imgX = imgX;
        this.imgY = imgY;
        this.hasKey = hasKey;


        imageGen(imagePath);
    }


    public GameCharacter fight(GameCharacter atacker, GameCharacter defender) {

        while (atacker.hp > 0 && defender.hp > 0) {
            strike(atacker, defender);
            if (defender.getHp() > 0) {
                strike(defender, atacker);
            }
        }
        if (atacker.hp > defender.hp) {
            if (defender.hasKey) {
                atacker.hasKey = true;
            }
            atacker.maxHp += dice();
            atacker.sp += dice();
            atacker.dp += dice();
            atacker.level++;
            if (atacker instanceof Hero && hasKey && defender instanceof Boss) {
                atacker.imgX = 0;
                atacker.imgY = 0;
                atacker.hp = atacker.maxHp;
                defender.level++;
                defender.hp = 2 * level * dice();
            }
            return defender;
        }

        return atacker;
    }


    public void strike(GameCharacter atacker, GameCharacter defender) {
        int sv = atacker.sp + dice();

        if (((2 * dice()) + atacker.sp) > defender.dp) {
            defender.getsHit(sv);
        }

    }

    public void getsHit(int sv) {

        this.hp -= sv;
    }

    // reads image file and puts it in Buffer image
    public void imageGen(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // draw
    public void draw(Graphics graphics) {
        if (image != null) {
            if (this.hp > 0) {
                graphics.drawImage(image, imgX, imgY, null);
            }
        }
    }

    // movement
    public void moveRight() {
        imgX += 72;
    }

    public void moveLeft() {
        imgX -= 72;
    }

    public void moveDown() {
        imgY += 72;
    }

    public void moveUp() {
        imgY -= 72;
    }

    // methods only for hero
    public void lookRight() {
    }

    public void lookLeft() {
    }

    public void lookDown() {
    }

    public void lookUp() {
    }

    // getters and setters
    public void setImgX(int imgX) {
        this.imgX = imgX;
    }

    public void setImgY(int imgY) {
        this.imgY = imgY;
    }

    public int getImgX() {
        return imgX;
    }

    public int getImgY() {
        return imgY;
    }

    protected int dice() {
        return (int) (Math.random() * 6) + 1;
    }

    public int getHp() {
        return hp;
    }

    public int getDp() {
        return dp;
    }

    public int getSp() {
        return sp;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public boolean isHasKey() {
        return hasKey;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }
}
