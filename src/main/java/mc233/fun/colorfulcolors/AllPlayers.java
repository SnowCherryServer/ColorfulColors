package mc233.fun.colorfulcolors;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import mc233.fun.colorfulcolors.AllTemplates;
import mc233.fun.colorfulcolors.DataFiles;
import mc233.fun.colorfulcolors.DataStructures.LetterData;
import mc233.fun.colorfulcolors.DataStructures.PlayerChatColor;
import mc233.fun.colorfulcolors.DataStructures.PlayerData;
import mc233.fun.colorfulcolors.DataStructures.PrismaticColor;
import mc233.fun.colorfulcolors.Util;
import org.bukkit.entity.Player;

import static org.apache.logging.log4j.LogManager.getLogger;

public class AllPlayers {
    private static HashMap<UUID, PlayerData> coloredPlayers = new HashMap();
    private static HashMap<UUID, PlayerChatColor> chatColor = new HashMap();
    private static HashMap<UUID, PlayerData> tempPlayers = new HashMap();

    public AllPlayers() {
    }

    public static synchronized void activateColor(Player p, PlayerData dt) {
        dt.setTarget("");
        if (dt.getTemplate() != null) {
            dt.getTemplate().ActivateTemplate(p, dt);
        } else {
            dt.save();
            if (dt.isGlowing()) {
                dt.startGlow(p);
            } else {
                dt.setPlayerDisplayName(p);
            }
        }

        saveData(p, dt);
    }

    public static synchronized void saveData(Player p, PlayerData pd) {
        coloredPlayers.put(p.getUniqueId(), pd);
    }

    public static synchronized PlayerData getData(Player p) {
        if (coloredPlayers.containsKey(p.getUniqueId())) {
            PlayerData dt = (PlayerData)coloredPlayers.get(p.getUniqueId());
            return dt;
        } else {
            return null;
        }
    }

    public static synchronized boolean exists(Player p) {
        return coloredPlayers.containsKey(p.getUniqueId());
    }

    public static synchronized void removePlayerDataAndColor(Player p) {
        if (coloredPlayers.containsKey(p.getUniqueId())) {
            PlayerData dt = getData(p);
            if (dt.getTemplate() != null) {
                dt.getTemplate().removeTemplate(p, dt);
            }

            if (((PlayerData)coloredPlayers.get(p.getUniqueId())).getTaskId() != null) {
                ((PlayerData)coloredPlayers.get(p.getUniqueId())).getTaskId().cancel();
            }

            dt.removeColor(p);
            coloredPlayers.remove(p.getUniqueId());
        }

    }

    public static synchronized Set<UUID> getKeyset() {
        return coloredPlayers.keySet();
    }

    public static synchronized PlayerData getDataByKey(UUID key) {
        return (PlayerData)coloredPlayers.get(key);
    }

    public static void generateHashMap() {
        loadHashMap();
    }

    public static synchronized boolean hasChatColor(Player p) {
        return chatColor.containsKey(p.getUniqueId());
    }

    public static synchronized PlayerChatColor getChatColor(Player p) {
        return (PlayerChatColor)chatColor.get(p.getUniqueId());
    }

    public static synchronized void setChatColor(Player p, PlayerChatColor playerChatColor) {
        chatColor.put(p.getUniqueId(), playerChatColor);
    }

    public static synchronized void removeChatColor(Player p) {
        chatColor.remove(p.getUniqueId());
    }

    public static synchronized void putInTemp(Player p, PlayerData dt) {
        tempPlayers.put(p.getUniqueId(), dt);
    }

    public static synchronized PlayerData getTempData(Player p) {
        if (existsInTemp(p)) {
            PlayerData dt = (PlayerData)tempPlayers.get(p.getUniqueId());
            return dt;
        } else {
            return null;
        }
    }

    public static synchronized boolean existsInTemp(Player p) {
        return tempPlayers.containsKey(p.getUniqueId());
    }

    public static synchronized void removeFromTemp(Player p) {
        if (tempPlayers.containsKey(p.getUniqueId())) {
            tempPlayers.remove(p.getUniqueId());
        }

    }

    public static synchronized void clearTemp() {
        tempPlayers.clear();
    }

    public static void saveHashMap() {
        Iterator var1 = DataFiles.get().getConfigurationSection("").getKeys(false).iterator();

        while(var1.hasNext()) {
            String key = (String)var1.next();
            DataFiles.get().set(key, (Object)null);
        }

        var1 = coloredPlayers.entrySet().iterator();

        Map.Entry entry;
        while(var1.hasNext()) {
            entry = (Map.Entry)var1.next();
            PlayerData dt = (PlayerData)entry.getValue();
            DataFiles.get().set(entry.getKey() + ".tab", dt.isTab());
            DataFiles.get().set(entry.getKey() + ".glow", dt.isGlowing());
            if (dt.getTemplate() != null) {
                DataFiles.get().set(entry.getKey() + ".template", dt.getTemplate().getTemplateName());
            }

            DataFiles.get().set(entry.getKey() + ".letters", (Object)null);

            for(int i = 0; i < dt.getName().getLetters().size(); ++i) {
                LetterData ldt = (LetterData)dt.getName().getLetters().get(i);
                DataFiles.get().set(entry.getKey() + ".letters." + ldt.getLetter() + i + ".red", ldt.getColor().getRed());
                DataFiles.get().set(entry.getKey() + ".letters." + ldt.getLetter() + i + ".green", ldt.getColor().getGreen());
                DataFiles.get().set(entry.getKey() + ".letters." + ldt.getLetter() + i + ".blue", ldt.getColor().getBlue());
                DataFiles.get().set(entry.getKey() + ".letters." + ldt.getLetter() + i + ".decorations", ldt.getDecorations());
            }
        }

        var1 = chatColor.entrySet().iterator();

        while(var1.hasNext()) {
            entry = (Map.Entry)var1.next();
            DataFiles.get().set(entry.getKey() + ".chatColor.color1", "#" + Integer.toHexString(((PlayerChatColor)entry.getValue()).getColor1().getColorObject().getRGB()).substring(2));
            if (((PlayerChatColor)entry.getValue()).getColor2() != null) {
                DataFiles.get().set(entry.getKey() + ".chatColor.color2", "#" + Integer.toHexString(((PlayerChatColor)entry.getValue()).getColor2().getColorObject().getRGB()).substring(2));
            } else {
                DataFiles.get().set(entry.getKey() + ".chatColor.color2", "");
            }
        }

    }

    public static void loadHashMap() {
        Iterator var1 = DataFiles.get().getConfigurationSection("").getKeys(false).iterator();

        while(true) {
            String key;
            do {
                do {
                    if (!var1.hasNext()) {
                        return;
                    }

                    key = (String)var1.next();
                } while(key == null);

                if (DataFiles.get().contains(key + ".chatColor")) {
                    PlayerChatColor pcc;
                    if (DataFiles.get().contains(key + ".chatColor.color1")) {
                        if (Util.isHex(DataFiles.get().getString(key + ".chatColor.color2")) && Util.isHex(DataFiles.get().getString(key + ".chatColor.color1"))) {
                            pcc = new PlayerChatColor(new PrismaticColor(DataFiles.get().getString(key + ".chatColor.color1")), new PrismaticColor(DataFiles.get().getString(key + ".chatColor.color2")));
                            chatColor.put(UUID.fromString(key), pcc);
                        } else if (Util.isHex(DataFiles.get().getString(key + ".chatColor.color1"))) {
                            pcc = new PlayerChatColor(new PrismaticColor(DataFiles.get().getString(key + ".chatColor.color1")));
                            chatColor.put(UUID.fromString(key), pcc);
                        }
                    } else if (DataFiles.get().getString(key + ".chatColor") != null) {
                        pcc = new PlayerChatColor(new PrismaticColor(DataFiles.get().getString(key + ".chatColor")));
                        chatColor.put(UUID.fromString(key), pcc);
                    }
                }
            } while(!DataFiles.get().contains(key + ".letters"));

            PlayerData dt = new PlayerData();
            dt.setTab(DataFiles.get().getBoolean(key + ".tab"));
            dt.setGlowing(DataFiles.get().getBoolean(key + ".glow"));
            if (DataFiles.get().contains(key + ".template")) {
                String templateName = DataFiles.get().getString(key + ".template");
                if (AllTemplates.exists(templateName)) {
                    dt.setTemplate(AllTemplates.getTemplate(templateName));
                }
            }

            dt.getName().setCurrentLetterNum(0);

            try {
                int i = 0;
                String name = "";
                Set<String> tempkey2 = null;
                tempkey2 = DataFiles.get().getConfigurationSection(key + ".letters").getKeys(false);
                if (tempkey2 != null) {
                    for(Iterator var7 = DataFiles.get().getConfigurationSection(key + ".letters").getKeys(false).iterator(); var7.hasNext(); ++i) {
                        String key2 = (String)var7.next();
                        LetterData ldt = new LetterData(key2.charAt(0));
                        ldt.getColor().setRed(DataFiles.get().getInt(key + ".letters." + key2 + ".red"));
                        ldt.getColor().setGreen(DataFiles.get().getInt(key + ".letters." + key2 + ".green"));
                        ldt.getColor().setBlue(DataFiles.get().getInt(key + ".letters." + key2 + ".blue"));
                        String decor = DataFiles.get().getString(key + ".letters." + key2 + ".decorations");
                        if (decor != null) {
                            if (decor.contains("&l")) {
                                ldt.setBold(true);
                            }

                            if (decor.contains("&m")) {
                                ldt.setStrikethrough(true);
                            }

                            if (decor.contains("&n")) {
                                ldt.setUnderline(true);
                            }

                            if (decor.contains("&o")) {
                                ldt.setItalic(true);
                            }
                        }

                        dt.getName().getLetters().put(i, ldt);
                        name = name + ldt.getLetter();
                    }
                }

                dt.getName().setWordString(name);
                dt.getName().setLength(dt.getName().getLetters().size());
                dt.save();
            } catch (NullPointerException var10) {
                getLogger().info("加载插件的时候出现了一个错误");
            }

            coloredPlayers.put(UUID.fromString(key), dt);
        }
    }
}
