package mc233.fun.colorfulcolors.GUI.PlayerColor;


import mc233.fun.colorfulcolors.AllPlayers;
import mc233.fun.colorfulcolors.AllTemplates;
import mc233.fun.colorfulcolors.DataStructures.PlayerData;
import mc233.fun.colorfulcolors.GUI.Button;
import mc233.fun.colorfulcolors.GUI.GUI;
import mc233.fun.colorfulcolors.GUI.GUIHandler;
import mc233.fun.colorfulcolors.GUI.PageUtilit;
import mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI;
import mc233.fun.colorfulcolors.Util;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TemplatesGUI extends GUI {
    private PlayerData dt = null;
    private int page = 1;

    public TemplatesGUI(Player p, int size, PlayerData dt, int page) {
        super("模板选择", size);
        this.dt = dt;
        this.page = page;
        this.setButtons(p);
        GUIHandler.putPlayer(p, this);
        this.open(p);
    }

    private void setButtons(final Player p) {
        List<ItemStack> allItems = new ArrayList();
        Iterator var3 = AllTemplates.getAllTemplates().keySet().iterator();

        while(var3.hasNext()) {
            String key = (String)var3.next();
            if (p.hasPermission("colorfulcolors.template." + key)) {
                allItems.add(AllTemplates.getTemplate(key).getIcon());
            }
        }

        ItemStack remove;
        ItemMeta removeMeta;
        if (PageUtilit.canSwitchPages(allItems, this.page - 1, 16)) {
            remove = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
            removeMeta = remove.getItemMeta();
            removeMeta.setDisplayName("" + ChatColor.RESET + ChatColor.of("#18d935") + "上一页");
            remove.setItemMeta(removeMeta);
            this.setButton(0, new Button(remove) {
                public void onClick(InventoryClickEvent e) {
                    new mc233.fun.colorfulcolors.GUI.PlayerColor.TemplatesGUI(p, 27, mc233.fun.colorfulcolors.GUI.PlayerColor.TemplatesGUI.this.dt, mc233.fun.colorfulcolors.GUI.PlayerColor.TemplatesGUI.this.page - 1);
                }
            });
        } else {
            remove = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            removeMeta = remove.getItemMeta();
            removeMeta.setDisplayName("" + ChatColor.RESET + ChatColor.of("#18d935") + "返回编辑");
            remove.setItemMeta(removeMeta);
            this.setButton(0, new Button(remove) {
                public void onClick(InventoryClickEvent e) {
                    Util.playSound(p, "nextPage");
                    new mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI(p, 27, mc233.fun.colorfulcolors.GUI.PlayerColor.TemplatesGUI.this.dt);
                }
            });
        }

        if (PageUtilit.canSwitchPages(allItems, this.page + 1, 16)) {
            remove = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
            removeMeta = remove.getItemMeta();
            removeMeta.setDisplayName("" + ChatColor.RESET + ChatColor.of("#18d935") + "下一页");
            remove.setItemMeta(removeMeta);
            this.setButton(8, new Button(remove) {
                public void onClick(InventoryClickEvent e) {
                    new mc233.fun.colorfulcolors.GUI.PlayerColor.TemplatesGUI(p, 27, mc233.fun.colorfulcolors.GUI.PlayerColor.TemplatesGUI.this.dt, mc233.fun.colorfulcolors.GUI.PlayerColor.TemplatesGUI.this.page + 1);
                }
            });
        } else {
            remove = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            removeMeta = remove.getItemMeta();
            removeMeta.setDisplayName("" + ChatColor.RESET);
            remove.setItemMeta(removeMeta);
            this.setButton(8, new Button(remove) {
                public void onClick(InventoryClickEvent e) {
                    Util.playSound(p, "noMorePages");
                }
            });
        }

        remove = new ItemStack(Material.BARRIER);
        removeMeta = remove.getItemMeta();
        removeMeta.setDisplayName("" + ChatColor.RESET + ChatColor.of("#ed0731") + "移除当前模板");
        remove.setItemMeta(removeMeta);
        this.setButton(22, new Button(remove) {
            public void onClick(InventoryClickEvent e) {
                mc233.fun.colorfulcolors.GUI.PlayerColor.TemplatesGUI.this.dt.removeColor(p);
                AllPlayers.putInTemp(p, mc233.fun.colorfulcolors.GUI.PlayerColor.TemplatesGUI.this.dt);
                AllPlayers.removePlayerDataAndColor(p);
                new mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI(p, 27, mc233.fun.colorfulcolors.GUI.PlayerColor.TemplatesGUI.this.dt);
                Util.playSound(p, "remove");
            }
        });
        boolean i = false;
        Iterator var6 = PageUtilit.getPageItems(allItems, this.page, 16).iterator();

        while(var6.hasNext()) {
            final ItemStack item = (ItemStack)var6.next();
            this.setButton(this.getFirstEmpty(27), new Button(item) {
                public void onClick(InventoryClickEvent e) {
                    String templateName = item.getItemMeta().getLocalizedName();
                    if (AllTemplates.exists(templateName)) {
                        mc233.fun.colorfulcolors.GUI.PlayerColor.TemplatesGUI.this.dt.setTemplate(AllTemplates.getTemplate(templateName));
                    }

                    Util.playSound(p, "applyTemplate");
                    new SavingGUI(p, 27, mc233.fun.colorfulcolors.GUI.PlayerColor.TemplatesGUI.this.dt);
                }
            });
        }

    }

    private int getFirstEmpty(int inventorySize) {
        int i = 0;

        do {
            if (!this.getAllButtons().containsKey(i)) {
                return i;
            }

            ++i;
        } while(i <= inventorySize - 1);

        return -1;
    }
}
