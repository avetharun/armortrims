package com.studiopulsar.feintha.armortrims.client;

import com.studiopulsar.feintha.armortrims.ArmorTrimBackportFabric;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.util.stream.Stream;

public class ArmorTrimBackportFabricClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    public static SpriteAtlasTexture TRIM_TEXTURES;
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ArmorTrimBackportFabric.SMITHING, SmithingScreen::new);
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return new Identifier("trimresources");
            }

            @Override
            public void reload(ResourceManager manager) {
                TRIM_TEXTURES = new SpriteAtlasTexture(Identifier.of("armortrims", "textures/atlas/armor_trims.png"));
                TRIM_TEXTURES.load(manager);
                Stream<Identifier> ids;
                var builder = Stream.<Identifier>builder();
                manager.findResources("textures/trims", identifier -> identifier.toString().endsWith(".png")).forEach(
                        (identifier, resource) -> {
                            var id = Identifier.tryParse(identifier.toString().replace(".png","").replaceFirst("textures/", ""));
                            builder.add(id);
                        }
                );
                var data = TRIM_TEXTURES.stitch(manager, builder.build(), MinecraftClient.getInstance().getProfiler(), 0);
                TRIM_TEXTURES.upload(data);
            }
        });
    }
}
