package com.mygdx.game.units;

public class Position {
    public int x;
    public int y;
    protected double maxDistance = Math.sqrt(Math.pow(9, 2) + Math.pow(9, 2));
    protected double minDistance = 1;

    protected Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected double distance(BaseHero target) {
        return Math.sqrt(Math.pow(target.position.x - x, 2) + Math.pow(target.position.y - y, 2));
    }
//     коэффициент дистанции, максимум - диагональ,
    protected double closenessCoefficient(BaseHero target) {
//        max distance = 12,73 --> 55% силы, min distance 1 --> 100% силы

        if (distance(target) <= ((maxDistance-minDistance)/4) + minDistance) return 1;
        else if (distance(target) > ((maxDistance-minDistance)/4) + minDistance &&
                distance(target) <= ((maxDistance-minDistance)/2) + minDistance ) return 0.85;
        else if (distance(target) > ((maxDistance-minDistance)/2) + minDistance &&
                distance(target) <= ((maxDistance-minDistance)*3/4) + minDistance) return 0.7;
        return 0.55;
    }

    protected int[] enemyDirection(BaseHero target) {
        return new int[] {target.position.x - this.x, target.position.y - this.y};
    }

}
