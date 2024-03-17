package mc233.fun.colorfulcolors.Commands;


import mc233.fun.colorfulcolors.Commands.CommandInterface;
import mc233.fun.colorfulcolors.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandHandler implements CommandInterface, CommandExecutor {
    private static HashMap<String, CommandInterface> commands = new HashMap();

    public CommandHandler() {
    }

    public void register(String name, CommandInterface cmd) {
        commands.put(name, cmd);
    }

    public boolean exists(String name) {
        return commands.containsKey(name);
    }

    public CommandInterface getExecutor(String name) {
        return (CommandInterface)commands.get(name);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length == 0) {
            this.getExecutor("colorfulcolors").onCommand(sender, cmd, commandLabel, args);
            return true;
        } else if (args.length > 0) {
            if (this.exists(args[0])) {
                this.getExecutor(args[0]).onCommand(sender, cmd, commandLabel, args);
                return true;
            } else {
                if (sender instanceof Player) {
                    Util.sendMessage((Player)sender, "unknownCommand");
                }

                return true;
            }
        } else {
            return false;
        }
    }
}
