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
            ".addreport", ".removereport", ".removecheckout", ".autocopy"};

    public static String[] HelpCommands = {".hm", ".help"};

    public static String[] FreezerCommands = {"/sban", "/freezing", "/frz", "/unfreezing", "/unfrz", ".freezing", ".frz"};

    public static String[] SettingsCommands = {".textlist", ".textclear", ".textadd", ".textremove",
            ".textedit", ".setvk", ".getvk", ".dupeip", ".settimercoords", ".setcountercoords",
            ".settimercolor", ".setcountercolor", ".getstats", ".clearstats", ".clearallstats", ".counter",
            ".addreport", ".removereport", ".removecheckout", ".autocopy"};
    public static String[] SettingsWithoutArguments = {".textlist", ".textclear",
            ".getvk", ".dupeip", ".getstats", ".clearstats", ".clearallstats", ".counter",
            ".addreport", ".removereport", ".removecheckout", ".autocopy"};
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
}