package mc233.fun.colorfulcolors.GUI.PlayerColor;


import mc233.fun.colorfulcolors.AllPlayers;
import mc233.fun.colorfulcolors.DataStructures.PlayerData;
import mc233.fun.colorfulcolors.GUI.Button;
import mc233.fun.colorfulcolors.GUI.GUI;
import mc233.fun.colorfulcolors.GUI.GUIHandler;
import mc233.fun.colorfulcolors.GUI.Global.LetterGUI;
import mc233.fun.colorfulcolors.GUI.PageUtilit;
import mc233.fun.colorfulcolors.GUI.PlayerColor.TemplatesGUI;
import mc233.fun.colorfulcolors.Util;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SavingGUI extends GUI {
    private PlayerData dt = null;

    public SavingGUI(Player p, int size, PlayerData dt) {
        super("保存", size);
        this.dt = dt;
        this.setButtons(p);
        GUIHandler.putPlayer(p, this);
        this.open(p);
    }

    public void setButtons(final Player p) {
        new ItemStack(Material.AIR);
        ItemStack previous;
        ItemMeta previousMeta;
        if (this.dt.getTemplate() != null) {
            previous = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            previousMeta = previous.getItemMeta();
            previousMeta.setDisplayName("" + ChatColor.RESET + ChatColor.of("#ed0731") + "编辑前删除模板");
            previous.setItemMeta(previousMeta);
            this.setButton(0, new Button(previous) {
                public void onClick(InventoryClickEvent e) {
                    Util.playSound(p, "noMorePages");
                }
            });
        } else {
            previous = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            previousMeta = previous.getItemMeta();
            previousMeta.setDisplayName("" + ChatColor.RESET + ChatColor.of("#18d935") + "返回编辑");
            previous.setItemMeta(previousMeta);
            this.setButton(0, new Button(previous) {
                public void onClick(InventoryClickEvent e) {
                    Util.playSound(p, "nextPage");
                    mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI.this.dt.getName().setCurrentLetterNum(mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI.this.dt.getName().getLetters().size() - 1);
                    new LetterGUI(p, 27, mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI.this.dt.getName());
                }
            });
        }

        ItemStack tab = new ItemStack(Material.SHROOMLIGHT);
        ItemMeta tabMeta = tab.getItemMeta();
        tabMeta.setDisplayName("" + ChatColor.RESET + ChatColor.of("#bd24ff") + ChatColor.BOLD + "设置 tab");
        tab.setItemMeta(tabMeta);
        if (this.dt.isTab()) {
            tabMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            tabMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
            tabMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
            tab.setItemMeta(tabMeta);
        }

        this.setButton(11, new Button(tab) {
            public void onClick(InventoryClickEvent e) {
                if (p.hasPermission("colorfulcolors.allowtab")) {
                    if (mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI.this.dt.getTemplate() == null) {
                        if (mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI.this.dt.isTab()) {
                            mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI.this.dt.setTab(false);
                        } else {
                            mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI.this.dt.setTab(true);
                        }

                        new mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI(p, 27, mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI.this.dt);
                        Util.playSound(p, "toggle");
                    } else {
                        Util.sendMessage(p, "removeTemplate");
                    }
                } else if (e.getCurrentItem().getType() != Material.BARRIER) {
                    ItemStack newItem = new ItemStack(Material.BARRIER);
                    ItemMeta meta = newItem.getItemMeta();
                    meta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "你没有权限设置 Tab");
                    newItem.setItemMeta(meta);
                    PageUtilit.cantAction(e.getInventory(), e.getSlot(), newItem);
                    Util.playSound(p, "error");
                    Util.sendMessage(p, "noPermTab");
                }

            }
        });
        ItemStack templates = new ItemStack(Material.PAINTING);
        ItemMeta templatesMeta = templates.getItemMeta();
        templatesMeta.setDisplayName("" + ChatColor.RESET + ChatColor.of("#f22e69") + ChatColor.BOLD + "模板");
        templates.setItemMeta(templatesMeta);
        this.setButton(13, new Button(templates) {
            public void onClick(InventoryClickEvent e) {
                Util.playSound(p, "templates");
                new TemplatesGUI(p, 27, mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI.this.dt, 1);
            }
        });
        ItemStack glow = new ItemStack(Material.HONEYCOMB);
        ItemMeta glowMeta = glow.getItemMeta();
        glowMeta.setDisplayName("" + ChatColor.RESET + ChatColor.of("#8eff24") + ChatColor.BOLD + "设置发光");
        ArrayList<String> glowLore = new ArrayList();
        glowLore.add(ChatColor.GRAY + "使用介于120到220之间的RGB值以获得最佳效果");
        glowMeta.setLore(glowLore);
        if (this.dt.isGlowing()) {
            glowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            glowMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
            glowMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        }

        glow.setItemMeta(glowMeta);
        this.setButton(15, new Button(glow) {
            public void onClick(InventoryClickEvent e) {
                if (p.hasPermission("colorfulcolors.allowglow")) {
                    if (mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI.this.dt.getTemplate() == null) {
                        if (mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI.this.dt.isGlowing()) {
                            mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI.this.dt.setGlowing(false);
                        } else {
                            mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI.this.dt.setGlowing(true);
                        }

                        new mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI(p, 27, mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI.this.dt);
                        Util.playSound(p, "toggle");
                    } else {
                        Util.sendMessage(p, "removeTemplate");
                    }
                } else if (e.getCurrentItem().getType() != Material.BARRIER) {
                    ItemStack newItem = new ItemStack(Material.BARRIER);
                    ItemMeta meta = newItem.getItemMeta();
                    meta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "你没有权限设置发光");
                    newItem.setItemMeta(meta);
                    PageUtilit.cantAction(e.getInventory(), e.getSlot(), newItem);
                    Util.playSound(p, "error");
                    Util.sendMessage(p, "noPermGlow");
                }

            }
        });
        ItemStack save = new ItemStack(Material.FLOWER_BANNER_PATTERN);
        ItemMeta saveMeta = save.getItemMeta();
        saveMeta.setDisplayName("" + ChatColor.RESET + ChatColor.of("#ff7424") + ChatColor.BOLD + "保存");
        ArrayList<String> saveLore = new ArrayList();
        if (this.dt.getTemplate() != null) {
            saveLore.add(ChatColor.of("#666666") + "你正在使用模板");
        } else {
            saveLore.add(ChatColor.RESET + this.dt.example());
        }

        saveMeta.setLore(saveLore);
        saveMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_POTION_EFFECTS});
        save.setItemMeta(saveMeta);
        this.setButton(22, new Button(save) {
            public void onClick(InventoryClickEvent e) {
                if (!mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI.this.dt.getTarget().equals("")) {
                    Player otherPlayer = Bukkit.getPlayerExact(mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI.this.dt.getTarget());
                    if (otherPlayer != null) {
                        if (AllPlayers.exists(otherPlayer)) {
                            AllPlayers.getData(otherPlayer).removeColor(otherPlayer);
                        }

                        AllPlayers.activateColor(otherPlayer, mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI.this.dt);
                        e.setCancelled(true);
                        p.closeInventory();
                        Util.playSound(p, "save");
                    } else {
                        Util.sendTargetMessage(p, "targetOffline", mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI.this.dt.getTarget());
                        e.setCancelled(true);
                        p.closeInventory();
                    }
                } else {
                    if (AllPlayers.exists(p)) {
                        AllPlayers.getData(p).removeColor(p);
                    }

                    AllPlayers.activateColor(p, mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI.this.dt);
                    p.closeInventory();
                    Util.playSound(p, "save");
                    p.closeInventory();
                }

            }
        });
    }
}
