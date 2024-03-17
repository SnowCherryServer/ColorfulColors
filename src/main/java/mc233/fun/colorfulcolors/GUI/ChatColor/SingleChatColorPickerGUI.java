package mc233.fun.colorfulcolors.GUI.ChatColor;

import java.util.ArrayList;
import mc233.fun.colorfulcolors.AllPlayers;
import mc233.fun.colorfulcolors.Util;
import mc233.fun.colorfulcolors.DataStructures.PlayerChatColor;
import mc233.fun.colorfulcolors.DataStructures.PrismaticColor;
import mc233.fun.colorfulcolors.GUI.Button;
import mc233.fun.colorfulcolors.GUI.GUI;
import mc233.fun.colorfulcolors.GUI.GUIHandler;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SingleChatColorPickerGUI extends GUI {
    private PrismaticColor color;
    private String target;

    public SingleChatColorPickerGUI(Player p, int size, PrismaticColor color, String target) {
        super(ChatColor.of(color.getColorObject()) + "聊天颜色", size);
        this.color = color;
        this.target = target;
        this.setButtons(p);
        GUIHandler.putPlayer(p, this);
        this.open(p);
    }

    private void setButtons(final Player p) {
        ItemStack red = new ItemStack(Material.RED_CONCRETE);
        ItemMeta redMeta = red.getItemMeta();
        redMeta.setDisplayName("" + ChatColor.RESET + ChatColor.BOLD + ChatColor.of("#ed0731") + this.color.getRed());
        ArrayList<String> redLore = new ArrayList();
        redLore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "左键: " + ChatColor.RESET + ChatColor.of("#ed0731") + "+1");
        redLore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "中键: " + ChatColor.RESET + ChatColor.of("#ed0731") + "+10");
        redLore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "Shift+左键: " + ChatColor.RESET + ChatColor.of("#ed0731") + "255");
        redLore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "右键: " + ChatColor.RESET + ChatColor.of("#ed0731") + "-1");
        redLore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "点Q: " + ChatColor.RESET + ChatColor.of("#ed0731") + "-10");
        redLore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "Shift+右键: " + ChatColor.RESET + ChatColor.of("#ed0731") + "0");
        redMeta.setLore(redLore);
        red.setItemMeta(redMeta);
        this.setButton(12, new Button(red) {
            public void onClick(InventoryClickEvent e) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color.addRed(1);
                } else if (e.getClick().equals(ClickType.MIDDLE)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color.addRed(10);
                } else if (e.getClick().equals(ClickType.DROP)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color.addRed(-10);
                } else if (e.getClick().equals(ClickType.RIGHT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color.addRed(-1);
                } else if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color.setRed(255);
                } else if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color.setRed(0);
                }

                Util.playSound(p, "colorModify");
                new mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI(p, 27, mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color, mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.target);
            }
        });
        ItemStack green = new ItemStack(Material.LIME_CONCRETE);
        ItemMeta greenMeta = green.getItemMeta();
        greenMeta.setDisplayName("" + ChatColor.RESET + ChatColor.BOLD + ChatColor.of("#1ae813") + this.color.getGreen());
        ArrayList<String> greenLore = new ArrayList();
        greenLore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "左键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "+1");
        greenLore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "中键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "+10");
        greenLore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "Shift+左键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "255");
        greenLore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "右键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "-1");
        greenLore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "点Q: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "-10");
        greenLore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "Shift+右键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "0");
        greenMeta.setLore(greenLore);
        green.setItemMeta(greenMeta);
        this.setButton(13, new Button(green) {
            public void onClick(InventoryClickEvent e) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color.addGreen(1);
                } else if (e.getClick().equals(ClickType.MIDDLE)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color.addGreen(10);
                } else if (e.getClick().equals(ClickType.DROP)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color.addGreen(-10);
                } else if (e.getClick().equals(ClickType.RIGHT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color.addGreen(-1);
                } else if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color.setGreen(255);
                } else if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color.setGreen(0);
                }

                Util.playSound(p, "colorModify");
                new mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI(p, 27, mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color, mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.target);
            }
        });
        ItemStack blue = new ItemStack(Material.LIGHT_BLUE_CONCRETE);
        ItemMeta blueMeta = blue.getItemMeta();
        blueMeta.setDisplayName("" + ChatColor.RESET + ChatColor.BOLD + ChatColor.of("#1d92d1") + this.color.getBlue());
        ArrayList<String> blueLore = new ArrayList();
        blueLore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "左键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "+1");
        blueLore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + ChatColor.BOLD + "中键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "+10");
        blueLore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "Shift+左键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "255");
        blueLore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "右键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "-1");
        blueLore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "点Q: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "-10");
        blueLore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "Shift+右键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "0");
        blueMeta.setLore(blueLore);
        blue.setItemMeta(blueMeta);
        this.setButton(14, new Button(blue) {
            public void onClick(InventoryClickEvent e) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color.addBlue(1);
                } else if (e.getClick().equals(ClickType.MIDDLE)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color.addBlue(10);
                } else if (e.getClick().equals(ClickType.DROP)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color.addBlue(-10);
                } else if (e.getClick().equals(ClickType.RIGHT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color.addBlue(-1);
                } else if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color.setBlue(255);
                } else if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color.setBlue(0);
                }

                Util.playSound(p, "colorModify");
                new mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI(p, 27, mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color, mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.target);
            }
        });
        ItemStack save = new ItemStack(Material.FLOWER_BANNER_PATTERN);
        ItemMeta saveMeta = save.getItemMeta();
        saveMeta.setDisplayName("" + ChatColor.RESET + ChatColor.of("#ff7424") + ChatColor.BOLD + "保存");
        ArrayList<String> saveLore = new ArrayList();
        saveLore.add("" + ChatColor.RESET + ChatColor.of(this.color.getColorObject()) + "这会成为你的聊天颜色");
        saveMeta.setLore(saveLore);
        saveMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_POTION_EFFECTS});
        save.setItemMeta(saveMeta);
        this.setButton(22, new Button(save) {
            public void onClick(InventoryClickEvent e) {
                if (!mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.target.equals("")) {
                    Player otherPlayer = Bukkit.getPlayerExact(mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.target);
                    if (otherPlayer != null) {
                        AllPlayers.setChatColor(otherPlayer, new PlayerChatColor(mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color));
                        e.setCancelled(true);
                        p.closeInventory();
                        Util.playSound(p, "save");
                    } else {
                        Util.sendTargetMessage(p, "targetOffline", mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.target);
                        e.setCancelled(true);
                        p.closeInventory();
                    }
                } else {
                    AllPlayers.setChatColor(p, new PlayerChatColor(mc233.fun.colorfulcolors.GUI.ChatColor.SingleChatColorPickerGUI.this.color));
                    p.closeInventory();
                    Util.playSound(p, "save");
                    p.closeInventory();
                }

            }
        });
    }
}
