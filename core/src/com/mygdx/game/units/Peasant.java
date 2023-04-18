package com.mygdx.game.units;

import java.util.ArrayList;

public class Peasant extends BaseHero{
//    protected int burden; // остаток подносимых боеприпасов

    public Peasant(int x, int y) {
        super("Оруженосец",5, x, y, 5, new int[]{0,0}, 1);
//        burden = 25;
    }

    @Override
    public void step(ArrayList<BaseHero> enemyTeam, ArrayList<BaseHero> myTeam) {
        if (state == State.busy) {
//            System.out.print(state);
            state = State.stand;
//            System.out.println(" --> " + state);
        }
    }
}
