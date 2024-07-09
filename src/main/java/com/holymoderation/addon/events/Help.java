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
        if (IsArrayContains(HelpCommands, command))
        {
            event.setCancelled(true);
            ClientMessage(BLUE + "HM Help:");
            ClientMessage(GOLD + ".hm" + RESET + " или " + GOLD + ".help" + RESET + " - показывает HM Help");
            ClientMessage("");
            ClientMessage(DARK_RED + "ПОМОЩЬ ПО ПРОВЕРКАМ:");
            ClientMessage(GOLD + "/freezing" + GREEN + " player" + RESET + " или " + GOLD + "/frz" + GREEN + " player" + RESET + " - замораживает игрока и начинает проверку");
            ClientMessage(GOLD + "/unfreezing" + RESET + " или " + GOLD + "/unfrz" + RESET + " - размораживает игрока, который находится на вашей проверке и заканчивает проверку");
            ClientMessage(GOLD + "/sban" + GREEN + " time reason" + RESET + " - банит игрока, который сейчас на вашей проверке");
            ClientMessage(GOLD + ".freezing" + GREEN + " player" + RESET + " или " + GOLD + ".frz" + GREEN + " player" + RESET + " - просто замораживает/размораживает игрока, " + "при условии, что он не находится на вашей проверке");
            ClientMessage("");
            ClientMessage(DARK_RED + "ПОМОЩЬ ПО НАСТРОЙКЕ АДДОНА:");
            ClientMessage(GOLD + ".textlist" + RESET + RESET + " - показывает настроенные тексты");
            ClientMessage(GOLD + ".textadd" + GREEN + " text" + RESET + " - добавляет новый текст");
            ClientMessage(GOLD + ".textremove" + GREEN + " number" + RESET + " - удаляет текст по его номеру");
            ClientMessage(GOLD + ".textedit" + GREEN + " number newtext" + RESET + " - изменяет текст на новый");
            ClientMessage(GOLD + ".textclear" + GREEN + " number newtext" + RESET + " - очищает тексты");
            ClientMessage(GOLD + ".setvk" + GREEN + " your vk" + RESET + " - установливает ссылку на вк (для банов), устанавливайте в формате 'vk.com/id'");
            ClientMessage(GOLD + ".getvk" + RESET + " - показывает установленный вк");
            ClientMessage(GOLD + ".dupeip" + RESET + " - включает/выключает автоматический /dupeip при проверке");
            ClientMessage(GOLD + ".autocopy" + RESET + " - включает/выключает автоматическое копирование айди AnyDesk от игрока");
            ClientMessage(GOLD + ".counter" + RESET + " - включает/выключает отображение счётчика");
            ClientMessage(GOLD + ".getstats" + RESET + " - выводит статистику за всё время");
            ClientMessage(GOLD + ".clearstats" + RESET + " - очищает временную информацию счётчика");
            ClientMessage(GOLD + ".addreport" + RESET + " - добавляет репорт (заменяет обычную проверку в статистике на репорт)");
            ClientMessage(GOLD + ".removereport" + RESET + " - удаляет репорт (заменяет репорт в статистике на обычную проверку)");
            ClientMessage(GOLD + ".addcheckout" + RESET + " - добавялет проверку");
            ClientMessage(GOLD + ".removecheckout" + RESET + " - удаляет проверку из статистики");
            ClientMessage(GOLD + ".settimercoords" + GREEN + " x y" + RESET + " - устанавливает позицию для таймера (считая от левого верхнего угла)");
            ClientMessage(GOLD + ".setcountercoords" + GREEN + " x y" + RESET + " - устанавливает позицию для счётчика (считая от левого верхнего угла)");
            ClientMessage(GOLD + ".settimercolor" + GREEN + " colorid" + RESET + " - устанавливает свой цвет для таймера");
            ClientMessage(GOLD + ".setcountercolor" + GREEN + " colorid" + RESET + " - устанавливает свой цвет для счётчика");
            ClientMessage("Пример: " + GREEN + "ff0000" + RESET + " - красный");
            ClientMessage("Чтобы вернуть радужный цвет напишите" + GOLD + " .setcolor" + GREEN + " 0");
            ClientMessage("");
            ClientMessage(DARK_RED + "СПИСОК НАКАЗАНИЙ:");
            ClientMessage(GOLD + "/mute" + GREEN + " nick reason" + RESET + " - мут навсегда");
            ClientMessage(GOLD + "/muteip" + GREEN + " nick reason" + RESET + " - мут по айпи навсегда");
            ClientMessage(GOLD + "/tempmute" + GREEN + " nick time reason" + RESET + " - мут на время");
            ClientMessage(GOLD + "/tempmuteip" + GREEN + " nick time reason" + RESET + " - мут по айпи на время");
            ClientMessage(GOLD + "/ban" + GREEN + " nick reason" + RESET + " - бан навсегда");
            ClientMessage(GOLD + "/banip" + GREEN + " nick reason" + RESET + " - бан по айпи на время/навсегда");
            ClientMessage(GOLD + "/tempban" + GREEN + " nick time reason" + RESET + " - бан на время");
            ClientMessage("");
            ClientMessage(GREEN + "Ссылки на инструкцию к моду и исходный код можно найти в файле 'README.TXT'");
        }
    }
}