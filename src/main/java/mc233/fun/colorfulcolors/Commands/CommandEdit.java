package mc233.fun.colorfulcolors.Commands;


import mc233.fun.colorfulcolors.AllPlayers;
import mc233.fun.colorfulcolors.Commands.CommandInterface;
import mc233.fun.colorfulcolors.Util;
import mc233.fun.colorfulcolors.DataStructures.PlayerData;
import mc233.fun.colorfulcolors.GUI.PlayerColor.SavingGUI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandEdit implements CommandInterface {
    public CommandEdit() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (p.hasPermission("colorfulcolors.edit")) {
                if (args.length == 2) {
                    if (p.hasPermission("colorfulcolors.admin.editothers")) {
                        if (!args[1].equals("")) {
                            Player otherPlayer = Bukkit.getPlayerExact(args[1]);
                            if (otherPlayer != null) {
                                if (AllPlayers.exists(otherPlayer)) {
                                    PlayerData dt = new PlayerData(AllPlayers.getData(otherPlayer));
                                    dt.setTarget(args[1]);
                                    AllPlayers.putInTemp(p, dt);
                                    new SavingGUI(p, 27, dt);
                                } else {
                                    Util.sendTargetMessage(p, "noColorTarget", args[1]);
                                }
                            } else {
                                Util.sendTargetMessage(p, "couldntFindTarget", args[1]);
                            }
                        }
                    } else {
                        Util.sendTargetMessage(p, "noPermModifyTarget", args[1]);
                    }
                } else if (AllPlayers.exists(p)) {
                    PlayerData dt = new PlayerData(AllPlayers.getData(p));
                    AllPlayers.putInTemp(p, dt);
                    new SavingGUI(p, 27, dt);
                } else {
                    Util.sendMessage(p, "noColor");
                }
            } else {
                Util.sendMessage(p, "noPermEdit");
            }

            return false;
        } else {
            sender.sendMessage("你必须是玩家才可以用！");
            return true;
        }
    }
}
