package com.holymoderation.addon.utils;

import net.labymod.api.event.events.client.gui.RenderGameOverlayEvent;
import net.labymod.core.LabyModCore;

import java.awt.*;

public class RenderHelper {

    public static int Rainbow(int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0D);
        rainbowState %= 360.0D;
        return Color.getHSBColor((float)(rainbowState / 360.0D), 0.5F, 1.0F).getRGB();
    }

    public static void DrawString(RenderGameOverlayEvent event, String text, int x, int y, int color) {
        LabyModCore.getMinecraft().getFontRenderer().drawString(event.getMatrixStack(), text, x, y, color);
    }
}
