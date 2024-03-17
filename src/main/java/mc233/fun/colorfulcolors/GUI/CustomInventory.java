package mc233.fun.colorfulcolors.GUI;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class CustomInventory implements InventoryHolder {
    private final String title;
    private final int size;

    public CustomInventory(String title, int size) {
        this.title = title;
        this.size = size;
    }

    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, this.size, this.title);
        return inventory;
    }
}
