package com.holymoderation.addon.utils;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.network.server.LoginServerEvent;
import org.apache.commons.io.IOUtils;

public class UpdateChecker {

    private static double version = 2.2;

    private static boolean needUpdate = false;

    private static String updateURL = "https://raw.githubusercontent.com/Gr0wMan/HolyModeration/master/Labymod/3/src/main/resources/version.txt";

    @Subscribe
    public void onLoginServer(LoginServerEvent event) {
        try (InputStream inputStream = new URL(updateURL).openStream())
        {
            ChatManager.ClientMessage(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
        }
        catch (IOException e) {
            ChatManager.ClientMessage(e.toString());
        }
        if (needUpdate) {
            ChatManager.ClientMessage(Colors.RED + "Доступна новая версия аддона HolyModeration. Пожалуйста, установите новую версию.");
        }
    }
}
