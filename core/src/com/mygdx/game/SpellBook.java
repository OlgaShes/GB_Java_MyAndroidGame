package com.mygdx.game;

public enum SpellBook {
    healOne(1,1), healFive(2, 5), healTen(3, 10),
//    damageOne(1,1), damageFive(5, 2), damageTen(10, 3),
//    resurect(25, Float.NaN)
    ;

    private final float power,cost;

    SpellBook(float cost, float power) {
        this.power = power;
        this.cost = cost;
    }
    public float getPower(){return power;}
    public float getCost(){return cost;}
}
