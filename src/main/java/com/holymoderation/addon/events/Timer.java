package com.holymoderation.addon.events;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.gui.RenderGameOverlayEvent;

import static com.holymoderation.addon.HMManager.*;

import static com.holymoderation.addon.SettingsManager.*;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

public class Timer {

    private static StopWatch stopWatch = null;

    @Subscribe
    public void OnRender(RenderGameOverlayEvent event) {
        if (Player != null) {
            if (TimerEnabled) {
                if (stopWatch == null) {
                    stopWatch = new StopWatch();
                    stopWatch.start();
                }
                DrawString(event, "Текущая проверка:", TXCoords, TYCoords,
                        TCustomColor == 0x0 ? Rainbow(300) : TCustomColor);
                DrawString(event, Player + " | " + stopWatch.getTime(TimeUnit.MINUTES) + ":"
                                + (stopWatch.getTime(TimeUnit.SECONDS) - stopWatch.getTime(TimeUnit.MINUTES)*60),
                        TXCoords, (TYCoords + 10), TCustomColor == 0x0 ? Rainbow(300) : TCustomColor);
            }
        }
        else {
            stopWatch = null;
        }
    }
}
