/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curso.pokemonlogin;

import com.curso.pokemonlogin.Pokemon.Type;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mjimen19
 */
public class GameManager {
    static ArrayList <Pokemon> allPokemon = new ArrayList();
    ArrayList <Pokemon> playerPokemon = new ArrayList();
    
    public GameManager(){
        initPokemon();
        catchRandomPokemon();   //To get a starter
    }
    
    /*
        Initialize all pokemon and stores them in allPokemon
    */
    private void initPokemon(){
        Pokemon venusaur = new Pokemon(3,"Venusaur",Type.GRASS,1, 0, 30, 6, null);
        Pokemon ivysaur = new Pokemon(2,"Ivysaur",Type.GRASS,1, 30, 15, 4, venusaur);        
        Pokemon bulbasaur = new Pokemon(1,"Bulbasaur",Type.GRASS,1, 15, 8, 2, ivysaur);
        
        Pokemon charizard = new Pokemon(6,"Charizard",Type.FIRE,1, 0, 30, 6, null);
        Pokemon charmeleon = new Pokemon(5,"Charmeleon",Type.FIRE,1, 30, 15, 4, charizard);        
        Pokemon charmander = new Pokemon(4,"Charmander",Type.FIRE,1, 15, 8, 2, charmeleon);
        
        Pokemon blastoise = new Pokemon(9,"Blastoise",Type.WATER,1, 0, 30, 6, null);
        Pokemon wartortle = new Pokemon(8,"Wartortle",Type.WATER,1, 30, 15, 4, blastoise);        
        Pokemon squirtle = new Pokemon(7,"Squirtle",Type.WATER,1, 15, 8, 2, wartortle);
        
        Pokemon mewtwo = new Pokemon(11,"Mewtwo",Type.NORMAL,1, 0, 50, 10, null);
        Pokemon mew = new Pokemon(10,"Mew",Type.NORMAL,1, 30, 15, 4, mewtwo);
    
        allPokemon.add(bulbasaur);
        allPokemon.add(ivysaur);
        allPokemon.add(venusaur);
        allPokemon.add(charmander);
        allPokemon.add(charmeleon);
        allPokemon.add(charizard);
        allPokemon.add(squirtle);
        allPokemon.add(wartortle);
        allPokemon.add(blastoise);
        allPokemon.add(mew);
        allPokemon.add(mewtwo);
    }
    
    /*
        Get a random Pokemon from the list allPokemon, and adds it to the player's Pokemon list
    */
    public Pokemon catchRandomPokemon(){
        Pokemon newPoke = null;
        if(playerPokemon.size() <= 6){
            Random random = new Random();
            newPoke = new Pokemon(allPokemon.get(random.nextInt(allPokemon.size())));
            playerPokemon.add(newPoke);
        }
        return newPoke;
    }

    public String getPlayerPokemonDataString(){
        String text = "";
        int count = 0;
        for(Pokemon p : playerPokemon){
            text += count + " -> " + p.getDataString();
            count++;
        }
        return text;
    }
    
    /*
        Train a Pokemon specified by an index, returns false if the Pokemon doesn't exist
    */
    public boolean trainPokemon(int index){
        boolean canTrain = false;
        if(index < playerPokemon.size()){
            playerPokemon.get(index).train();
            canTrain = true;
        }
        return canTrain;
    }
    
    /*
        Combat with a random rival from the allPokemon list, returns true if player wins and false if player ties or losess
    */
    public boolean combatPokemon(int index){
        boolean playerWin = false;
        Random random = new Random();
        Pokemon rival = new Pokemon(allPokemon.get(random.nextInt(allPokemon.size())));
        rival.setRandomLevel();
        System.out.println("Te encuentras con un pokemon salvaje: ");
        System.out.println(rival.getDataString());
        if(index <= playerPokemon.size()){
            playerWin = playerPokemon.get(index).combat(rival);
        }
        
        return playerWin;
    }
    
    public boolean combatUser(User rival, int index){
        boolean playerWin = false;
        Pokemon rivalPoke = new Pokemon(rival.getRandomPokemon());
        System.out.println("The rival takes to combat the pokemon:");
        System.out.println(rivalPoke.getDataString());
        if(index <= playerPokemon.size()){
            playerWin = playerPokemon.get(index).combat(rivalPoke);
        }
        
        return playerWin;
    }
    
    public static ArrayList<Pokemon> getAllPokemonList(){
        return allPokemon;
    }
}
