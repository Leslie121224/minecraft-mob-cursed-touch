package com.leslie121224.cursedtouch;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod("cursedtouch") // 這裡的 modId 必須與 mods.toml 一致
public class CursedTouchMod { // 這裡要改成 cursedtouch
    public CursedTouchMod() {
        System.out.println("Mob Cursed Touch Mod Loaded!");
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        System.out.println("Client setup complete!");
    }
}
