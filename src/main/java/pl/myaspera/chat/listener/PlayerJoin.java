package pl.myaspera.chat.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.myaspera.chat.ChatPlugin;
import pl.myaspera.chat.util.ChatUtil;

public class PlayerJoin implements Listener {

    public PlayerJoin(final ChatPlugin plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    private final ChatPlugin plugin;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(final PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(player.hasPermission("machat.notifyupdate")) {
            if(this.plugin.isNewPluginUpdate()) {
                ChatUtil.sendMessage(player, "&8[&3MAChat&8] &cDostępna jest nowa wersja pluginu!");
                ChatUtil.sendURLMessage(player, "&8[&3MAChat&8] &aKliknij na wiadomość aby pobrać najnowszą wersję z githuba", "https://github.com/MyAspera/MyAsperaChat");
            }
        }
    }
}