package com.holymoderation.addon.events;

import com.holymoderation.addon.utils.RenderHelper;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.gui.RenderGameOverlayEvent;

public class Counter {

    //all time
    private static int checkouts = 0;
    private static int reports = 0;
    private static int punishments = 0;
    private static int bans = 0;
    private static int mutes = 0;
    //all time

    //temp
    private static int tcheckouts = 0;
    private static int treports = 0;
    private static int tpunishments = 0;
    private static int tbans = 0;
    private static int tmutes = 0;
    //temp

    private static int xCoords = 0;
    private static int yCoords = 0;

    private static int interval = 10;

    private static int customColor = 0x0;

    private static boolean counterEnabled = false;

    @Subscribe
    public void onRender(RenderGameOverlayEvent event) {
        if (!counterEnabled) {
            return;
        }
        RenderHelper.DrawString(event,"Всего наказаний: " + tpunishments, xCoords, yCoords,
                customColor == 0x0 ? RenderHelper.Rainbow(300) : customColor);
        RenderHelper.DrawString(event,"Мутов: " + tmutes, xCoords, yCoords + interval,
                customColor == 0x0 ? RenderHelper.Rainbow(300) : customColor);
        RenderHelper.DrawString(event,"Банов: " + tbans, xCoords + 60, yCoords + interval,
                customColor == 0x0 ? RenderHelper.Rainbow(300) : customColor);
        RenderHelper.DrawString(event,"Всего проверок: " + tcheckouts, xCoords, yCoords + interval*3,
                customColor == 0x0 ? RenderHelper.Rainbow(300) : customColor);
        RenderHelper.DrawString(event,"Репортов: " + treports, xCoords, yCoords + interval*4,
                customColor == 0x0 ? RenderHelper.Rainbow(300) : customColor);
        RenderHelper.DrawString(event,"Обычных проверок: " + (tcheckouts - treports), xCoords + 78, yCoords + interval*4,
                customColor == 0x0 ? RenderHelper.Rainbow(300) : customColor);
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

    public static int[] GetAllTimeInfo() {
        return new int[] {checkouts, reports, punishments, bans, mutes};

    }

    public static void SetAllTimeInfo(int[] info) {
        checkouts = info[0];
        reports = info[1];
        punishments = info[2];
        bans = info[3];
        mutes = info[4];
    }

    public static int[] GetTempInfo() {
        return new int[] {tcheckouts, treports, tpunishments, tbans, tmutes};
    }

    public static void SetTempInfo(int[] info) {
        tcheckouts = info[0];
        treports = info[1];
        tpunishments = info[2];
        tbans = info[3];
        tmutes = info[4];
    }

    public static void IncreaseInfo(String info) {
        switch (info) {
            case ("checkouts"):
                checkouts++;
                tcheckouts++;
                break;
            case ("reports"):
                reports++;
                treports++;
                break;
            case ("punishments"):
                punishments++;
                tpunishments++;
                break;
            case ("bans"):
                bans++;
                tbans++;
                break;
            case ("mutes"):
                mutes++;
                tmutes++;
                break;
        }
    }

    public static boolean GetCounterEnabled() {
        return counterEnabled;
    }

    public static void SetCounterEnabled(boolean value) {
        counterEnabled = value;
    }

    public static void AddReport() {
        reports += 1;
        treports += 1;
    }

    public static void RemoveReport() {
        reports-=1;
        treports-=1;
    }

    public static void RemoveCheckOut() {
        checkouts-=1;
        tcheckouts-=1;
    }
}
