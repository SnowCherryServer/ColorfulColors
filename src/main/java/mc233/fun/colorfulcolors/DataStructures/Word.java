package mc233.fun.colorfulcolors.DataStructures;


import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mc233.fun.colorfulcolors.DataStructures.LetterData;
import net.md_5.bungee.api.ChatColor;

public class Word {
    private String wordString;
    private int currentLetterNum;
    private HashMap<Integer, mc233.fun.colorfulcolors.DataStructures.LetterData> letters = new HashMap();
    private int length;
    private int type;

    public Word(String word, int type) {
        this.wordString = word;

        for(int i = 0; i < word.length(); ++i) {
            mc233.fun.colorfulcolors.DataStructures.LetterData ldt = new mc233.fun.colorfulcolors.DataStructures.LetterData(word.charAt(i));
            this.letters.put(i, ldt);
        }

        this.length = this.letters.size();
        this.type = type;
    }

    public Word(mc233.fun.colorfulcolors.DataStructures.Word otherWord) {
        this.wordString = otherWord.getWordString();
        Iterator var2 = otherWord.getLetters().entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<Integer, mc233.fun.colorfulcolors.DataStructures.LetterData> entry = (Map.Entry)var2.next();
            this.letters.put((Integer)entry.getKey(), new mc233.fun.colorfulcolors.DataStructures.LetterData((mc233.fun.colorfulcolors.DataStructures.LetterData)entry.getValue()));
        }

        this.length = this.letters.size();
        this.type = otherWord.type;
    }

    public Word() {
    }

    public String getGlow(int i) {
        String glowyVersion = "";
        double frequency = 0.4;
        double jfrequency = 0.4;
        int amplitude = 35;

        for(int j = 0; j < this.letters.size(); ++j) {
            mc233.fun.colorfulcolors.DataStructures.LetterData ldt = (mc233.fun.colorfulcolors.DataStructures.LetterData)this.letters.get(j);
            int red = (int)(Math.sin(frequency * (double)i + jfrequency * (double)j) * (double)amplitude + (double)ldt.getColor().getRed());
            int green = (int)(Math.sin(frequency * (double)i + jfrequency * (double)j) * (double)amplitude + (double)ldt.getColor().getGreen());
            int blue = (int)(Math.sin(frequency * (double)i + jfrequency * (double)j) * (double)amplitude + (double)ldt.getColor().getBlue());
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

            Color letterColor = new Color(red, green, blue);
            glowyVersion = glowyVersion + ChatColor.of(letterColor) + ChatColor.translateAlternateColorCodes('&', ldt.getDecorations() + ldt.getLetter());
        }

        return glowyVersion;
    }

    public String getColoredWord() {
        String word = "";

        Map.Entry entry;
        for(Iterator var2 = this.letters.entrySet().iterator(); var2.hasNext(); word = word + ((mc233.fun.colorfulcolors.DataStructures.LetterData)entry.getValue()).getColoredLetter()) {
            entry = (Map.Entry)var2.next();
        }

        return word;
    }

    public mc233.fun.colorfulcolors.DataStructures.LetterData getCurrentLetter() {
        return (mc233.fun.colorfulcolors.DataStructures.LetterData)this.letters.get(this.currentLetterNum);
    }

    public HashMap<Integer, mc233.fun.colorfulcolors.DataStructures.LetterData> getLetters() {
        return this.letters;
    }

    public int getCurrentLetterNum() {
        return this.currentLetterNum;
    }

    public void setCurrentLetterNum(int currentLetterNum) {
        if (this.letters.get(currentLetterNum) != null && ((LetterData)this.letters.get(currentLetterNum)).getLetter() == ' ') {
            if (this.currentLetterNum > currentLetterNum) {
                --currentLetterNum;
                this.setCurrentLetterNum(currentLetterNum);
            }

            if (this.currentLetterNum < currentLetterNum) {
                ++currentLetterNum;
                this.setCurrentLetterNum(currentLetterNum);
            }
        } else {
            this.currentLetterNum = currentLetterNum;
        }

    }

    public String getWordString() {
        return this.wordString;
    }

    public void setWordString(String word) {
        this.wordString = word;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getType() {
        return this.type;
    }
}
