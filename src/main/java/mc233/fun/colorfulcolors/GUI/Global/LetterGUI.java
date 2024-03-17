package mc233.fun.colorfulcolors.GUI.Global;

import java.util.ArrayList;
import mc233.fun.colorfulcolors.AllPlayers;
import mc233.fun.colorfulcolors.TempData;
import mc233.fun.colorfulcolors.Util;
import mc233.fun.colorfulcolors.hexInput;
import mc233.fun.colorfulcolors.DataStructures.LetterData;
import mc233.fun.colorfulcolors.DataStructures.Word;
import mc233.fun.colorfulcolors.GUI.Button;
import mc233.fun.colorfulcolors.GUI.GUI;
import mc233.fun.colorfulcolors.GUI.GUIHandler;
import mc233.fun.colorfulcolors.GUI.PageUtilit;
import mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI;
import mc233.fun.colorfulcolors.GUI.Rename.RenameSavingGUI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LetterGUI extends GUI {
    private Word word = null;

    public LetterGUI(Player p, int size, Word word) {
        super("设置字母: " + ChatColor.RESET + ChatColor.BOLD + word.getCurrentLetter().getColoredLetter(), size);
        this.word = word;
        this.setButtons(p);
        GUIHandler.putPlayer(p, this);
        this.open(p);
    }

    private void setButtons(final Player p) {
        final LetterData ldt = this.word.getCurrentLetter();
        ItemStack previous = new ItemStack(Material.RED_STAINED_GLASS);
        ItemMeta previousMeta = previous.getItemMeta();
        if (this.word.getCurrentLetterNum() == 0) {
            previous = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            previousMeta.setDisplayName("" + ChatColor.RESET);
            previous.setItemMeta(previousMeta);
            this.setButton(0, new Button(previous) {
                public void onClick(InventoryClickEvent e) {
                    Util.playSound(p, "noMorePages");
                }
            });
        } else {
            previous = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
            previousMeta.setDisplayName("" + ChatColor.RESET + ChatColor.of("#18d935") + "上个字母");
            ArrayList<String> previousLore = new ArrayList();
            previousLore.add(ChatColor.of("#666666") + "Shift 点击跳过首字母");
            previousMeta.setLore(previousLore);
            previous.setItemMeta(previousMeta);
            this.setButton(0, new Button(previous) {
                public void onClick(InventoryClickEvent e) {
                    if (mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.getCurrentLetterNum() - 1 < 0) {
                        mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.setCurrentLetterNum(0);
                    } else {
                        if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                            mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.setCurrentLetterNum(0);
                        } else {
                            mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.setCurrentLetterNum(mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.getCurrentLetterNum() - 1);
                        }

                        new mc233.fun.colorfulcolors.GUI.Global.LetterGUI(p, 27, mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word);
                        Util.playSound(p, "nextPage");
                    }

                }
            });
        }

        ItemStack next = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta nextMeta = next.getItemMeta();
        if (this.word.getCurrentLetterNum() >= this.word.getLength() - 1) {
            next = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
            nextMeta.setDisplayName("" + ChatColor.RESET + ChatColor.of("#18a9d9") + "完成");
            next.setItemMeta(nextMeta);
            this.setButton(8, new Button(next) {
                public void onClick(InventoryClickEvent e) {
                    mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.setCurrentLetterNum(mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.getLength() - 1);
                    if (mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.getType() == 1) {
                        new RenameSavingGUI(p, 27, TempData.getRenameData(p));
                    } else {
                        new SavingGUI(p, 27, AllPlayers.getTempData(p));
                    }

                    Util.playSound(p, "nextPage");
                }
            });
        } else {
            next = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
            nextMeta.setDisplayName("" + ChatColor.RESET + ChatColor.of("#18d935") + "下一个字母");
            ArrayList<String> nextLore = new ArrayList();
            nextLore.add(ChatColor.of("#666666") + "Shift 点击跳过最后一个字母");
            nextMeta.setLore(nextLore);
            next.setItemMeta(nextMeta);
            this.setButton(8, new Button(next) {
                public void onClick(InventoryClickEvent e) {
                    if (mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.getCurrentLetterNum() + 1 > mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.getLength() - 1) {
                        mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.setCurrentLetterNum(mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.getLength() - 1);
                    } else {
                        if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                            mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.setCurrentLetterNum(mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.getLength() - 1);
                        } else {
                            mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.setCurrentLetterNum(mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.getCurrentLetterNum() + 1);
                        }

                        new mc233.fun.colorfulcolors.GUI.Global.LetterGUI(p, 27, mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word);
                        Util.playSound(p, "nextPage");
                    }

                }
            });
        }

        ItemStack strikethrough = new ItemStack(Material.DETECTOR_RAIL);
        ItemMeta strikethroughMeta = strikethrough.getItemMeta();
        strikethroughMeta.setDisplayName("" + ChatColor.RESET + ChatColor.WHITE + "设置删除线");
        ArrayList<String> strikethroughLore = new ArrayList();
        strikethroughMeta.setLore(strikethroughLore);
        if (ldt.isStrikethrough()) {
            strikethroughMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            strikethroughMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
            strikethroughMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        }

        strikethrough.setItemMeta(strikethroughMeta);
        this.setButton(2, new Button(strikethrough) {
            public void onClick(InventoryClickEvent event) {
                if (p.hasPermission("colorfulcolors.allowStrikethrough")) {
                    if (mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.getType() == 0 && AllPlayers.getTempData(p).getTemplate() != null) {
                        Util.sendMessage(p, "removeTemplate");
                    } else {
                        if (ldt.isStrikethrough()) {
                            ldt.setStrikethrough(false);
                        } else {
                            ldt.setStrikethrough(true);
                        }

                        new mc233.fun.colorfulcolors.GUI.Global.LetterGUI(p, 27, mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word);
                        Util.playSound(p, "toggle");
                    }
                } else if (event.getCurrentItem().getType() != Material.BARRIER) {
                    ItemStack newItem = new ItemStack(Material.BARRIER);
                    ItemMeta meta = newItem.getItemMeta();
                    meta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "你没有权限设置删除线");
                    newItem.setItemMeta(meta);
                    PageUtilit.cantAction(event.getInventory(), event.getSlot(), newItem);
                    Util.playSound(p, "error");
                    Util.sendMessage(p, "noPermStrikethrough");
                }

            }
        });
        ItemStack underline = new ItemStack(Material.ACTIVATOR_RAIL);
        ItemMeta underlineMeta = underline.getItemMeta();
        underlineMeta.setDisplayName("" + ChatColor.RESET + ChatColor.WHITE + "设置下划线");
        ArrayList<String> underLineLore = new ArrayList();
        underlineMeta.setLore(underLineLore);
        if (ldt.isUnderline()) {
            underlineMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            underlineMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
            underlineMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        }

        underline.setItemMeta(underlineMeta);
        this.setButton(3, new Button(underline) {
            public void onClick(InventoryClickEvent event) {
                if (p.hasPermission("colorfulcolors.allowUnderline")) {
                    if (mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.getType() == 0 && AllPlayers.getTempData(p).getTemplate() != null) {
                        Util.sendMessage(p, "removeTemplate");
                    } else {
                        if (ldt.isUnderline()) {
                            ldt.setUnderline(false);
                        } else {
                            ldt.setUnderline(true);
                        }

                        new mc233.fun.colorfulcolors.GUI.Global.LetterGUI(p, 27, mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word);
                        Util.playSound(p, "toggle");
                    }
                } else if (event.getCurrentItem().getType() != Material.BARRIER) {
                    ItemStack newItem = new ItemStack(Material.BARRIER);
                    ItemMeta meta = newItem.getItemMeta();
                    meta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "你没有权限设置下划线");
                    newItem.setItemMeta(meta);
                    PageUtilit.cantAction(event.getInventory(), event.getSlot(), newItem);
                    Util.playSound(p, "error");
                    Util.sendMessage(p, "noPermUnderline");
                }

            }
        });
        ItemStack hex = new ItemStack(Material.MAP);
        ItemMeta hexMeta = hex.getItemMeta();
        hexMeta.setDisplayName("" + ChatColor.RESET + ChatColor.BOLD + ChatColor.of("#fff04d") + "设置16制颜色");
        hex.setItemMeta(hexMeta);
        this.setButton(4, new Button(hex) {
            public void onClick(InventoryClickEvent event) {
                if (p.hasPermission("colorfulcolors.allowhex")) {
                    p.closeInventory();
                    Util.playSound(p, "templates");
                    hexInput.addPlayer(p, mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word);
                    Util.sendMessage(p, "hexInstructions1");
                    Util.sendMessage(p, "hexInstructions2");
                } else if (event.getCurrentItem().getType() != Material.BARRIER) {
                    ItemStack newItem = new ItemStack(Material.BARRIER);
                    ItemMeta meta = newItem.getItemMeta();
                    meta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "你没有权限设置");
                    newItem.setItemMeta(meta);
                    PageUtilit.cantAction(event.getInventory(), event.getSlot(), newItem);
                    Util.playSound(p, "error");
                    Util.sendMessage(p, "noPermHex");
                }

            }
        });
        ItemStack bold = new ItemStack(Material.POWERED_RAIL);
        ItemMeta boldMeta = bold.getItemMeta();
        boldMeta.setDisplayName("" + ChatColor.RESET + ChatColor.WHITE + "设置粗体");
        ArrayList<String> boldLore = new ArrayList();
        boldMeta.setLore(boldLore);
        if (ldt.isBold()) {
            boldMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            boldMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
            boldMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        }

        bold.setItemMeta(boldMeta);
        this.setButton(5, new Button(bold) {
            public void onClick(InventoryClickEvent event) {
                if (p.hasPermission("colorfulcolors.allowBold")) {
                    if (mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.getType() == 0 && AllPlayers.getTempData(p).getTemplate() != null) {
                        Util.sendMessage(p, "removeTemplate");
                    } else {
                        if (ldt.isBold()) {
                            ldt.setBold(false);
                        } else {
                            ldt.setBold(true);
                        }

                        new mc233.fun.colorfulcolors.GUI.Global.LetterGUI(p, 27, mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word);
                        Util.playSound(p, "toggle");
                    }
                } else if (event.getCurrentItem().getType() != Material.BARRIER) {
                    ItemStack newItem = new ItemStack(Material.BARRIER);
                    ItemMeta meta = newItem.getItemMeta();
                    meta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "你没有权限设置粗体");
                    newItem.setItemMeta(meta);
                    PageUtilit.cantAction(event.getInventory(), event.getSlot(), newItem);
                    Util.playSound(p, "error");
                    Util.sendMessage(p, "noPermBold");
                }

            }
        });
        ItemStack italic = new ItemStack(Material.RAIL);
        ItemMeta italicMeta = italic.getItemMeta();
        italicMeta.setDisplayName("" + ChatColor.RESET + ChatColor.WHITE + "设置斜体");
        ArrayList<String> italicLore = new ArrayList();
        italicMeta.setLore(italicLore);
        if (ldt.isItalic()) {
            italicMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            italicMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
            italicMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        }

        italic.setItemMeta(italicMeta);
        this.setButton(6, new Button(italic) {
            public void onClick(InventoryClickEvent event) {
                if (p.hasPermission("colorfulcolors.allowItalic")) {
                    if (mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.getType() == 0 && AllPlayers.getTempData(p).getTemplate() != null) {
                        Util.sendMessage(p, "removeTemplate");
                    } else {
                        if (ldt.isItalic()) {
                            ldt.setItalic(false);
                        } else {
                            ldt.setItalic(true);
                        }

                        new mc233.fun.colorfulcolors.GUI.Global.LetterGUI(p, 27, mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word);
                        Util.playSound(p, "toggle");
                    }
                } else if (event.getCurrentItem().getType() != Material.BARRIER) {
                    ItemStack newItem = new ItemStack(Material.BARRIER);
                    ItemMeta meta = newItem.getItemMeta();
                    meta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "你没有权限设置斜体");
                    newItem.setItemMeta(meta);
                    PageUtilit.cantAction(event.getInventory(), event.getSlot(), newItem);
                    Util.playSound(p, "error");
                    Util.sendMessage(p, "noPermItalic");
                }

            }
        });
        ItemStack red = new ItemStack(Material.RED_CONCRETE);
        ItemMeta redMeta = red.getItemMeta();
        redMeta.setDisplayName("" + ChatColor.RESET + ChatColor.BOLD + ChatColor.of("#ed0731") + ldt.getColor().getRed());
        ArrayList<String> redLore = new ArrayList();
        redLore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "左键: " + ChatColor.RESET + ChatColor.of("#ed0731") + "+1");
        redLore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "中键: " + ChatColor.RESET + ChatColor.of("#ed0731") + "+10");
        redLore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "Shift+左键 " + ChatColor.RESET + ChatColor.of("#ed0731") + "255");
        redLore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "右键: " + ChatColor.RESET + ChatColor.of("#ed0731") + "-1");
        redLore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "点Q: " + ChatColor.RESET + ChatColor.of("#ed0731") + "-10");
        redLore.add("" + ChatColor.RESET + ChatColor.of("#463D3E") + ChatColor.BOLD + "Shift+右键: " + ChatColor.RESET + ChatColor.of("#ed0731") + "0");
        redMeta.setLore(redLore);
        red.setItemMeta(redMeta);
        this.setButton(21, new Button(red) {
            public void onClick(InventoryClickEvent e) {
                LetterData ldt = mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.getCurrentLetter();
                if (e.getClick().equals(ClickType.LEFT)) {
                    ldt.getColor().addRed(1);
                } else if (e.getClick().equals(ClickType.MIDDLE)) {
                    ldt.getColor().addRed(10);
                } else if (e.getClick().equals(ClickType.DROP)) {
                    ldt.getColor().addRed(-10);
                } else if (e.getClick().equals(ClickType.RIGHT)) {
                    ldt.getColor().addRed(-1);
                } else if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    ldt.getColor().setRed(255);
                } else if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    ldt.getColor().setRed(0);
                }

                Util.playSound(p, "colorModify");
                new mc233.fun.colorfulcolors.GUI.Global.LetterGUI(p, 27, mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word);
            }
        });
        ItemStack green = new ItemStack(Material.LIME_CONCRETE);
        ItemMeta greenMeta = green.getItemMeta();
        greenMeta.setDisplayName("" + ChatColor.RESET + ChatColor.BOLD + ChatColor.of("#1ae813") + ldt.getColor().getGreen());
        ArrayList<String> greenLore = new ArrayList();
        greenLore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "左键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "+1");
        greenLore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "中键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "+10");
        greenLore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "Shift+左键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "255");
        greenLore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "右键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "-1");
        greenLore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "点Q: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "-10");
        greenLore.add("" + ChatColor.RESET + ChatColor.of("#3c453e") + ChatColor.BOLD + "Shift+右键: " + ChatColor.RESET + ChatColor.of("#3bd11d") + "0");
        greenMeta.setLore(greenLore);
        green.setItemMeta(greenMeta);
        this.setButton(22, new Button(green) {
            public void onClick(InventoryClickEvent e) {
                LetterData ldt = mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.getCurrentLetter();
                if (e.getClick().equals(ClickType.LEFT)) {
                    ldt.getColor().addGreen(1);
                } else if (e.getClick().equals(ClickType.MIDDLE)) {
                    ldt.getColor().addGreen(10);
                } else if (e.getClick().equals(ClickType.DROP)) {
                    ldt.getColor().addGreen(-10);
                } else if (e.getClick().equals(ClickType.RIGHT)) {
                    ldt.getColor().addGreen(-1);
                } else if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    ldt.getColor().setGreen(255);
                } else if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    ldt.getColor().setGreen(0);
                }

                Util.playSound(p, "colorModify");
                new mc233.fun.colorfulcolors.GUI.Global.LetterGUI(p, 27, mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word);
            }
        });
        ItemStack blue = new ItemStack(Material.LIGHT_BLUE_CONCRETE);
        ItemMeta blueMeta = blue.getItemMeta();
        blueMeta.setDisplayName("" + ChatColor.RESET + ChatColor.BOLD + ChatColor.of("#1d92d1") + ldt.getColor().getBlue());
        ArrayList<String> blueLore = new ArrayList();
        blueLore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "左键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "+1");
        blueLore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + ChatColor.BOLD + "中键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "+10");
        blueLore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "Shift+左键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "255");
        blueLore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "右键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "-1");
        blueLore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "点Q: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "-10");
        blueLore.add("" + ChatColor.RESET + ChatColor.of("#3D4046") + ChatColor.BOLD + "Shift+右键: " + ChatColor.RESET + ChatColor.of("#1d92d1") + "0");
        blueMeta.setLore(blueLore);
        blue.setItemMeta(blueMeta);
        this.setButton(23, new Button(blue) {
            public void onClick(InventoryClickEvent e) {
                LetterData ldt = mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word.getCurrentLetter();
                if (e.getClick().equals(ClickType.LEFT)) {
                    ldt.getColor().addBlue(1);
                } else if (e.getClick().equals(ClickType.MIDDLE)) {
                    ldt.getColor().addBlue(10);
                } else if (e.getClick().equals(ClickType.DROP)) {
                    ldt.getColor().addBlue(-10);
                } else if (e.getClick().equals(ClickType.RIGHT)) {
                    ldt.getColor().addBlue(-1);
                } else if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    ldt.getColor().setBlue(255);
                } else if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    ldt.getColor().setBlue(0);
                }

                Util.playSound(p, "colorModify");
                new mc233.fun.colorfulcolors.GUI.Global.LetterGUI(p, 27, mc233.fun.colorfulcolors.GUI.Global.LetterGUI.this.word);
            }
        });
    }
}
