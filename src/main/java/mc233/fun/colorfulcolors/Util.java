package mc233.fun.colorfulcolors;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mc233.fun.colorfulcolors.AllPlayers;
import mc233.fun.colorfulcolors.DataFiles;
import mc233.fun.colorfulcolors.DataStructures.PlayerData;
import mc233.fun.colorfulcolors.GUI.CustomInventory;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Util {
    static Random random = new Random();
    public static final String PREFIX = "&8[#3cb305C#3cbf00o#41cf00l#44d900o#46de00r#4ceb02f#4ef500u#51ff00l#5aff0dC#ad006bo#bd0075l#cf0080o#de0089r#e6028fs#ff009es&8] ";
    private static HashMap<String, String> messages = new HashMap();
    static final Pattern pat = Pattern.compile("#[a-fA-F0-9 ]{6}");

    public Util() {
    }

    public static boolean isHex(String s) {
        String regex = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
        Pattern pat = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
        if (s == null) {
            return false;
        } else {
            Matcher m = pat.matcher(s);
            return m.matches();
        }
    }

    public static int getRandomInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public static void closeGUI() {
        Iterator var1 = Bukkit.getOnlinePlayers().iterator();

        while(var1.hasNext()) {
            Player p = (Player)var1.next();
            if (p.getOpenInventory().getTopInventory().getHolder() instanceof CustomInventory) {
                p.closeInventory();
            }
        }

    }

    public static void cancelTask() {
        Iterator var1 = mc233.fun.colorfulcolors.AllPlayers.getKeyset().iterator();

        while(var1.hasNext()) {
            UUID key = (UUID)var1.next();
            PlayerData dt = mc233.fun.colorfulcolors.AllPlayers.getDataByKey(key);
            if (dt.getTaskId() != null) {
                dt.getTaskId().cancel();
            }
        }

    }

    public static void startTask() {
        Iterator var1 = Bukkit.getOnlinePlayers().iterator();

        while(var1.hasNext()) {
            Player p = (Player)var1.next();
            if (mc233.fun.colorfulcolors.AllPlayers.exists(p)) {
                PlayerData dt = AllPlayers.getData(p);
                if (dt.getTemplate() != null) {
                    dt.getTemplate().ActivateTemplate(p, dt);
                } else if (dt.isGlowing() && dt.getTaskId() == null) {
                    dt.startGlow(p);
                }
            }
        }

    }

    public static void loadMessages() {
        Iterator var1 = mc233.fun.colorfulcolors.DataFiles.getMessages().getConfigurationSection("messages").getKeys(false).iterator();

        while(var1.hasNext()) {
            String key = (String)var1.next();
            String value = DataFiles.getMessages().getString("messages." + key);
            messages.put(key, format("&8[#3cb305C#3cbf00o#41cf00l#44d900o#46de00r#4ceb02f#4ef500u#51ff00l#5aff0dC#ad006bo#bd0075l#cf0080o#de0089r#e6028fs#ff009es&8] " + value));
        }

    }

    public static String format(String str) {
        for(Matcher match = pat.matcher(str); match.find(); match = pat.matcher(str)) {
            String colorStr = str.substring(match.start(), match.end());
            str = str.replace(colorStr, "" + ChatColor.of(colorStr));
        }

        str = ChatColor.translateAlternateColorCodes('&', str);
        return str;
    }

    public static void sendMessage(Player p, String key) {
        if (messages.containsKey(key)) {
            p.sendMessage((String)messages.get(key));
        } else if (p.hasPermission("colorfulcolors.admin.messageError")) {
            p.sendMessage("请在 messages.yml 文件的 messages 部分下添加 " + key + " 条目");
        }

    }

    public static void sendTargetMessage(Player p, String key, String replace) {
        if (messages.containsKey(key)) {
            String msg = (String)messages.get(key);
            if (msg.contains("%target%")) {
                p.sendMessage(msg.replace("%target%", replace));
            } else {
                p.sendMessage(msg);
            }
        } else if (p.hasPermission("colorfulcolors.admin.messageError")) {
            p.sendMessage("请在 messages.yml 文件的 messages 部分下添加 " + key + " 条目");
        }

    }

    public static void playSound(Player p, String action) {
        switch (action) {
            case "applyTemplate":
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_WORK_SHEPHERD, 1.0F, 1.0F);
                break;
            case "remove":
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_WORK_MASON, 1.0F, 1.0F);
                break;
            case "noMorePages":
                p.playSound(p.getLocation(), Sound.BLOCK_WOODEN_BUTTON_CLICK_ON, 1.0F, 1.0F);
                break;
            case "toggle":
                p.playSound(p.getLocation(), Sound.BLOCK_NETHERITE_BLOCK_BREAK, 1.0F, 1.0F);
                break;
            case "save":
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_WORK_FLETCHER, 1.0F, 1.0F);
                break;
            case "error":
                p.playSound(p.getLocation(), Sound.ENTITY_PAINTING_BREAK, 1.0F, 1.0F);
                break;
            case "colorModify":
                p.playSound(p.getLocation(), Sound.BLOCK_SAND_PLACE, 1.0F, 1.0F);
                break;
            case "nextPage":
                p.playSound(p.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_ON, 1.0F, 1.0F);
                break;
            case "templates":
                p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 2.0F, 1.0F);
        }

    }
}
