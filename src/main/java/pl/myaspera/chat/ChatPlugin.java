package pl.myaspera.chat;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.validator.okaeri.OkaeriValidator;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import me.mattstudios.mf.base.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.myaspera.chat.command.ChatCommand;
import pl.myaspera.chat.data.MessageConfiguration;
import pl.myaspera.chat.data.PluginConfiguration;
import pl.myaspera.chat.data.UserData;
import pl.myaspera.chat.listener.PlayerChat;
import pl.myaspera.chat.listener.PlayerJoin;
import pl.myaspera.chat.util.ChatUtil;
import pl.myaspera.chat.util.UpdatePlugin;

import java.io.File;

public class ChatPlugin extends JavaPlugin {

    private boolean newPluginUpdate = false;

    private CommandManager commandManager;

    private PluginConfiguration pluginConfiguration;
    private MessageConfiguration messageConfiguration;

    private UserData userData;

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        this.checkPluginUpdate();
        this.loadConfiguration();
        this.registerCommands();
        this.registerListeners();

        this.userData = new UserData();
    }

    private void checkPluginUpdate() {
        new UpdatePlugin(this).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                this.getLogger().info("The plugin is updated to the latest version!");
            } else {
                this.newPluginUpdate = true;
                this.getLogger().info("There is a new update available!");
            }
        });
    }

    private void loadConfiguration() {
        if(!this.getDataFolder().exists()) this.getDataFolder().mkdir();
        this.pluginConfiguration = ConfigManager.create(PluginConfiguration.class, (it) -> {
            it.withConfigurer(new OkaeriValidator(new YamlBukkitConfigurer(), true));
            it.withBindFile(new File(this.getDataFolder(), "config.yml"));
            it.saveDefaults();
            it.load(true);
        });
        this.messageConfiguration = ConfigManager.create(MessageConfiguration.class, (it) -> {
            it.withConfigurer(new OkaeriValidator(new YamlBukkitConfigurer(), true));
            it.withBindFile(new File(this.getDataFolder(), "messages.yml"));
            it.saveDefaults();
            it.load(true);
        });
    }

    private void registerCommands() {
        this.commandManager = new CommandManager(this);
        this.commandManager.getMessageHandler().register("cmd.no.exists", sender -> ChatUtil.sendMessage(sender, "&cTaka komenda nie istnieje!"));
        this.commandManager.getMessageHandler().register("cmd.no.permission", sender -> ChatUtil.sendMessage(sender, "&cNie posiadasz uprawnień do tej komendy!"));

        new ChatCommand(this);
    }

    private void registerListeners() {
        new PlayerJoin(this);
        new PlayerChat(this);
    }

    public boolean isNewPluginUpdate() {
        return this.newPluginUpdate;
    }

    public PluginConfiguration getPluginConfiguration() {
        return this.pluginConfiguration;
    }

    public MessageConfiguration getMessageConfiguration() {
        return this.messageConfiguration;
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    public UserData getUserData() {
        return this.userData;
    }
}
