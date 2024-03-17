package mc233.fun.colorfulcolors;

import java.awt.Color;
import net.md_5.bungee.api.ChatColor;

public class GradientMaker {
    public GradientMaker() {
    }

    public static ChatColor[] getGradient(Color from, Color to) {
        int max = 20;
        ChatColor[] colors = new ChatColor[max * 2];

        for(int i = 0; i < max; ++i) {
            int r = from.getRed() + i * (to.getRed() - from.getRed()) / (max - 1);
            int g = from.getGreen() + i * (to.getGreen() - from.getGreen()) / (max - 1);
            int b = from.getBlue() + i * (to.getBlue() - from.getBlue()) / (max - 1);
            colors[i] = ChatColor.of(new Color(r, g, b));
            colors[colors.length - i - 1] = ChatColor.of(new Color(r, g, b));
        }

        return colors;
    }
}
