package mc233.fun.colorfulcolors;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import mc233.fun.colorfulcolors.AbstractTemplate;
import mc233.fun.colorfulcolors.DataStructures.LetterData;
import mc233.fun.colorfulcolors.DataStructures.PlayerData;
import mc233.fun.colorfulcolors.ColorfulColors;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

class RandomAnimation extends AbstractTemplate {
    Color mainColor;

    public RandomAnimation(int rarity, String name, Material material, String displayName, ArrayList<String> lore, Color mainColor, Color[] colors, boolean tab) {
        this.setTemplateName(name);
        this.colors = colors;
        this.setGlow(false);
        this.setTab(tab);
        this.setAnimated(true);
        this.mainColor = mainColor;
        this.setGUIIcon(material, displayName, lore);
        this.addToTemplateMap();
        this.setRarity(rarity);
    }

    void ActivateTemplate(Player p, PlayerData dt) {
        int colorsamount = this.colors.length;
        Iterator var5 = dt.getName().getLetters().entrySet().iterator();

        while(var5.hasNext()) {
            Map.Entry<Integer, LetterData> i = (Map.Entry)var5.next();
            LetterData ldt = (LetterData)i.getValue();
            ldt.getColor().setRed(255);
            ldt.getColor().setGreen(255);
            ldt.getColor().setBlue(255);
            ldt.setBold(false);
            ldt.setStrikethrough(false);
            ldt.setItalic(false);
            ldt.setUnderline(false);
        }

        dt.setTab(this.isTab());
        dt.setTemplate(this);
        this.animateTask(p, dt, colorsamount, dt.getName().getLetters().size());
    }

    void animateTask(final Player p, final PlayerData dt, final int colorsamount, final int nameLength) {
        final Random ran = new Random();
        dt.setTaskId(Bukkit.getScheduler().runTaskTimerAsynchronously(ColorfulColors.plugin, new Runnable() {
            public void run() {
                String coloredName = "";
                coloredName = "";

                for(int j = 0; j < dt.getName().getLetters().size(); ++j) {
                    int shouldcolor = ran.nextInt(nameLength * 2);
                    int red;
                    int green;
                    int blue;
                    if (shouldcolor < nameLength / 2 - 1) {
                        int randompos = ran.nextInt(colorsamount);
                        red = mc233.fun.colorfulcolors.RandomAnimation.this.colors[randompos].getRed();
                        green = mc233.fun.colorfulcolors.RandomAnimation.this.colors[randompos].getGreen();
                        blue = mc233.fun.colorfulcolors.RandomAnimation.this.colors[randompos].getBlue();
                    } else {
                        red = mc233.fun.colorfulcolors.RandomAnimation.this.mainColor.getRed();
                        green = mc233.fun.colorfulcolors.RandomAnimation.this.mainColor.getGreen();
                        blue = mc233.fun.colorfulcolors.RandomAnimation.this.mainColor.getBlue();
                    }

                    LetterData ldt = (LetterData)dt.getName().getLetters().get(j);
                    Color letterColor = new Color(red, green, blue);
                    coloredName = coloredName + ChatColor.of(letterColor) + ldt.getLetter();
                }

                dt.setDisplayname(coloredName + ChatColor.RESET);
                Bukkit.getScheduler().runTask(ColorfulColors.plugin, new Runnable() {
                    public void run() {
                        p.setDisplayName(dt.getDisplayname());
                        if (mc233.fun.colorfulcolors.RandomAnimation.this.isTab()) {
                            p.setPlayerListName(dt.getDisplayname());
                        } else {
                            p.setPlayerListName(p.getName());
                        }

                    }
                });
            }
        }, 1L, 30L));
    }
}
