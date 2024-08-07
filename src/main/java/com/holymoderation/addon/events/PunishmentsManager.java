package com.holymoderation.addon.events;

import net.labymod.api.event.events.client.chat.MessageSendEvent;

import static com.holymoderation.addon.HolyModeration.SaveCfg;
import static com.holymoderation.addon.HMManager.*;
import static com.holymoderation.addon.SettingsManager.*;
import static com.holymoderation.addon.Colors.*;

public class PunishmentsManager {

    private static boolean nicknameHasChar = false;
    private static boolean StrangePunishmentConfirm = false;
    private static boolean StrangeFrzPunishmentConfirm = false;
    private static String StrangeMessage = "null";
    private static String StrangeFrzMessage = "null";

    public static void Update() {
        if (MSEvent != null) {
            OnMessageSend(MSEvent);
        }
    }

    private static void OnMessageSend(MessageSendEvent event) {
        String message = event.getMessage();
        String command = message.split(" ", 0)[0];

        if (!message.equals(StrangeMessage)) {
            StrangePunishmentConfirm = false;
        }

        if (!message.equals(StrangeFrzMessage)) {
            StrangeFrzPunishmentConfirm = false;
        }

        if (IsArrayContains(PunishmentsCommands, command)) {
            event.setCancelled(true);
            String[] messageSplit = message.split(" ", 3);
            if (messageSplit.length > 1) {
                char lastChar = messageSplit[1].charAt(messageSplit[1].length() - 1);
                for (String c : Chars) {
                    if (messageSplit[1].contains(c)) {
                        nicknameHasChar = true;
                        break;
                    }
                }
                if (nicknameHasChar) {
                    ClientMessage(RED + "Некорректный никнейм!");
                    return;
                }
                if ((lastChar == 'h' || lastChar == 'H' || lastChar == 'd' || lastChar == 'D')
                        && CheckCorrectInt(messageSplit[1].substring(0, messageSplit[1].length() - 1))) {
                    if (!StrangePunishmentConfirm) {
                        ClientMessage(RESET + "Вы " + RED + "УВЕРЕНЫ" + RESET
                                + ", что хотите выдать наказание игроку " + GOLD + messageSplit[1] + RESET + "?");
                        ClientMessage(RESET + "Если вы " + RED + "УВЕРЕНЫ" + RESET
                                + ", то введите команду " + GOLD + "ещё раз" + RESET + ".");
                        StrangeMessage = message;
                        StrangePunishmentConfirm = true;
                        return;
                    } else {
                        StrangePunishmentConfirm = false;
                        StrangeMessage = "null";
                    }
                }
                if (messageSplit[1].equals(Player)) {
                    if (!StrangeFrzPunishmentConfirm) {
                        ClientMessage(RESET + "Вы " + RED + "УВЕРЕНЫ" + RESET
                                + ", что хотите выдать наказание игроку, который у вас на проверке?");
                        ClientMessage(RESET + "Если вы " + RED + "УВЕРЕНЫ" + RESET
                                + ", то введите команду " + GOLD + "ещё раз" + RESET + ".");
                        StrangeFrzMessage = message;
                        StrangeFrzPunishmentConfirm = true;
                        return;
                    } else {
                        StrangeFrzMessage = "null";
                    }
                }
            }
            if (IsArrayContains(TempPunishments, command)) {
                messageSplit = message.split(" ", 4);
                switch (messageSplit.length) {
                    case (1):
                        ClientMessage(RED + "Вы не указали ник игрока, время и причину!");
                        return;
                    case (2):
                        ClientMessage(RED + "Вы не указали время и причину!");
                        return;
                    case (3):
                        ClientMessage(RED + "Вы не указали причину!");
                        return;
                }
                String nick = messageSplit[1];
                String time = messageSplit[2];
                String reason = messageSplit[3];
                if (!CheckTimeFormat(time)) {
                    return;
                }
                if (IsArrayContains(MuteCommands, command)) {
                    Punish(command, nick, time, reason, false);
                }
                if (IsArrayContains(BanCommands, command)) {
                    if (!CheckVK()) {
                        return;
                    }
                    Punish(command, nick, time, reason, true);
                }
            } else if (IsArrayContains(InfinityPunishments, command)) {
                messageSplit = message.split(" ", 3);
                switch (messageSplit.length) {
                    case (1):
                        ClientMessage(RED + "Вы не указали ник игрока и причину!");
                        return;
                    case (2):
                        ClientMessage(RED + "Вы не указали причину!");
                        return;
                }
                String nick = messageSplit[1];
                String reason = messageSplit[2];
                if (IsArrayContains(MuteCommands, command)) {
                    Punish(command, nick, reason, false);
                }
                if (IsArrayContains(BanCommands, command)) {
                    if (!CheckVK()) {
                        return;
                    }
                    Punish(command, nick, reason, true);
                }
            }

            if (IsArrayContains(BanCommands, command)) {
                if (StrangeFrzPunishmentConfirm && StrangeFrzMessage.equals("null")) {
                    StrangeFrzPunishmentConfirm = false;
                    EndCheckOut();
                }
            }

            if (IsArrayContains(MuteCommands, command)) {
                if (StrangeFrzPunishmentConfirm && StrangeFrzMessage.equals("null")) {
                    StrangeFrzPunishmentConfirm = false;
                }
            }

            SaveCfg();
        }
    }
}