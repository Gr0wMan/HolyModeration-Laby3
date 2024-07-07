package com.holymoderation.addon.events;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import com.holymoderation.addon.utils.ChatManager;
import com.holymoderation.addon.utils.Colors;
import com.holymoderation.addon.utils.PunishmentsManager;

public class Punishments {

    private static String player = null;
    private static String[] messageSplit;

    private static boolean nicknameHasChar = false;
    private static boolean punishmentConfirm = false;
    private static String tempPlayer = "null";

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        String command = message.split(" ", 0)[0];

        if (ChatManager.IsArrayContains(ChatManager.PunishmentsCommands, command)) {
            event.setCancelled(true);
            messageSplit = message.split(" ", 3);
            char lastChar = messageSplit[1].charAt(messageSplit[1].length() - 1);
            for (int i = 0; i < ChatManager.Chars.length; i++) {
                if (messageSplit[1].contains(ChatManager.Chars[i])) {
                    nicknameHasChar = true;
                    break;
                }
            }
            if (nicknameHasChar) {
                ChatManager.ClientMessage(Colors.RED + "Некорректный никнейм!");
                return;
            }
            if ((lastChar == 'h' || lastChar == 'H' || lastChar == 'd' || lastChar == 'D')) {
                if (!messageSplit[1].equals(tempPlayer)) {
                    punishmentConfirm = false;
                }
                if (!punishmentConfirm) {
                    ChatManager.ClientMessage(Colors.RESET + "Вы " + Colors.RED + "УВЕРЕНЫ" + Colors.RESET
                            + ", что хотите выдать наказание игроку " + Colors.GOLD + messageSplit[1] + Colors.RESET + "?");
                    ChatManager.ClientMessage(Colors.RESET + "Если вы " + Colors.RED + "УВЕРЕНЫ" + Colors.RESET
                            + ", то введите команду " + Colors.GOLD + "ещё раз" + Colors.RESET + ".");
                    tempPlayer = messageSplit[1];
                    punishmentConfirm = true;
                    return;
                }
            }
            punishmentConfirm = false;
            tempPlayer = "null";
            if (ChatManager.IsArrayContains(ChatManager.TempPunishments, command)) {
                messageSplit = message.split(" ", 4);
                switch (messageSplit.length) {
                    case (1):
                        ChatManager.ClientMessage(Colors.RED + "Вы не указали ник игрока, время и причину!");
                        return;
                    case (2):
                        ChatManager.ClientMessage(Colors.RED + "Вы не указали время и причину!");
                        return;
                    case (3):
                        ChatManager.ClientMessage(Colors.RED + "Вы не указали причину!");
                        return;
                }
                String nick = messageSplit[1];
                String time = messageSplit[2];
                String reason = messageSplit[3];
                if (PunishmentsManager.CheckPlayerOnCheck(player, nick)) {
                    return;
                }
                if (!PunishmentsManager.CheckTimeFormat(time)) {
                    return;
                }
                if (ChatManager.IsArrayContains(ChatManager.MuteCommands, command)) {
                    PunishmentsManager.Punish(command, nick, time, reason, false);
                }
                if (ChatManager.IsArrayContains(ChatManager.BanCommands, command)) {
                    if (!PunishmentsManager.CheckVK()) {
                        return;
                    }
                    PunishmentsManager.Punish(command, nick, time, reason, true);
                }
            }
            else if (ChatManager.IsArrayContains(ChatManager.InfinityPunishments, command)) {
                messageSplit = message.split(" ", 3);
                switch (messageSplit.length) {
                    case (1):
                        ChatManager.ClientMessage(Colors.RED + "Вы не указали ник игрока и причину!");
                        return;
                    case (2):
                        ChatManager.ClientMessage(Colors.RED + "Вы не указали причину!");
                        return;
                }
                String nick = messageSplit[1];
                String reason = messageSplit[2];
                if (PunishmentsManager.CheckPlayerOnCheck(player, nick)) {
                    return;
                }
                if (ChatManager.IsArrayContains(ChatManager.MuteCommands, command)) {
                    PunishmentsManager.Punish(command, nick, reason, false);
                }
                if (ChatManager.IsArrayContains(ChatManager.BanCommands, command)) {
                    if (!PunishmentsManager.CheckVK()) {
                        return;
                    }
                    PunishmentsManager.Punish(command, nick, reason, true);
                }
            }
        }
    }

    public static void SetPlayer(String value) {
        player = value;
    }
}