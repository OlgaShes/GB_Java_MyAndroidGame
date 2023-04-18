package com.mygdx.game.units;

import java.util.ArrayList;

public abstract class Infantry extends BaseHero {

    public Infantry(String className, float hp, int x, int y, int def, int[] damage, int priority) {
        super(className, hp, x, y, def, damage, priority);
    }

    @Override
    public void step(ArrayList<BaseHero> enemyTeam, ArrayList<BaseHero> myTeam) {
        if (state == State.dead) return;
        BaseHero enemy = findClosestEnemy(enemyTeam);
        if (enemy == null) return;
        if (position.distance(enemy) < 2)
            shoot(enemy);
        else {
            move(enemy, myTeam);
        }
    }
    protected void shoot(BaseHero enemy) {
        double hit = countDamage();
        if (enemy.state != State.dead) {
            enemy.getDamage(hit);
        }
    }
    private double countDamage() {
//        случайная сила удара
        return (Math.random()*(force[1] - force[0]) + force[0]);
    }
    protected void move(BaseHero enemy, ArrayList<BaseHero> myTeam) {
        int[] directionToEnemy = position.enemyDirection(enemy);
        if (Math.abs(directionToEnemy[0]) > Math.abs(directionToEnemy[1]) &&
        checkFreePlace(myTeam, position.x + (int)Math.signum(directionToEnemy[0]), position.y)) {
            this.position.x += Math.signum(directionToEnemy[0]);
        } else if (checkFreePlace(myTeam, position.x, position.y + (int)Math.signum(directionToEnemy[1])))
            this.position.y += Math.signum(directionToEnemy[1]);
    }

    protected boolean checkFreePlace(ArrayList<BaseHero> myTeam, int x, int y) {
        for (BaseHero hero: myTeam) {
            if (hero.getCoords()[0] == x && hero.getCoords()[1] == y && hero.state != State.dead)
                return false;
        }
        return true;
    }
}
