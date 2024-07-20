package com.holymoderation.addon.events;

import static com.holymoderation.addon.HolyModeration.SaveCfg;
import static com.holymoderation.addon.HMManager.*;
import static com.holymoderation.addon.SettingsManager.*;
import static com.holymoderation.addon.Colors.*;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class Settings {

    private static boolean clearStatsMessage = false;
    private static boolean clearAllStatsMessage = false;

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        String command = message.split(" ")[0];

        if (!command.equals(".clearstats")) {
            clearStatsMessage = false;
        }

        if (!command.equals(".clearallstats")) {
            clearAllStatsMessage = false;
        }

        if (IsArrayContains(SettingsCommands, command)) {
            event.setCancelled(true);
            String[] messageSplit;
            if (IsArrayContains(SettingsWithoutArguments, command)) {
                switch (command) {
                    case (".textlist"):
                        ClientMessage(AQUA + "Список ваших текстов:");
                        if (Texts == null) {
                            ClientMessage(RED + "У вас нет настроенных текстов");
                        } else {
                            String[] splitTexts = GetSplitTexts();
                            for (int i = 0; i < splitTexts.length; i++) {
                                ClientMessage((i + 1) + ". " + splitTexts[i]);
                            }
                        }
                        break;
                    case (".textclear"):
                        Texts = null;
                        ClientMessage(GREEN + "Вы успешно очистили все тексты!");
                        break;
                    case (".getvk"):
                        if (!CheckVK()) {
                            return;
                        }
                        ClientMessage(AQUA + "Ваша ссылка на вк: " + VkUrl);
                        break;
                    case (".dupeip"):
                        DupeIpEnabled = !DupeIpEnabled;
                        if (DupeIpEnabled) {
                            ClientMessage(YELLOW + "Автоматический /dupeip" + GREEN + " ВКЛЮЧЁН");
                        } else {
                            ClientMessage(YELLOW + "Автоматический /dupeip" + RED + " ВЫКЛЮЧЕН");
                        }
                        break;
                    case (".timer"):
                        TimerEnabled = !TimerEnabled;
                        if (TimerEnabled) {
                            ClientMessage(YELLOW + "Таймер" + GREEN + " ВКЛЮЧЁН");
                        } else {
                            ClientMessage(YELLOW + "Таймер" + RED + " ВЫКЛЮЧЁН");
                        }
                        break;
                    case (".counter"):
                        CounterEnabled = !CounterEnabled;
                        if (CounterEnabled) {
                            ClientMessage(YELLOW + "Счётчик" + GREEN + " ВКЛЮЧЁН");
                        } else {
                            ClientMessage(YELLOW + "Счётчик" + RED + " ВЫКЛЮЧЁН");
                        }
                        break;
                    case (".getstats"):
                        ClientMessage(AQUA + "Всего проверок: " + GOLD + Checkouts);
                        ClientMessage(AQUA + "Всего репортов: " + GOLD + Reports);
                        ClientMessage(AQUA + "Всего простых проверок: " + GOLD + (Checkouts - Reports));
                        ClientMessage(AQUA + "Всего наказаний: " + GOLD + Punishments);
                        ClientMessage(AQUA + "Всего банов: " + GOLD + Bans);
                        ClientMessage(AQUA + "Всего мутов: " + GOLD + Mutes);
                        ClientMessage(AQUA + "Всего успешных гарантов: " + GOLD + Garants);
                        break;
                    case (".clearstats"):
                        if (clearStatsMessage) {
                            TCheckouts = TReports = TNotReports = TPunishments = TBans = TMutes = TGarants = 0;
                            ClientMessage(GREEN + "Вы успешно очистили вашу статистику!");
                        } else {
                            ClientMessage(AQUA + "Вы " + RED + "УВЕРЕНЫ" + AQUA
                                    + ", что хотите очистить вашу статистику? Если да, то напишите команду ещё раз");
                        }
                        clearStatsMessage = !clearStatsMessage;
                        break;
                    case (".clearallstats"):
                        if (clearAllStatsMessage) {
                            Checkouts = Reports = NotReports = Punishments = Bans = Mutes = TGarants = 0;
                            ClientMessage(GREEN + "Вы успешно очистили " + RED + "ВСЮ" + GREEN + " вашу статистику!");
                        } else {
                            ClientMessage(AQUA + "Вы " + RED + "УВЕРЕНЫ" + AQUA + ", что хотите очистить " + RED
                                    + "ВСЮ" + AQUA + " вашу статистику? Если да, то напишите команду ещё раз");
                        }
                        clearAllStatsMessage = !clearAllStatsMessage;
                        break;
                    case (".addreport"):
                        Checkouts += 1;
                        TCheckouts += 1;
                        Reports += 1;
                        TReports += 1;
                        ClientMessage(GREEN + "Вы успешно добавили репорт!");
                        break;
                    case (".removereport"):
                        Checkouts -= 1;
                        TCheckouts -= 1;
                        Reports -= 1;
                        TReports -= 1;
                        ClientMessage(GREEN + "Вы успешно удалили репорт!");
                        break;
                    case (".addnotreport"):
                        Checkouts += 1;
                        TCheckouts += 1;
                        NotReports += 1;
                        TNotReports += 1;
                        ClientMessage(GREEN + "Вы успешно добавили простую проверку!");
                        break;
                    case (".removenotreport"):
                        Checkouts -= 1;
                        TCheckouts -= 1;
                        NotReports -= 1;
                        TNotReports -= 1;
                        ClientMessage(GREEN + "Вы успешно удалили простую проверку!");
                        break;
                    case (".autocopy"):
                        AutoAnyDeskEnabled = !AutoAnyDeskEnabled;
                        ClientMessage(YELLOW + "Автоматическое копирование айди AnyDesk " + (AutoAnyDeskEnabled ? GREEN + "ВКЛЮЧЕНО" : RED + "ВЫКЛЮЧЕНО"));
                        break;
                    case (".vanish"):
                        AutoVanishEnabled = !AutoVanishEnabled;
                        ClientMessage(YELLOW + "Автоматический ваниш " + (AutoVanishEnabled ? GREEN + "ВКЛЮЧЁН" : RED + "ВЫКЛЮЧЕН"));
                        break;
                    case (".autotp"):
                        AutoTpEnabled = !AutoTpEnabled;
                        ClientMessage(YELLOW + "Атоматический телепорт на /warp logo " + (AutoTpEnabled ? GREEN + "ВКЛЮЧЁН" : RED + "ВЫКЛЮЧЕН"));
                        break;
                    case (".vanishstatus"):
                        VanishStatusEnabled = !VanishStatusEnabled;
                        ClientMessage(YELLOW + "Отображение состояния ваниша " + (VanishStatusEnabled ? GREEN + "ВКЛЮЧЕНО" : RED + "ВЫКЛЮЧЕНО"));
                        break;
                    case (".cp"):
                        CPEnabled = !CPEnabled;
                        ClientMessage(YELLOW + "Цветные наказания " + (CPEnabled ? GREEN + "ВКЛЮЧЕНЫ" : RED + "ВЫКЛЮЧЕНЫ"));
                        break;
                }
            } else if (IsArrayContains(SettingsWithOneArgument, command)) {
                messageSplit = message.split(" ", 2);
                switch (command) {
                    case (".setvk"):
                        if (messageSplit.length == 1) {
                            ClientMessage(RED + "Вы не ввели ссылку на вк!");
                            return;
                        }
                        String vkUrl = messageSplit[1];
                        boolean hasSpaces = false;
                        for (int i = 0; i < vkUrl.length(); i++) {
                            if (vkUrl.charAt(i) == ' ') {
                                hasSpaces = true;
                                break;
                            }
                        }
                        if (hasSpaces) {
                            ClientMessage(RED + "В ссылке обнаружены пробелы, пожалуйста, " +
                                    "указывайте ссылку на вк в формате 'vk.com/id'");
                            return;
                        }
                        VkUrl = vkUrl;
                        ClientMessage(GREEN + "Теперь ваша ссылка на вк: " + VkUrl);
                        break;
                    case (".textadd"):
                        if (messageSplit.length == 1) {
                            ClientMessage(RED + "Вы не указали текст!");
                            return;
                        }
                        String text = messageSplit[1];
                        Texts = Texts == null ? text : Texts + "%" + text;
                        ClientMessage(GREEN + "Вы добавили новый текст!");
                        break;
                    case (".textremove"):
                        if (Texts == null) {
                            ClientMessage(RED + "У вас нет настроенных текстов");
                            return;
                        }
                        if (messageSplit.length == 1) {
                            ClientMessage(RED + "Вы не указали номер текста!");
                            return;
                        }
                        String indexText = messageSplit[1];
                        if (!CheckCorrectInt(indexText)) {
                            ClientMessage(RED + "Некорректный номер текста!");
                            return;
                        }
                        int intIndex = Integer.parseInt(indexText) - 1;
                        if (intIndex >= GetSplitTexts().length || intIndex < 0) {
                            ClientMessage(RED + "Элемента с таким номером в списке ваших текстов не существует!");
                            return;
                        }
                        ArrayList<String> textsarlist = new ArrayList<>(Arrays.asList(GetSplitTexts()));
                        textsarlist.remove(intIndex);
                        Texts = null;
                        for (String s : textsarlist) {
                            Texts = Texts == null ? s : Texts + "%" + s;
                        }
                        ClientMessage(RED + "Вы удалили текст номер " + GREEN + messageSplit[1] + "!");
                        break;
                    case (".settimercolor"):
                    case (".setcountercolor"):
                    case (".setvanishcolor"): {
                        if (messageSplit.length == 1) {
                            ClientMessage(RED + "Вы не указали айди цвета!");
                        }
                        String stringColor = "0x" + messageSplit[1];
                        int intColor;
                        {
                            try {
                                intColor = Integer.decode(stringColor);
                            } catch (NumberFormatException e) {
                                ClientMessage(RED + "Некорректный цветовой код!");
                                return;
                            }
                        }
                        switch (messageSplit[0]) {
                            case (".settimercolor"):
                                TCustomColor = intColor;
                                break;
                            case (".setcountercolor"):
                                CCustomColor = intColor;
                                break;
                            case (".setvanishcolor"):
                                VCustomColor = intColor;
                                break;
                        }
                        ClientMessage(GREEN + "Успешно применено!");
                        break;
                    }
                    case (".setvkc"):
                    case (".setqc"):
                    case (".setdefc"):
                    case (".setdesc"): {
                        if (messageSplit.length == 1) {
                            ClientMessage(RED + "Вы не указали цветовой код!");
                        }
                        if (!messageSplit[1].contains("&")) {
                            ClientMessage(RED + "В вашем цветовом коде не обнаружено ключевого знака!");
                            return;
                        } else if (messageSplit[1].contains(" ")) {
                            ClientMessage(RED + "В вашем цветовом коде обнаружены пробелы!");
                            return;
                        }

                        switch (messageSplit[0]) {
                            case (".setvkc"):
                                VkColor = messageSplit[1];
                                break;
                            case (".setqc"):
                                QColor = messageSplit[1];
                                break;
                            case (".setdefc"):
                                DefaultColor = messageSplit[1];
                                break;
                            case (".setdesc"):
                                DescriptionColor = messageSplit[1];
                                break;
                        }
                        ClientMessage(GREEN + "Успешно применено!");
                        break;
                    }
                }
            } else if (IsArrayContains(SettingsWithTwoArguments, command)) {
                messageSplit = message.split(" ", 3);
                switch (command) {
                    case (".textedit"):
                        if (Texts == null) {
                            ClientMessage(RED + "У вас нет настроенных текстов");
                            return;
                        }
                        if (messageSplit.length == 1) {
                            ClientMessage(RED + "Вы не указали номер текста и новый текст!");
                            return;
                        }
                        String indexText = messageSplit[1];
                        if (!CheckCorrectInt(indexText)) {
                            ClientMessage(RED + "Некорректный номер текста!");
                            return;
                        }
                        int index = Integer.parseInt(indexText) - 1;
                        if (messageSplit.length == 2) {
                            ClientMessage(RED + "Вы не указали новый текст!");
                            return;
                        }
                        if (index >= GetSplitTexts().length || index < 0) {
                            ClientMessage(RED + "Элемента с таким номером в списке ваших текстов не существует!");
                            return;
                        }
                        String text = messageSplit[2];
                        String[] textsList = Texts.split("%");
                        textsList[index] = text;
                        Texts = null;
                        for (String t : textsList) {
                            Texts = Texts == null ? t : Texts + "%" + t;
                        }
                        ClientMessage(YELLOW + "Вы изменили текст номер " + GREEN + (index + 1));
                        break;
                    case (".settimercoords"):
                    case (".setcountercoords"):
                    case (".setvanishcoords"): {
                        if (messageSplit.length == 1) {
                            ClientMessage(RED + "Вы не указали X и Y координаты!");
                            return;
                        } else if (messageSplit.length == 2) {
                            ClientMessage(RED + "Вы не указали Y координату!");
                            return;
                        }
                        String xText = messageSplit[1];
                        String yText = messageSplit[2];
                        boolean isXCorrect = CheckCorrectInt(xText);
                        boolean isYCorrect = CheckCorrectInt(yText);

                        if (!isXCorrect || !isYCorrect) {
                            if (!isXCorrect && !isYCorrect)
                                ClientMessage(RED + "Некорректные X и Y координаты!");
                            else if (!isXCorrect)
                                ClientMessage(RED + "Некорректная X координата!");
                            else if (!isYCorrect)
                                ClientMessage(RED + "Некорректная Y координата!");
                            return;
                        }

                        switch (messageSplit[0]) {
                            case (".settimercoords"):
                                TXCoords = Integer.parseInt(xText);
                                TYCoords = Integer.parseInt(yText);
                                break;
                            case (".setcountercoords"):
                                CXCoords = Integer.parseInt(xText);
                                CYCoords = Integer.parseInt(yText);
                                break;
                            case (".setvanishcoords"):
                                VXCoords = Integer.parseInt(xText);
                                VYCoords = Integer.parseInt(yText);
                                break;
                        }
                        ClientMessage(GREEN + "Успешно применено!");
                        break;
                    }
                }
            }

            SaveCfg();
        }
    }
}