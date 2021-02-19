package com.sg.Goblin_tower.model;

import java.lang.Math;

public class Goblin {
    // Create variables to store all of the goblin's attributes.

    // health = the amount of health the goblin has.
    // attackPower = the amount of damage the goblin can do to the hero.
    // defense = the amount of resilience the goblin possesses.

    public static int health;
    public static int attackPower;
    public static int defense;

    public Goblin() {
        // When a new goblin spawns, have its health be set to a random value between 5-10.
        health = 5 + (int) (Math.random() * ((10 - 5) + 1));

        // Have a goblin's initial attack power be set to a random value between 2-3.
        attackPower = 2 + (int) (Math.random() * ((3 - 2) + 1));

        // Have a goblin's defense be set to a random value between 1-2.
        defense = 1 + (int) (Math.random() * ((2 - 1) + 1));
    }

    // Method to return the goblin's health.

    public int getGoblinHealth() {
        return health;
    }

    // Method to set the goblin's health to a new value.
    // @hit = the new value of the goblin's health.

    public void setGoblinHealth(int hit) {
        health = hit;
    }

    // Method to return the goblin's attack power.

    public static int getGoblinAttackPower() {
        return attackPower;
    }

    // Method to return the goblin's defense.

    public int getGoblinDefense() {
        return defense;
    }

    // Method that checks and sees if a hero has encountered a goblin
    // while walking.

    public static boolean encounter() {
        // Boolean variable to see if an encounter has occurred.
        boolean encountered = false;

        // Create a new int variable that acts as the percentile for encountering goblins.
        // Note: I set it so that you only have a 50% of running into a goblin, but free to
        // modify the rate of encounter as you see fit.
        int encounterRate = 1 + (int) (Math.random() * ((100 - 1) + 1));

        // If the random percentage generated is greater than 50
        // then an encounter has occurred.
        if (encounterRate > 50) {
            encountered = true;
        }
        return encountered;
    }

    // Method for dealing with the battling phase of the goblin.
    //@traveler = the hero the goblin is attacking.

    public void attackHero(Hero traveler) {
        // Display the events transpiring.
        System.out.println("The goblin attacks!");
        System.out.println("--------------------");

        // Formula for calculating the damage dealt to the hero.
        int damageDealt = (getGoblinAttackPower() * 4)/(traveler.getDefense() * 2);

        // If the damage dealt is a decimal value (since it is an integer variable,
        // it will automatically set it equal to zero), set the damage dealt value equal to 2
        // by default.
        if (damageDealt == 0) {
            damageDealt = 2;
        }

        // Display how much damage the hero took
        // and modify the hero's health to reflect these changes.
        System.out.println("Yeouch!! " + damageDealt + " damage dealt.");
        System.out.println("--------------------");
        traveler.setHealth(traveler.getHealth() - damageDealt);

        // If the hero's health goes below zero, set it equal to zero.
        if (traveler.getHealth() < 0) {
            traveler.setHealth(0);
        }

        // Show how much health the hero has left.
        System.out.println("You have " + traveler.getHealth() + " health left.");
        System.out.println("--------------------");
        }

    // Method for checking whether or not the hero has killed a goblin.

    public boolean vanquished() {
        // Create a boolean value to see if the goblin has been defeated.
        boolean defeated;

        // If the goblin's health is zero, then it has been killed.
        if (health == 0) {
            defeated = true;

        // Otherwise, this method remains false.
        } else {
            defeated = false;
        }
        return defeated;
    }
}
