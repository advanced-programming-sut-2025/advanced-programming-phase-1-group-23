package controller;

import model.Basics.ProcessingRecord;

import java.util.Timer;

public class ProcessingController {
    public int timeFreeze = 0;


    public ProcessingRecord beeHouse() {
        timeFreeze = 4;// Change it

        return new ProcessingRecord("It's a sweet syrup produced by bees.", 75);
    }

    public ProcessingRecord cheesePress(String input) {
        String name = input; // change

        if (name.equals("Cheese")) {
            return cheese();
        }

        if (name.equals("Goat Cheese")) {
            return goatChesse();
        }

        return null;
    }

    private ProcessingRecord cheese() {
        return new ProcessingRecord("It's your basic cheese.", 100);
    }

    private ProcessingRecord goatChesse() {
        return new ProcessingRecord("Soft cheese made from goat's milk.", 100);
    }

    public ProcessingRecord keg(String input) {
        String name = input;// Change

        if (name.equals("Beer")) {
            return new ProcessingRecord("Drink in moderation.", 50);
        } else if (name.equals("Vinegar")) {
            return new ProcessingRecord("An aged fermented liquid used in many cooking recipes.", 13);
        } else if (name.equals("Coffee")) {
            return new ProcessingRecord("It smells delicious. This is sure to give you a boost.", 75);
        } else if (name.equals("Juice")) {
            return new ProcessingRecord("A sweet, nutritious beverage.", 2);
        } else if (name.equals("Mead")) {
            return new ProcessingRecord("A fermented beverage made from honey.Drink in moderation.", 100);
        } else if (name.equals("Pale Ale")) {
            return new ProcessingRecord("Drink in moderation.", 50);
        } else if (name.equals("Wine")) {
            return new ProcessingRecord("Drink in moderation.", 1.75);
        } else {
            return null;
        }

    }

    public ProcessingRecord dehydrator(String input) {
        String name = input;

        if (name.equals("Dried Mushrooms")) {
            return new ProcessingRecord("A package of gourmet mushrooms.", 50);
        } else if (name.equals("Dried Fruit")) {
            return new ProcessingRecord("Chewy pieces of dried fruit.", 75);
        } else if (name.equals("Raisins")) {
            return new ProcessingRecord("It's said to be the Junimos' favorite food.", 125);
        } else {
            return null;
        }
    }

    public ProcessingRecord charcoalKiln(String input) {
        return new ProcessingRecord("Turns 10 pieces of wood into one piece of coal.", 0);
    }

    public ProcessingRecord loom(String input) {
        return new ProcessingRecord("A bolt of fine wool cloth.", 0);
    }

    public ProcessingRecord mayonnaiseMachine(String input) {
        String name = input;
        if (name.equals("Mayonnaise")) {
            return new ProcessingRecord("It looks spreadable.", 50);
        } else if (name.equals("Duck Mayonnaise")) {
            return new ProcessingRecord("It's a rich, yellow mayonnaise.", 75);
        } else if (name.equals("Dinosaur Mayonnaise")) {
            return new ProcessingRecord("It's thick and creamy, with a vivid green hue. " +
                    "It smells like grass and leather.", 125);
        } else {
            return null;
        }
    }

    public ProcessingRecord oilMaker(String input) {
        String name = input;
        if (name.equals("Truffle Oil")) {
            return new ProcessingRecord("A gourmet cooking ingredient.", 38);
        } else if (name.equals("Oil")) {
            return new ProcessingRecord("All purpose cooking oil.", 13);
        } else {
            return null;
        }
    }

    public ProcessingRecord preservesJar(String input) {
        String name = input;
        if (name.equals("Pickles")) {
            return new ProcessingRecord("A jar of your home-made pickles.", 1.75);
        } else if (name.equals("Jelly")) {
            return new ProcessingRecord("Gooey.", 2);
        } else {
            return null;
        }
    }

    public ProcessingRecord fishSmoker() {
        return new ProcessingRecord("A whole fish, smoked to perfection.", 1.5);
    }

    public ProcessingRecord Furnace() {
        return new ProcessingRecord("Turns ore and coal into metal bars.", 0);
    }
}