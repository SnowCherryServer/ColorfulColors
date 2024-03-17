package mc233.fun.colorfulcolors;


import mc233.fun.colorfulcolors.AllPlayers;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PcAPI extends PlaceholderExpansion {
    private Plugin plugin;

    public PcAPI(Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean persist() {
        return true;
    }

    public boolean canRegister() {
        return true;
    }

    public String getAuthor() {
        return this.plugin.getDescription().getAuthors().toString();
    }

    public String getIdentifier() {
        return "colorfulcolors";
    }

    public String getVersion() {
        return this.plugin.getDescription().getVersion();
    }

    public String onPlaceholderRequest(Player p, String identifier) {
        if (p == null) {
            return "";
        } else if (identifier.equals("chatname")) {
            return mc233.fun.colorfulcolors.AllPlayers.exists(p) ? mc233.fun.colorfulcolors.AllPlayers.getData(p).getDisplayname() : p.getName();
        } else if (!identifier.equals("tabname")) {
            return null;
        } else {
            return mc233.fun.colorfulcolors.AllPlayers.exists(p) && mc233.fun.colorfulcolors.AllPlayers.getData(p).isTab() ? AllPlayers.getData(p).getDisplayname() : p.getName();
        }
    }
}
