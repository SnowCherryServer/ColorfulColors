package mc233.fun.colorfulcolors.Commands;


import mc233.fun.colorfulcolors.AllPlayers;
import mc233.fun.colorfulcolors.Commands.CommandInterface;
import mc233.fun.colorfulcolors.Util;
import mc233.fun.colorfulcolors.DataStructures.PlayerChatColor;
import mc233.fun.colorfulcolors.DataStructures.PrismaticColor;
import mc233.fun.colorfulcolors.GUI.ChatColor.ChatColorOptionsGUI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandChat implements CommandInterface {
    public CommandChat() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player p;
        if (sender instanceof Player) {
            p = (Player)sender;
            if (p.hasPermission("colorfulcolors.chatcolor")) {
                if (args.length > 1) {
                    if (Util.isHex(args[1])) {
                        if (args.length == 2) {
                            AllPlayers.setChatColor(p, new PlayerChatColor(new PrismaticColor(args[1])));
                            Util.sendMessage(p, "chatColorSet");
                        } else if (p.hasPermission("colorfulcolors.chatcolor.gradient")) {
                            if (Util.isHex(args[2])) {
                                AllPlayers.setChatColor(p, new PlayerChatColor(new PrismaticColor(args[1]), new PrismaticColor(args[2])));
                                Util.sendMessage(p, "chatColorSet");
                            } else {
                                Util.sendMessage(p, "invalidHex");
                            }
                        } else {
                            Util.sendMessage(p, "noPermGradientChatColor");
                        }
                    } else {
                        Player otherPlayer = Bukkit.getPlayerExact(args[1]);
                        if (otherPlayer != null) {
                            if (p.hasPermission("colorfulcolors.admin.chatcolorothers")) {
                                if (args.length != 2) {
                                    if (Util.isHex(args[2])) {
                                        if (args.length == 3) {
                                            AllPlayers.setChatColor(otherPlayer, new PlayerChatColor(new PrismaticColor(args[2])));
                                            Util.sendTargetMessage(p, "chatColorSetTarget", args[1]);
                                        } else if (Util.isHex(args[3])) {
                                            AllPlayers.setChatColor(otherPlayer, new PlayerChatColor(new PrismaticColor(args[2]), new PrismaticColor(args[3])));
                                            Util.sendTargetMessage(p, "chatColorSetTarget", args[1]);
                                        } else {
                                            Util.sendMessage(p, "invalidHex");
                                        }
                                    } else {
                                        Util.sendMessage(p, "invalidHex");
                                    }
                                }
                            } else {
                                Util.sendTargetMessage(p, "noPermModifyTarget", args[1]);
                            }
                        } else if (p.hasPermission("colorfulcolors.admin.chatcolorothers")) {
                            Util.sendTargetMessage(p, "couldntFindTarget", args[1]);
                        } else {
                            Util.sendMessage(p, "invalidHex");
                        }
                    }
                } else {
                    new ChatColorOptionsGUI(p, 27);
                }
            } else {
                Util.sendMessage(p, "noPermChatColor");
            }

            return false;
        } else {
            if (sender.hasPermission("colorfulcolors.admin.chatcolorothers")) {
                if (args.length > 2) {
                    p = Bukkit.getPlayerExact(args[1]);
                    if (p != null) {
                        if (args.length == 3) {
                            if (Util.isHex(args[2])) {
                                AllPlayers.setChatColor(p, new PlayerChatColor(new PrismaticColor(args[2])));
                                sender.sendMessage("成功将聊天颜色设置为" + args[1]);
                            } else {
                                sender.sendMessage("未知的16进制颜色");
                            }
                        } else if (args.length == 4) {
                            if (Util.isHex(args[2]) && Util.isHex(args[3])) {
                                AllPlayers.setChatColor(p, new PlayerChatColor(new PrismaticColor(args[2]), new PrismaticColor(args[3])));
                                sender.sendMessage("成功将聊天颜色设置为 " + args[1]);
                            } else {
                                sender.sendMessage("未知的16进制颜色");
                            }
                        } else {
                            sender.sendMessage("错误");
                        }
                    } else {
                        sender.sendMessage("无法找到 " + args[1]);
                    }
                } else {
                    sender.sendMessage("错误");
                }
            } else {
                sender.sendMessage("你没有权限使用");
            }

            return true;
        }
    }
}
