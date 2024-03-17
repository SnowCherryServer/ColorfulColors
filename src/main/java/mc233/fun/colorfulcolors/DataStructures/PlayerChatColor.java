package mc233.fun.colorfulcolors.DataStructures;


import mc233.fun.colorfulcolors.DataStructures.PrismaticColor;
import mc233.fun.colorfulcolors.GradientMaker;
import net.md_5.bungee.api.ChatColor;

public class PlayerChatColor {
    private PrismaticColor color1;
    private PrismaticColor color2;
    private ChatColor[] gradient;
    private String targt;

    public PlayerChatColor(PrismaticColor color1, PrismaticColor color2) {
        this.color1 = color1;
        this.color2 = color2;
        this.setGradient(GradientMaker.getGradient(color1.getColorObject(), color2.getColorObject()));
    }

    public PlayerChatColor(PrismaticColor color1) {
        this.color1 = color1;
    }

    public PrismaticColor getColor1() {
        return this.color1;
    }

    public PrismaticColor getColor2() {
        return this.color2;
    }

    public ChatColor[] getGradient() {
        return this.gradient;
    }

    public void setGradient(ChatColor[] gradient) {
        this.gradient = gradient;
    }
}
