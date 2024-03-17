package mc233.fun.colorfulcolors.Commands;


import mc233.fun.colorfulcolors.Commands.CommandInterface;
import mc233.fun.colorfulcolors.DataStructures.RenameData;
import mc233.fun.colorfulcolors.GUI.Global.LetterGUI;
import mc233.fun.colorfulcolors.TempData;
import mc233.fun.colorfulcolors.Util;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRename implements CommandInterface {
    public CommandRename() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (p.hasPermission("colorfulcolors.rename")) {
                if (args.length >= 2) {
                    if (p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType() != Material.AIR) {
                        String name = "";

                        for(int i = 1; i < args.length; ++i) {
                            name = name + args[i] + " ";
                        }

                        name = name.trim();
                        RenameData rd = new RenameData(name, p.getInventory().getItemInMainHand());
                        TempData.putRenameData(p, rd);
                        new LetterGUI(p, 27, rd.getName());
                    } else {
                        Util.sendMessage(p, "holdItem");
                    }
                } else {
                    Util.sendMessage(p, "specifyName");
                }
            } else {
                Util.sendMessage(p, "noPermRename");
            }
        }

        return true;
    }
}
