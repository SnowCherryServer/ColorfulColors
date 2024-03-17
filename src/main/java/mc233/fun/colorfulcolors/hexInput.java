package mc233.fun.colorfulcolors;


import java.util.HashMap;
import java.util.UUID;
import mc233.fun.colorfulcolors.DataStructures.Word;
import org.bukkit.entity.Player;

public class hexInput {
    private static HashMap<UUID, Word> hexInput = new HashMap();

    public hexInput() {
    }

    public static synchronized boolean exists(Player p) {
        return hexInput.containsKey(p.getUniqueId());
    }

    public static synchronized void removePlayer(Player p) {
        if (hexInput.containsKey(p.getUniqueId())) {
            hexInput.remove(p.getUniqueId());
        }

    }

    public static synchronized Word getData(Player p) {
        return hexInput.containsKey(p.getUniqueId()) ? (Word)hexInput.get(p.getUniqueId()) : null;
    }

    public static synchronized void addPlayer(Player p, Word word) {
        hexInput.put(p.getUniqueId(), word);
    }

    public static synchronized void clearList() {
        hexInput.clear();
    }
}
