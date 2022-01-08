package pl.myaspera.chat.data;

import eu.okaeri.configs.OkaeriConfig;

import java.util.Arrays;
import java.util.List;

public class MessageConfiguration extends OkaeriConfig {

    public String noPermission = "&5&lCHAT &8%> &cNie posiadasz uprawnienia &3%permission%&c!";
    public String chatCooldown = "&5&lCHAT &8%> &7Następną wiadomość możesz wysłać za &3%time%&7!";
    public String chatIsDisable = "&5&lCHAT &8%> &cCzat jest &4wyłączony&c!";
    public String chatAlreadyOn = "&5CHAT &8%> &aCzat jest już włączony!";
    public String chatAlreadyOff = "&5CHAT &8%> &cCzat jest już wyłączony!";

    public String chatOnBroadcast = "&a&oCzat został &2&owłączony &a&oprzez &3&o%admin%&a&o!";
    public String chatOffBroadcast = "&c&oCzat został &4&owyłączony &c&oprzez &3&o%admin%&a&o!";
    public String chatClearBroadcast = "&e&oCzat został &6&owyczyszczony &e&oprzez &3&o%admin%&a&o!";
    public String chatCooldownBroadcast = "&e&oOd teraz na czacie można pisać co &6%cooldown%&e&o! Zmiany dokonał &3&o%admin%&e&o!";

    public List<String> chatHelp = Arrays.asList("&8&m------&5 &lZarządzanie czatem&8 &m------",
            "&8* &3/chat on &8- &bWłącza czat",
            "&8* &3/chat off &8- &bWyłącza czat",
            "&8* &3/chat clear 8- &bCzyści czat",
            "&8* &3/chat cooldown <czas np. 5s> 8- &bUstawia cooldown czatu");
}
