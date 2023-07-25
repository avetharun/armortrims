package com.studiopulsar.feintha.atbf.client;

import com.studiopulsar.feintha.atbf.ArmorTrimBackportFabric;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ArmorTrimBackportFabricClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */

    @Override
    public void onInitializeClient() {
        HandledScreens.register(ArmorTrimBackportFabric.SMITHING, SmithingScreen::new);

    }
}
