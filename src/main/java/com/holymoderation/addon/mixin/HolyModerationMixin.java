package com.holymoderation.addon.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class HolyModerationMixin {

  @Inject(method = "runTick", at = @At("HEAD"))
  private void injectRunTick(CallbackInfo ci) {
    System.out.println("Tick!");
  }

}
