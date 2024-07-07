package com.holymoderation.addon.events;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import com.holymoderation.addon.utils.Colors;
import com.holymoderation.addon.utils.ChatManager;

public class Help {

    public static String[] messageSplit;

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        messageSplit = message.split(" ");
        String command = messageSplit[0];
        if (ChatManager.IsArrayContains(ChatManager.HelpCommands, command))
        {
            event.setCancelled(true);
            ChatManager.ClientMessage(Colors.BLUE + "HM Help:");
            ChatManager.ClientMessage(Colors.GOLD + ".hm" + Colors.RESET + " или "
                    + Colors.GOLD + ".help" + Colors.RESET + " - показывает HM Help");
            ChatManager.ClientMessage("");
            ChatManager.ClientMessage(Colors.DARK_RED + "ПОМОЩЬ ПО ПРОВЕРКАМ:");
            ChatManager.ClientMessage(Colors.GOLD + "/freezing" + Colors.GREEN + " player" + Colors.RESET
                    + " или " + Colors.GOLD + "/frz" + Colors.GREEN + " player" + Colors.RESET
                    + " - замораживает игрока и начинает проверку");
            ChatManager.ClientMessage(Colors.GOLD + "/unfreezing" + Colors.RESET
                    + " или " + Colors.GOLD + "/unfrz" + Colors.RESET
                    + " - размораживает игрока, который находится на вашей проверке и заканчивает проверку");
            ChatManager.ClientMessage(Colors.GOLD + "/sban" + Colors.GREEN + " time reason" + Colors.RESET
                    + " - банит игрока, который сейчас на вашей проверке");
            ChatManager.ClientMessage(Colors.GOLD + ".freezing" + Colors.GREEN + " player" + Colors.RESET
                    + " или " + Colors.GOLD + ".frz" + Colors.GREEN + " player" + Colors.RESET
                    + " - просто замораживает/размораживает игрока, " +
                    "при условии, что он не находится на вашей проверке");
            ChatManager.ClientMessage("");
            ChatManager.ClientMessage(Colors.DARK_RED + "ПОМОЩЬ ПО НАСТРОЙКЕ АДДОНА:");
            ChatManager.ClientMessage(Colors.GOLD + ".textlist" + Colors.RESET + Colors.RESET
                    + " - показывает настроенные тексты");
            ChatManager.ClientMessage(Colors.GOLD + ".textadd" + Colors.GREEN + " text" + Colors.RESET
                    + " - добавляет новый текст");
            ChatManager.ClientMessage(Colors.GOLD + ".textremove" + Colors.GREEN + " number" + Colors.RESET
                    + " - удаляет текст по его номеру");
            ChatManager.ClientMessage(Colors.GOLD + ".textedit" + Colors.GREEN + " number newtext" + Colors.RESET
                    + " - изменяет текст на новый");
            ChatManager.ClientMessage(Colors.GOLD + ".textclear" + Colors.GREEN + " number newtext" + Colors.RESET
                    + " - очищает тексты");
            ChatManager.ClientMessage(Colors.GOLD + ".setvk" + Colors.GREEN + " your vk" + Colors.RESET
                    + " - установливает ссылку на вк (для банов), устанавливайте в формате 'vk.com/id'");
            ChatManager.ClientMessage(Colors.GOLD + ".getvk" + Colors.RESET
                    + " - показывает установленный вк");
            ChatManager.ClientMessage(Colors.GOLD + ".dupeip" + Colors.RESET
                    + " - включает/выключает автоматический /dupeip при проверке");
            ChatManager.ClientMessage(Colors.GOLD + ".counter" + Colors.RESET + " - включает/выключает отображение счётчика");
            ChatManager.ClientMessage(Colors.GOLD + ".getstats" + Colors.RESET
                    + " - выводит статистику за всё время");
            ChatManager.ClientMessage(Colors.GOLD + ".clearstats" + Colors.RESET
                    + " - очищает временную информацию счётчика");
            ChatManager.ClientMessage(Colors.GOLD + ".addreport" + Colors.RESET
                    + " - добавляет репорт (заменяет обычную проверку в статистике на репорт)");
            ChatManager.ClientMessage(Colors.GOLD + ".removereport" + Colors.RESET
                    + " - удаляет репорт (заменяет репорт в статистике на обычную проверку)");
            ChatManager.ClientMessage(Colors.GOLD + ".removecheckout" + Colors.RESET
                    + " - удаляет проверку из статистики");
            ChatManager.ClientMessage(Colors.GOLD + ".settimercoords" + Colors.GREEN + " x y" + Colors.RESET
                    + " - устанавливает позицию для таймера (считая от левого верхнего угла)");
            ChatManager.ClientMessage(Colors.GOLD + ".setcountercoords" + Colors.GREEN + " x y" + Colors.RESET
                    + " - устанавливает позицию для счётчика (считая от левого верхнего угла)");
            ChatManager.ClientMessage(Colors.GOLD + ".settimercolor" + Colors.GREEN + " colorid" + Colors.RESET
                    + " - устанавливает свой цвет для таймера");
            ChatManager.ClientMessage(Colors.GOLD + ".setcountercolor" + Colors.GREEN + " colorid" + Colors.RESET
                    + " - устанавливает свой цвет для счётчика");
            ChatManager.ClientMessage("Пример: " + Colors.GREEN + "ff0000" + Colors.RESET + " - красный");
            ChatManager.ClientMessage("Чтобы вернуть радужный цвет напишите" + Colors.GOLD + " .setcolor"
                    + Colors.GREEN + " 0");
            ChatManager.ClientMessage("");
            ChatManager.ClientMessage(Colors.DARK_RED + "СПИСОК НАКАЗАНИЙ:");
            ChatManager.ClientMessage(Colors.GOLD + "/mute" + Colors.GREEN + " nick reason" + Colors.RESET
                    + " - мут навсегда");
            ChatManager.ClientMessage(Colors.GOLD + "/muteip" + Colors.GREEN + " nick reason" + Colors.RESET
                    + " - мут по айпи навсегда");
            ChatManager.ClientMessage(Colors.GOLD + "/tempmute" + Colors.GREEN + " nick time reason" + Colors.RESET
                    + " - мут на время");
            ChatManager.ClientMessage(Colors.GOLD + "/tempmuteip" + Colors.GREEN + " nick time reason" + Colors.RESET
                    + " - мут по айпи на время");
            ChatManager.ClientMessage(Colors.GOLD + "/ban" + Colors.GREEN + " nick reason" + Colors.RESET
                    + " - бан навсегда");
            ChatManager.ClientMessage(Colors.GOLD + "/banip" + Colors.GREEN + " nick reason" + Colors.RESET
                    + " - бан по айпи на время/навсегда");
            ChatManager.ClientMessage(Colors.GOLD + "/tempban" + Colors.GREEN + " nick time reason" + Colors.RESET
                    + " - бан на время");
            ChatManager.ClientMessage("");
            ChatManager.ClientMessage(Colors.GREEN + "Ссылки на инструкцию к моду и исходный код можно найти в файле 'README.TXT'");

        }
    }
}