package com.holymoderation.addon;

import com.holymoderation.addon.events.*;

import static com.holymoderation.addon.SettingsManager.*;

import net.labymod.api.LabyModAddon;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.gui.RenderGameOverlayEvent;
import net.labymod.settings.elements.*;

import java.util.List;

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
    getApi().getEventService().registerListener(new MessageReceive());
    getApi().getEventService().registerListener(new AutoVanish());
  }

  @Override
  public void loadConfig() {
    VkUrl = getConfig().has("vk_url") ? getConfig().get("vk_url").getAsString() : null;

    TimerEnabled = getConfig().has("timer_enabled") ? getConfig().get("timer_enabled").getAsBoolean() : true;
    TCustomColor = getConfig().has("timer_custom_color") ? getConfig().get("timer_custom_color").getAsInt() : 0x0;
    TXCoords = getConfig().has("timerx") ? getConfig().get("timerx").getAsInt() : 0;
    TYCoords = getConfig().has("timery") ? getConfig().get("timery").getAsInt() : 0;

    CounterEnabled = getConfig().has("counter_enabled") ? getConfig().get("counter_enabled").getAsBoolean() : false;
    CCustomColor = getConfig().has("counter_custom_color") ? getConfig().get("counter_custom_color").getAsInt() : 0x0;
    CXCoords = getConfig().has("counterx") ? getConfig().get("counterx").getAsInt() : 0x0;
    CYCoords = getConfig().has("countery") ? getConfig().get("countery").getAsInt() : 0x0;

    Checkouts = getConfig().has("checkouts") ? getConfig().get("checkouts").getAsInt() : 0;
    Reports = getConfig().has("reports") ? getConfig().get("reports").getAsInt() : 0;
    NotReports = getConfig().has("notreports") ? getConfig().get("notreports").getAsInt() : 0;
    Punishments = getConfig().has("punishments") ? getConfig().get("punishments").getAsInt() : 0;
    Bans = getConfig().has("bans") ? getConfig().get("bans").getAsInt() : 0;
    Mutes = getConfig().has("mutes") ? getConfig().get("mutes").getAsInt() : 0;

    TCheckouts = getConfig().has("tcheckouts") ? getConfig().get("tcheckouts").getAsInt() : 0;
    TReports = getConfig().has("treports") ? getConfig().get("treports").getAsInt() : 0;
    TNotReports = getConfig().has("tnotreports") ? getConfig().get("notreports").getAsInt() : 0;
    TPunishments = getConfig().has("tpunishments") ? getConfig().get("tpunishments").getAsInt() : 0;
    TBans = getConfig().has("tbans") ? getConfig().get("tbans").getAsInt() : 0;
    TMutes = getConfig().has("tmutes") ? getConfig().get("tmutes").getAsInt() : 0;

    AutoAnyDeskEnabled = getConfig().has("auto_any_desk_copy") ? getConfig().get("auto_any_desk_copy").getAsBoolean() : false;
    AutoBanEnabled = getConfig().has("auto_ban") ? getConfig().get("auto_ban").getAsBoolean() : false;
    AutoVanishEnabled = getConfig().has("auto_vanish") ? getConfig().get("auto_vanish").getAsBoolean() : false;
    DupeIpEnabled = getConfig().has("enable_dupe_ip") ? getConfig().get("enable_dupe_ip").getAsBoolean() : false;
    Texts = getConfig().has("texts_list") ? getConfig().get("texts_list").getAsString() : null;
  }

  @Override
  protected void fillSettings(List<SettingsElement> list) {
  }

  @Subscribe
  public void SaveCfgCheck(RenderGameOverlayEvent event) {
    if (!saveCfg) {
      return;
    }

    HolyModeration.this.getConfig().addProperty("vk_url", VkUrl);

    HolyModeration.this.getConfig().addProperty("timer_enabled", TimerEnabled);
    HolyModeration.this.getConfig().addProperty("timer_custom_color", TCustomColor);
    HolyModeration.this.getConfig().addProperty("timerx", TXCoords);
    HolyModeration.this.getConfig().addProperty("timery", TYCoords);

    HolyModeration.this.getConfig().addProperty("counter_enabled", CounterEnabled);
    HolyModeration.this.getConfig().addProperty("counter_custom_color", CCustomColor);
    HolyModeration.this.getConfig().addProperty("counterx", CXCoords);
    HolyModeration.this.getConfig().addProperty("countery", CYCoords);

    HolyModeration.this.getConfig().addProperty("checkouts", Checkouts);
    HolyModeration.this.getConfig().addProperty("reports", Reports);
    HolyModeration.this.getConfig().addProperty("notreports", NotReports);
    HolyModeration.this.getConfig().addProperty("punishments", Punishments);
    HolyModeration.this.getConfig().addProperty("bans", Bans);
    HolyModeration.this.getConfig().addProperty("mutes", Mutes);

    HolyModeration.this.getConfig().addProperty("tcheckouts", TCheckouts);
    HolyModeration.this.getConfig().addProperty("treports", TReports);
    HolyModeration.this.getConfig().addProperty("tnotreports", TNotReports);
    HolyModeration.this.getConfig().addProperty("tpunishments", TPunishments);
    HolyModeration.this.getConfig().addProperty("tbans", TBans);
    HolyModeration.this.getConfig().addProperty("tmutes", TMutes);

    HolyModeration.this.getConfig().addProperty("auto_any_desk_copy", AutoAnyDeskEnabled);
    HolyModeration.this.getConfig().addProperty("auto_ban", AutoBanEnabled);
    HolyModeration.this.getConfig().addProperty("auto_vanish", AutoVanishEnabled);
    HolyModeration.this.getConfig().addProperty("enable_dupe_ip", DupeIpEnabled);
    HolyModeration.this.getConfig().addProperty("texts_list", Texts);

    HolyModeration.this.saveConfig();

    saveCfg = false;
  }

  public static void SaveCfg() {
    saveCfg = true;
  }
}