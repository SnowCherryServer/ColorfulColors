package mc233.fun.colorfulcolors.GUI.ChatColor;


import mc233.fun.colorfulcolors.DataStructures.PrismaticColor;
import mc233.fun.colorfulcolors.GUI.Button;
import mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI;
import mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI;
import mc233.fun.colorfulcolors.GUI.GUI;
import mc233.fun.colorfulcolors.GUI.GUIHandler;
import mc233.fun.colorfulcolors.GUI.PageUtilit;
import mc233.fun.colorfulcolors.Util;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ChatColorOptionsGUI extends GUI {
    private String target;

    public ChatColorOptionsGUI(Player p, int size) {
        super("聊天颜色设置", size);
        this.target = "";
        this.setButtons(p);
        GUIHandler.putPlayer(p, this);
        this.open(p);
    }

    public ChatColorOptionsGUI(Player p, int size, String target) {
        super("聊天颜色设置", size);
        this.target = target;
        this.setButtons(p);
        GUIHandler.putPlayer(p, this);
        this.open(p);
    }

    private void setButtons(final Player p) {
        ItemStack singleChatColor = new ItemStack(Material.YELLOW_TERRACOTTA);
        ItemMeta singleChatColorMeta = singleChatColor.getItemMeta();
        singleChatColorMeta.setDisplayName("" + ChatColor.RESET + ChatColor.BOLD + ChatColor.of("#F0C91B") + "单种聊天颜色");
        ArrayList<String> singleChatColorLore = new ArrayList();
        singleChatColorLore.add("" + ChatColor.RESET + ChatColor.GRAY + "点击进入GUI设置");
        singleChatColorMeta.setLore(singleChatColorLore);
        singleChatColor.setItemMeta(singleChatColorMeta);
        this.setButton(12, new Button(singleChatColor) {
            public void onClick(InventoryClickEvent e) {
                Util.playSound(p, "nextPage");
                new SingleChatColorPickerGUI(p, 27, new PrismaticColor(), mc233.fun.colorfulcolors.GUI.ChatColor.ChatColorOptionsGUI.this.target);
            }
        });
        ItemStack gradientChatColor = new ItemStack(Material.YELLOW_GLAZED_TERRACOTTA);
        ItemMeta gradientChatColorMeta = gradientChatColor.getItemMeta();
        gradientChatColorMeta.setDisplayName("" + ChatColor.RESET + ChatColor.BOLD + ChatColor.of("#FFDA33") + "渐变聊天颜色");
        ArrayList<String> gradientChatColorLore = new ArrayList();
        gradientChatColorLore.add("" + ChatColor.RESET + ChatColor.GRAY + "点击进入GUI设置");
        gradientChatColorMeta.setLore(gradientChatColorLore);
        gradientChatColor.setItemMeta(gradientChatColorMeta);
        this.setButton(14, new Button(gradientChatColor) {
            public void onClick(InventoryClickEvent e) {
                if (p.hasPermission("colorfulcolors.chatcolor.gradient")) {
                    Util.playSound(p, "nextPage");
                    new GradientChatColorPickerGUI(p, 27, new PrismaticColor(), new PrismaticColor(), mc233.fun.colorfulcolors.GUI.ChatColor.ChatColorOptionsGUI.this.target);
                } else if (e.getCurrentItem().getType() != Material.BARRIER) {
                    ItemStack newItem = new ItemStack(Material.BARRIER);
                    ItemMeta meta = newItem.getItemMeta();
                    meta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "你没有权限使用渐变聊天颜色！");
                    newItem.setItemMeta(meta);
                    PageUtilit.cantAction(e.getInventory(), e.getSlot(), newItem);
                    Util.playSound(p, "error");
                    Util.sendMessage(p, "noPermGradientChatColor");
                }

            }
        });
    }
}
