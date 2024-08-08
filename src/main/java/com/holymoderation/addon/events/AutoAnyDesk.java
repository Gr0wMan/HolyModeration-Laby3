package com.holymoderation.addon.events;

import static com.holymoderation.addon.HMManager.*;
import static com.holymoderation.addon.SettingsManager.*;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageReceiveEvent;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class AutoAnyDesk {

    @Subscribe
    public void OnMessageReceiveEvent(MessageReceiveEvent event) {
        String receivedText;
        if (AutoAnyDeskEnabled && !event.getComponent().getString().isEmpty() && (receivedText = event.getComponent().getString()).contains(Player)) {
            String chatText;
            String msgText;
            if (receivedText.startsWith("[" + Player + " ->") && CheckCorrectLong(msgText = receivedText.split("я]", 0)[1].replace(" ", "")) && msgText.length() >= 9 && msgText.length() <= 11) {
                CopyToClipboard(msgText);
            }
            if (CheckCorrectLong(chatText = receivedText.split(":")[1].replace(" ", "")) && chatText.length() >= 9 && chatText.length() <= 11) {
                CopyToClipboard(chatText);
            }
        }
    }

    public static void CopyToClipboard(String text) {
        StringSelection strsel = new StringSelection(text);
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        clip.setContents(strsel, null);
    }
}
