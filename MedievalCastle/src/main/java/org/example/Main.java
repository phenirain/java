package org.example;

import java.security.PublicKey;
import java.util.*;

public class Main {
    public ArrayList<String> castles = new ArrayList<>() {{
        add("North");
        add("West");
        add("East");
        add("South");
    }};
    public Map<String, String> wars = new HashMap<>();
    public Map<String, Integer> resources = new HashMap<>() {{
        put("Walls", 100);
        put("Army", 100);
        put("Food", 100);
        put("Water", 100);
        put("Gold", 100);
        put("Weapon", 100);
    }};


    public static void main(String[] args) {

        System.out.println("Hello world!");
    }

    public void repairWalls() {
        int wallsHealth = resources.get("Walls");
        if (wallsHealth < 100 && wallsHealth > 90) wallsHealth = 100;
        else if (wallsHealth < 90) wallsHealth += 10;
        System.out.println("Walls is repaired");
    }

    public void buyWeapon() {
        int gold = resources.get("Gold");
        if (gold > 10) {
            resources.put("Gold", gold - 10);
            resources.put("Weapon", resources.get("Weapon") + 5);
        } else {
            System.out.println("You don`t have enough money for it");
        }
    }

    public int getFood() {
        return resources.get("Food");
    }

    public int harvest(int amount) {
        resources.put("Food", resources.get("Food") + amount);
        return resources.get("Food");
    }

    public void declareWar(String to) {
        if (castles.contains(to)) {
            wars.put("Main",  to);
            System.out.printf("War is declare to %s\n", to);
        } else {
            System.out.println("This castle is not exists");
        }
    }

    public void allMyEnemies() {
        if (!wars.keySet().isEmpty()) System.out.println(wars.keySet().toString());
        else System.out.println("U don`t have enemies");
    }

    public void declareWarToAll() {
        for (String castle : castles) {
            wars.put("Main", castle);
        }
    }

    public static String findWeakCastle(Map<String, Map<String, Integer>> castles) {
        int weakest = 101;
        String weakCastle = "";
        for (String key : castles.keySet()) {
            if (castles.get(key).get("Army") < weakest) {
                weakCastle = key;
            }
        }
        return weakCastle;
    }

    public static int getStrength(Map<String, Integer> resources) {
        return resources.get("Walls") * resources.get("Army") * resources.get("Weapon");
    }

    public static Boolean isRuined(Map<String, Integer> resources) {
        int walls = resources.get("Walls");
        int army = resources.get("Army");
        int weapon = resources.get("Weapon");
        if (walls == 0 && army == 0 && weapon == 0) {
            return Boolean.TRUE;
        }
        else {
            return Boolean.FALSE;
        }
    }

}