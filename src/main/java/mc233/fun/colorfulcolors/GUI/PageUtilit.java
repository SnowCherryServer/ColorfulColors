package mc233.fun.colorfulcolors.GUI;


import mc233.fun.colorfulcolors.ColorfulColors;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PageUtilit {
    public PageUtilit() {
    }

    public static List<ItemStack> getPageItems(List<ItemStack> items, int page, int spaces) {
        int upperBound = page * spaces;
        int lowerBound = upperBound - spaces;
        List<ItemStack> pageItems = new ArrayList();

        for(int i = lowerBound; i < upperBound; ++i) {
            try {
                pageItems.add((ItemStack)items.get(i));
            } catch (IndexOutOfBoundsException var8) {
            }
        }

        return pageItems;
    }

    public static boolean canSwitchPages(List<ItemStack> items, int page, int spaces) {
        if (page <= 0) {
            return false;
        } else {
            int upperBound = page * spaces;
            int lowerBound = upperBound - spaces;
            return items.size() > lowerBound;
        }
    }

    public static void cantAction(final Inventory inventory, final int slot, ItemStack newItem) {
        final ItemStack prevItem = inventory.getItem(slot);
        inventory.setItem(slot, newItem);
        Bukkit.getScheduler().runTaskLater(ColorfulColors.plugin, new Runnable() {
            public void run() {
                inventory.setItem(slot, prevItem);
            }
        }, 25L);
    }
}
