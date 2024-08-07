package com.holymoderation.addon;

import net.labymod.api.event.events.client.chat.MessageReceiveEvent;
import net.labymod.api.event.events.client.chat.MessageSendEvent;
import net.labymod.api.event.events.client.gui.RenderGameOverlayEvent;
import net.labymod.api.event.events.network.server.DisconnectServerEvent;
import net.labymod.api.event.events.network.server.LoginServerEvent;
import net.labymod.api.event.events.network.server.ServerSwitchEvent;

public class SettingsManager {

    //Global
    public static String Moder = null;
    public static String Player;
    public static String VkUrl;
    public static boolean VanishEnabled = false;
    public static boolean InHub = false;
    //Global

    //Events
    public static RenderGameOverlayEvent RGOEvent;
    public static ServerSwitchEvent SSEvent;
    public static MessageReceiveEvent MREvent;
    public static MessageSendEvent MSEvent;
    public static LoginServerEvent LSEvent;
    public static DisconnectServerEvent DSEvent;
    //Events

    //Timer
    public static boolean TimerEnabled;
    public static int TXCoords;
    public static int TYCoords;
    public static int TCustomColor;
    //Timer

    //Counter
    public static boolean CounterEnabled;
    public static int CXCoords;
    public static int CYCoords;
    public static int CCustomColor;

    //all time
    public static int Checkouts;
    public static int Reports;
    public static int NotReports;
    public static int Punishments;
    public static int Bans;
    public static int Mutes;
    public static int Garants;
    //all time

    //temp
    public static int TCheckouts;
    public static int TReports;
    public static int TNotReports;
    public static int TPunishments;
    public static int TBans;
    public static int TMutes;
    public static int TGarants;
    //temp
    //Counter

    //Vanish
    public static boolean VanishStatusEnabled;
    public static int VXCoords;
    public static int VYCoords;
    public static int VCustomColor;
    //Vanish

    //Freezer
    public static boolean DupeIpEnabled;
    public static boolean AutoAnyDeskEnabled;
    public static boolean AutoVanishEnabled;
    public static boolean AutoTpEnabled;
    public static String Texts;
    //Freezer

    //BanColors
    public static boolean CPEnabled;
    public static String VkColor;
    public static String QColor;
    public static String DefaultColor;
    public static String DescriptionColor;
    //BanColors
}