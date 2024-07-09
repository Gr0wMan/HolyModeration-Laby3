package com.holymoderation.addon.events;

import static com.holymoderation.addon.HolyModeration.SaveCfg;

import static com.holymoderation.addon.HMManager.*;

import static com.holymoderation.addon.SettingsManager.*;

import static com.holymoderation.addon.Colors.*;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

public class Punishments {

    private static boolean nicknameHasChar = false;
    private static boolean punishmentConfirm = false;
    private static String tempPlayer = "null";

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        String command = message.split(" ", 0)[0];

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
                if ((lastChar == 'h' || lastChar == 'H' || lastChar == 'd' || lastChar == 'D')) {
                    if (!messageSplit[1].equals(tempPlayer)) {
                        punishmentConfirm = false;
                    }
                    if (!punishmentConfirm) {
                        ClientMessage(RESET + "Вы " + RED + "УВЕРЕНЫ" + RESET
                                + ", что хотите выдать наказание игроку " + GOLD + messageSplit[1] + RESET + "?");
                        ClientMessage(RESET + "Если вы " + RED + "УВЕРЕНЫ" + RESET
                                + ", то введите команду " + GOLD + "ещё раз" + RESET + ".");
                        tempPlayer = messageSplit[1];
                        punishmentConfirm = true;
                        return;
                    }
                }
                punishmentConfirm = false;
                tempPlayer = "null";
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
                if (Player.equals(nick)) {
                    //ПОДТВЕРЖДЕНИЕ
                }
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
            }
            else if (IsArrayContains(InfinityPunishments, command)) {
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
                if (Player.equals(nick)) {
                    //ПОДТВЕРЖДЕНИЕ
                }
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

            SaveCfg();
        }
    }
}