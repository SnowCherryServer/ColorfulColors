package mc233.fun.colorfulcolors.GUI;

import mc233.fun.colorfulcolors.GUI.GUI;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GUIHandler {
    private static ConcurrentHashMap<UUID, mc233.fun.colorfulcolors.GUI.GUI> openedGUIs = new ConcurrentHashMap();

    public GUIHandler() {
    }

    public static mc233.fun.colorfulcolors.GUI.GUI getGUI(Player p) {
        return (mc233.fun.colorfulcolors.GUI.GUI)openedGUIs.get(p.getUniqueId());
    }

    public static void putPlayer(Player p, GUI gui) {
        openedGUIs.put(p.getUniqueId(), gui);
    }

    public static void removePlayer(Player p) {
        openedGUIs.remove(p.getUniqueId());
    }
}