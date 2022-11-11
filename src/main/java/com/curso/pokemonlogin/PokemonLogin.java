/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.curso.pokemonlogin;

import java.util.Scanner;

/**
 *
 * @author mjimen19
 */
public class PokemonLogin {

    private static final GameManager game = new GameManager();
    private static User userLogged;
    
    public static void main(String[] args) {
        mainMenu();
        
    }

    private static void commandTraining(Scanner sc) {
        System.out.println("Select a Pokemon to train:");
        System.out.println(userLogged.getPlayerPokemonDataString());
        int command = 0;
        try {
            command = sc.nextInt();
            userLogged.trainPokemon(command);
        } catch (Exception e) {
            System.out.println("Error at selecting the command");
        }

    }

    private static void commandCatch() {
        Pokemon pokeCatch = userLogged.catchRandomPokemon();
        if (pokeCatch == null) {
            System.out.println("You can't catch a new Pokemon because your team are full");
        } else {
            System.out.println("Congratulations! You catched a " + pokeCatch.getName());
        }
    }

    private static void commandCombat(Scanner sc) {
        System.out.println("Select a Pokemon to send to the combat:");
        System.out.println(userLogged.getPlayerPokemonDataString());
        int command = 0;
        try {
            command = sc.nextInt();
            boolean isWin = userLogged.combatPokemon(command);
            if (isWin) {
                System.out.println("You win the combat!");
            } else {
                System.out.println("You lost the combat");
            }
        } catch (Exception e) {
            System.out.println("Error at selecting the command");
        }
    }

    private static void commandCheckPokemon() {
        System.out.println(userLogged.getPlayerPokemonDataString());
    }
    
    private static void commandCombatUser(Scanner sc){
        System.out.println("Choose user by entering email:");
        UserList.showUsers();
        String rivalName = sc.nextLine();
        User rival = UserList.getUser(rivalName);
        if(rival != null){
            System.out.println("Select a Pokemon to send to the combat:");
            System.out.println(userLogged.getPlayerPokemonDataString());
            int command = 0;
            try {
                command = sc.nextInt();
                boolean isWin = userLogged.combatUser(rival, command);
                if (isWin) {
                    System.out.println("You win the combat!");
                } else {
                    System.out.println("You lost the combat");
                }
            } catch (Exception e) {
                System.out.println("Error at selecting the command");
            }
            
        }else{
            System.out.println("That user doesn't exist");
        }
        
    }

    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        String option;
        do {
            System.out.println("Elige que quieres hacer\n 1=Iniciar sesion\n 2=Crear usuario\n 3=Mostrar usuarios\n 7=Salir");
            option = sc.nextLine();
            switch (option) {
                case "1" ->  login(sc);
                case "2" ->  createUser(sc);
                case "3" ->  UserList.showUsers();

            }
        } while (!"7".equals(option));
    }
    
    private static void login(Scanner sc){
        UserList user = new UserList();

        System.out.println("Iniciar sesion");

        System.out.println("Añada su direccion email:");
        String emailLogin = sc.nextLine();

        System.out.println("Añada una contraseña:");
        String passwordLogin = sc.nextLine();
        UserList.userLogin(emailLogin, passwordLogin);
        userLogged = user.getUser(emailLogin);

        while (UserList.auth) {
            playPokemon();
        }
    }
    
    private static void createUser(Scanner sc){
        System.out.println("Crear usuario");
        System.out.println("Añada su nombre de usuario:");
        String username = sc.nextLine();

        System.out.println("Añada su direccion email:");
        String emailCreate = sc.nextLine();

        System.out.println("Añada su contraseña:");
        String passwordCreate = sc.nextLine();

        User user = new User(username, emailCreate, passwordCreate);
        UserList.addUser(user);
    }
    
    private static void playPokemon(){
        System.out.println("Game starts!");
        System.out.println("Enter a command to play:");
        System.out.println("Commands:");
        System.out.println("1 -> Train your Pokemon");
        System.out.println("2 -> Catch a new Pokemon");
        System.out.println("3 -> Combat with a wild Pokemon");
        System.out.println("4 -> Check your current Pokemon");
        System.out.println("5 -> Combat with another user");
        System.out.println("exit -> Ends the game");

        Scanner sc = new Scanner(System.in);
        String command;
        do {
            System.out.println("Enter a command");
            command = sc.nextLine();

            switch (command) {
                case "1" ->
                    commandTraining(sc);
                case "2" ->
                    commandCatch();
                case "3" ->
                    commandCombat(sc);
                case "4" ->
                    commandCheckPokemon();
                case "5" ->
                    commandCombatUser(sc);
            }

        } while (!command.equals("exit"));
        UserList.userLogout();
    }
}
