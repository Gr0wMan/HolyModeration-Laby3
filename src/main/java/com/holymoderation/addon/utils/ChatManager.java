package com.holymoderation.addon.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;

import net.labymod.core.LabyModCore;

import java.util.Arrays;

public class ChatManager {

    public static String[] AllCommands = {".hm", ".help", "/sban",
            "/freezing", "/frz", "/unfreezing", "/unfrz", ".freezing",
            ".frz", ".textlist", ".textclear", ".textadd", ".textremove",
            ".textedit", ".setvk", ".getvk", ".dupeip", ".settimercoords",
            "/ban", ".setcountercoords", ".settimercolor", ".setcountercolor",
            ".getstats", ".clearstats", ".clearallstats", ".counter", "/mute",
            "/muteip", "/tempmute", "/tempmuteip", "/banip", "/tempban",
            ".addreport", ".removereport", ".removecheckout"};

    public static String[] HelpCommands = {".hm", ".help"};

    public static String[] FreezerCommands = {"/sban", "/freezing", "/frz", "/unfreezing", "/unfrz", ".freezing", ".frz"};

    public static String[] SettingsCommands = {".textlist", ".textclear", ".textadd", ".textremove",
            ".textedit", ".setvk", ".getvk", ".dupeip", ".settimercoords", ".setcountercoords",
            ".settimercolor", ".setcountercolor", ".getstats", ".clearstats", ".clearallstats", ".counter",
            ".addreport", ".removereport", ".removecheckout"};
    public static String[] SettingsWithoutArguments = {".textlist", ".textclear",
            ".getvk", ".dupeip", ".getstats", ".clearstats", ".clearallstats", ".counter",
            ".addreport", ".removereport", ".removecheckout"};
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


    public static void SendMessage(String message) {
        LabyModCore.getMinecraft().getPlayer().sendChatMessage(message);
    }

    public static void ClientMessage(String message) {
        Minecraft.getInstance().player.sendMessage(new StringTextComponent(message), null);
    }

    public static boolean IsArrayContains(String[] array, String value) {
        return Arrays.asList(array).contains(value);
    }

    public static boolean CheckCorrectInt(String message) {
        int value = 0; {
            try {
                value = Integer.parseInt(message);
                return(true);
            }
            catch (NumberFormatException e) {
                return(false);
            }
        }
    }
}