package pl.myaspera.chat.data;

import org.bukkit.entity.Player;
import pl.myaspera.chat.object.User;

import java.util.HashMap;
import java.util.Map;

public final class UserData {

    private final Map<String, User> users;

    public UserData() {
        this.users = new HashMap<>();
    }

    public User getOrCreate(final Player player) {
        return this.users.computeIfAbsent(player.getName(), user -> new User());
    }
}
