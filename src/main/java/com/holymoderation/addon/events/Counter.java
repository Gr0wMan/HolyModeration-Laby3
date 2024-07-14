package com.holymoderation.addon.events;

import static com.holymoderation.addon.HMManager.*;

import static com.holymoderation.addon.SettingsManager.*;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.gui.RenderGameOverlayEvent;

public class Counter {

    @Subscribe
    public void onRender(RenderGameOverlayEvent event) {
        if (!CounterEnabled) {
            return;
        }
        DrawString(event, "Всего наказаний: " + TPunishments, CXCoords, CYCoords,
                CCustomColor == 0x0 ? Rainbow(300) : CCustomColor);
        DrawString(event, "Мутов: " + TMutes, CXCoords, CYCoords + 10,
                CCustomColor == 0x0 ? Rainbow(300) : CCustomColor);
        DrawString(event, "Банов: " + TBans, CXCoords + 60, CYCoords + 10,
                CCustomColor == 0x0 ? Rainbow(300) : CCustomColor);
        DrawString(event, "Всего проверок: " + TCheckouts, CXCoords, CYCoords + 10 * 3,
                CCustomColor == 0x0 ? Rainbow(300) : CCustomColor);
        DrawString(event, "Репортов: " + TReports, CXCoords, CYCoords + 10 * 4,
                CCustomColor == 0x0 ? Rainbow(300) : CCustomColor);
        DrawString(event, "Обычных проверок: " + TNotReports, CXCoords + 78, CYCoords + 10 * 4,
                CCustomColor == 0x0 ? Rainbow(300) : CCustomColor);
    }
}
