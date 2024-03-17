package mc233.fun.colorfulcolors.DataStructures;


import mc233.fun.colorfulcolors.DataStructures.Word;
import org.bukkit.inventory.ItemStack;

public class RenameData {
    private Word name;
    private ItemStack prevItem;

    public RenameData(String name, ItemStack prevItem) {
        this.name = new Word(name, 1);
        this.prevItem = prevItem;
    }

    public Word getName() {
        return this.name;
    }

    public void setName(Word name) {
        this.name = name;
    }

    public ItemStack getPrevItem() {
        return this.prevItem;
    }

    public void setPrevItem(ItemStack prevItem) {
        this.prevItem = prevItem;
    }
}

