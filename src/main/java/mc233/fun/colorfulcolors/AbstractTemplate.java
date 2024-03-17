package mc233.fun.colorfulcolors;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mc233.fun.colorfulcolors.AllTemplates;
import mc233.fun.colorfulcolors.DataStructures.LetterData;
import mc233.fun.colorfulcolors.DataStructures.PlayerData;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

public abstract class AbstractTemplate implements Comparable<mc233.fun.colorfulcolors.AbstractTemplate> {
    public Color[] colors;
    private String templateName;
    private ItemStack icon;
    public ArrayList<String> lore;
    private boolean glow;
    private boolean tab;
    private boolean animated;
    private Integer rarity;

    public AbstractTemplate() {
        this.icon = new ItemStack(Material.BARRIER);
        this.lore = new ArrayList();
        this.glow = false;
        this.tab = false;
        this.animated = false;
        this.rarity = 0;
    }

    public void setGUIIcon(Material material, String displayName, ArrayList<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        List<String> moreLore = meta.getLore();
        moreLore.add("");
        moreLore.add(ChatColor.DARK_GRAY + "[" + ChatColor.BOLD + ChatColor.YELLOW + "+" + ChatColor.DARK_GRAY + "] " + ChatColor.of("#666666") + this.colors.length + " 颜色");
        if (this.isTab()) {
            moreLore.add(ChatColor.DARK_GRAY + "[" + ChatColor.BOLD + ChatColor.YELLOW + "+" + ChatColor.DARK_GRAY + "] " + ChatColor.of("#666666") + "tab");
        }

        if (this.isGlow()) {
            moreLore.add(ChatColor.DARK_GRAY + "[" + ChatColor.BOLD + ChatColor.YELLOW + "+" + ChatColor.DARK_GRAY + "] " + ChatColor.of("#666666") + "发光");
        }

        if (this.isAnimated()) {
            moreLore.add(ChatColor.DARK_GRAY + "[" + ChatColor.BOLD + ChatColor.YELLOW + "+" + ChatColor.DARK_GRAY + "]" + ChatColor.of("#f0d71f") + " 动态");
        }

        meta.setLore(moreLore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_POTION_EFFECTS});
        meta.setLocalizedName(this.getTemplateName());
        item.setItemMeta(meta);
        this.icon = item;
    }

    public void addToTemplateMap() {
        synchronized(AllTemplates.getAllTemplates()) {
            AllTemplates.getTemplatesList().add(this);
        }
    }

    public ItemStack getIcon() {
        return this.icon;
    }

    public void removeTemplate(Player p, PlayerData dt) {
        if (dt.getTaskId() != null) {
            dt.getTaskId().cancel();
            dt.setTaskId((BukkitTask)null);
        }

        dt.setTab(false);
        dt.setGlowing(false);
        Iterator var4 = dt.getName().getLetters().entrySet().iterator();

        while(var4.hasNext()) {
            Map.Entry<Integer, LetterData> i = (Map.Entry)var4.next();
            LetterData ldt = (LetterData)i.getValue();
            ldt.getColor().setRed(255);
            ldt.getColor().setGreen(255);
            ldt.getColor().setBlue(255);
        }

        dt.setTemplate((mc233.fun.colorfulcolors.AbstractTemplate)null);
        dt.removeColor(p);
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Integer getRarity() {
        return this.rarity;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public boolean isGlow() {
        return this.glow;
    }

    public void setGlow(boolean glow) {
        this.glow = glow;
    }

    public boolean isTab() {
        return this.tab;
    }

    public void setTab(boolean tab) {
        this.tab = tab;
    }

    public boolean isAnimated() {
        return this.animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public int compareTo(mc233.fun.colorfulcolors.AbstractTemplate o) {
        return this.getRarity().compareTo(o.getRarity());
    }

    abstract void ActivateTemplate(Player var1, PlayerData var2);
}

