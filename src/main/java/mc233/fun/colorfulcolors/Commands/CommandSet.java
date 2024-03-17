package mc233.fun.colorfulcolors.Commands;


import mc233.fun.colorfulcolors.AllPlayers;
import mc233.fun.colorfulcolors.Commands.CommandInterface;
import mc233.fun.colorfulcolors.DataFiles;
import mc233.fun.colorfulcolors.DataStructures.PlayerData;
import mc233.fun.colorfulcolors.GUI.Global.LetterGUI;
import mc233.fun.colorfulcolors.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSet implements CommandInterface {
    static int maxChar = DataFiles.getConfig().getInt("max-nick-length");
    static int minChar = DataFiles.getConfig().getInt("min-nick-length");

    public CommandSet() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (p.hasPermission("colorfulcolors.set")) {
                PlayerData dt;
                if (args.length == 1) {
                    if (AllPlayers.exists(p)) {
                        Util.sendMessage(p, "removeColor");
                        return true;
                    }

                    dt = new PlayerData(p.getName());
                    if (p.hasPermission("colorfulcolors.forcetab") && !p.hasPermission("colorfulcolors.allowtab")) {
                        dt.setTab(true);
                    }

                    if (p.hasPermission("colorfulcolors.forceglow") && !p.hasPermission("colorfulcolors.allowglow")) {
                        dt.setGlowing(true);
                    }

                    AllPlayers.putInTemp(p, dt);
                    new LetterGUI(p, 27, dt.getName());
                } else if (args.length == 2) {
                    if (p.hasPermission("colorfulcolors.set.nick")) {
                        if (AllPlayers.exists(p)) {
                            Util.sendMessage(p, "removeColor");
                            return true;
                        }

                        if (args[1].length() > maxChar && !p.hasPermission("colorfulcolors.admin.bypassrestrictions")) {
                            Util.sendMessage(p, "nameTooLong");
                            return true;
                        }

                        if (args[1].length() < minChar && !p.hasPermission("colorfulcolors.admin.bypassrestrictions")) {
                            Util.sendMessage(p, "nameTooShort");
                            return true;
                        }

                        if (Bukkit.getPlayerExact(args[1]) != null && !p.hasPermission("colorfulcolors.admin.bypassrestrictions")) {
                            Util.sendMessage(p, "cantUsePlayerName");
                            return true;
                        }

                        dt = new PlayerData(args[1]);
                        if (p.hasPermission("colorfulcolors.forcetab") && !p.hasPermission("colorfulcolors.allowtab")) {
                            dt.setTab(true);
                        }

                        if (p.hasPermission("colorfulcolors.forceglow") && !p.hasPermission("colorfulcolors.allowglow")) {
                            dt.setGlowing(true);
                        }

                        AllPlayers.putInTemp(p, dt);
                        new LetterGUI(p, 27, dt.getName());
                    } else {
                        Util.sendMessage(p, "noPermNick");
                    }
                } else if (args.length >= 3) {
                    if (p.hasPermission("colorfulcolors.admin.setothers")) {
                        Player otherPlayer = Bukkit.getPlayerExact(args[1]);
                        if (otherPlayer == null) {
                            Util.sendTargetMessage(p, "couldntFindTarget", args[1]);
                            return true;
                        }

                        if (args[2].length() > maxChar && !p.hasPermission("colorfulcolors.admin.bypassrestrictions")) {
                            Util.sendMessage(p, "nameTooLong");
                            return true;
                        }

                        if (args[2].length() < minChar && !p.hasPermission("colorfulcolors.admin.bypassrestrictions")) {
                            Util.sendMessage(p, "nameTooShort");
                            return true;
                        }

                        if (Bukkit.getPlayerExact(args[2]) != null && !p.hasPermission("colorfulcolors.admin.bypassrestrictions")) {
                            Util.sendMessage(p, "cantUsePlayerName");
                            return true;
                        }

                        PlayerData dt1 = new PlayerData(args[2]);
                        dt1.setTarget(args[1]);
                        AllPlayers.putInTemp(p, dt1);
                        new LetterGUI(p, 27, dt1.getName());
                    } else {
                        Util.sendTargetMessage(p, "noPermModifyTarget", args[1]);
                    }
                }

                return false;
            } else {
                Util.sendMessage(p, "noPermColor");
                return true;
            }
        } else {
            sender.sendMessage("你必须是玩家才可以！");
            return true;
        }
    }
}
