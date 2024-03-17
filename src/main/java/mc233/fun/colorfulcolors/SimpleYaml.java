package mc233.fun.colorfulcolors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConstructor;
import org.bukkit.configuration.file.YamlRepresenter;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.representer.Representer;

public class SimpleYaml extends FileConfiguration {
    protected static final String COMMENT_PREFIX = "# ";
    protected static final String BLANK_CONFIG = "{}\n";
    private final DumperOptions yamlOptions = new DumperOptions();
    private final Representer yamlRepresenter = new YamlRepresenter();
    private final Yaml yaml;

    public SimpleYaml() {
        this.yaml = new Yaml(new YamlConstructor(), this.yamlRepresenter, this.yamlOptions);
    }

    public String saveToString() {
        this.yamlOptions.setIndent(2);
        this.yamlOptions.setDefaultFlowStyle(FlowStyle.BLOCK);
        this.yamlOptions.setWidth(4096);
        this.yamlRepresenter.setDefaultFlowStyle(FlowStyle.BLOCK);
        String header = this.buildHeader();
        String dump = this.yaml.dump(this.getValues(false));
        if (dump.equals("{}\n")) {
            dump = "";
        }

        return header + dump;
    }

    public void loadFromString(String contents) throws InvalidConfigurationException {
        Validate.notNull(contents, "内容不能为空");

        Map input;
        try {
            input = (Map)this.yaml.load(contents);
        } catch (YAMLException var4) {
            throw new InvalidConfigurationException(var4);
        } catch (ClassCastException var5) {
            throw new InvalidConfigurationException("顶层不是Map类型");
        }

        String header = this.parseHeader(contents);
        if (header.length() > 0) {
            this.options().header(header);
        }

        if (input != null) {
            this.convertMapsToSections(input, this);
        }

    }

    protected void convertMapsToSections(Map<?, ?> input, ConfigurationSection section) {
        Iterator var4 = input.entrySet().iterator();

        while(var4.hasNext()) {
            Map.Entry<?, ?> entry = (Map.Entry)var4.next();
            String key = entry.getKey().toString();
            Object value = entry.getValue();
            if (value instanceof Map) {
                this.convertMapsToSections((Map)value, section.createSection(key));
            } else {
                section.set(key, value);
            }
        }

    }

    protected String parseHeader(String input) {
        String[] lines = input.split("\r?\n", -1);
        StringBuilder result = new StringBuilder();
        boolean readingHeader = true;
        boolean foundHeader = false;

        for(int i = 0; i < lines.length && readingHeader; ++i) {
            String line = lines[i];
            if (line.startsWith("# ")) {
                if (i > 0) {
                    result.append("\n");
                }

                if (line.length() > "# ".length()) {
                    result.append(line.substring("# ".length()));
                }

                foundHeader = true;
            } else if (foundHeader && line.length() == 0) {
                result.append("\n");
            } else if (foundHeader) {
                readingHeader = false;
            }
        }

        return result.toString();
    }

    protected String buildHeader() {
        String header = this.options().header();
        if (this.options().copyHeader()) {
            Configuration def = this.getDefaults();
            if (def != null && def instanceof FileConfiguration) {
                String defaultsHeader = "你好";
                if (defaultsHeader != null && defaultsHeader.length() > 0) {
                    return defaultsHeader;
                }
            }
        }

        if (header == null) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            String[] lines = header.split("\r?\n", -1);
            boolean startedHeader = false;

            for(int i = lines.length - 1; i >= 0; --i) {
                builder.insert(0, "\n");
                if (startedHeader || lines[i].length() != 0) {
                    builder.insert(0, lines[i]);
                    builder.insert(0, "# ");
                    startedHeader = true;
                }
            }

            return builder.toString();
        }
    }

    public static mc233.fun.colorfulcolors.SimpleYaml loadConfiguration(File file) {
        Validate.notNull(file, "文件不能为空");
        mc233.fun.colorfulcolors.SimpleYaml config = new mc233.fun.colorfulcolors.SimpleYaml();

        try {
            config.load(file);
        } catch (FileNotFoundException var3) {
        } catch (IOException var4) {
            Bukkit.getLogger().log(Level.SEVERE, "无法加载文件 " + file, var4);
        } catch (InvalidConfigurationException var5) {
            Bukkit.getLogger().log(Level.SEVERE, "无法加载文件 " + file, var5);
        }

        return config;
    }

    public static mc233.fun.colorfulcolors.SimpleYaml loadConfiguration(Reader reader) {
        Validate.notNull(reader, "输入流不能为空");
        mc233.fun.colorfulcolors.SimpleYaml config = new mc233.fun.colorfulcolors.SimpleYaml();

        try {
            config.load(reader);
        } catch (IOException var3) {
            Bukkit.getLogger().log(Level.SEVERE, "无法从配置中加载！", var3);
        } catch (InvalidConfigurationException var4) {
            Bukkit.getLogger().log(Level.SEVERE, "无法从配置中加载！", var4);
        }

        return config;
    }

    public DumperOptions getYamlOptions() {
        return this.yamlOptions;
    }

    public Representer getYamlRepresenter() {
        return this.yamlRepresenter;
    }
}
