package mc233.fun.colorfulcolors.GUI;


import java.util.HashMap;

import mc233.fun.colorfulcolors.GUI.Button;
import mc233.fun.colorfulcolors.GUI.CustomInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class GUI {
    private Inventory inv;
    private HashMap<Integer, Button> buttons;

    public GUI(String title, int size) {
        this.inv = (new CustomInventory(title, size)).getInventory();
        this.buttons = new HashMap();
    }

    public void setButton(int slot, Button button) {
        this.getAllButtons().put(slot, button);
    }

    public void performClick(InventoryClickEvent event) {
        if (this.getAllButtons().get(event.getSlot()) != null) {
            ((Button)this.getAllButtons().get(event.getSlot())).onClick(event);
        }

    }

    private void loadButtons() {
        this.getAllButtons().forEach((slot, button) -> {
            this.inv.setItem(slot, button);
        });
    }

    protected void open(Player player) {
        this.loadButtons();
        player.openInventory(this.inv);
    }

    public Inventory getInventory() {
        return this.inv;
    }

    public HashMap<Integer, Button> getAllButtons() {
        return this.buttons;
    }
}
