/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curso.pokemonlogin;

/**
 *
 * @author mjimen19
 */
import static com.curso.pokemonlogin.GameManager.allPokemon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class User {

    String name;
    String email;
    String password;
    String securityQuestion;

    ArrayList<Pokemon> playerPokemon = new ArrayList<Pokemon>();

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public ArrayList<Pokemon> getPokemonList() {
        return playerPokemon;
    }

    public void showPokemon() {
        for (Pokemon poke : playerPokemon) {
            System.out.println(poke.getDataString());
        }
    }

    public Pokemon getRandomPokemon() {
        Random random = new Random();
        Pokemon poke = playerPokemon.get(random.nextInt(playerPokemon.size()));
        return poke;
    }

    public String getPlayerPokemonDataString() {
        String text = "";
        int count = 0;
        for (Pokemon p : playerPokemon) {
            text += count + " -> " + p.getDataString();
            count++;
        }
        return text;
    }

    public boolean trainPokemon(int index) {
        boolean canTrain = false;
        if (index < playerPokemon.size()) {
            playerPokemon.get(index).train();
            canTrain = true;
        }
        return canTrain;
    }
    
    public Pokemon catchRandomPokemon(){
        Pokemon newPoke = null;
        if(playerPokemon.size() <= 6){
            Random random = new Random();
            newPoke = new Pokemon(GameManager.getAllPokemonList().get(random.nextInt(GameManager.getAllPokemonList().size())));
            playerPokemon.add(newPoke);
        }
        return newPoke;
    }
    
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

    @Override
    public String toString() {
        return "User [name=" + name + ", email=" + email + ", password=" + password + ", securityQuestion="
                + securityQuestion + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
