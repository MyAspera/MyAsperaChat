package pl.myaspera.chat.data;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.Exclude;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.exception.OkaeriException;
import pl.myaspera.chat.util.TimeUtil;


@Header("Konfiguracja pluginu MyAsperaChat")
@Header("Github: https://github.com/MyAspera/MyAsperaChat")
@Header("")
@Header("Uprawnienia:")
@Header("machat.notifyupdate - Informacja o nowej wersji pluginu po wejściu na serwer")
@Header("machat.nocooldown- Gracz z tym uprawnieniem może pisać na czacie bez ograniczenia czasowego")
@Header("machat.nochatoff- Gracz z tym uprawnieniem może pisać na czacie gdy jest wyłączony")
@Header("machat.chat.command - Dostęp do komendy /chat")
@Header("machat.chat.on - Dostęp do komendy /chat on")
@Header("machat.chat.off - Dostęp do komendy /chat off")
@Header("machat.chat.clear - Dostęp do komendy /chat clear")
@Header("machat.chat.cooldown - Dostęp do komendy /chat cooldown")
public class PluginConfiguration extends OkaeriConfig {

    @Comment("Czy czat ma być włączony? (Można ustawić komendą /chat on/off")
    public boolean chatEnable = true;

    @Comment("Co jaki czas można pisać na czacie? (np. 5s - 5 sekund)")
    public String chatCooldown = "5s";
    @Exclude
    public long cfgChatCooldown;

    @Override
    public OkaeriConfig load() throws OkaeriException {
        super.load();
        this.cfgChatCooldown = TimeUtil.getTimeWithString(this.chatCooldown);
        return this;
    }
}
