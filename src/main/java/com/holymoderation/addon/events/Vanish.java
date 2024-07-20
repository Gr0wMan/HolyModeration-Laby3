package com.holymoderation.addon.events;

import static com.holymoderation.addon.HMManager.*;
import static com.holymoderation.addon.SettingsManager.*;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.gui.RenderGameOverlayEvent;

import java.awt.*;

public class Vanish {

    @Subscribe
    public void onRender(RenderGameOverlayEvent event) {
        if (VanishStatusEnabled) {
            DrawString(event, "Статус ваниша: ", VXCoords, VYCoords, VCustomColor == 0x0 ? Rainbow(300) : VCustomColor);
            if (VanishEnabled) {
                DrawString(event, "ВКЛЮЧЁН", VXCoords + 85, VYCoords, Color.green.getRGB());
            } else {
                DrawString(event, "ВЫКЛЮЧЕН", VXCoords + 85, VYCoords, Color.red.getRGB());
            }
        }
    }
}
