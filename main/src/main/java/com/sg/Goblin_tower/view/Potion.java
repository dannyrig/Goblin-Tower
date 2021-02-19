package com.sg.Goblin_tower.view;

import com.sg.Goblin_tower.model.Hero;

import java.util.Scanner;

public class Potion {
    // Method for when the hero consumes a potion.
    // @person = the hero drinking the potion.

    public static void drinkPotion(Hero person) {
        // Create a int variable to keep track of whether or not the hero
        // has any potions left;
        int emptyPotions = 0;

        // See if the hero already has full health.
        if (person.getHealth() == person.getMaxHealth()) {
            // If he does then tell the player their health is full.
            System.out.println("Your health is full.");
            System.out.println("--------------------");

        } else {
            // Otherwise, loop through the potions array and
            // see if the hero can drink any more potions
            for (int i = 0; i < Hero.potions.length; i++) {
                // If all the values in potions array are zero
                // then increment emptyPotions.
                if (Hero.potions[i] == 0) {
                    emptyPotions += 1;
                }

                // If all five potion slots are empty
                // tell the player they have no methods of healing.
                if (emptyPotions == 5) {
                    System.out.println("You have no more potions left to drink.");
                    System.out.println("--------------------");
                 // end the for-loop
                    break;

                // If the player has potions left to consume
                // set their health equal to their current health + 2.
                // (1 Potion heals 2 hit points)
                } else if (Hero.potions[i] == 2) {
                    person.setHealth(person.getHealth() + 2);

                    // Make sure the hero's health does not exceed their maximum health.
                    if (person.getHealth() > person.getMaxHealth()) {
                        person.setHealth(person.getMaxHealth());
                    }

                    // Set the value of the index of the potion just used to 0,
                    // so it can no longer be used.
                    Hero.potions[i] = 0;

                    // end the for-loop to prevent the player from consuming more than one potion at a time.
                    break;
                }
            }
        }
    }

    // Method for visiting the potionShop everytime the player takes 10 steps.
    //@customer = the player buying the potions.

    public static void potionShop(Hero customer) {
        // Keep track of how many potions the hero can buy.
        int potionsLeftToBuy = 0;

        // Loop through the potions array to see if all the slots are filled.
        for (int i = 0; i < Hero.potions.length; i++) {
            if (Hero.potions[i] == 2) {
                // If all slots are filled increment the potionsLeftToBuy value by 1.
                potionsLeftToBuy += 1;
            }
        }

        // Create a scanner to accept user input.
        Scanner browse = new Scanner(System.in);

        // Have the player be greeted by the potion seller, and ask if
        // they wish to purchase any potions.
        System.out.println("Welcome weary traveler, would you like to buy a potion?(Y|N)");

        // Store their response.
        String response = browse.nextLine();

        // If their response is yes, they have enough to buy a potion, and they have room to store
        // potions, proceed with the method.
        if (response.equals("Y") && customer.getGold() >= 4 && potionsLeftToBuy != 5) {
            // Loop through the potions array and fill in the first empty slot
            // with a potion.
            for (int i = 0; i < Hero.potions.length; i++) {
                if (Hero.potions[i] == 0) {
                    Hero.potions[i] = 2;
                    // Deduct the pay from their wallet.
                    customer.setGold(customer.getGold() - 4);
                    // Increment the number of potions left that they can store.
                    potionsLeftToBuy += 1;
                    // end the for-loop
                    break;
                }
            }

            // While the hero has enough gold left to buy more potions ask if
            // they would like to buy more.
            while (customer.getGold() >= 4) {
                // Show the amount of gold the player has left
                System.out.println("Gold left: " + customer.getGold());
                System.out.println("Would you like another potion?(Y|N)");
                // Record the answer.
                String answer = browse.nextLine();
                // If they answered yes and the player has room to store more potions
                // then repeat the purchasing steps.
                if (answer.equals("Y") && potionsLeftToBuy != 5) {
                    for (int i = 0; i < Hero.potions.length; i++) {
                        if (Hero.potions[i] == 0) {
                            Hero.potions[i] = 2;
                            customer.setGold(customer.getGold() - 4);
                            potionsLeftToBuy += 1;
                            break;
                        }
                    }

                    // If the player answers no or their is no more room left
                    // to store potions, display a message thanking them for their purchase.
                } else if (answer.equals("N") || potionsLeftToBuy == 5) {
                    System.out.println("Thank you for your purchase! ");
                    // end the while loop
                    break;
                }
            }

            // If the user doesn't give a proper response to
            // the potion seller's first question, tell the user to
            // get lost.
        } else if (!response.equals("Y") && !response.equals("N")) {
            System.out.println("Yeah I can't help you if don't want to give me a valid answer. BEGONE!!!");
            System.out.println("--------------------");

            // If the player answers no to the very first time the potion seller
            // asked if they would like to make a purchase, tell them to come back some other time.
        } else if (response.equals("N")) {
            System.out.println("Alright, maybe some other time.");
            System.out.println("--------------------");

            // If the user comes into the store without the sufficient amount of
            // cash they need to make a purchase, let them know.
        } else if (customer.getGold() < 4) {
            System.out.println("You don't have enough money to make a purchase.");
            System.out.println("--------------------");

            // If the use walks into the store with a maxed out inventory,
            // let them know.
        } else if (potionsLeftToBuy == 5) {
            System.out.println("So sorry lad, but your inventory is full, please come again.");
            System.out.println("--------------------");
        }
    }
}

