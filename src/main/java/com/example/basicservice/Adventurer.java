package com.example.basicservice;

import java.io.Serializable;

public class Adventurer implements Serializable {

    private String name;
    private String adventurerClass;
    private int health;
    private int mana;

    public Adventurer() {
    }

    public Adventurer(String name, AdventurerClasses adventurerClass, int health, int mana) {
        this.name = name;
        this.adventurerClass = getClass(adventurerClass);
        this.health = health;
        this.mana = mana;
    }

    private String getClass(AdventurerClasses adventurerClass){
        String result;
        switch(adventurerClass){
            case FIGHTER:
                return "fighter";
            case MAGE:
                return "mage";
            case THIEF:
                return "thief";
            case DRUID:
                return "druid";
            default:
                return "";
        }
    }

    public String getName(){
        return name;
    }

    public void updateHealth(int val){
        int res;
        // no negative health
        if(health + val <= 0){
            health = 0;
        } else {
            health = health + val;
        }
    }

    public int getHealth(){
        return health;
    }
}
