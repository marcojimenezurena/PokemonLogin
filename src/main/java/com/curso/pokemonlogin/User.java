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

    private String name;
    private String email;
    private String password;
    private String securityQuestion;

    private ArrayList<Pokemon> playerPokemon = new ArrayList<Pokemon>();

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public ArrayList<Pokemon> getPokemonList() {
        return playerPokemon;
    }


    @Override
    public String toString() {
        return "User [name=" + name + ", email=" + email + ", password=" + password + ", securityQuestion="
                + securityQuestion + "]";
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public void addPokemon(Pokemon p){
        playerPokemon.add(p);
    }
}
