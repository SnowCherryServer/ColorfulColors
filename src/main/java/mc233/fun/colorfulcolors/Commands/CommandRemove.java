package mc233.fun.colorfulcolors.Commands;


import mc233.fun.colorfulcolors.AllPlayers;
import mc233.fun.colorfulcolors.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRemove implements CommandInterface {
    public CommandRemove() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player p;
        if (sender instanceof Player) {
            p = (Player)sender;
            if (p.hasPermission("colorfulcolors.remove")) {
                if (args.length == 2) {
                    if (p.hasPermission("colorfulcolors.remove.others")) {
                        Player otherPlayer = Bukkit.getPlayerExact(args[1]);
                        if (otherPlayer == null) {
                            Util.sendTargetMessage(p, "couldntFindTarget", args[1]);
                            return true;
                        }

                        if (!AllPlayers.exists(otherPlayer)) {
                            Util.sendTargetMessage(p, "noColorTarget", args[1]);
                            return true;
                        }

                        AllPlayers.removePlayerDataAndColor(otherPlayer);
                        Util.sendTargetMessage(p, "colorRemovedTarget", args[1]);
                    } else {
                        Util.sendTargetMessage(p, "noPermModifyTarget", args[1]);
                    }

                    return false;
                } else if (AllPlayers.exists(p)) {
                    AllPlayers.removePlayerDataAndColor(p);
                    Util.sendMessage(p, "colorRemoved");
                    return true;
                } else {
                    Util.sendMessage(p, "noColor");
                    return true;
                }
            } else {
                Util.sendMessage(p, "noPermRemove");
                return true;
            }
        } else {
            if (args.length == 2) {
                if (!sender.hasPermission("colorfulcolors.remove.others")) {
                    sender.sendMessage("你没有权限");
                    return true;
                }

                p = Bukkit.getPlayerExact(args[1]);
                if (p == null) {
                    sender.sendMessage("无法找到 " + args[1]);
                    return true;
                }

                if (!AllPlayers.exists(p)) {
                    sender.sendMessage(args[1] + " 没有正在活动的颜色");
                    return true;
                }

                AllPlayers.removePlayerDataAndColor(p);
                sender.sendMessage("成功移除的颜色来自 " + args[1]);
            }

            return false;
        }
    }
}
