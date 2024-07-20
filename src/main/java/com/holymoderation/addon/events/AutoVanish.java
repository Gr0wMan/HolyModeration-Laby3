package com.holymoderation.addon.events;

import static com.holymoderation.addon.HMManager.*;
import static com.holymoderation.addon.SettingsManager.*;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;
import net.labymod.api.event.events.network.server.ServerSwitchEvent;

public class AutoVanish {

    @Subscribe
    public void OnServerSwitchEvent(ServerSwitchEvent event) {
        if (AutoVanishEnabled) {
            ChatMessage("/v");
        }
    }

    @Subscribe
    public void OnMessageSend(MessageSendEvent event) {
        if (event.getMessage().startsWith("/hub") || event.getMessage().startsWith("/рги")) {
            VanishEnabled = false;
        }
    }
}
