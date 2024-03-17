package mc233.fun.colorfulcolors.GUI;


import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public abstract class Button extends ItemStack {
    public Button(Material material) {
        super(material);
    }

    public Button(ItemStack item) {
        super(item);
    }

    public abstract void onClick(InventoryClickEvent var1);
}
