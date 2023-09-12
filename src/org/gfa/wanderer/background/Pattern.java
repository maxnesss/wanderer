package org.gfa.wanderer.background;

public class Pattern {

        private boolean[][] patternLevelOne = {
                {true,true,true,false,true,true,true,true,true,true},
                {true,true,true,false,true,false,true,false,false,true},
                {true,false,false,false,true,false,true,false,false,true},
                {true,true,true,true,true,false,true,true,true,true},
                {false,false,false,false,true,false,false,false,false,true},
                {true,false,true,false,true,true,true,true,true,true},
                {true,false,true,false,true,false,false,true,false,true},
                {true,true,true,true,true,false,false,true,false,true},
                {true,false,false,false,true,true,true,true,false,true},
                {true,true,true,false,true,false,false,true,true,true},
        };

    public boolean[][] getPatternLevelOne() {
        return patternLevelOne;
    }
}
