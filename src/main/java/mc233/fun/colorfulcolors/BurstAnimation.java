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
import mc233.fun.colorfulcolors.Util;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

class BurstAnimation extends AbstractTemplate {
    public BurstAnimation(String name, Material material, String displayName, ArrayList<String> lore, Color[] colors, boolean tab) {
        super();
        setTemplateName(name);
        this.colors = colors;
        setGlow(false);
        setTab(tab);
        setAnimated(true);
        setGUIIcon(material, displayName, lore);
        addToTemplateMap();
        setRarity(5);
    }

    @Override
    void ActivateTemplate(Player p, PlayerData dt) {
        resetPlayerData(dt);
        dt.setTab(isTab());
        dt.setTemplate(this);
        animateTask(p, dt, this.colors.length);
    }

    private void resetPlayerData(PlayerData dt) {
        Iterator var4 = dt.getName().getLetters().entrySet().iterator();
        while(var4.hasNext()) {
            Map.Entry<Integer, LetterData> i = (Map.Entry)var4.next();
            LetterData ldt = (LetterData)i.getValue();
            ldt.reset();
        }
    }

    private void animateTask(final Player p, final PlayerData dt, final int colorsamount) {
        Random ran = new Random();
        final int randompos = ran.nextInt(dt.getName().getLetters().size());
        final Color color = this.colors[Util.getRandomInt(1, this.colors.length)];
        final float percent = 1.0F;
        dt.setTaskId(Bukkit.getScheduler().runTaskTimerAsynchronously(ColorfulColors.plugin, new Runnable() {
            float indivdualpercent = percent;

            public void run() {
                miniAnimation(p, dt, randompos, this.indivdualpercent, color);
                this.indivdualpercent = (float)((double)this.indivdualpercent - 0.05);
                if ((double)this.indivdualpercent < 0.1) {
                    dt.getTaskId().cancel();
                    animateTask(p, dt, colorsamount);
                }

            }
        }, 1L, 2L));
    }

    private void miniAnimation(final Player p, final PlayerData dt, int pos, float percent, Color color) {
        String coloredName = "";
        coloredName = "";

        for(int j = 0; j < dt.getName().getLetters().size(); ++j) {
            float realpercent = percent;
            if (pos > j) {
                realpercent = (float)((double)percent - (double)(pos - j) * 0.1);
            } else if (pos < j) {
                realpercent = (float)((double)percent + (double)(pos - j) * 0.1);
            }

            Color letterColor = getColor(realpercent, color);
            coloredName = coloredName + ChatColor.of(letterColor) + dt.getName().getWordString().charAt(j);
        }

        dt.setDisplayname(coloredName + ChatColor.RESET);
        Bukkit.getScheduler().runTask(ColorfulColors.plugin, new Runnable() {
            public void run() {
                p.setDisplayName(dt.getDisplayname());
                if (isTab()) {
                    p.setPlayerListName(dt.getDisplayname());
                } else {
                    p.setPlayerListName(p.getName());
                }

            }
        });
    }

    private Color getColor(float realpercent, Color color) {
        int red = (int)((float)color.getRed() * realpercent + (float)this.colors[0].getRed() * (1.0F - realpercent));
        int green = (int)((float)color.getGreen() * realpercent + (float)this.colors[0].getGreen() * (1.0F - realpercent));
        int blue = (int)((float)color.getBlue() * realpercent + (float)this.colors[0].getBlue() * (1.0F - realpercent));
        if (red > 255) {
            red = 255;
        }

        if (red < 0) {
            red = 0;
        }

        if (green > 255) {
            green = 255;
        }

        if (green < 0) {
            green = 0;
        }

        if (blue > 255) {
            blue = 255;
        }

        if (blue < 0) {
            blue = 0;
        }

        return new Color(red, green, blue);
    }

    void preAnimation(final Player p, final PlayerData dt, int pos, float percent) {
        String coloredName = "";
        coloredName = "";
        float realpercent = percent;

        for(int j = 0; j < dt.getName().getLetters().size(); ++j) {
            Color letterColor;
            if (j == pos) {
                letterColor = getColor(realpercent, this.colors[1]);
            } else {
                letterColor = this.colors[0];
            }

            coloredName = coloredName + ChatColor.of(letterColor) + dt.getName().getWordString().charAt(j);
        }

        dt.setDisplayname(coloredName + ChatColor.RESET);
        Bukkit.getScheduler().runTask(ColorfulColors.plugin, new Runnable() {
            public void run() {
                p.setDisplayName(dt.getDisplayname());
                if (isTab()) {
                    p.setPlayerListName(dt.getDisplayname());
                } else {
                    p.setPlayerListName(p.getName());
                }

            }
        });
    }
}
