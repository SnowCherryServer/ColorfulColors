package mc233.fun.colorfulcolors;

import mc233.fun.colorfulcolors.Commands.*;
import mc233.fun.colorfulcolors.GUI.GUIListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ColorfulColors extends JavaPlugin {
    public static ColorfulColors plugin;
    public static Constants constants;

    public void registerCommands() {
        CommandHandler handler = new CommandHandler();
        handler.register("colorfulcolors", new CommandBase());
        handler.register("help", new CommandBase());
        handler.register("set", new CommandSet());
        handler.register("edit", new CommandEdit());
        handler.register("rename", new CommandRename());
        handler.register("chat", new CommandChat());
        handler.register("template", new CommandTemplate());
        handler.register("remove", new CommandRemove());
        handler.register("reload", new CommandReload());
        this.getCommand("colorfulcolors").setExecutor(handler);
        this.getCommand("rename").setExecutor(new CommandRenameNative());
    }

    public void onEnable() {
        getLogger().info("插件已加载");
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        if (Integer.parseInt(version.split("_")[1]) < 16) {
            getLogger().info("不被支持的版本，请使用1.16及以上！");
            getLogger().info("正在关闭插件...");
            Bukkit.getServer().getPluginManager().disablePlugin(this);
        }

        plugin = this;
        plugin.getDataFolder().mkdir();
        AllTemplates.initiate();
        DataFiles.setupAll();
        AllPlayers.loadHashMap();
        Bukkit.getPluginManager().registerEvents(new GUIListener(), this);
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        Util.loadMessages();
        Util.startTask();
        this.registerCommands();
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            (new PcAPI(this)).register();
        }

        constants = new Constants();
    }

    public void onDisable() {
        getLogger().info("插件已关闭");
        hexInput.clearList();
        TempData.clearRename();
        Util.closeGUI();
        Util.cancelTask();
        AllPlayers.clearTemp();
        AllPlayers.saveHashMap();
        DataFiles.savefile();
    }
}
