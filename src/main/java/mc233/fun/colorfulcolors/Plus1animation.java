package mc233.fun.colorfulcolors;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import mc233.fun.colorfulcolors.AbstractTemplate;
import mc233.fun.colorfulcolors.DataStructures.LetterData;
import mc233.fun.colorfulcolors.DataStructures.PlayerData;
import mc233.fun.colorfulcolors.ColorfulColors;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

class Plus1animation extends AbstractTemplate {
    public Plus1animation(String name, Material material, String displayName, ArrayList<String> lore, Color[] colors, boolean tab) {
        this.setTemplateName(name);
        this.colors = colors;
        this.setGlow(false);
        this.setTab(tab);
        this.setAnimated(true);
        this.setGUIIcon(material, displayName, lore);
        this.addToTemplateMap();
        this.setRarity(3);
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
        this.animateTask(p, dt, colorsamount);
    }

    void animateTask(final Player p, final PlayerData dt, final int colorsamount) {
        dt.setTaskId(Bukkit.getScheduler().runTaskTimerAsynchronously(mc233.fun.colorfulcolors.ColorfulColors.plugin, new Runnable() {
            int outercolor = 0;
            int innercolor = 0;
            String coloredName = "";

            public void run() {
                this.coloredName = "";

                for(int j = 0; j < dt.getName().getLetters().size(); ++j) {
                    LetterData ldt = (LetterData)dt.getName().getLetters().get(j);
                    if (this.innercolor >= colorsamount) {
                        this.innercolor = 0;
                    }

                    int red = mc233.fun.colorfulcolors.Plus1animation.this.colors[this.innercolor].getRed();
                    int green = mc233.fun.colorfulcolors.Plus1animation.this.colors[this.innercolor].getGreen();
                    int blue = mc233.fun.colorfulcolors.Plus1animation.this.colors[this.innercolor].getBlue();
                    Color letterColor = new Color(red, green, blue);
                    this.coloredName = this.coloredName + ChatColor.of(letterColor) + ldt.getLetter();
                    ++this.innercolor;
                }

                ++this.outercolor;
                if (this.outercolor >= colorsamount) {
                    this.outercolor = 0;
                }

                this.innercolor = this.outercolor;
                dt.setDisplayname(this.coloredName + ChatColor.RESET);
                Bukkit.getScheduler().runTask(ColorfulColors.plugin, new Runnable() {
                    public void run() {
                        p.setDisplayName(dt.getDisplayname());
                        if (mc233.fun.colorfulcolors.Plus1animation.this.isTab()) {
                            p.setPlayerListName(dt.getDisplayname());
                        } else {
                            p.setPlayerListName(p.getName());
                        }

                    }
                });
            }
        }, 1L, 15L));
    }
}
