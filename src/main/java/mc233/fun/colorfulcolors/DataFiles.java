package mc233.fun.colorfulcolors;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import static org.apache.logging.log4j.LogManager.getLogger;

public class DataFiles {
    private static File data;
    private static FileConfiguration modifiedData;
    private static File messages;
    private static SimpleYaml modifiedMessages;
    private static SimpleYaml newmodifiedMessages;
    static FileConfiguration virtogMessages = new YamlConfiguration();
    private static File config;
    private static SimpleYaml modifiedConfig;
    private static SimpleYaml newmodifiedConfig;
    static FileConfiguration virtogConfig = new YamlConfiguration();

    public DataFiles() {
    }

    public static void setup() {
        data = new File(Bukkit.getServer().getPluginManager().getPlugin(ColorfulColors.plugin.getName()).getDataFolder(), "PlayerData.yml");
        if (!data.exists()) {
            try {
                data.createNewFile();
            } catch (IOException var1) {
                getLogger().info("无法创建文件");
                var1.printStackTrace();
            }
        }

        modifiedData = YamlConfiguration.loadConfiguration(data);
    }

    public static FileConfiguration get() {
        return modifiedData;
    }

    public static File get2() {
        return data;
    }

    public static void savefile() {
        try {
            modifiedData.save(data);
        } catch (IOException var1) {
            getLogger().info("无法保存文件");
            var1.printStackTrace();
        }

    }

    public static void setupMessages() {
        messages = new File(Bukkit.getServer().getPluginManager().getPlugin(ColorfulColors.plugin.getName()).getDataFolder(), "messages.yml");
        if (!messages.exists()) {
            ColorfulColors.plugin.saveResource("messages.yml", false);
        }

        modifiedMessages = SimpleYaml.loadConfiguration(messages);
        newmodifiedMessages = new SimpleYaml();
    }

    public static void saveMessagesFile() {
        try {
            modifiedMessages.save(messages);
        } catch (IOException var1) {
            var1.printStackTrace();
            getLogger().info("无法保存语言文件");
        }

    }

    public static FileConfiguration getMessages() {
        return modifiedMessages;
    }

    public static void laodogmessages() {
        Reader targetReader = new InputStreamReader(ColorfulColors.plugin.getResource("messages.yml"));

        try {
            virtogMessages.load(targetReader);
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }

    public static FileConfiguration getogMessages() {
        return virtogMessages;
    }

    public static void compareMessages() {
        laodogmessages();
        Iterator var0 = getogMessages().getConfigurationSection("messages").getKeys(false).iterator();

        while(var0.hasNext()) {
            String key = (String)var0.next();
            if (getMessages().contains("messages." + key)) {
                newmodifiedMessages.set("messages." + key, getMessages().getString("messages." + key));
            } else {
                newmodifiedMessages.set("messages." + key, getogMessages().getString("messages." + key));
            }
        }

        modifiedMessages = newmodifiedMessages;
        saveMessagesFile();
        File file = new File(ColorfulColors.plugin.getDataFolder(), "messages.yml");
        Charset charset = StandardCharsets.UTF_8;
        String fileAsString = "";
        String header = "# 如果你想使用特殊字符，你必须将文件保存为 UTF-8 编码。";
        header = String.valueOf(header) + "\n# 如果在加载 Prismatic Colors 时出现错误，请确保：";
        header = String.valueOf(header) + "\n#   - 不存在制表符：YAML 只允许使用空格。";
        header = String.valueOf(header) + "\n#   - 缩进正确：YAML 的层次结构基于缩进。";
        header = String.valueOf(header) + "\n#   - 你在文本中已经 \"转义\" 了所有单引号：如果你想写 \"doesn't\" - 写成 \"doesn''t\"";
        header = String.valueOf(header) + "\n# 有些消息可能会显示目标玩家的用户名。只需写上 %target%，插件会自动替换为玩家的名字。\n";
        Path path = Paths.get(file.getPath());

        try {
            fileAsString = new String(Files.readAllBytes(path), charset);
        } catch (IOException var9) {
            getLogger().info("在检索 colorfulcolors 消息时发生错误。请尝试重新启动服务器或删除消息文件。");
            getLogger().info("如果错误仍然存在，请在 苦力怕论坛 或 QQ交流群 上联系小浩。");
            var9.printStackTrace();
        }

        String content = String.valueOf(header) + fileAsString;
        saveMessagesFile();
        Iterator var6 = getMessages().getConfigurationSection("messages").getKeys(false).iterator();

        while(var6.hasNext()) {
            String key = (String)var6.next();
            if (key.contains("__message__")) {
                content = content.replace(String.valueOf(key) + ": ", "\r  #");
            }
        }

        try {
            Files.write(path, content.getBytes(charset), new OpenOption[0]);
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }

    public static void setupConfig() {
        config = new File(Bukkit.getServer().getPluginManager().getPlugin(ColorfulColors.plugin.getName()).getDataFolder(), "config.yml");
        if (!config.exists()) {
            ColorfulColors.plugin.saveResource("config.yml", false);
        }

        modifiedConfig = SimpleYaml.loadConfiguration(config);
        newmodifiedConfig = new SimpleYaml();
    }

    public static void saveConfigFile() {
        try {
            modifiedConfig.save(config);
        } catch (IOException var1) {
            var1.printStackTrace();
            getLogger().info("无法保存配置文件");
        }

    }

    public static FileConfiguration getConfig() {
        return modifiedConfig;
    }

    public static void laodogConfig() {
        Reader targetReader = new InputStreamReader(ColorfulColors.plugin.getResource("config.yml"));

        try {
            virtogConfig.load(targetReader);
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }

    public static FileConfiguration getogConfig() {
        return virtogConfig;
    }

    public static void compareConfig() {
        laodogConfig();
        Iterator var0 = getogConfig().getKeys(false).iterator();

        while(var0.hasNext()) {
            String key = (String)var0.next();
            if (getConfig().contains(key)) {
                newmodifiedConfig.set(key, getConfig().get(key));
            } else {
                newmodifiedConfig.set(key, getogConfig().get(key));
            }
        }

        modifiedConfig = newmodifiedConfig;
        saveConfigFile();
        File file = new File(ColorfulColors.plugin.getDataFolder(), "config.yml");
        Charset charset = StandardCharsets.UTF_8;
        String fileAsString = "";
        String header = "# 如果你想使用特殊字符，你必须将文件保存为 UTF-8 编码。";
        header = String.valueOf(header) + "\n# 如果在加载 Prismatic Colors 时出现错误，请确保：";
        header = String.valueOf(header) + "\n#   - 不存在制表符：YAML 只允许使用空格。";
        header = String.valueOf(header) + "\n#   - 缩进正确：YAML 的层次结构基于缩进。";
        header = String.valueOf(header) + "\n#   - 你在文本中已经 \"转义\" 了所有单引号：如果你想写 \"doesn't\" - 写成 \"doesn''t\"";
        header = String.valueOf(header) + "\n\n";
        Path path = Paths.get(file.getPath());

        try {
            fileAsString = new String(Files.readAllBytes(path), charset);
        } catch (IOException var9) {
            getLogger().info("在检索 colorfulcolors 配置时发生错误。请尝试重新启动服务器或删除配置文件。");
            getLogger().info("如果错误仍然存在，请在 苦力怕BBS 或 QQ交流群 上联系小浩。");
            var9.printStackTrace();
        }

        String content = String.valueOf(header) + fileAsString;
        saveConfigFile();
        Iterator var6 = getConfig().getKeys(false).iterator();

        while(var6.hasNext()) {
            String key = (String)var6.next();
            if (key.contains("__message__")) {
                content = content.replace(String.valueOf(key) + ": ", "\r#");
            }
        }

        try {
            Files.write(path, content.getBytes(charset), new OpenOption[0]);
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }

    public static void setupAll() {
        setup();
        setupMessages();
        setupConfig();
    }

}