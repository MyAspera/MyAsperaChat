package pl.myaspera.chat.util;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public final class ChatUtil {

    public static String fixColor(String s){
        return ChatColor.translateAlternateColorCodes('&', s.replace("%>", "\u00BB").replace("<%", "\u00AB").replace("*", "\u2022"));
    }

    public static boolean sendMessage(CommandSender sender, String message){
        sender.sendMessage(fixColor(message));
        return true;
    }
    public static boolean sendMessage(CommandSender sender, List<String> message){
        for(String s : message) {
            sender.sendMessage(fixColor(s));
        }
        return true;
    }
    public static void sendURLMessage(Player player, String message, String url) {
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(fixColor(message)));
        component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        player.spigot().sendMessage(component);
    }

    public static boolean sendBroadcast(String message){
        Bukkit.broadcastMessage(fixColor(message));
        return true;
    }
}
