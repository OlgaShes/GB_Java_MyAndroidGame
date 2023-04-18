package com.mygdx.game.units;


import com.mygdx.game.SpellBook;

import java.util.ArrayList;

public abstract class Healer extends BaseHero {
    protected int mana, maxMana;

    public Healer(String className, float hp, int x, int y, int def, int[] force,
                  int mana, int priority) {
        super(className, hp, x, y, def, force, priority);
        this.mana = this.maxMana = mana;
    }

    @Override
    public void step(ArrayList<BaseHero> enemyTeam, ArrayList<BaseHero> myTeam) {
        if (state == State.dead ) return;
        else if (mana == 0) mana += 1;
        else {
            BaseHero woundedFiend = findWounded(myTeam);
            if (woundedFiend != null) {
                heal(woundedFiend);
            } else {
                mana += 1;
            }
        }
    }

    public BaseHero findWounded(ArrayList<BaseHero> myTeam) {
        for (BaseHero hero : myTeam) {
            if (hero.hp < hero.maxHp && hero.state != State.dead) return hero;
        }
        return null;
    }

    public void heal(BaseHero friend) {
        double toHeal = friend.maxHp - friend.hp;
        if (toHeal > SpellBook.healTen.getPower() &&
                mana >= SpellBook.healTen.getCost()) {
            mana -= SpellBook.healTen.getCost();
            friend.hp += SpellBook.healTen.getPower();
        } else if (toHeal > SpellBook.healFive.getPower() &&
                mana >= SpellBook.healFive.getCost()) {
            mana -= SpellBook.healFive.getCost();
            friend.hp += SpellBook.healFive.getPower();
        } else if (toHeal > SpellBook.healOne.getPower()){
            mana -= SpellBook.healOne.getCost();
            friend.hp += SpellBook.healOne.getPower();
        }
    }

    @Override
    public String toString() {
        return super.toString() +  ", \uE736: " + mana;
    }
}
