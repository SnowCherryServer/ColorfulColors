package mc233.fun.colorfulcolors.DataStructures;


import java.awt.Color;

public class PrismaticColor {
    private int red;
    private int green;
    private int blue;

    public PrismaticColor() {
        this.red = 0;
        this.green = 0;
        this.blue = 0;
    }

    public PrismaticColor(mc233.fun.colorfulcolors.DataStructures.PrismaticColor otherColor) {
        this.red = otherColor.getRed();
        this.green = otherColor.getGreen();
        this.blue = otherColor.getBlue();
    }

    public PrismaticColor(String hexColor) {
        this.hexToRGB(hexColor);
    }

    public void hexToRGB(String hexColor) {
        if (hexColor.length() == 7 && hexColor.charAt(0) == '#') {
            Color color = Color.decode(hexColor);
            this.setRed(color.getRed());
            this.setGreen(color.getGreen());
            this.setBlue(color.getBlue());
        }

    }

    public Color getColorObject() {
        return new Color(this.red, this.green, this.blue);
    }

    public int getRed() {
        return this.red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void addRed(int amount) {
        if (this.red + amount > 255) {
            this.red = 255;
        } else if (this.red + amount < 0) {
            this.red = 0;
        } else {
            this.red += amount;
        }

    }

    public int getGreen() {
        return this.green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void addGreen(int amount) {
        if (this.green + amount > 255) {
            this.green = 255;
        } else if (this.green + amount < 0) {
            this.green = 0;
        } else {
            this.green += amount;
        }

    }

    public int getBlue() {
        return this.blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public void addBlue(int amount) {
        if (this.blue + amount > 255) {
            this.blue = 255;
        } else if (this.blue + amount < 0) {
            this.blue = 0;
        } else {
            this.blue += amount;
        }

    }
}
