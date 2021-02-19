package com.sg.Goblin_tower.model;

import java.lang.Math;

public class Hero {
    // Create variables to store all of the hero's attributes

    // health = the hero's life
    // maxHealth = the maximum amount of health the hero has
    // attackPower = the hero's offensive capabilities
    // defense = depending on the amount can soften blows received from foes.
    // potions[] = an array carrying the current number of potions in the hero's possession
    // gold = the amount of money the hero has
    // level = the floor the hero is currently on inside the tower.

    public static int health;
    public static int maxHealth;
    public static int attackPower;
    public static int defense;
    public static int[] potions;
    public static int gold;
    public static int level;

    public Hero() {
        // When making a new hero, set the health equal to a random value between 20-30.
        health = 20 + (int) (Math.random() * ((30 - 20) + 1));

        // Have the maximum health equal the hero's initial health
        maxHealth = health;

        // Set the attack power equal to a random value between 1-3.
        attackPower = 1 + (int) (Math.random() * ((3 - 1) + 1));

        // Set the hero's defense equal to a random value between 1-5.
        defense = 1 + (int) (Math.random() * ((5 - 1) + 1));

        // Give the hero five potions to start with in the array and set them all equal to 2
        // (1 potion restores the hero's health by 2)
        potions = new int[]{2, 2, 2, 2, 2};

        // Set the gold equal to the current amount of gold in the hero's wallet.
        gold = getGold();

        // Have the hero start his journey at the first level of the tower.
        level = 1;
    }

    // Method to return the hero's health.

    public int getHealth() {
        return health;
    }

    // Method to set the hero's health.
    //@hitPoints = the value you want to set the hero's health to.

    public void setHealth(int hitPoints) {
        health = hitPoints;
    }

    // Method to get the hero's attack power.

    public int getAttackPower() {
        return attackPower;
    }

    // Method to get the hero's defense.

    public int getDefense() {
        return defense;
    }

    // Method to get the gold in the hero's possession.

    public int getGold() {
        return gold;
    }

    // Method to set the amount of gold in the hero's possession.
    // @money = the amount of gold the hero should have.

    public void setGold(int money) {
        gold = money;
    }

    // Method to return the hero's maximum health.

    public int getMaxHealth() {
        return maxHealth;
    }

    // Method to return the hero's current level.

    public int getLevel() {
        return level;
    }

    // Method to set the hero's level.
    //@location = the level the hero should be on.

    public void setLevel(int location) {
        level = location;
    }

    // Method for attacking a goblin in battle.
    //@enemy = the goblin the hero is facing.

    public void attackGoblin(Goblin enemy) {
        // Describe what is happening.
        System.out.println("The player attacks!");

        // Display a divider to make the text easier to read.
        System.out.println("--------------------");

        // Formula for determining how much damage is done to the goblin.
        int damageDealt = (getAttackPower() * 4)/(enemy.getGoblinDefense() * 2);

        // Display the amount of damage done.
        System.out.println("Yeouch!! " + damageDealt + " damage dealt.");

        // Display a divider to make the text easier to read.
        System.out.println("--------------------");

        // Set the goblin's health equal to the goblin's current health minus the damage dealt.
        enemy.setGoblinHealth(enemy.getGoblinHealth() - damageDealt);

        // If the goblin's health is negative, set it to zero.
        if (enemy.getGoblinHealth() < 0) {
            enemy.setGoblinHealth(0);
        }

        // Show how much health the goblin has left.
        System.out.println("The enemy has " + enemy.getGoblinHealth() + " health left.");
        System.out.println("--------------------");
        }

    // Method for checking if the hero is dead.

    public boolean gameOver() {
        // Create a variable to store the boolean value.
        Boolean dead;

        // If the hero's health is zero, then he is dead.
        if (health == 0) {
            dead = true;
        // Otherwise, he is still alive
        } else {
            dead = false;
        }
        // Return the boolean value.
        return dead;
    }
}