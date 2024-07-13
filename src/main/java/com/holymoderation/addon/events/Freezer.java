package com.holymoderation.addon.events;

import static com.holymoderation.addon.HolyModeration.SaveCfg;

import static com.holymoderation.addon.HMManager.*;

import static com.holymoderation.addon.SettingsManager.*;

import static com.holymoderation.addon.Colors.*;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Freezer {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        String command = message.split(" ")[0];
        //АВТОТП
        if (IsArrayContains(FreezerCommands, command)) {
            event.setCancelled(true);
            String[] messageSplit;
            switch (command) {
                case ("/freezing"):
                case ("/frz"): {
                    if (Player != null) {
                        ClientMessage(RED + "Вы уже проверяете какого-то игрока! " +
                                RED + "Сначала закончите текущую проверку. --> " + GOLD + "/unfreezing"
                                + " или " + GOLD + "/unfrz");
                        return;
                    }
                    messageSplit = message.split(" ", 2);
                    if (messageSplit.length < 2) {
                        ClientMessage(RED + "Вы не указали ник игрока!");
                        return;
                    }
                    Player = messageSplit[1];
                    if (Player.split(" ")[Player.split(" ").length - 1].equals("-r")) {
                        IncreaseInfo("reports");
                    }
                    else {
                        IncreaseInfo("notreports");
                    }
                    Player = Player.split(" ")[0];
                    IncreaseInfo("checkouts");
                    if (DupeIpEnabled) {
                        ChatMessage("/dupeip " + Player);
                    }
                    if (AutoVanishEnabled && VanishEnabled) {
                        ChatMessage("/v");
                    }
                    if (AutoTpEnabled) {
                        ChatMessage("/warp logo");
                    }
                    ChatMessage("/freezing " + Player);
                    ChatMessage("/checkmute " + Player);
                    ChatMessage("/prova");
                    if (Texts == null) {
                        ClientMessage(RED + "У вас нет настроенных текстов для отправки! " +
                                "Добавить тексты --> " + GOLD + ".textadd");
                        ClientMessage(RED + "Просмотреть тексты --> " + GOLD + ".textlist");
                    }
                    else {
                        for (String text : GetSplitTexts()) {
                            scheduler.schedule(() -> {
                                if (Player != null) {
                                    ChatMessage("/msg " + Player + " " + text);
                                }
                            }, 3, TimeUnit.SECONDS);
                        }
                    }
                    break;
                }

                case ("/unfreezing"):
                case("/unfrz"): {
                    if (Player == null) {
                        ClientMessage(RED + "Вы никого не проверяете!");
                        return;
                    }
                    EndCheckOut();
                    break;
                }

                case ("/sban"): {
                    messageSplit = message.split(" ", 3);
                    if (Player == null) {
                        ClientMessage(RED + "Вы никого не проверяете!");
                        return;
                    }
                    if (!CheckVK()) {
                        return;
                    }
                    switch (messageSplit.length) {
                        case (1):
                            ClientMessage(RED + "Вы не указали время и причину бана!");
                            return;
                        case (2):
                            ClientMessage(RED + "Вы не указали причину бана!");
                            return;
                    }
                    String time = messageSplit[1];
                    String reason = messageSplit[2];
                    if (!CheckTimeFormat(time)) {
                        return;
                    }
                    Punish("/banip", Player, time, "2.4 (" + reason + ")", true);
                    EndCheckOut();
                    break;
                }

                case (".freezing"):
                case (".frz"): {
                    messageSplit = message.split(" ", 2);
                    if (messageSplit.length < 2) {
                        ClientMessage(RED + "Вы не указали ник игрока!");
                        return;
                    }
                    String unFrzPlayer = messageSplit[1].split(" ")[0];
                    if (unFrzPlayer.equals(Player)) {
                        ClientMessage(RED + "Этот игрок находиться у вас на проверке! " +
                                "Для его разморозки используйте" + GOLD + " /unfreezing" + RED
                                + " или " + GOLD + "/unfrz");
                        return;
                    }
                    ChatMessage("/freezing " + unFrzPlayer);
                    break;
                }
            }

            SaveCfg();
        }
    }


}
