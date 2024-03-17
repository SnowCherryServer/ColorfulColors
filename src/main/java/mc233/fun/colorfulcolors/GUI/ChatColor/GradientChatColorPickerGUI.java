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

public class GradientChatColorPickerGUI extends GUI {
    private PrismaticColor color1;
    private PrismaticColor color2;
    private String target;

    public GradientChatColorPickerGUI(Player p, int size, PrismaticColor color1, PrismaticColor color2, String target) {
        super(ChatColor.of(color1.getColorObject()) + "聊天颜色 1 " + ChatColor.of(color2.getColorObject()) + "聊天颜色 2", size);
        this.color1 = color1;
        this.color2 = color2;
        this.target = target;
        this.setButtons(p);
        GUIHandler.putPlayer(p, this);
        this.open(p);
    }

    private void setButtons(final Player p) {
        ItemStack red1 = new ItemStack(Material.RED_CONCRETE);
        ItemMeta red1Meta = red1.getItemMeta();
        red1Meta.setDisplayName("" + ChatColor.RESET + ChatColor.BOLD + ChatColor.of("#ed0731") + this.color1.getRed());
        ArrayList<String> red1Lore = new ArrayList();
        red1Lore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "左键: " + ChatColor.RESET + ChatColor.of("#ed0731") + "+1");
        red1Lore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "中键: " + ChatColor.RESET + ChatColor.of("#ed0731") + "+10");
        red1Lore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "Shift+左键: " + ChatColor.RESET + ChatColor.of("#ed0731") + "255");
        red1Lore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "右键: " + ChatColor.RESET + ChatColor.of("#ed0731") + "-1");
        red1Lore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "点Q: " + ChatColor.RESET + ChatColor.of("#ed0731") + "-10");
        red1Lore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "Shift+右键: " + ChatColor.RESET + ChatColor.of("#ed0731") + "0");
        red1Meta.setLore(red1Lore);
        red1.setItemMeta(red1Meta);
        this.setButton(9, new Button(red1) {
            public void onClick(InventoryClickEvent e) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1.addRed(1);
                } else if (e.getClick().equals(ClickType.MIDDLE)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1.addRed(10);
                } else if (e.getClick().equals(ClickType.DROP)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1.addRed(-10);
                } else if (e.getClick().equals(ClickType.RIGHT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1.addRed(-1);
                } else if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1.setRed(255);
                } else if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1.setRed(0);
                }

                Util.playSound(p, "colorModify");
                new mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI(p, 27, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.target);
            }
        });
        ItemStack green1 = new ItemStack(Material.LIME_CONCRETE);
        ItemMeta green1Meta = green1.getItemMeta();
        green1Meta.setDisplayName("" + ChatColor.RESET + ChatColor.BOLD + ChatColor.of("#1ae813") + this.color1.getGreen());
        ArrayList<String> green1Lore = new ArrayList();
        green1Lore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "左键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "+1");
        green1Lore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "中键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "+10");
        green1Lore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "Shift+左键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "255");
        green1Lore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "右键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "-1");
        green1Lore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "点Q: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "-10");
        green1Lore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "Shift+右键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "0");
        green1Meta.setLore(green1Lore);
        green1.setItemMeta(green1Meta);
        this.setButton(10, new Button(green1) {
            public void onClick(InventoryClickEvent e) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1.addGreen(1);
                } else if (e.getClick().equals(ClickType.MIDDLE)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1.addGreen(10);
                } else if (e.getClick().equals(ClickType.DROP)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1.addGreen(-10);
                } else if (e.getClick().equals(ClickType.RIGHT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1.addGreen(-1);
                } else if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1.setGreen(255);
                } else if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1.setGreen(0);
                }

                Util.playSound(p, "colorModify");
                new mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI(p, 27, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.target);
            }
        });
        ItemStack blue1 = new ItemStack(Material.LIGHT_BLUE_CONCRETE);
        ItemMeta blue1Meta = blue1.getItemMeta();
        blue1Meta.setDisplayName("" + ChatColor.RESET + ChatColor.BOLD + ChatColor.of("#1d92d1") + this.color1.getBlue());
        ArrayList<String> blue1Lore = new ArrayList();
        blue1Lore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "左键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "+1");
        blue1Lore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + ChatColor.BOLD + "中键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "+10");
        blue1Lore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "Shift+左键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "255");
        blue1Lore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "右键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "-1");
        blue1Lore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "点Q: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "-10");
        blue1Lore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "Shift+右键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "0");
        blue1Meta.setLore(blue1Lore);
        blue1.setItemMeta(blue1Meta);
        this.setButton(11, new Button(blue1) {
            public void onClick(InventoryClickEvent e) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1.addBlue(1);
                } else if (e.getClick().equals(ClickType.MIDDLE)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1.addBlue(10);
                } else if (e.getClick().equals(ClickType.DROP)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1.addBlue(-10);
                } else if (e.getClick().equals(ClickType.RIGHT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1.addBlue(-1);
                } else if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1.setBlue(255);
                } else if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1.setBlue(0);
                }

                Util.playSound(p, "colorModify");
                new mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI(p, 27, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.target);
            }
        });
        ItemStack red2 = new ItemStack(Material.RED_CONCRETE);
        ItemMeta red2Meta = red2.getItemMeta();
        red2Meta.setDisplayName("" + ChatColor.RESET + ChatColor.BOLD + ChatColor.of("#ed0731") + this.color2.getRed());
        ArrayList<String> red2Lore = new ArrayList();
        red2Lore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "左键: " + ChatColor.RESET + ChatColor.of("#ed0731") + "+1");
        red2Lore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "中键: " + ChatColor.RESET + ChatColor.of("#ed0731") + "+10");
        red2Lore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "Shift+左键: " + ChatColor.RESET + ChatColor.of("#ed0731") + "255");
        red2Lore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "右键: " + ChatColor.RESET + ChatColor.of("#ed0731") + "-1");
        red2Lore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "点Q: " + ChatColor.RESET + ChatColor.of("#ed0731") + "-10");
        red2Lore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "Shift+右键: " + ChatColor.RESET + ChatColor.of("#ed0731") + "0");
        red2Meta.setLore(red2Lore);
        red2.setItemMeta(red2Meta);
        this.setButton(15, new Button(red2) {
            public void onClick(InventoryClickEvent e) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2.addRed(1);
                } else if (e.getClick().equals(ClickType.MIDDLE)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2.addRed(10);
                } else if (e.getClick().equals(ClickType.DROP)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2.addRed(-10);
                } else if (e.getClick().equals(ClickType.RIGHT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2.addRed(-1);
                } else if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2.setRed(255);
                } else if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2.setRed(0);
                }

                Util.playSound(p, "colorModify");
                new mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI(p, 27, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.target);
            }
        });
        ItemStack green2 = new ItemStack(Material.LIME_CONCRETE);
        ItemMeta green2Meta = green2.getItemMeta();
        green2Meta.setDisplayName("" + ChatColor.RESET + ChatColor.BOLD + ChatColor.of("#1ae813") + this.color2.getGreen());
        ArrayList<String> green2Lore = new ArrayList();
        green2Lore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "左键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "+1");
        green2Lore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "中键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "+10");
        green2Lore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "Shift+左键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "255");
        green2Lore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "右键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "-1");
        green2Lore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "点Q: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "-10");
        green2Lore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "Shift+右键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "0");
        green2Meta.setLore(green2Lore);
        green2.setItemMeta(green2Meta);
        this.setButton(16, new Button(green2) {
            public void onClick(InventoryClickEvent e) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2.addGreen(1);
                } else if (e.getClick().equals(ClickType.MIDDLE)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2.addGreen(10);
                } else if (e.getClick().equals(ClickType.DROP)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2.addGreen(-10);
                } else if (e.getClick().equals(ClickType.RIGHT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2.addGreen(-1);
                } else if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2.setGreen(255);
                } else if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2.setGreen(0);
                }

                Util.playSound(p, "colorModify");
                new mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI(p, 27, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.target);
            }
        });
        ItemStack blue2 = new ItemStack(Material.LIGHT_BLUE_CONCRETE);
        ItemMeta blue2Meta = blue2.getItemMeta();
        blue2Meta.setDisplayName("" + ChatColor.RESET + ChatColor.BOLD + ChatColor.of("#1d92d1") + this.color2.getBlue());
        ArrayList<String> blue2Lore = new ArrayList();
        blue2Lore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "左键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "+1");
        blue2Lore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + ChatColor.BOLD + "中键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "+10");
        blue2Lore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "Shift+左键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "255");
        blue2Lore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "右键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "-1");
        blue2Lore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "点Q: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "-10");
        blue2Lore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "Shift+右键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "0");
        blue2Meta.setLore(blue2Lore);
        blue2.setItemMeta(blue2Meta);
        this.setButton(17, new Button(blue2) {
            public void onClick(InventoryClickEvent e) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2.addBlue(1);
                } else if (e.getClick().equals(ClickType.MIDDLE)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2.addBlue(10);
                } else if (e.getClick().equals(ClickType.DROP)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2.addBlue(-10);
                } else if (e.getClick().equals(ClickType.RIGHT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2.addBlue(-1);
                } else if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2.setBlue(255);
                } else if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2.setBlue(0);
                }

                Util.playSound(p, "colorModify");
                new mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI(p, 27, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.target);
            }
        });
        ItemStack barrier1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta barrier1Meta = barrier1.getItemMeta();
        barrier1Meta.setDisplayName("" + ChatColor.RESET);
        barrier1.setItemMeta(barrier1Meta);
        this.setButton(4, new Button(barrier1) {
            public void onClick(InventoryClickEvent e) {
                Util.playSound(p, "noMorePages");
            }
        });
        ItemStack barrier2 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta barrier2Meta = barrier2.getItemMeta();
        barrier2Meta.setDisplayName("" + ChatColor.RESET);
        barrier2.setItemMeta(barrier2Meta);
        this.setButton(13, new Button(barrier2) {
            public void onClick(InventoryClickEvent e) {
                Util.playSound(p, "noMorePages");
            }
        });
        ItemStack save = new ItemStack(Material.FLOWER_BANNER_PATTERN);
        ItemMeta saveMeta = save.getItemMeta();
        saveMeta.setDisplayName("" + ChatColor.RESET + ChatColor.of("#ff7424") + ChatColor.BOLD + "保存");
        ArrayList<String> saveLore = new ArrayList();
        saveLore.add("" + ChatColor.RESET + ChatColor.GRAY + "这会成为你的聊天颜色");
        saveMeta.setLore(saveLore);
        saveMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_POTION_EFFECTS});
        save.setItemMeta(saveMeta);
        this.setButton(22, new Button(save) {
            public void onClick(InventoryClickEvent e) {
                if (!mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.target.equals("")) {
                    Player otherPlayer = Bukkit.getPlayerExact(mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.target);
                    if (otherPlayer != null) {
                        AllPlayers.setChatColor(otherPlayer, new PlayerChatColor(mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2));
                        e.setCancelled(true);
                        p.closeInventory();
                        Util.playSound(p, "save");
                    } else {
                        Util.sendTargetMessage(p, "targetOffline", mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.target);
                        e.setCancelled(true);
                        p.closeInventory();
                    }
                } else {
                    AllPlayers.setChatColor(p, new PlayerChatColor(mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color1, mc233.fun.colorfulcolors.GUI.ChatColor.GradientChatColorPickerGUI.this.color2));
                    p.closeInventory();
                    Util.playSound(p, "save");
                    p.closeInventory();
                }

            }
        });
    }
}
