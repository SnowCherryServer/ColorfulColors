package mc233.fun.colorfulcolors.GUI.Rename;


import mc233.fun.colorfulcolors.DataStructures.RenameData;
import mc233.fun.colorfulcolors.GUI.Button;
import mc233.fun.colorfulcolors.GUI.GUI;
import mc233.fun.colorfulcolors.GUI.GUIHandler;
import mc233.fun.colorfulcolors.GUI.Global.LetterGUI;
import mc233.fun.colorfulcolors.TempData;
import mc233.fun.colorfulcolors.Util;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class RenameSavingGUI extends GUI {
    private RenameData rd;

    public RenameSavingGUI(Player p, int size, RenameData rd) {
        super("保存", size);
        this.rd = rd;
        this.setButtons(p);
        GUIHandler.putPlayer(p, this);
        this.open(p);
    }

    public void setButtons(final Player p) {
        ItemStack previous = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta previousMeta = previous.getItemMeta();
        previousMeta.setDisplayName("" + ChatColor.RESET + ChatColor.of("#18d935") + "返回编辑");
        previous.setItemMeta(previousMeta);
        this.setButton(0, new Button(previous) {
            public void onClick(InventoryClickEvent e) {
                Util.playSound(p, "nextPage");
                new LetterGUI(p, 27, mc233.fun.colorfulcolors.GUI.Rename.RenameSavingGUI.this.rd.getName());
            }
        });
        ItemStack save = new ItemStack(Material.FLOWER_BANNER_PATTERN);
        ItemMeta saveMeta = save.getItemMeta();
        saveMeta.setDisplayName("" + ChatColor.RESET + ChatColor.of("#ff7424") + ChatColor.BOLD + "保存");
        new ArrayList();
        saveMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_POTION_EFFECTS});
        save.setItemMeta(saveMeta);
        this.setButton(22, new Button(save) {
            public void onClick(InventoryClickEvent e) {
                if (p.getInventory().getItemInMainHand() != null && mc233.fun.colorfulcolors.GUI.Rename.RenameSavingGUI.this.rd.getPrevItem().equals(p.getInventory().getItemInMainHand())) {
                    ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                    meta.setDisplayName(mc233.fun.colorfulcolors.GUI.Rename.RenameSavingGUI.this.rd.getName().getColoredWord());
                    p.getInventory().getItemInMainHand().setItemMeta(meta);
                    TempData.removeRenameData(p);
                } else {
                    Util.sendMessage(p, "物品");
                }

                p.closeInventory();
            }
        });
        ItemStack example = new ItemStack(this.rd.getPrevItem());
        ItemMeta exampleMeta = example.getItemMeta();
        exampleMeta.setDisplayName(ChatColor.WHITE + "新的名字: " + this.rd.getName().getColoredWord());
        example.setItemMeta(exampleMeta);
        this.setButton(13, new Button(example) {
            public void onClick(InventoryClickEvent e) {
                Util.playSound(p, "noMorePages");
            }
        });
    }
}
