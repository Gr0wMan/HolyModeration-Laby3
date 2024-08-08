package com.holymoderation.addon.events;

import static com.holymoderation.addon.HMManager.*;
import static com.holymoderation.addon.SettingsManager.*;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.gui.RenderGameOverlayEvent;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

public class Timer {

    private static StopWatch stopWatch = null;

    @Subscribe
    public void OnRenderGameOverlay(RenderGameOverlayEvent event) {
        if (Player != null) {
            if (TimerEnabled) {
                if (stopWatch == null) {
                    stopWatch = new StopWatch();
                    stopWatch.start();
                }

                DrawString(event, "Текущая проверка:", TXCoords, TYCoords,
                        TCustomColor == 0x0 ? Rainbow(300) : TCustomColor);
                String secondsString = (stopWatch.getTime(TimeUnit.SECONDS) - stopWatch.getTime(TimeUnit.MINUTES) * 60) < 10
                        ? "0" + (stopWatch.getTime(TimeUnit.SECONDS) - stopWatch.getTime(TimeUnit.MINUTES) * 60)
                        : "" + (stopWatch.getTime(TimeUnit.SECONDS) - stopWatch.getTime(TimeUnit.MINUTES) * 60);
                String minutesString = stopWatch.getTime(TimeUnit.MINUTES) < 10
                        ? "0" + stopWatch.getTime(TimeUnit.MINUTES) : "" + stopWatch.getTime(TimeUnit.MINUTES);
                DrawString(event, Player + " | " + minutesString + ":" + secondsString,
                        TXCoords, (TYCoords + 10), TCustomColor == 0x0 ? Rainbow(300) : TCustomColor);
            }
        } else {
            stopWatch = null;
        }
    }
}
