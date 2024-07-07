package com.holymoderation.addon;

import java.util.List;

import net.labymod.api.LabyModAddon;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;
import net.labymod.api.event.events.client.gui.RenderGameOverlayEvent;
import net.labymod.settings.elements.*;

import com.holymoderation.addon.events.*;
import com.holymoderation.addon.utils.*;

public class HolyModeration extends LabyModAddon {

  private static boolean saveCfg = false;

  @Override
  public void onEnable() {
    getApi().getEventService().registerListener(this);
    getApi().getEventService().registerListener(new Freezer());
    getApi().getEventService().registerListener(new Timer());
    getApi().getEventService().registerListener(new Settings());
    getApi().getEventService().registerListener(new Help());
    getApi().getEventService().registerListener(new Punishments());
    getApi().getEventService().registerListener(new Counter());
    getApi().getEventService().registerListener(new UpdateChecker());
  }

  @Override
  public void loadConfig() {
    Timer.SetCustomColor(getConfig().has("timer_custom_color") ? getConfig().get("timer_custom_color").getAsInt() : 0x0);
    Timer.SetXCoords(getConfig().has("timerx") ? getConfig().get("timerx").getAsInt() : 0);
    Timer.SetYCoords(getConfig().has("timery") ? getConfig().get("timery").getAsInt() : 0);

    PunishmentsManager.SetVkUrl(getConfig().has("vk_url") ? getConfig().get("vk_url").getAsString() : null);

    Freezer.SetDupeIp(getConfig().has("enable_dupe_ip") ? getConfig().get("enable_dupe_ip").getAsBoolean() : false);
    Freezer.SetTexts(getConfig().has("texts_list") ? getConfig().get("texts_list").getAsString() : null);

    Counter.SetCounterEnabled(getConfig().has("counter_enabled") ? getConfig().get("counter_enabled").getAsBoolean() : false);

    Counter.SetCustomColor(getConfig().has("counter_custom_color") ? getConfig().get("counter_custom_color").getAsInt() : 0x0);
    Counter.SetXCoords(getConfig().has("counterx") ? getConfig().get("counterx").getAsInt() : 0x0);
    Counter.SetYCoords(getConfig().has("countery") ? getConfig().get("countery").getAsInt() : 0x0);

    int checkouts = (getConfig().has("checkouts") ? getConfig().get("checkouts").getAsInt() : 0);
    int reports = (getConfig().has("reports") ? getConfig().get("reports").getAsInt() : 0);
    int punishments = (getConfig().has("punishments") ? getConfig().get("punishments").getAsInt() : 0);
    int bans = (getConfig().has("bans") ? getConfig().get("bans").getAsInt() : 0);
    int mutes = (getConfig().has("mutes") ? getConfig().get("mutes").getAsInt() : 0);
    Counter.SetAllTimeInfo(new int[] {checkouts, reports, punishments, bans, mutes});

    int tcheckouts = (getConfig().has("tcheckouts") ? getConfig().get("tcheckouts").getAsInt() : 0);
    int treports = (getConfig().has("treports") ? getConfig().get("treports").getAsInt() : 0);
    int tpunishments = (getConfig().has("tpunishments") ? getConfig().get("tpunishments").getAsInt() : 0);
    int tbans = (getConfig().has("tbans") ? getConfig().get("tbans").getAsInt() : 0);
    int tmutes = (getConfig().has("tmutes") ? getConfig().get("tmutes").getAsInt() : 0);
    Counter.SetTempInfo(new int[] {tcheckouts, treports, tpunishments, tbans, tmutes});
  }

  @Override
  protected void fillSettings(List<SettingsElement> list) {
  }

  @Subscribe
  public void SaveCfgCheck(RenderGameOverlayEvent event) {
    if (!saveCfg) {
      return;
    }

    HolyModeration.this.getConfig().addProperty("timer_custom_color", Timer.GetCustomColor());
    HolyModeration.this.getConfig().addProperty("timerx", Timer.GetXCoords());
    HolyModeration.this.getConfig().addProperty("timery", Timer.GetYCoords());

    HolyModeration.this.getConfig().addProperty("vk_url", PunishmentsManager.GetVkUrl());

    HolyModeration.this.getConfig().addProperty("enable_dupe_ip", Freezer.GetDupeIp());
    HolyModeration.this.getConfig().addProperty("texts_list", Freezer.GetTexts());

    HolyModeration.this.getConfig().addProperty("counter_enabled", Counter.GetCounterEnabled());

    HolyModeration.this.getConfig().addProperty("counter_custom_color", Counter.GetCustomColor());
    HolyModeration.this.getConfig().addProperty("counterx", Counter.GetXCoords());
    HolyModeration.this.getConfig().addProperty("countery", Counter.GetYCoords());

    HolyModeration.this.getConfig().addProperty("checkouts", Counter.GetAllTimeInfo()[0]);
    HolyModeration.this.getConfig().addProperty("reports", Counter.GetAllTimeInfo()[1]);
    HolyModeration.this.getConfig().addProperty("punishments", Counter.GetAllTimeInfo()[2]);
    HolyModeration.this.getConfig().addProperty("bans", Counter.GetAllTimeInfo()[3]);
    HolyModeration.this.getConfig().addProperty("mutes", Counter.GetAllTimeInfo()[4]);

    HolyModeration.this.getConfig().addProperty("tcheckouts", Counter.GetTempInfo()[0]);
    HolyModeration.this.getConfig().addProperty("treports", Counter.GetTempInfo()[1]);
    HolyModeration.this.getConfig().addProperty("tpunishments", Counter.GetTempInfo()[2]);
    HolyModeration.this.getConfig().addProperty("tbans", Counter.GetTempInfo()[3]);
    HolyModeration.this.getConfig().addProperty("tmutes", Counter.GetTempInfo()[4]);

    HolyModeration.this.saveConfig();

    saveCfg = false;
  }

  public static void SaveCfg() {
    saveCfg = true;
  }
}