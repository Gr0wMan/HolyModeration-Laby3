package com.holymoderation.addon.events;

import static com.holymoderation.addon.HMManager.*;
import static com.holymoderation.addon.SettingsManager.*;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;
import net.labymod.api.event.events.network.server.DisconnectServerEvent;
import net.labymod.api.event.events.network.server.LoginServerEvent;
import net.labymod.api.event.events.network.server.ServerSwitchEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.world.GameType;

public class AutoVanish {

    @Subscribe
    public void OnServerSwitchEvent(ServerSwitchEvent event) {
        if (Minecraft.getInstance().playerController.getCurrentGameType() == GameType.ADVENTURE) {
            InHub = true;
            VanishEnabled = false;
        }
        else {
            InHub = false;
        }

        if (AutoVanishEnabled && !InHub) {
            ChatMessage("/v");
            VanishEnabled = true;
        }
    }

    @Subscribe
    public void OnMessageSend(MessageSendEvent event) {
        if (event.getMessage().startsWith("/v") && !InHub) {
            VanishEnabled = !VanishEnabled;
        }
    }

    @Subscribe
    public void OnLoginServer(LoginServerEvent event) {
        VanishEnabled = false;
        InHub = true;
    }

    @Subscribe
    public void OnDisconnectServer(DisconnectServerEvent event) {
        VanishEnabled = false;
    }
}
