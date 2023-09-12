package org.gfa.wanderer.character;

public class Hero extends GameCharacter {


    public Hero() {
        super("img/hero-down.png", 0, 0, false);
    }


    @Override
    public void lookRight() {
        imageGen("img/hero-right.png");
    }

    @Override
    public void lookLeft() {
        imageGen("img/hero-left.png");
    }

    @Override
    public void lookDown() {
        imageGen("img/hero-down.png");
    }

    @Override
    public void lookUp() {
        imageGen("img/hero-up.png");
    }
}
