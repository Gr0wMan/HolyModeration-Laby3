package com.holymoderation.addon.events;

import static com.holymoderation.addon.HMManager.*;
import static com.holymoderation.addon.SettingsManager.*;

import net.labymod.api.event.events.client.chat.MessageSendEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.world.GameType;

public class AutoVanish {

    public static void Update() {
        if (SSEvent != null) {
            OnServerSwitchEvent();
        }

        if (MSEvent != null) {
            OnMessageSend(MSEvent);
        }

        if (LSEvent != null) {
            OnLoginServer();
        }

        if (DSEvent != null) {
            OnDisconnectServer();
        }

    }

    private static void OnServerSwitchEvent() {
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

    private static void OnMessageSend(MessageSendEvent event) {
        if (event.getMessage().startsWith("/v") && !InHub) {
            VanishEnabled = !VanishEnabled;
        }
    }

    private static void OnLoginServer() {
        VanishEnabled = false;
        InHub = true;
    }

    private static void OnDisconnectServer() {
        VanishEnabled = false;
    }

}
