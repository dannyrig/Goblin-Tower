package com.sg.Goblin_tower.view;

import com.sg.Goblin_tower.model.Goblin;
import com.sg.Goblin_tower.model.Hero;

import java.util.Scanner;

public class Gameplay {
    // stepsTaken = the number of steps the hero has taken in total.
    public static int stepsTaken;
    // goblinsSlain = the number of goblins killed by the hero altogether.
    public static int goblinsSlain;
    // Create a new hero to start the game
    static Hero fighter = new Hero();

    // Method for dealing with the turn-based combat presented
    // when facing a goblin.
    public static void battle() {
        // Create two boolean variables to see whether it is the hero's turn
        // to attack or the goblin's
        // (Note: The hero will always have the first move.)
        boolean heroTurn = true;
        boolean goblinTurn = false;

        // Spawn a new goblin to initiate the fight.
        Goblin trigger = new Goblin();

        // Create a new scanner to accept the user's decisions made during battle.
        Scanner options = new Scanner(System.in);
        System.out.println("A Goblin appears!!");

        // So long as the hero is still alive and the goblin is not dead,
        // have the battle loop continuously.
        while (!fighter.gameOver() && !trigger.vanquished()) {
            // While it is the hero's turn ask them whether they
            // wish to restore health or attack the enemy being fought.
            while (heroTurn) {
                System.out.println("What will you do?:");
                System.out.println("--------------------");
                System.out.println("Drink Potion (type 'p') || Attack Enemy (type 'a')");
                // Store the response of their decision.
                String choice = options.nextLine().toString();
                // If a proper response is given, wait until one is received.
                while (!choice.equals("p") && !choice.equals("a")) {
                    System.out.println("Type a valid response");
                    choice = options.nextLine().toString();
                }
                // If the user types "p", call the method to have
                // the player drink a potion.
                if (choice.equals("p")) {
                    Potion.drinkPotion(fighter);

                    // Otherwise, call the method for attacking goblins.
                } else {
                    fighter.attackGoblin(trigger);
                }

                // Check if the goblin is dead.
                if (trigger.getGoblinHealth() == 0) {
                    // Call the goblin slain method so that the goblin no longer exists.
                    trigger.vanquished();

                    // Increment the hero's kill count.
                    goblinsSlain += 1;

                    // Have the player receive two pieces of gold for their victory.
                    fighter.setGold(fighter.getGold() + 2);

                    // End the hero's turn and make sure the goblin can no longer attack either.
                    heroTurn = false;
                    goblinTurn = false;

                    // Display the results of your victory.
                    System.out.println("Monster slain! Earned 2 Gold!");
                    System.out.println("--------------------");

                    // If the goblin is not dead, end the hero's turn and begin the goblins.
                } else {
                    heroTurn = false;
                    goblinTurn = true;
                }
            }

            // While it is the goblin's turn, have the goblin
            // attack the hero.
            while (goblinTurn) {
                trigger.attackHero(fighter);
                // If the fighter dies from the goblin's attacks, end the game.
                if (fighter.getHealth() == 0) {
                    fighter.gameOver();
                    heroTurn = false;
                    goblinTurn = false;
                    // Otherwise, rinse and repeat.
                } else {
                    goblinTurn = false;
                    heroTurn = true;
                }
            }
        }
    }
    // Method used to make the hero walk and track the number of steps
    // he has taken.

    public static void takeAStep() {
        // Store the user's input and ask them to type 's' to move.
        Scanner move = new Scanner(System.in);
        System.out.println("Type 's' to move");
        String input = move.nextLine();

        // If the intended response is given, ask them to give the correct result.
        while (!input.equals("s")) {
            System.out.println("Please type 's'");
            System.out.println("--------------------");
            input = move.nextLine();
        }

        // Show that the hero is taking a step and increment the counter.
        System.out.println("Taking a step...");
        System.out.println("--------------------");
        stepsTaken += 1;
    }

    // Method to start and play the entire game

    public static void start() {
        // Show all of the hero's attributes.
        System.out.println("Current Stats:");
        System.out.println("Level: " + fighter.getLevel());
        System.out.println("Health: " + fighter.getHealth());
        System.out.println("Attack: " + fighter.getAttackPower());
        System.out.println("Defense: " + fighter.getDefense());
        System.out.println("Gold: " + fighter.getGold());
        System.out.println("--------------------");

        // As long as the hero breathes, keep running the game.
        while(!fighter.gameOver()) {
            // Call the method to make the hero move.
            takeAStep();
            // Call the method to check and see if an enemy encounter has occurred.
            if (Goblin.encounter()) {
                // If so, call the method to start the combat.
                battle();
            } else {
                // Otherwise, have the player keep moving.
                System.out.println("No monster encountered moving on.");
                System.out.println("--------------------");
            }

            // If the number of steps taken is divisible by 10 and the
            // player isn't dead, tell them they have advanced further in the tower,
            // set and display the next level they are currently on, and call the method
            // for visiting the potion shop.
            if (stepsTaken % 10 == 0 && fighter.getHealth() != 0) {
                System.out.println("You reached the next level!");
                fighter.setLevel(fighter.getLevel() + 1);
                System.out.println("Your current level is: " + fighter.getLevel());
                Potion.potionShop(fighter);
            }
        }

        // If the hero is dead, store and ask if they would to try again.
        Scanner retry = new Scanner(System.in);
        System.out.println("Would you like to play again?(Y|N)");
        String giveAnswer = retry.nextLine();

        // If a yes or no is not given, ask for those specific responses.
        while (!giveAnswer.equals("Y") && !giveAnswer.equals("N")) {
            System.out.println("Please type 'Y' or 'N' ");
            giveAnswer = retry.nextLine();
        }

        // If yes, then...
        if (giveAnswer.equals("Y")) {
            // Create a new hero.
            Hero reborn = new Hero();
            // Have the new hero inherit the gold held by the previous hero.
            reborn.setGold(fighter.getGold());
            // Reset the step and goblins killed counters
            stepsTaken = 0;
            goblinsSlain = 0;
            // Have the default fighter created at the beginning, be equal
            // to the new one.
            fighter = reborn;
            // Call the method recursively to restart the process.
            start();

            // If the player answers no, tell them their final level reached in the tower
            // and the number of goblins they had slain.
        } else {
            System.out.println("Your final level reached in the tower was " + fighter.getLevel() + " and the number of goblins slain was " + goblinsSlain + "!");
        }
    }
}



