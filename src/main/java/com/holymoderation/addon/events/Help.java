package com.holymoderation.addon.events;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import static com.holymoderation.addon.HMManager.*;
import static com.holymoderation.addon.Colors.*;

public class Help {

    public static String[] messageSplit;

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        messageSplit = message.split(" ");
        String command = messageSplit[0];
        if (IsArrayContains(HelpCommands, command)) {
            event.setCancelled(true);
            ClientMessage(BLUE + "HM Help");
            ClientMessage(GOLD + ".hm" + RESET + " или " + GOLD + ".help" + RESET + " - показывает HM Help");
            ClientMessage("");
            ClientMessage(DARK_RED + "ПОМОЩЬ ПО ПРОВЕРКАМ:");
            ClientMessage(GOLD + "/freezing" + GREEN + " player" + RESET + " или " + GOLD + "/frz" + GREEN + " player" + RESET + " - замораживает игрока и начинает проверку");
            ClientMessage(GOLD + "/unfreezing" + RESET + " или " + GOLD + "/unfrz" + RESET + " - размораживает игрока, который находится на вашей проверке и заканчивает проверку");
            ClientMessage(GOLD + "/sban" + GREEN + " time reason" + RESET + " - банит игрока, который сейчас на вашей проверке");
            ClientMessage(GOLD + ".freezing" + GREEN + " player" + RESET + " или " + GOLD + ".frz" + GREEN + " player" + RESET + " - просто замораживает/размораживает игрока, " + "при условии, что он не находится на вашей проверке");
            ClientMessage("");
            ClientMessage(DARK_RED + "НАСТРОЙКА АДДОНА:");
            ClientMessage("");
            ClientMessage(RED + "ВК:");
            ClientMessage(GOLD + ".setvk" + GREEN + " your vk" + RESET + " - установливает ссылку на вк (для банов), устанавливайте в формате 'vk.com/id'");
            ClientMessage(GOLD + ".getvk" + RESET + " - показывает установленный вк");
            ClientMessage("");
            ClientMessage(RED + "ТЕКСТЫ:");
            ClientMessage(GOLD + ".textlist" + RESET + RESET + " - показывает настроенные тексты");
            ClientMessage(GOLD + ".textadd" + GREEN + " text" + RESET + " - добавляет новый текст");
            ClientMessage(GOLD + ".textremove" + GREEN + " number" + RESET + " - удаляет текст по его номеру");
            ClientMessage(GOLD + ".textedit" + GREEN + " number newtext" + RESET + " - изменяет текст на новый");
            ClientMessage(GOLD + ".textclear" + GREEN + " number newtext" + RESET + " - очищает тексты");
            ClientMessage("");
            ClientMessage(RED + "ПЕРЕКЛЮЧАТЕЛИ:");
            ClientMessage(GOLD + ".dupeip" + RESET + " - включает/выключает автоматический /dupeip при проверке");
            ClientMessage(GOLD + ".vanish" + RESET + " - включает/выключает автоматический ваниш");
            ClientMessage(GOLD + ".autocopy" + RESET + " - включает/выключает автоматическое копирование айди AnyDesk от игрока");
            ClientMessage(GOLD + ".autotp" + RESET + " - включает/выключает автоматический телепорт на /warp logo при начале проверки");
            ClientMessage("");
            ClientMessage(RED + "ТАЙМЕР:");
            ClientMessage(GOLD + ".timer" + RESET + " - включает/выключает отображение таймера при проверке");
            ClientMessage(GOLD + ".settimercoords" + GREEN + " x y" + RESET + " - устанавливает позицию для таймера (считая от левого верхнего угла)");
            ClientMessage(GOLD + ".settimercolor" + GREEN + " colorid" + RESET + " - устанавливает свой цвет для таймера");
            ClientMessage("");
            ClientMessage(RED + "СЧЁТЧИК:");
            ClientMessage(GOLD + ".counter" + RESET + " - включает/выключает отображение счётчика");
            ClientMessage(GOLD + ".setcountercoords" + GREEN + " x y" + RESET + " - устанавливает позицию для счётчика (считая от левого верхнего угла)");
            ClientMessage(GOLD + ".setcountercolor" + GREEN + " colorid" + RESET + " - устанавливает свой цвет для счётчика");
            ClientMessage(GOLD + ".getstats" + RESET + " - выводит статистику за всё время");
            ClientMessage(GOLD + ".clearstats" + RESET + " - очищает временную информацию счётчика (отображаемую)");
            ClientMessage(GOLD + ".clearallstats" + RESET + " - очищает информацию счётчика за всё время");
            ClientMessage(GOLD + ".addreport" + RESET + " - добавляет репорт");
            ClientMessage(GOLD + ".removereport" + RESET + " - удаляет репорт");
            ClientMessage(GOLD + ".addnotreport" + RESET + " - добавялет простую проверку");
            ClientMessage(GOLD + ".removenotreport" + RESET + " - удаляет простую проверку");
            ClientMessage(GOLD + ".addgarant" + RESET + " - добавляет гарант");
            ClientMessage(GOLD + ".removegarant" + RESET + " - удаляет гарант");
            ClientMessage("");
            ClientMessage(RED + "СТАТУС ВАНИША:");
            ClientMessage(GOLD + ".vanishstatus" + RESET + " - включает/выключает отображение состояния ваниша");
            ClientMessage(GOLD + ".setvanishcoords" + GREEN + " x y" + RESET + " - устанавливает позицию для отображения статуса ваниша");
            ClientMessage(GOLD + ".setvanishcolor" + GREEN + " colorid" + RESET + " - устанавливает свой цвет для отображения статуса ваниша");
            ClientMessage("");
            ClientMessage(RED + "ГАЙД ПО ЦВЕТАМ:");
            ClientMessage("Пример: " + GREEN + "ff0000" + RESET + " - красный");
            ClientMessage("Чтобы вернуть радужный цвет напишите" + GOLD + " .settimercolor/.setcountercolor" + GREEN + " 0");
            ClientMessage("");
            ClientMessage(RED + "ЦВЕТНЫЕ НАКАЗАНИЯ:");
            ClientMessage(GOLD + ".cp" + RESET + " - NUH UH!");
            ClientMessage(GOLD + ".setvkc" + GREEN + " color" + RESET + " - настроить цвет ссылки вк");
            ClientMessage(GOLD + ".setqc" + GREEN + " color" + RESET + " - настроить цвет 'Вопросы?'");
            ClientMessage(GOLD + ".setdefc" + GREEN + " color" + RESET + " - настроить основной цвет");
            ClientMessage(GOLD + ".setdesc" + GREEN + " color" + RESET + " - настроить цвет описания наказания (то, что в скобочках)");
            ClientMessage("");
            ClientMessage(DARK_RED + "СПИСОК НАКАЗАНИЙ:");
            ClientMessage(GOLD + "/mute" + GREEN + " nick reason" + RESET + " - мут навсегда");
            ClientMessage(GOLD + "/muteip" + GREEN + " nick reason" + RESET + " - мут по айпи навсегда");
            ClientMessage(GOLD + "/tempmute" + GREEN + " nick time reason" + RESET + " - мут на время");
            ClientMessage(GOLD + "/tempmuteip" + GREEN + " nick time reason" + RESET + " - мут по айпи на время");
            ClientMessage(GOLD + "/ban" + GREEN + " nick reason" + RESET + " - бан навсегда");
            ClientMessage(GOLD + "/banip" + GREEN + " nick reason" + RESET + " - бан по айпи на время/навсегда");
            ClientMessage(GOLD + "/tempban" + GREEN + " nick time reason" + RESET + " - бан на время");
        }
    }
}