package com.holymoderation.addon;

import static com.holymoderation.addon.SettingsManager.*;

import net.labymod.api.event.events.client.gui.RenderGameOverlayEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;
import net.labymod.core.LabyModCore;

import java.awt.*;
import java.util.Arrays;

public class HMManager {

    public static String[] AllCommands = {".hm", ".help", "/sban",
            "/freezing", "/frz", "/unfreezing", "/unfrz", ".freezing",
            ".frz", ".textlist", ".textclear", ".textadd", ".textremove",
            ".textedit", ".setvk", ".getvk", ".dupeip", ".settimercoords",
            ".setcountercoords", ".settimercolor", ".setcountercolor", ".getstats",
            ".clearstats", ".clearallstats", ".counter", ".timer", "/mute",
            "/muteip", "/tempmute", "/tempmuteip", "/ban", "/banip", "/tempban",
            ".addreport", ".removereport", ".removecheckout", ".autocopy",
            ".autoban", ".vanish", ".addcheckout"};

    public static String[] HelpCommands = {".hm", ".help"};

    public static String[] FreezerCommands = {"/sban", "/freezing", "/frz", "/unfreezing", "/unfrz", ".freezing", ".frz"};

    public static String[] SettingsCommands = {".textlist", ".textclear", ".textadd", ".textremove",
            ".textedit", ".setvk", ".getvk", ".dupeip", ".settimercoords", ".setcountercoords",
            ".settimercolor", ".setcountercolor", ".getstats", ".clearstats", ".clearallstats",
            ".counter", ".timer", ".addreport", ".removereport", ".removecheckout",
            ".autocopy", ".autoban", ".vanish", ".addcheckout"};
    public static String[] SettingsWithoutArguments = {".textlist", ".textclear",
            ".getvk", ".dupeip", ".getstats", ".clearstats", ".clearallstats", ".counter", ".timer",
            ".addreport", ".removereport", ".removecheckout", ".autocopy", ".vanish", ".autoban", ".addcheckout"};
    public static String[] SettingsWithOneArgument = {".textadd",
            ".textremove", ".setvk", ".settimercolor", ".setcountercolor"};
    public static String[] SettingsWithTwoArguments = {".textedit", ".settimercoords", ".setcountercoords"};

    public static String[] PunishmentsCommands = {"/mute", "/muteip",
            "/tempmute", "/tempmuteip", "/ban", "/banip", "/tempban"};
    public static String[] TempPunishments = {"/tempmute", "/tempmuteip", "/tempban"};
    public static String[] InfinityPunishments = {"/mute", "/muteip", "/ban", "/banip"};
    public static String[] BanCommands = {"/ban", "/banip", "/tempban"};
    public static String[] MuteCommands = {"/mute", "/muteip", "/tempmute", "/tempmuteip"};

    public static String[] Chars = {"!", "/", "#", "$", "%", "&", "'", "(", ")", "*", "+", "-", ",", ".", ":",
            ";", "<", ">", "=", "?", "@", "[", "]", "^", "`", "|", "~", "{", "}"};

    public static void ChatMessage(String message) {
        LabyModCore.getMinecraft().getPlayer().sendChatMessage(message);
    }

    public static void ClientMessage(String message) {
        Minecraft.getInstance().player.sendMessage(new StringTextComponent(message), null);
    }

    public static boolean IsArrayContains(String[] array, String value) {
        return Arrays.asList(array).contains(value);
    }

    public static boolean CheckCorrectInt(String value) {
        int newValue = 0; {
            try {
                newValue = Integer.parseInt(value);
                return(true);
            }
            catch (NumberFormatException e) {
                return(false);
            }
        }
    }

    public static boolean CheckCorrectLong(String value) {
        long newValue = 0; {
            try {
                newValue = Long.parseLong(value);
                return(true);
            }
            catch (NumberFormatException e) {
                return(false);
            }
        }
    }

    public static int Rainbow(int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0D);
        rainbowState %= 360.0D;
        return Color.getHSBColor((float)(rainbowState / 360.0D), 0.5F, 1.0F).getRGB();
    }

    public static void DrawString(RenderGameOverlayEvent event, String text, int x, int y, int color) {
        LabyModCore.getMinecraft().getFontRenderer().drawString(event.getMatrixStack(), text, x, y, color);
    }

    public static void EndCheckOut() {
        ChatMessage("/freezing " + Player);
        ChatMessage("/prova");
        Player = null;
    }

    public static boolean CheckVK() {
        if (VkUrl == null) {
            ClientMessage(Colors.RED + "У вас не установлена ссылка на вк!");
            return false;
        }
        return true;
    }

    public static boolean CheckTimeFormat(String message) {
        char lastChar = message.charAt(message.length() - 1);
        String stringTime = message.substring(0, message.length() - 1);
        if (lastChar != 'h' && lastChar != 'H' && lastChar != 'd' && lastChar != 'D' || message.length() > 4 || !CheckCorrectInt(stringTime)) {
            ClientMessage(Colors.RED + "Неверный формат времени! Должно быть 1-999h/H или 1-999d/D");
            return false;
        }
        return true;
    }

    public static void Punish(String punishCommand, String player, String reason, boolean addVk) {
        if (addVk)
            ChatMessage(punishCommand + " " + player + " " + reason + " | Вопросы? " + VkUrl + " -s");
        else
            ChatMessage(punishCommand + " " + player + " " + reason + " -s");

        if (punishCommand.equals("/mute") || punishCommand.equals("/muteip") || punishCommand.equals("/tempmute") || punishCommand.equals("/tempmuteip"))
        {
            IncreaseInfo("mutes");
        }

        else if (punishCommand.equals("/ban") || punishCommand.equals("/banip") || punishCommand.equals("/tempban"))
        {
            IncreaseInfo("bans");
        }
        IncreaseInfo("punishments");
    }

    public static void Punish(String punishCommand, String player, String time, String reason, boolean addVk) {
        String command = punishCommand;
        command.toCharArray()[0] = '/';
        if (addVk)
            ChatMessage(command + " " + player + " " + time + " " + reason + " | Вопросы? " + VkUrl + " -s");
        else
            ChatMessage(command + " " + player + " " + time + " " + reason + " -s");

        if (command.equals("/mute") || command.equals("/muteip") || command.equals("/tempmute") || command.equals("/tempmuteip"))
        {
            IncreaseInfo("mutes");
        }

        else if (command.equals("/ban") || command.equals("/banip") || command.equals("/tempban"))
        {
            IncreaseInfo("bans");
        }
        IncreaseInfo("punishments");
    }

    public static void IncreaseInfo(String info) {
        switch (info) {
            case ("checkouts"):
                Checkouts++;
                TCheckouts++;
                break;
            case ("reports"):
                Reports++;
                TReports++;
                break;
            case ("punishments"):
                Punishments++;
                TPunishments++;
                break;
            case ("bans"):
                Bans++;
                TBans++;
                break;
            case ("mutes"):
                Mutes++;
                TMutes++;
                break;
        }
    }

    public static String[] GetSplitTexts() {
        return Texts.split("%", 0);
    }
}