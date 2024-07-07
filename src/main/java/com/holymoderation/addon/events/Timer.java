package com.holymoderation.addon.events;

import java.util.concurrent.TimeUnit;

import com.holymoderation.addon.utils.RenderHelper;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.gui.RenderGameOverlayEvent;

import org.apache.commons.lang3.time.StopWatch;

public class Timer {

    private static StopWatch stopWatch;

    private static String player = null;

    private static int xCoords = 0;
    private static int yCoords = 0;

    private static int customColor = 0x0;

    @Subscribe
    public void onRender(RenderGameOverlayEvent event) {
        if (player != null) {
            RenderHelper.DrawString(event, "Текущая проверка:", xCoords, yCoords,
                    customColor == 0x0 ? RenderHelper.Rainbow(300) : customColor);
            RenderHelper.DrawString(event, player + " | " + stopWatch.getTime(TimeUnit.MINUTES) + ":"
                    + (stopWatch.getTime(TimeUnit.SECONDS) - stopWatch.getTime(TimeUnit.MINUTES)*60),
                    xCoords, (yCoords + 10), customColor == 0x0 ? RenderHelper.Rainbow(300) : customColor);
        }
    }

    public static void StopWatchStart() {
        stopWatch = new StopWatch();
        stopWatch.start();
    }

    public static int GetXCoords() {
        return xCoords;
    }

    public static int GetYCoords() {
        return yCoords;
    }

    public static void SetXCoords(int value) {
        xCoords = value;
    }

    public static void SetYCoords(int value) {
        yCoords = value;
    }

    public static int GetCustomColor() {
        return customColor;
    }

    public static void SetCustomColor(int color) {
        customColor = color;
    }

    public static void SetPlayer(String value) {
        player = value;
    }
}
