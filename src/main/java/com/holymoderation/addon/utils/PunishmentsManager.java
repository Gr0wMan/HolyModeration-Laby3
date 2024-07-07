package com.holymoderation.addon.utils;

import com.holymoderation.addon.events.Counter;

public class PunishmentsManager {

    private static String vkUrl = null;

    public static boolean CheckTimeFormat(String message) {
        char lastChar = message.charAt(message.length() - 1);
        String stringTime = message.substring(0, message.length() - 1);
        if (lastChar != 'h' && lastChar != 'H' && lastChar != 'd' && lastChar != 'D' || message.length() > 4 || !ChatManager.CheckCorrectInt(stringTime)) {
            ChatManager.ClientMessage(Colors.RED + "Неверный формат времени! Должно быть 1-999h/H или 1-999d/D");
            return false;
        }
        return true;
    }

    public static boolean CheckPlayerOnCheck(String player, String nick) {
        if (player != null && player.equals(nick)) {
            ChatManager.ClientMessage(Colors.RED + "Вы не можете наказать этого игрока, " +
                    "так как он находится у вас на проверке!");
            return true;
        }
        return false;
    }

    public static void Punish(String command, String player, String reason, boolean addVk) {
        if (addVk)
            ChatManager.SendMessage(command + " " + player + " " + reason + " | Вопросы? " + vkUrl + " -s");
        else
            ChatManager.SendMessage(command + " " + player + " " + reason + " -s");

        if (command.equals("/mute") || command.equals("/muteip") || command.equals("/tempmute") || command.equals("/tempmuteip"))
        {
            Counter.IncreaseInfo("mutes");
        }

        else if (command.equals("/ban") || command.equals("/banip") || command.equals("/tempban"))
        {
            Counter.IncreaseInfo("bans");
        }
        Counter.IncreaseInfo("punishments");
    }

    public static void Punish(String punishCommand, String player, String time, String reason, boolean addVk) {
        String command = punishCommand;
        command.toCharArray()[0] = '/';
        if (addVk)
            ChatManager.SendMessage(command + " " + player + " " + time + " " + reason + " | Вопросы? " + vkUrl + " -s");
        else
            ChatManager.SendMessage(command + " " + player + " " + time + " " + reason + " -s");

        if (command.equals("/mute") || command.equals("/muteip") || command.equals("/tempmute") || command.equals("/tempmuteip"))
        {
            Counter.IncreaseInfo("mutes");
        }

        else if (command.equals("/ban") || command.equals("/banip") || command.equals("/tempban"))
        {
            Counter.IncreaseInfo("bans");
        }
        Counter.IncreaseInfo("punishments");
    }

    public static String GetVkUrl() {
        return vkUrl;
    }

    public static void SetVkUrl(String value) {
        vkUrl = value;
    }

    public static boolean CheckVK() {
        if (PunishmentsManager.GetVkUrl() == null) {
            ChatManager.ClientMessage(Colors.RED + "У вас не установлена ссылка на вк!");
            return false;
        }
        return true;
    }
}
