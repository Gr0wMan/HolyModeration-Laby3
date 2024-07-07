package com.holymoderation.addon.events;

import com.holymoderation.addon.utils.ChatManager;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageReceiveEvent;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;

public class AutoAnyDesk {
    private static boolean autoAnyDeskEnabled;

    @Subscribe
    public void GetMsgText(MessageReceiveEvent event) {
        if (!event.getComponent().getString().equals("") && autoAnyDeskEnabled) {
            String receivedText = event.getComponent().getString();

            if (receivedText.startsWith("[" + Freezer.GetPlayer() + " ->")) {
                String msgText = receivedText.split("я]", 0)[1].replace(" ", "");

                if (ChatManager.CheckCorrectLong(msgText)) {
                    StringSelection strsel = new StringSelection(msgText);
                    Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clip.setContents(strsel, (ClipboardOwner)null);
                }
            }
        }
    }

    @Subscribe
    public void GetChatText(MessageReceiveEvent event) {
        if (!event.getComponent().getString().equals("") && autoAnyDeskEnabled) {
            String receivedText = event.getComponent().getString();

            if (receivedText.contains(Freezer.GetPlayer())) {
                String chatText = receivedText.split(":")[1].replace(" ", "");

                if (ChatManager.CheckCorrectLong(chatText)) {
                    StringSelection strsel = new StringSelection(chatText);
                    Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clip.setContents(strsel, (ClipboardOwner)null);
                }
            }
        }
    }

    public static boolean GetAutoAnyDeskEnabled() {
        return autoAnyDeskEnabled;
    }

    public static void SetAutoAnyDeskEnabled(boolean value) {
        autoAnyDeskEnabled = value;
    }
}
