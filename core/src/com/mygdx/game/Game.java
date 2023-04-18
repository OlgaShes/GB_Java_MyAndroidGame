package com.mygdx.game;

import com.mygdx.game.units.*;


import java.util.*;

public class Game {
    static Scanner scanner = new Scanner(System.in);
    public static int UNITS;
    public static ArrayList<BaseHero> blueTeam = new ArrayList<>();
    public static ArrayList<BaseHero> greenTeam = new ArrayList<>();
    public static ArrayList<BaseHero> allTeams = new ArrayList<>();

    public void setUnits(int units){
        this.UNITS = units;
    }

    public static void gameTurn() {
        Collections.sort(allTeams);
        for (BaseHero h : allTeams) {
            if (blueTeam.contains(h)) h.step(greenTeam, blueTeam);
            else h.step(blueTeam, greenTeam);
        }
    }
    public static boolean checkLosing(ArrayList<BaseHero> team) {
        for (BaseHero hero: team) {
            if (hero.getState() != BaseHero.State.dead) return false;
        }
        return true;
    }

    private static ArrayList<BaseHero> createTeam(boolean firstTeam) {
        ArrayList<BaseHero> team = new ArrayList<>();
        if (firstTeam) {
            for (int i = 0; i < UNITS; i++) {
                switch (new Random().nextInt(4)) {
                    case 0:
                        team.add(new Arbalester(i + 1, 1));
                        break;
                    case 1:
                        team.add(new Magican(i + 1, 1));
                        break;
                    case 2:
                        team.add(new Peasant(i + 1, 1));
                        break;
                    default:
                        team.add(new Thief(i + 1, 1));
                }
            }
        } else {
            for (int i = 0; i < UNITS; i++) {
                switch (new Random().nextInt(4)) {
                    case 0:
                        team.add(new Monk(i + 1, UNITS));
                        break;
                    case 1:
                        team.add(new Peasant(i + 1, UNITS));
                        break;
                    case 2:
                        team.add(new Sniper(i + 1, UNITS));
                        break;
                    default:
                        team.add(new Pikeman(i + 1, UNITS));
                }
            }
        }
        return team;
    }

    public static void initTeams() {
        blueTeam = createTeam(true);
        greenTeam = createTeam(false);
        allTeams.addAll(blueTeam);
        allTeams.addAll(greenTeam);
        Collections.sort(allTeams);
    }
}