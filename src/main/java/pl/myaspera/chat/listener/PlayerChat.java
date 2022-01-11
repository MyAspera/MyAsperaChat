package pl.myaspera.chat.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.myaspera.chat.ChatPlugin;
import pl.myaspera.chat.object.User;
import pl.myaspera.chat.util.ChatUtil;
import pl.myaspera.chat.util.TimeUtil;

public final class PlayerChat implements Listener {

    public PlayerChat(final ChatPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    private final ChatPlugin plugin;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(final AsyncPlayerChatEvent event) {
        if(event.isCancelled()) return;
        Player player = event.getPlayer();
        User user = this.plugin.getUserData().getOrCreate(player);

        if(!player.hasPermission("machat.nochatoff")) {
            if(!this.plugin.getPluginConfiguration().chatEnable) {
                event.setCancelled(true);
                ChatUtil.sendMessage(player, this.plugin.getMessageConfiguration().chatIsDisable);
                return;
            }
        }
        if(!player.hasPermission("machat.nocooldown")){
            if(!user.isWriteChat()) {
                event.setCancelled(true);
                ChatUtil.sendMessage(player, this.plugin.getMessageConfiguration().chatCooldown.replace("%time%", TimeUtil.getDuration(user.getWriteChat() - System.currentTimeMillis())));
                return;
            }
            user.setWriteChat(System.currentTimeMillis() + this.plugin.getPluginConfiguration().cfgChatCooldown);
        }
    }
}
