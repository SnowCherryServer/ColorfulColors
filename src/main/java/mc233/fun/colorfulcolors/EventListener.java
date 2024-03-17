package mc233.fun.colorfulcolors;

import mc233.fun.colorfulcolors.AllPlayers;
import mc233.fun.colorfulcolors.DataStructures.*;
import mc233.fun.colorfulcolors.GUI.Global.LetterGUI;
import mc233.fun.colorfulcolors.ColorfulColors;
import mc233.fun.colorfulcolors.TempData;
import mc233.fun.colorfulcolors.Util;
import mc233.fun.colorfulcolors.hexInput;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.scheduler.BukkitTask;

public class EventListener implements Listener {

    @EventHandler
    public void handleChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        handleHexInput(player, event.getMessage());
        handleChatColor(player, event);
    }

    @EventHandler
    public void handleCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (hexInput.exists(player)) {
            event.setCancelled(true);
            Util.sendMessage(player, "sayCancel");
        }
    }

    @EventHandler
    public void handlePlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (AllPlayers.exists(player)) {
            PlayerData playerData = AllPlayers.getData(player);
            AllPlayers.activateColor(player, playerData);
        }
    }

    @EventHandler
    public void handlePlayerQuitEvent(PlayerQuitEvent event) {
        handlePlayerDisconnect(event.getPlayer());
    }

    @EventHandler
    public void handlePlayerKickEvent(PlayerKickEvent event) {
        handlePlayerDisconnect(event.getPlayer());
    }

    private void handleHexInput(Player player, String message) {
        if (hexInput.exists(player)) {
            Word word = hexInput.getData(player);
            LetterData letterData = word.getCurrentLetter();

            if (message.equalsIgnoreCase("cancel") || Util.isHex(message)) {
                if (Util.isHex(message)) {
                    letterData.getColor().hexToRGB(message);
                }

                Bukkit.getScheduler().runTaskLater(ColorfulColors.plugin, () -> {
                    hexInput.removePlayer(player);
                    new LetterGUI(player, 27, word);
                }, 1L);
            } else {
                Util.sendMessage(player, "invalidHex");
            }
        }
    }

    private void handleChatColor(Player player, AsyncPlayerChatEvent event) {
        if (AllPlayers.hasChatColor(player)) {
            PlayerChatColor playerChatColor = AllPlayers.getChatColor(player);
            if (playerChatColor.getGradient() != null) {
                event.setMessage(getGradientMessage(playerChatColor, event.getMessage()));
            } else {
                event.setMessage(ChatColor.of(playerChatColor.getColor1().getColorObject()) + event.getMessage());
            }
        }
    }

    private String getGradientMessage(PlayerChatColor playerChatColor, String message) {
        StringBuilder builder = new StringBuilder();
        int colorStage = 0;
        char[] messageChars = message.toCharArray();

        for (char messageChar : messageChars) {
            builder.append(playerChatColor.getGradient()[colorStage]).append(messageChar);
            colorStage = (colorStage + 1) % playerChatColor.getGradient().length;
        }

        return builder.toString();
    }

    private void handlePlayerDisconnect(Player player) {
        AllPlayers.removeFromTemp(player);
        TempData.removeRenameData(player);

        if (AllPlayers.exists(player)) {
            PlayerData playerData = AllPlayers.getData(player);
            if (playerData.getTaskId() != null) {
                playerData.getTaskId().cancel();
                playerData.setTaskId(null);
            }
        }
    }
}
