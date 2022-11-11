/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curso.pokemonlogin;

import java.util.Random;

/**
 *
 * @author mjimen19
 */
public class Pokemon {
    private int number;
    private String name;
    private Type type;
    private int level;
    private int levelToEvolve;
    private int strength;
    private final int typeBonus = 5;    //number of strentgh bonus depending of pokemons types
    private int strengthBonus;          //number of max strentgh gained at leveling up
    private Pokemon nextEvolution;
    
    public enum Type{
    NORMAL,WATER,FIRE,GRASS}

    public Pokemon(int number, String name, Type type, int level, int levelToEvolve, int strength, int strengthBonus, Pokemon nextEvolution) {
        this.number = number;
        this.name = name;
        this.type = type;
        this.level = level;
        this.levelToEvolve = levelToEvolve;
        this.strength = strength;
        this.strengthBonus = strengthBonus;
        this.nextEvolution = nextEvolution;
    }
    
    public Pokemon(Pokemon p) {
        this(p.getNumber(), p.getName(), p.getType(), p.getLevel(),
                p.getLevelToEvolve(), p.getStrength(),
                p.getStrengthBonus(), p.getNextEvolution());
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getLevelToEvolve() {
        return levelToEvolve;
    }

    public int getStrength() {
        return strength;
    }

    public int getTypeBonus() {
        return typeBonus;
    }

    public int getStrengthBonus() {
        return strengthBonus;
    }

    public Pokemon getNextEvolution() {
        return nextEvolution;
    }
    
    public void setRandomLevel(){
        Random rnd = new Random();
        level = rnd.nextInt(50);
        strength += level;
    }
    
    /*
        Simulate a training, that makes the Pokemon level up
    */
    public void train(){
        levelUp();
    }
    
    /*
        Simulates a combat, where the stronger Pokemon wins.
        When both strengths are equal, it is considered a lost so no one levels up
    */
    public boolean combat(Pokemon rival){
        boolean isWin = false;
        
        if(this.getTypeBonus(rival) > rival.strength){
            isWin = true;
            levelUp();
        }
        return isWin;
    }
    
    /*
        Method called when level >= levelToEvolve, it makes this Pokemon equals its evolution but with level and strentgh
    */
    public void evolve(){
        this.name = nextEvolution.getName();
        this.strength += nextEvolution.getStrength();
        this.type = nextEvolution.getType();
        this.number = nextEvolution.getNumber();
        this.levelToEvolve = nextEvolution.getLevelToEvolve();
        this.strengthBonus = nextEvolution.getStrengthBonus();
        this.nextEvolution = nextEvolution.getNextEvolution();
    }
    
    /*
        Gain a level, that makes the pokemon stronger and checks if it can evolve
    */
    private void levelUp(){
        if(level < 100) level++;    //level up capped at level 100
        Random random = new Random();
        strength += random.nextInt(strengthBonus);      //Add strength at leveling up randomized for variety
        if(level >= levelToEvolve && levelToEvolve != 0){   // levelToEvolve != 0 to check for pokemon who doesn't evolve
            evolve();
        }
        
    }
    
    /*
        Check the types of this pokemon and a given rival, and returns the strength based on the types
    */
    private int getTypeBonus(Pokemon rival){
        int bonus = strength;
        switch(type){
            case WATER -> {
                if(rival.getType() == Type.FIRE){
                    bonus += typeBonus;
                }else if(rival.getType() == Type.GRASS){
                    bonus -= typeBonus;
                }
            }
            case FIRE -> {
                if(rival.getType() == Type.GRASS){
                    bonus += typeBonus;
                }else if(rival.getType() == Type.WATER){
                    bonus -= typeBonus;
                }
            }
            case GRASS -> {
                if(rival.getType() == Type.WATER){
                    bonus += typeBonus;
                }else if(rival.getType() == Type.FIRE){
                    bonus -= typeBonus;
                }
            }
        }
        
        return bonus;
    }
    
    public String getDataString(){
        return name + " Level(" + level + ")" + " Strength(" + strength + ")" + " Type(" + type  +") \n";
    }
}
