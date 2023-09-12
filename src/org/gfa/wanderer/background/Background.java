package org.gfa.wanderer.background;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Background {
    public final int TILESIZE = 72;


    List<Title> listOfAvailableTitles = new ArrayList();
    boolean[][] patern = new Pattern().getPatternLevelOne();
    private Title[][] titles = new Title[patern.length][patern.length];
    public Background(){
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                if (patern[column][row]){
                    titles[row][column] = new Ground(row*TILESIZE,column*TILESIZE);
                } else {
                    titles[row][column] = new Wall(row*TILESIZE,column*TILESIZE);
                }
            }
        }
    }

    public void drawBackground(Graphics graphics) {
        for (int row = 0; row < titles.length; row++) {
            for (int column = 0; column < titles[row].length; column++) {
                titles[row][column].draw(graphics);
            }
        }
    }

    public boolean canMoveto(int x, int y){
        return (titles[x/72][y/72]) instanceof Ground;
    }

    public List<Title> getListOfAvailableTitles() {
        for (int row = 0; row < titles.length; row++) {
            for (int columns = 0; columns < titles[row].length; columns++) {
                if (titles[row][columns] instanceof Ground){
                    listOfAvailableTitles.add(titles[row][columns]);
                }
            }
        }
        return listOfAvailableTitles;
    }

    public Title[][] getTitles() {
        return titles;
    }
}
