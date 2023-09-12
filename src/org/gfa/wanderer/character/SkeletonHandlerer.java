package org.gfa.wanderer.character;

import org.gfa.wanderer.background.Background;
import org.gfa.wanderer.background.Title;

import java.util.ArrayList;
import java.util.List;

public class SkeletonHandlerer {
    private List<GameCharacter> listOfSkeletons = new ArrayList<>();
    private List<Title> listOfAT = new ArrayList<>(new Background().getListOfAvailableTitles());

    public SkeletonHandlerer() {

        for (int i = 0; i < 3; i++) {
            int randIndex = (int) (Math.random() * listOfAT.size())-1;
            int x = listOfAT.get(randIndex).getImgX();
            int y = listOfAT.get(randIndex).getImgY();
            listOfSkeletons.add(new Skeleton(x,y));
        }

        listOfSkeletons.get(0).setHasKey(true);
    }

    public List<GameCharacter> getListOfSkeletons() {
        return listOfSkeletons;
    }
}
