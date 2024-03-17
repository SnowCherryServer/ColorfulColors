package mc233.fun.colorfulcolors.DataStructures;

import mc233.fun.colorfulcolors.DataStructures.PrismaticColor;
import net.md_5.bungee.api.ChatColor;

public class LetterData {
    private char letter;
    private boolean bold;
    private boolean underline;
    private boolean strikethrough;
    private boolean italic;
    private PrismaticColor color;
    private String decorations;

    public PrismaticColor getColor() {
        return this.color;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }

    public void setStrikethrough(boolean strikethrough) {
        this.strikethrough = strikethrough;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public void setDecorations(String decorations) {
        this.decorations = decorations;
    }

    public LetterData(LetterData odt) {
        this.setLetter(odt.getLetter());
        this.bold = odt.isBold();
        this.strikethrough = odt.isStrikethrough();
        this.underline = odt.isUnderline();
        this.italic = odt.isItalic();
        this.decorations = odt.decorations;
        this.color = new PrismaticColor(odt.getColor());
    }

    public LetterData(char letter) {
        this.letter = letter;
        this.decorations = "";
        this.color = new PrismaticColor();
    }

    public char getLetter() {
        return this.letter;
    }

    public String getColoredLetter() {
        this.decorations = "";
        if (this.bold) {
            this.decorations = String.valueOf(this.decorations) + "&l";
        }

        if (this.strikethrough) {
            this.decorations = String.valueOf(this.decorations) + "&m";
        }

        if (this.underline) {
            this.decorations = String.valueOf(this.decorations) + "&n";
        }

        if (this.italic) {
            this.decorations = String.valueOf(this.decorations) + "&o";
        }

        return ChatColor.of(this.color.getColorObject()) + ChatColor.translateAlternateColorCodes('&', String.valueOf(this.decorations) + this.getLetter());
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public boolean isBold() {
        return this.bold;
    }

    public boolean isUnderline() {
        return this.underline;
    }

    public boolean isStrikethrough() {
        return this.strikethrough;
    }

    public boolean isItalic() {
        return this.italic;
    }

    public String getDecorations() {
        return this.decorations;
    }
    public void reset() {
        this.letter = '\0'; // reset to null character
        this.bold = false;
        this.underline = false;
        this.strikethrough = false;
        this.italic = false;
        this.decorations = "";
        this.color = new PrismaticColor(); // reset to default color
    }

}
