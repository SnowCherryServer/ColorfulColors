package mc233.fun.colorfulcolors.Commands;


import mc233.fun.colorfulcolors.AllPlayers;
import mc233.fun.colorfulcolors.AllTemplates;
import mc233.fun.colorfulcolors.Commands.CommandInterface;
import mc233.fun.colorfulcolors.DataStructures.PlayerData;
import mc233.fun.colorfulcolors.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTemplate implements CommandInterface {
    public CommandTemplate() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player p;
        if (sender instanceof Player) {
            p = (Player)sender;
            if (p.hasPermission("colorfulcolors.admin.templateothers")) {
                if (args.length == 4) {
                    if (args[1].equalsIgnoreCase("set")) {
                        Player otherPlayer = Bukkit.getPlayerExact(args[2]);
                        if (otherPlayer != null) {
                            PlayerData pd;
                            if (AllPlayers.exists(otherPlayer)) {
                                if (AllTemplates.exists(args[3])) {
                                    pd = AllPlayers.getData(otherPlayer);
                                    pd.setTemplate(AllTemplates.getTemplate(args[3]));
                                    AllPlayers.activateColor(otherPlayer, pd);
                                    Util.sendTargetMessage(p, "templateSetTarget", args[2]);
                                } else {
                                    Util.sendMessage(p, "unknownTemplate");
                                }
                            } else if (AllTemplates.exists(args[3])) {
                                pd = new PlayerData(otherPlayer.getName());
                                pd.setTemplate(AllTemplates.getTemplate(args[3]));
                                AllPlayers.activateColor(otherPlayer, pd);
                                Util.sendTargetMessage(p, "templateSetTarget", args[2]);
                            } else {
                                Util.sendMessage(p, "unknownTemplate");
                            }
                        } else {
                            Util.sendTargetMessage(p, "couldntFindTarget", args[2]);
                        }
                    } else {
                        Util.sendMessage(p, "wrongUsage");
                    }
                } else {
                    Util.sendMessage(p, "wrongUsage");
                }
            }
        } else if (sender.hasPermission("colorfulcolors.admin.templateothers")) {
            if (args.length != 4) {
                sender.sendMessage("请使用这样的指令: /ccs template set <玩家ID> <模板名>");
                return true;
            }

            if (!args[1].equalsIgnoreCase("set")) {
                sender.sendMessage("请使用这样的指令: /ccs template set <玩家ID> <模板名>");
                return true;
            }

            p = Bukkit.getPlayerExact(args[2]);
            if (p == null) {
                sender.sendMessage("无法找到 " + args[2]);
                return true;
            }

            PlayerData pd;
            if (AllPlayers.exists(p)) {
                if (AllTemplates.exists(args[3])) {
                    pd = AllPlayers.getData(p);
                    pd.setTemplate(AllTemplates.getTemplate(args[3]));
                    AllPlayers.activateColor(p, pd);
                    sender.sendMessage("成功设置了 " + args[2] + "的颜色");
                } else {
                    sender.sendMessage("这个模板不存在");
                }
            } else {
                if (!AllTemplates.exists(args[3])) {
                    sender.sendMessage("这个模板不存在");
                    return true;
                }

                pd = new PlayerData(p.getName());
                pd.setTemplate(AllTemplates.getTemplate(args[3]));
                AllPlayers.activateColor(p, pd);
                sender.sendMessage("成功设置了 " + args[2] + "的颜色");
            }
        }

        return false;
    }
}
