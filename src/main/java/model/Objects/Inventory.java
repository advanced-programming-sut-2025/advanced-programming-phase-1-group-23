package model.Objects;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    public enum InventoryTypes{
        Initial(new Inventory(12)),
        Huge(new Inventory(24)),
        Deluxe(new Inventory(100000));

        private final Inventory inventory;
        InventoryTypes(Inventory inventory) {
            this.inventory = inventory;
        }

        public Inventory getInventory() {
            return inventory;
        }
    }
    private final HashMap<Tool, Integer> tools=new HashMap<>();
    private int  capacity;

    public Inventory( int capacity) {
        this.capacity = capacity;
    }

    public void decreaseOccupiedCapacity(int amount){
        this.capacity-=amount;
    }
    public HashMap<Tool, Integer> getTools() {
        return tools;
    }

}
