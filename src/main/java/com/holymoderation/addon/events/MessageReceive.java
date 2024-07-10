package com.holymoderation.addon.events;

import static com.holymoderation.addon.HMManager.*;

import static com.holymoderation.addon.SettingsManager.*;

import static com.holymoderation.addon.Colors.*;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageReceiveEvent;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;

public class MessageReceive {

    @Subscribe
    public void AutoAnyDesk(MessageReceiveEvent event) {
        if (AutoAnyDeskEnabled) {
            if (!event.getComponent().getString().isEmpty()) {
                String receivedText = event.getComponent().getString();

                if (receivedText.contains(Player)) {
                    if (receivedText.startsWith("[" + Player + " ->")) {
                        String msgText = receivedText.split("я]", 0)[1].replace(" ", "");

                        if (CheckCorrectLong(msgText) && msgText.length() >= 9 && msgText.length() <= 11) {
                            CopyToClipboard(msgText);
                        }
                    }

                    String chatText = receivedText.split(":")[1].replace(" ", "");

                    if (CheckCorrectLong(chatText) && chatText.length() >= 9 && chatText.length() <= 11) {
                        CopyToClipboard(chatText);
                    }
                }

            }
        }
    }

    @Subscribe
    public void AutoBan(MessageReceiveEvent event) {
        if (Player != null && AutoBanEnabled) {
            String receveivedText = event.getComponent().getString();

            if (receveivedText.startsWith("▶ Замороженный игрок " + Player)) {
                String tempPlayer = Player;
                EndCheckOut();
                if (VkUrl == null) {
                    ClientMessage(RED + "У вас не установлена ссылка на вк, поэтому игрок, который ливнул с проверки, не был забанен!");
                    ClientMessage(YELLOW + "Пожалуйста, установите ссылку на вк и забаньте игрока самостоятельно.");
                    return;
                }
                Punish("/banip", tempPlayer, "30d", "2.4 (Лив с проверки)", true);
            }
        }
    }

    @Subscribe
    public void GetMessage(MessageReceiveEvent event) {
    }

    public static void CopyToClipboard(String text) {
        StringSelection strsel = new StringSelection(text);
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        clip.setContents(strsel, (ClipboardOwner)null);
    }
}
