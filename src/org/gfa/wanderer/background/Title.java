package org.gfa.wanderer.background;

import org.gfa.wanderer.character.GameCharacter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Title {
    private String type;
    private String imagePath;
    private BufferedImage image;
    private int imgX;
    private int imgY;
    private List<GameCharacter> characterList = new ArrayList<>();

    public Title(String type, String imagePath, int imgX, int imgY) {
        this.imgX = imgX;
        this.imgY = imgY;
        this.type = type;
        this.imagePath = imagePath;


        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics graphics) {
        if (image != null) {
            graphics.drawImage(image, imgX, imgY, null);
        }

    }

    public int getImgX() {
        return imgX;
    }

    public int getImgY() {
        return imgY;
    }

    public List<GameCharacter> getCharacterList() {
        return characterList;
    }
    public void addToCharacterList(GameCharacter character) {
        characterList.add(character);
    }
    public void removeFromCharacterList(GameCharacter character) {
        characterList.remove(character);
    }

    @Override
    public String toString() {
        return "Title{" +
                "imgX=" + imgX +
                ", imgY=" + imgY +
                ", characterList=" + characterList +
                '}';
    }
}
