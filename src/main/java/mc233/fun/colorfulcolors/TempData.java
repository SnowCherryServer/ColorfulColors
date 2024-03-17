package mc233.fun.colorfulcolors;


import java.util.HashMap;
import java.util.UUID;
import mc233.fun.colorfulcolors.DataStructures.RenameData;
import org.bukkit.entity.Player;

public class TempData {
    private static HashMap<UUID, RenameData> renaming = new HashMap();

    public TempData() {
    }

    public static RenameData getRenameData(Player p) {
        return (RenameData)renaming.get(p.getUniqueId());
    }

    public static void removeRenameData(Player p) {
        renaming.remove(p.getUniqueId());
    }

    public static void putRenameData(Player p, RenameData rd) {
        renaming.put(p.getUniqueId(), rd);
    }

    public static boolean existsRename(Player p) {
        return renaming.containsKey(p.getUniqueId());
    }

    public static void clearRename() {
        renaming.clear();
    }
}
