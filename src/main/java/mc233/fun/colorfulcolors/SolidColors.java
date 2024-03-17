package mc233.fun.colorfulcolors;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import mc233.fun.colorfulcolors.AbstractTemplate;
import mc233.fun.colorfulcolors.DataStructures.LetterData;
import mc233.fun.colorfulcolors.DataStructures.PlayerData;
import org.bukkit.Material;
import org.bukkit.entity.Player;

class SolidColors extends AbstractTemplate {
    public SolidColors(String name, Material material, String displayName, ArrayList<String> lore, Color[] colors, boolean glow, boolean tab) {
        this.setTemplateName(name);
        this.colors = colors;
        this.setGlow(glow);
        this.setTab(tab);
        this.setGUIIcon(material, displayName, lore);
        this.addToTemplateMap();
    }

    void ActivateTemplate(Player p, PlayerData dt) {
        dt.removeColor(p);
        Iterator var4 = dt.getName().getLetters().entrySet().iterator();

        while(var4.hasNext()) {
            Map.Entry<Integer, LetterData> i = (Map.Entry)var4.next();
            LetterData ldt = (LetterData)i.getValue();
            ldt.getColor().setRed(this.colors[0].getRed());
            ldt.getColor().setGreen(this.colors[0].getGreen());
            ldt.getColor().setBlue(this.colors[0].getBlue());
            ldt.setBold(false);
            ldt.setStrikethrough(false);
            ldt.setItalic(false);
            ldt.setUnderline(false);
        }

        dt.setGlowing(this.isGlow());
        dt.setTab(this.isTab());
        dt.save();
        if (dt.isGlowing()) {
            dt.startGlow(p);
        } else {
            dt.setPlayerDisplayName(p);
            dt.setPlayerDisplayName(p);
        }

        dt.setTemplate(this);
    }
}
