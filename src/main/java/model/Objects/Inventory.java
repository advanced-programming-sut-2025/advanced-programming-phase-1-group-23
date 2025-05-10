package model.Objects;

import model.enums.Ingredients;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    public HashMap<Ingredients, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(HashMap<Ingredients, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public enum InventoryTypes{
        Initial(12),
        Huge(24),
        Deluxe(100000);

        private final int capacity;
        InventoryTypes(int capacity) {
            this.capacity = capacity;
        }

    }
    private HashMap<Tool, Integer> tools=new HashMap<>();
    private HashMap<Ingredients, Integer> ingredients=new HashMap<>();
    private final InventoryTypes inventoryTypes=InventoryTypes.Initial;


    public HashMap<Tool, Integer> getTools() {
        return tools;
    }

}
