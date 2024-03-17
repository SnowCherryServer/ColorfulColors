package mc233.fun.colorfulcolors.Commands;


import mc233.fun.colorfulcolors.Commands.CommandInterface;
import mc233.fun.colorfulcolors.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBase implements CommandInterface {
    public CommandBase() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            p.sendMessage(Util.format("&8&m----------------------------------------"));
            p.sendMessage(Util.format("#86f578/ccs set #94E51A- 设置有颜色的名称 #86f578| #94E51A可选参数: #E51A94<昵称> #86f578/ #E51A94<玩家>, <昵称> "));
            p.sendMessage(Util.format("#86f578/ccs edit #94E51A- 编辑您的有颜色的名称 #86f578| #94E51A可选参数: #E51A94<玩家>"));
            p.sendMessage(Util.format("#86f578/ccs chat #E51A94<十六进制颜色> #94E51A- 设置您的聊天颜色 #86f578| #94E51A可选参数: #E51A94<玩家>, <十六进制颜色>"));
            p.sendMessage(Util.format("#86f578/ccs rename #E51A94<新名称> #94E51A- 打开物品重命名的GUI"));
            p.sendMessage(Util.format("#86f578/ccs template set #E51A94<玩家> #E51A94<模板> #94E51A- 为玩家设置模板 "));
            p.sendMessage(Util.format("#86f578/ccs remove #94E51A- 移除您当前的名称 #86f578| #94E51A可选参数: #E51A94<玩家>"));
            p.sendMessage(Util.format("#86f578/ccs reload #94E51A- 重新加载玩家数据和消息"));
            p.sendMessage(Util.format("&8&m----------------------------------------"));
        } else {
            sender.sendMessage(Util.format("&8&m----------------------------------------"));
            sender.sendMessage(Util.format("#86f578/ccs set #94E51A- 设置有颜色的名称 #86f578| #94E51A可选参数: #E51A94<昵称> #86f578/ #E51A94<玩家>, <昵称> "));
            sender.sendMessage(Util.format("#86f578/ccs edit #94E51A- 编辑您的有颜色的名称 #86f578| #94E51A可选参数: #E51A94<玩家>"));
            sender.sendMessage(Util.format("#86f578/ccs chat #E51A94<十六进制颜色> #94E51A- 设置您的聊天颜色 #86f578| #94E51A可选参数: #E51A94<玩家>, <十六进制颜色>"));
            sender.sendMessage(Util.format("#86f578/ccs rename #E51A94<新名称> #94E51A- 打开物品重命名的GUI"));
            sender.sendMessage(Util.format("#86f578/ccs template set #E51A94<玩家> #E51A94<模板> #94E51A- 为玩家设置模板 "));
            sender.sendMessage(Util.format("#86f578/ccs remove #94E51A- 移除您当前的名称 #86f578| #94E51A可选参数: #E51A94<玩家>"));
            sender.sendMessage(Util.format("#86f578/ccs reload #94E51A- 重新加载玩家数据和消息"));
            sender.sendMessage(Util.format("&8&m----------------------------------------"));
        }

        return false;
    }
}
