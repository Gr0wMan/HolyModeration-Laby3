package com.holymoderation.addon.events;

import com.holymoderation.addon.HolyModeration;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import com.holymoderation.addon.utils.ChatManager;
import com.holymoderation.addon.utils.Colors;
import com.holymoderation.addon.utils.PunishmentsManager;

public class Settings {

    private static String[] messageSplit;

    private static boolean clearStatsMessage = false;
    private static boolean clearAllStatsMessage = false;

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        String command = message.split(" ")[0];

        if (ChatManager.IsArrayContains(ChatManager.AllCommands, command) && !command.equals(".clearstats")) {
            clearStatsMessage = false;
        }

        if (ChatManager.IsArrayContains(ChatManager.AllCommands, command) && !command.equals(".clearallstats")) {
            clearAllStatsMessage = false;
        }

        if (ChatManager.IsArrayContains(ChatManager.SettingsCommands, command)) {
            event.setCancelled(true);
            if (ChatManager.IsArrayContains(ChatManager.SettingsWithoutArguments, command)) {
                switch (command) {
                    case (".textlist"):
                        ChatManager.ClientMessage(Colors.AQUA + "Список ваших текстов:");
                        if (Freezer.GetTexts() == null) {
                            ChatManager.ClientMessage(Colors.RED + "У вас нет настроенных текстов");
                        }
                        else {
                            for (int i = 0; i < Freezer.GetSplitTexts().length; i++) {
                                ChatManager.ClientMessage((i+1) + ". " + Freezer.GetSplitTexts()[i]);
                            }
                        }
                        break;
                    case (".textclear"):
                        Freezer.ClearTexts();
                        ChatManager.ClientMessage(Colors.GREEN + "Вы успешно очистили все тексты!");
                        break;
                    case (".getvk"):
                        if (PunishmentsManager.GetVkUrl() == null) {
                            ChatManager.ClientMessage(Colors.RED + "У вас не установлена ссылка на вк!");
                            return;
                        }
                        ChatManager.ClientMessage(Colors.AQUA + "Ваша ссылка на вк: " + PunishmentsManager.GetVkUrl());
                        break;
                    case (".dupeip"):
                        Freezer.SetDupeIp(!Freezer.GetDupeIp());
                        if (Freezer.GetDupeIp()) {
                            ChatManager.ClientMessage(Colors.YELLOW + "Автоматический /dupeip" + Colors.GREEN + " ВКЛЮЧЁН");
                        }
                        else {
                            ChatManager.ClientMessage(Colors.YELLOW + "Автоматический /dupeip" + Colors.RED + " ВЫКЛЮЧЕН");
                        }
                        break;
                    case (".counter"):
                        Counter.SetCounterEnabled(!Counter.GetCounterEnabled());
                        if (Counter.GetCounterEnabled()) {
                            ChatManager.ClientMessage(Colors.YELLOW + "Счётчик" + Colors.GREEN + " ВКЛЮЧЁН");
                        }
                        else {
                            ChatManager.ClientMessage(Colors.YELLOW + "Счётчик" + Colors.RED + " ВЫКЛЮЧЁН");
                        }
                        break;
                    case (".getstats"):
                        ChatManager.ClientMessage(Colors.AQUA + "Всего проверок: " +
                                Colors.GOLD + Counter.GetAllTimeInfo()[0]);
                        ChatManager.ClientMessage(Colors.AQUA + "Всего репортов: " +
                                Colors.GOLD + Counter.GetAllTimeInfo()[1]);
                        ChatManager.ClientMessage(Colors.AQUA + "Всего простых проверок: " +
                                Colors.GOLD + (Counter.GetAllTimeInfo()[0] - Counter.GetAllTimeInfo()[1]));
                        ChatManager.ClientMessage(Colors.AQUA + "Всего наказаний: " +
                                Colors.GOLD + Counter.GetAllTimeInfo()[2]);
                        ChatManager.ClientMessage(Colors.AQUA + "Всего банов: " +
                                Colors.GOLD + Counter.GetAllTimeInfo()[3]);
                        ChatManager.ClientMessage(Colors.AQUA + "Всего мутов: " +
                                Colors.GOLD + Counter.GetAllTimeInfo()[4]);
                        break;
                    case (".clearstats"):
                        if (!clearStatsMessage) {
                            ChatManager.ClientMessage(Colors.AQUA + "Вы " + Colors.RED + "УВЕРЕНЫ" + Colors.AQUA
                                    + ", что хотите очистить вашу статистику? Если да, то напишите команду ещё раз");
                            clearStatsMessage = !clearStatsMessage;
                        }
                        else if (clearStatsMessage) {
                            Counter.SetTempInfo(new int[] {0, 0, 0, 0, 0});
                            ChatManager.ClientMessage(Colors.GREEN + "Вы успешно очистили вашу статистику!");
                            clearStatsMessage = !clearStatsMessage;
                        }
                        break;
                    case (".clearallstats"):
                        if (!clearAllStatsMessage) {
                            ChatManager.ClientMessage(Colors.AQUA + "Вы " + Colors.RED + "УВЕРЕНЫ" + Colors.AQUA
                                    + ", что хотите очистить " + Colors.RED + "ВСЮ" + Colors.AQUA +
                                    " вашу статистику? Если да, то напишите команду ещё раз");
                            clearAllStatsMessage = !clearAllStatsMessage;
                        }
                        else if (clearAllStatsMessage) {
                            Counter.SetAllTimeInfo(new int[] {0, 0, 0, 0, 0});
                            ChatManager.ClientMessage(Colors.GREEN + "Вы успешно очистили " + Colors.RED +
                                    "ВСЮ" + Colors.GREEN + " вашу статистику!");
                            clearAllStatsMessage = !clearAllStatsMessage;
                        }
                        break;
                    case (".addreport"):
                        Counter.AddReport();
                        ChatManager.ClientMessage(Colors.GREEN + "Вы успешно добавили репорт!");
                        break;
                    case (".removereport"):
                        Counter.RemoveReport();
                        ChatManager.ClientMessage(Colors.GREEN + "Вы успешно удалили репорт!");
                        break;
                    case (".removecheckout"):
                        Counter.RemoveCheckOut();
                        ChatManager.ClientMessage(Colors.GREEN + "Вы успешно удалили проверку!");
                        break;
                }
            }
            else if (ChatManager.IsArrayContains(ChatManager.SettingsWithOneArgument, command)) {
                messageSplit = message.split(" ", 2);
                switch (command) {
                    case (".setvk"):
                        if (messageSplit.length == 1) {
                            ChatManager.ClientMessage(Colors.RED + "Вы не ввели ссылку на вк!");
                            return;
                        }
                        String vkUrl = messageSplit[1];
                        boolean hasSpaces = false;
                        for (int i = 0; i < vkUrl.length(); i++) {
                            if (vkUrl.charAt(i) == ' ') {
                                hasSpaces = true;
                            }
                        }
                        if (hasSpaces) {
                            ChatManager.ClientMessage(Colors.RED + "В ссылке обнаружены пробелы, пожалуйста, " +
                                    "указывайте ссылку на вк в формате 'vk.com/id'");
                            return;
                        }
                        PunishmentsManager.SetVkUrl(vkUrl);
                        ChatManager.ClientMessage(Colors.GREEN + "Теперь ваша ссылка на вк: " + PunishmentsManager.GetVkUrl());
                        break;
                    case (".textadd"):
                        if (messageSplit.length == 1) {
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали текст!");
                            return;
                        }
                        String text = messageSplit[1];
                        Freezer.AddText(text);
                        ChatManager.ClientMessage(Colors.GREEN + "Вы добавили новый текст!");
                        break;
                    case (".textremove"):
                        if (Freezer.GetTexts() == null) {
                            ChatManager.ClientMessage(Colors.RED + "У вас нет настроенных текстов");
                            return;
                        }
                        if (messageSplit.length == 1) {
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали номер текста!");
                            return;
                        }
                        String indexText = messageSplit[1];
                        if (!ChatManager.CheckCorrectInt(indexText)) {
                            ChatManager.ClientMessage(Colors.RED + "Некорректный номер текста!");
                            return;
                        }
                        int intIndex = Integer.parseInt(indexText) - 1;
                        if (intIndex >= Freezer.GetSplitTexts().length || intIndex < 0) {
                            ChatManager.ClientMessage(Colors.RED
                                    + "Элемента с таким номером в списке ваших текстов не существует!");
                            return;
                        }
                        Freezer.RemoveText(intIndex);
                        ChatManager.ClientMessage(Colors.RED + "Вы удалили текст номер "
                                + Colors.GREEN + messageSplit[1] + "!");
                        break;
                    case (".settimercolor"):
                    case (".setcountercolor"): {
                        if (messageSplit.length == 1) {
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали айди цвета!");
                        }
                        String stringColor = "0x" + messageSplit[1];
                        int intColor; {
                            try {
                                intColor = Integer.decode(stringColor);
                            }
                            catch (NumberFormatException e) {
                                ChatManager.ClientMessage(Colors.RED + "Некорректный цветовой код!");
                                return;
                            }
                        }
                        switch (messageSplit[0]) {
                            case (".settimercolor"):
                                Timer.SetCustomColor(intColor);
                                break;
                            case (".setcountercolor"):
                                Counter.SetCustomColor(intColor);
                                break;
                        }
                        ChatManager.ClientMessage(Colors.GREEN + "Успешно применено!");
                        break;
                    }
                }
            }
            else if (ChatManager.IsArrayContains(ChatManager.SettingsWithTwoArguments, command)) {
                messageSplit = message.split(" ", 3);
                switch (command) {
                    case (".textedit"):
                        if (Freezer.GetTexts() == null) {
                            ChatManager.ClientMessage(Colors.RED + "У вас нет настроенных текстов");
                            return;
                        }
                        if (messageSplit.length == 1) {
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали номер текста и новый текст!");
                            return;
                        }
                        String indexText = messageSplit[1];
                        if (!ChatManager.CheckCorrectInt(indexText)) {
                            ChatManager.ClientMessage(Colors.RED + "Некорректный номер текста!");
                            return;
                        }
                        int index = Integer.parseInt(indexText) - 1;
                        if (messageSplit.length == 2) {
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали новый текст!");
                            return;
                        }
                        if (index >= Freezer.GetSplitTexts().length || index < 0) {
                            ChatManager.ClientMessage(Colors.RED
                                    + "Элемента с таким номером в списке ваших текстов не существует!");
                            return;
                        }
                        String text = messageSplit[2];
                        Freezer.EditText(index, text);
                        ChatManager.ClientMessage(Colors.YELLOW + "Вы изменили текст номер " + Colors.GREEN + (index + 1));
                        break;
                    case (".settimercoords"):
                    case (".setcountercoords"): {
                        if (messageSplit.length == 1) {
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали X и Y координаты!");
                            return;
                        }
                        else if (messageSplit.length == 2) {
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали Y координату!");
                            return;
                        }
                        String xText = messageSplit[1];
                        String yText = messageSplit[2];
                        boolean isXcorrect = ChatManager.CheckCorrectInt(xText);
                        boolean isYcorrect = ChatManager.CheckCorrectInt(yText);

                        if (!isXcorrect || !isYcorrect) {
                            if (!isXcorrect && !isYcorrect)
                                ChatManager.ClientMessage(Colors.RED + "Некорректные X и Y координаты!");
                            else if (!isXcorrect && isYcorrect)
                                ChatManager.ClientMessage(Colors.RED + "Некорректная X координата!");
                            else if (isXcorrect && !isYcorrect)
                                ChatManager.ClientMessage(Colors.RED + "Некорректная Y координата!");
                            return;
                        }

                        switch (messageSplit[0]) {
                            case (".settimercoords"):
                                Timer.SetXCoords(Integer.parseInt(xText));
                                Timer.SetYCoords(Integer.parseInt(yText));
                                break;
                            case (".setcountercoords"):
                                Counter.SetXCoords(Integer.parseInt(xText));
                                Counter.SetYCoords(Integer.parseInt(yText));
                                break;
                        }
                        ChatManager.ClientMessage(Colors.GREEN + "Успешно применено!");
                        break;
                    }
                }
            }

            HolyModeration.SaveCfg();
        }
    }
}