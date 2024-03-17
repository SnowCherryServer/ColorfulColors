package mc233.fun.colorfulcolors.GUI;

import mc233.fun.colorfulcolors.GUI.CustomInventory;
import mc233.fun.colorfulcolors.GUI.GUIHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIListener implements Listener {
    public GUIListener() {
    }

    @EventHandler
    public void lettersGUI(InventoryClickEvent e) {
        if (e.getView().getTopInventory().getHolder() instanceof mc233.fun.colorfulcolors.GUI.CustomInventory) {
            if (e.getClickedInventory() != null && e.getClickedInventory().getHolder() instanceof CustomInventory && e.getWhoClicked() instanceof Player) {
                Player p = (Player)e.getWhoClicked();
                GUIHandler.getGUI(p).performClick(e);
            }

            e.setCancelled(true);
        }

    }
}