package com.holymoderation.addon.events;

import java.util.ArrayList;
import java.util.Arrays;

import com.holymoderation.addon.utils.PunishmentsManager;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import com.holymoderation.addon.utils.ChatManager;
import com.holymoderation.addon.utils.Colors;

public class Freezer {

    private static Boolean dupeIpEnabled = false;
    private static String texts = null;
    private static String player = null;
    private static String[] messageSplit;

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        String command = message.split(" ")[0];

        if (ChatManager.IsArrayContains(ChatManager.FreezerCommands, command)) {
            event.setCancelled(true);
            switch (command) {
                case ("/freezing"):
                case ("/frz"): {
                    messageSplit = message.split(" ", 2);
                    if (player != null) {
                        ChatManager.ClientMessage(Colors.RED + "Вы уже проверяете какого-то игрока! " +
                                Colors.RED + "Сначала закончите текущую проверку. --> " + Colors.GOLD + "/unfreezing"
                                + " или " + Colors.GOLD + "/unfrz");
                        return;
                    }
                    if (messageSplit.length < 2) {
                        ChatManager.ClientMessage(Colors.RED + "Вы не указали ник игрока!");
                        return;
                    }
                    player = messageSplit[1];
                    if (player.split(" ")[player.split(" ").length - 1].equals("-r")) {
                        Counter.IncreaseInfo("reports");
                    }
                    player = player.split(" ")[0];
                    Timer.SetPlayer(player);
                    Punishments.SetPlayer(player);
                    Timer.StopWatchStart();
                    Counter.IncreaseInfo("checkouts");
                    if (dupeIpEnabled)
                        ChatManager.SendMessage("/dupeip " + player);
                    if (texts == null) {
                        ChatManager.ClientMessage(Colors.RED + "У вас нет настроенных текстов для отправки! " +
                                "Добавить тексты --> " + Colors.GOLD + ".addtext");
                        ChatManager.ClientMessage(Colors.RED + "Просмотреть тексты --> " + Colors.GOLD + ".textlist");
                    }
                    else {
                        for (String text : GetSplitTexts()) {
                            ChatManager.SendMessage("/msg " + player + " " + text);
                        }
                    }
                    ChatManager.SendMessage("/freezing " + player);
                    ChatManager.SendMessage("/checkmute " + player);
                    ChatManager.SendMessage("/prova");
                    break;
                }

                case ("/unfreezing"):
                case("/unfrz"): {
                    if (player == null) {
                        ChatManager.ClientMessage(Colors.RED + "Вы никого не проверяете!");
                        return;
                    }
                    ChatManager.SendMessage("/freezing " + player);
                    ChatManager.SendMessage("/prova");
                    player = null;
                    Timer.SetPlayer(player);
                    Punishments.SetPlayer(player);
                    break;
                }

                case ("/sban"): {
                    messageSplit = message.split(" ", 3);
                    if (player == null) {
                        ChatManager.ClientMessage(Colors.RED + "Вы никого не проверяете!");
                        return;
                    }
                    if (!PunishmentsManager.CheckVK()) {
                        return;
                    }
                    switch (messageSplit.length) {
                        case (1):
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали время и причину бана!");
                            return;
                        case (2):
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали причину бана!");
                            return;
                    }
                    String time = messageSplit[1];
                    String reason = messageSplit[2];
                    if (!PunishmentsManager.CheckTimeFormat(time)) {
                        return;
                    }
                    PunishmentsManager.Punish("/banip", player, time, "2.4 (" + reason + ")", true);
                    ChatManager.SendMessage("/freezing " + player);
                    ChatManager.SendMessage("/prova");
                    player = null;
                    Punishments.SetPlayer(player);
                    Timer.SetPlayer(player);
                    break;
                }

                case (".freezing"):
                case (".frz"): {
                    messageSplit = message.split(" ", 2);
                    if (messageSplit.length < 2) {
                        ChatManager.ClientMessage(Colors.RED + "Вы не указали ник игрока!");
                        return;
                    }
                    String unFrzPlayer = messageSplit[1];
                    if (unFrzPlayer.equals(player)) {
                        ChatManager.ClientMessage(Colors.RED + "Этот игрок находиться у вас на проверке! " +
                                "Для его разморозки используйте" + Colors.GOLD + " /unfreezing" + Colors.RED
                                + " или " + Colors.GOLD + "/unfrz");
                        return;
                    }
                    ChatManager.SendMessage("/freezing " + unFrzPlayer);
                    break;
                }
            }
        }
    }

    public static Boolean GetDupeIp() {
        return dupeIpEnabled;
    }

    public static String GetTexts() {
        return texts;
    }

    public static String[] GetSplitTexts() {
        if (texts == null)
            return null;
        else
            return texts.split("%", 0);
    }

    public static void SetTexts(String value) {
        texts = value;
    }

    public static void AddText(String value) {
        if (value == null)
            return;

        if (texts == null)
            texts = value;
        else
            texts = texts + "%" + value;
    }

    public static void RemoveText(int index){
        ArrayList<String> textsarlist = new ArrayList<>(Arrays.asList(texts.split("%", 0)));
        textsarlist.remove(index);
        texts = null;
        for (int i = 0; i < textsarlist.size(); i++) {
            if (texts == null)
                texts = textsarlist.get(i);
            else
                texts = texts + "%" + textsarlist.get(i);
        }
    }

    public static void EditText(int index, String value) {
        String[] textsList = texts.split("%");
        textsList[index] = value;
        texts = null;
        for (String text : textsList) {
            if (texts == null)
                texts = text;
            else
                texts = texts + "%" + text;
        }
    }

    public static void ClearTexts() {
        texts = null;
    }

    public static void SetDupeIp(boolean value) {
        dupeIpEnabled = value;
    }
}
