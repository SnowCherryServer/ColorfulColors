package mc233.fun.colorfulcolors.DataStructures;


import mc233.fun.colorfulcolors.AbstractTemplate;
import mc233.fun.colorfulcolors.DataStructures.Word;
import mc233.fun.colorfulcolors.ColorfulColors;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class PlayerData {
    private String displayName = "";
    private boolean tab = false;
    private boolean glowing = false;
    private String target = "";
    private BukkitTask taskId;
    private AbstractTemplate template;
    private Word name;

    public Word getName() {
        return this.name;
    }

    public String getDisplayname() {
        return this.displayName;
    }

    public void setDisplayname(String displayname) {
        this.displayName = displayname;
    }

    public boolean isTab() {
        return this.tab;
    }

    public void setTab(boolean bool) {
        this.tab = bool;
    }

    public boolean isGlowing() {
        return this.glowing;
    }

    public void setGlowing(boolean bool) {
        this.glowing = bool;
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public BukkitTask getTaskId() {
        return this.taskId;
    }

    public void setTaskId(BukkitTask taskId) {
        this.taskId = taskId;
    }

    public AbstractTemplate getTemplate() {
        return this.template != null ? this.template : null;
    }

    public void setTemplate(AbstractTemplate template) {
        this.template = template;
    }

    public PlayerData(String name) {
        this.name = new Word(name, 0);
    }

    public PlayerData() {
        this.name = new Word();
    }

    public PlayerData(mc233.fun.colorfulcolors.DataStructures.PlayerData odt) {
        this.displayName = odt.getDisplayname();
        this.name = new Word(odt.getName());
        this.tab = odt.isTab();
        this.glowing = odt.isGlowing();
        this.taskId = odt.getTaskId();
        this.template = odt.getTemplate();
    }

    public void save() {
        this.displayName = this.name.getColoredWord();
    }

    public String example() {
        return this.name.getColoredWord();
    }

    public void setPlayerDisplayName(final Player p) {
        if (!this.displayName.equals("")) {
            Bukkit.getScheduler().runTask(ColorfulColors.plugin, new Runnable() {
                public void run() {
                    if (!ColorfulColors.constants.usingChatAPI) {
                        p.setDisplayName(String.valueOf(mc233.fun.colorfulcolors.DataStructures.PlayerData.this.displayName) + ChatColor.RESET);
                    }

                    if (mc233.fun.colorfulcolors.DataStructures.PlayerData.this.tab) {
                        if (!ColorfulColors.constants.usingTabAPI) {
                            p.setPlayerListName(String.valueOf(mc233.fun.colorfulcolors.DataStructures.PlayerData.this.displayName) + ChatColor.RESET);
                        }
                    } else if (!ColorfulColors.constants.usingTabAPI) {
                        p.setPlayerListName(p.getName());
                    }

                }
            });
        } else {
            if (this.getTaskId() != null) {
                this.getTaskId().cancel();
                this.setTaskId((BukkitTask)null);
            }

            Bukkit.getScheduler().runTask(ColorfulColors.plugin, new Runnable() {
                public void run() {
                    p.setDisplayName(p.getName());
                    p.setPlayerListName(p.getName());
                }
            });
        }

    }

    private void setPlayerGlowing(final Player p, int i) {
        this.displayName = String.valueOf(this.name.getGlow(i)) + ChatColor.RESET;
        Bukkit.getScheduler().runTask(ColorfulColors.plugin, new Runnable() {
            public void run() {
                if (!ColorfulColors.constants.usingChatAPI) {
                    p.setDisplayName(mc233.fun.colorfulcolors.DataStructures.PlayerData.this.displayName);
                }

                if (mc233.fun.colorfulcolors.DataStructures.PlayerData.this.tab) {
                    if (!ColorfulColors.constants.usingTabAPI) {
                        p.setPlayerListName(mc233.fun.colorfulcolors.DataStructures.PlayerData.this.displayName);
                    }
                } else if (!ColorfulColors.constants.usingTabAPI) {
                    p.setPlayerListName(p.getName());
                }

            }
        });
    }

    public void startGlow(final Player p) {
        if (this.getTaskId() != null) {
            this.getTaskId().cancel();
            this.setTaskId((BukkitTask)null);
        }

        this.setTaskId(Bukkit.getScheduler().runTaskTimerAsynchronously(ColorfulColors.plugin, new Runnable() {
            int i = 0;

            public void run() {
                if (mc233.fun.colorfulcolors.DataStructures.PlayerData.this.glowing) {
                    mc233.fun.colorfulcolors.DataStructures.PlayerData.this.setPlayerGlowing(p, this.i * 2);
                    if (this.i >= 30) {
                        this.i = 0;
                    } else {
                        ++this.i;
                    }
                } else {
                    mc233.fun.colorfulcolors.DataStructures.PlayerData.this.getTaskId().cancel();
                }

            }
        }, 1L, 2L));
    }

    public void removeColor(Player p) {
        this.displayName = "";
        if (this.getTaskId() != null) {
            this.getTaskId().cancel();
            this.setTaskId((BukkitTask)null);
        }

        if (this.getTemplate() != null) {
            this.getTemplate().removeTemplate(p, this);
            this.setTemplate((AbstractTemplate)null);
        }

        this.setPlayerDisplayName(p);
    }
}
