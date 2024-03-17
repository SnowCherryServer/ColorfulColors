package mc233.fun.colorfulcolors;

import mc233.fun.colorfulcolors.DataFiles;

public class Constants {
    public int maxChar = DataFiles.getConfig().getInt("max-nick-length");
    public int minChar = DataFiles.getConfig().getInt("min-nick-length");
    public boolean usingChatAPI = DataFiles.getConfig().getBoolean("using-chat-placeholder");
    public boolean usingTabAPI = DataFiles.getConfig().getBoolean("using-tab-placeholder");

    public Constants() {
    }
}
