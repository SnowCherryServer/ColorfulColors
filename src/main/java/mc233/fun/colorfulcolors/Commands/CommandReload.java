package mc233.fun.colorfulcolors.Commands;


import mc233.fun.colorfulcolors.*;
import mc233.fun.colorfulcolors.Commands.CommandInterface;
import mc233.fun.colorfulcolors.Commands.CommandSet;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandReload implements CommandInterface {
    public CommandReload() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (p.hasPermission("colorfulcolors.admin.reload")) {
                hexInput.clearList();
                TempData.clearRename();
                Util.closeGUI();
                Util.cancelTask();
                AllPlayers.clearTemp();
                AllPlayers.saveHashMap();
                DataFiles.savefile();
                AllTemplates.initiate();
                DataFiles.setup();
                DataFiles.setupMessages();
                DataFiles.setupConfig();
                DataFiles.compareMessages();
                DataFiles.compareConfig();
                CommandSet.maxChar = DataFiles.getConfig().getInt("max-nick-length");
                CommandSet.minChar = DataFiles.getConfig().getInt("min-nick-length");
                AllPlayers.loadHashMap();
                Util.loadMessages();
                Util.startTask();
                Util.sendMessage(p, "pluginReloaded");
            } else {
                Util.sendMessage(p, "noPermReload");
            }

            return true;
        } else if (sender.hasPermission("colorfulcolors.admin.reload")) {
            hexInput.clearList();
            TempData.clearRename();
            Util.closeGUI();
            Util.cancelTask();
            AllPlayers.clearTemp();
            AllPlayers.saveHashMap();
            DataFiles.savefile();
            AllTemplates.initiate();
            DataFiles.setup();
            DataFiles.setupMessages();
            DataFiles.setupConfig();
            DataFiles.compareMessages();
            DataFiles.compareConfig();
            CommandSet.maxChar = DataFiles.getConfig().getInt("max-nick-length");
            CommandSet.minChar = DataFiles.getConfig().getInt("min-nick-length");
            AllPlayers.loadHashMap();
            Util.loadMessages();
            Util.startTask();
            sender.sendMessage("刷新成功");
            return true;
        } else {
            return false;
        }
    }
}
