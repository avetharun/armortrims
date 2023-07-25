package com.studiopulsar.feintha.atbf;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ArmorTrimBackportFabric implements ModInitializer {

    public static final ScreenHandlerType<HandledSmithingScreen> SMITHING = Registry.register(Registry.SCREEN_HANDLER, new Identifier("atbf", "smithing"), new ScreenHandlerType(HandledSmithingScreen::new));;

    public static Item NETHERITE_UPGRADE = registerI("netherite_upgrade_smithing_template", new Item(new FabricItemSettings()));
    public static AbstractSmithingTemplateItem COAST_SMITHING_TEMPLATE = register("coast_armor_trim_smithing_template", new AbstractSmithingTemplateItem() {
        @Override
        public Identifier getTrimPattern() {
            return Identifier.of("minecraft", "coast");
        }
    });
    public static AbstractSmithingTemplateItem DUNE_SMITHING_TEMPLATE = register("dune_armor_trim_smithing_template", new AbstractSmithingTemplateItem() {
        @Override
        public Identifier getTrimPattern() {
            return Identifier.of("minecraft", "dune");
        }
    });
    public static AbstractSmithingTemplateItem EYE_SMITHING_TEMPLATE = register("eye_armor_trim_smithing_template", new AbstractSmithingTemplateItem() {
        @Override
        public Identifier getTrimPattern() {
            return Identifier.of("minecraft", "eye");
        }
    });
    public static AbstractSmithingTemplateItem HOST_SMITHING_TEMPLATE = register("host_armor_trim_smithing_template", new AbstractSmithingTemplateItem() {
        @Override
        public Identifier getTrimPattern() {
            return Identifier.of("minecraft", "host");
        }
    });
    public static AbstractSmithingTemplateItem RAISER_SMITHING_TEMPLATE = register("raiser_armor_trim_smithing_template", new AbstractSmithingTemplateItem() {
        @Override
        public Identifier getTrimPattern() {
            return Identifier.of("minecraft", "raiser");
        }
    });
    public static AbstractSmithingTemplateItem RIB_SMITHING_TEMPLATE = register("rib_armor_trim_smithing_template", new AbstractSmithingTemplateItem() {
        @Override
        public Identifier getTrimPattern() {
            return Identifier.of("minecraft", "rib");
        }
    });
    public static AbstractSmithingTemplateItem SENTRY_SMITHING_TEMPLATE = register("sentry_armor_trim_smithing_template", new AbstractSmithingTemplateItem() {
        @Override
        public Identifier getTrimPattern() {
            return Identifier.of("minecraft", "sentry");
        }
    });
    public static AbstractSmithingTemplateItem SHAPER_SMITHING_TEMPLATE = register("shaper_armor_trim_smithing_template", new AbstractSmithingTemplateItem() {
        @Override
        public Identifier getTrimPattern() {
            return Identifier.of("minecraft", "shaper");
        }
    });
    public static AbstractSmithingTemplateItem SILENCE_SMITHING_TEMPLATE = register("silence_armor_trim_smithing_template", new AbstractSmithingTemplateItem() {
        @Override
        public Identifier getTrimPattern() {
            return Identifier.of("minecraft", "silence");
        }
    });
    public static AbstractSmithingTemplateItem SNOUT_SMITHING_TEMPLATE = register("snout_armor_trim_smithing_template", new AbstractSmithingTemplateItem() {
        @Override
        public Identifier getTrimPattern() {
            return Identifier.of("minecraft", "snout");
        }
    });
    public static AbstractSmithingTemplateItem SPIRE_SMITHING_TEMPLATE = register("spire_armor_trim_smithing_template", new AbstractSmithingTemplateItem() {
        @Override
        public Identifier getTrimPattern() {
            return Identifier.of("minecraft", "spire");
        }
    });
    public static AbstractSmithingTemplateItem TIDE_SMITHING_TEMPLATE = register("tide_armor_trim_smithing_template", new AbstractSmithingTemplateItem() {
        @Override
        public Identifier getTrimPattern() {
            return Identifier.of("minecraft", "tide");
        }
    });
    public static AbstractSmithingTemplateItem VEX_SMITHING_TEMPLATE = register("vex_armor_trim_smithing_template", new AbstractSmithingTemplateItem() {
        @Override
        public Identifier getTrimPattern() {
            return Identifier.of("minecraft", "vex");
        }
    });
    public static AbstractSmithingTemplateItem WARD_SMITHING_TEMPLATE = register("ward_armor_trim_smithing_template", new AbstractSmithingTemplateItem() {
        @Override
        public Identifier getTrimPattern() {
            return Identifier.of("minecraft", "ward");
        }
    });
    public static AbstractSmithingTemplateItem WAYFINDER_SMITHING_TEMPLATE = register("wayfinder_armor_trim_smithing_template", new AbstractSmithingTemplateItem() {
        @Override
        public Identifier getTrimPattern() {
            return Identifier.of("minecraft", "wayfinder");
        }
    });
    public static AbstractSmithingTemplateItem WILD_SMITHING_TEMPLATE = register("wild_armor_trim_smithing_template", new AbstractSmithingTemplateItem() {
        @Override
        public Identifier getTrimPattern() {
            return Identifier.of("minecraft", "wild");
        }
    });



    public static Identifier AMETHYST_PALETTE_ID = Identifier.of("minecraft", "amethyst");
    public static Identifier COPPER_PALETTE_ID = Identifier.of("minecraft", "copper");
    public static Identifier DIAMOND_PALETTE_ID = Identifier.of("minecraft", "diamond");
    public static Identifier EMERALD_PALETTE_ID = Identifier.of("minecraft", "emerald");
    public static Identifier GOLD_PALETTE_ID = Identifier.of("minecraft", "gold");
    public static Identifier IRON_PALETTE_ID = Identifier.of("minecraft", "iron");
    public static Identifier LAPIS_PALETTE_ID = Identifier.of("minecraft", "lapis");
    public static Identifier NETHERITE_PALETTE_ID = Identifier.of("minecraft", "netherite");
    public static Identifier QUARTZ_PALETTE_ID = Identifier.of("minecraft", "quartz");
    public static Identifier REDSTONE_PALETTE_ID = Identifier.of("minecraft", "redstone");
    @Override
    public void onInitialize() {
        Registry.register(ATAPI.ARMOR_TRIM_PALETTES, AMETHYST_PALETTE_ID, new ATAPI.TrimmedMaterialPalette(AMETHYST_PALETTE_ID));
        Registry.register(ATAPI.ARMOR_TRIM_PALETTES, COPPER_PALETTE_ID, new ATAPI.TrimmedMaterialPalette(COPPER_PALETTE_ID));
        Registry.register(ATAPI.ARMOR_TRIM_PALETTES, DIAMOND_PALETTE_ID, new ATAPI.TrimmedMaterialPalette(DIAMOND_PALETTE_ID));
        Registry.register(ATAPI.ARMOR_TRIM_PALETTES, EMERALD_PALETTE_ID, new ATAPI.TrimmedMaterialPalette(EMERALD_PALETTE_ID));
        Registry.register(ATAPI.ARMOR_TRIM_PALETTES, GOLD_PALETTE_ID, new ATAPI.TrimmedMaterialPalette(GOLD_PALETTE_ID));
        Registry.register(ATAPI.ARMOR_TRIM_PALETTES, IRON_PALETTE_ID, new ATAPI.TrimmedMaterialPalette(IRON_PALETTE_ID));
        Registry.register(ATAPI.ARMOR_TRIM_PALETTES, LAPIS_PALETTE_ID, new ATAPI.TrimmedMaterialPalette(LAPIS_PALETTE_ID));
        Registry.register(ATAPI.ARMOR_TRIM_PALETTES, NETHERITE_PALETTE_ID, new ATAPI.TrimmedMaterialPalette(NETHERITE_PALETTE_ID));
        Registry.register(ATAPI.ARMOR_TRIM_PALETTES, QUARTZ_PALETTE_ID, new ATAPI.TrimmedMaterialPalette(QUARTZ_PALETTE_ID));
        Registry.register(ATAPI.ARMOR_TRIM_PALETTES, REDSTONE_PALETTE_ID, new ATAPI.TrimmedMaterialPalette(REDSTONE_PALETTE_ID));
    }
    public static <T extends AbstractSmithingTemplateItem> T register(String name, T item) {
        return Registry.register(Registry.ITEM, Identifier.of("minecraft",name), item);
    }
    public static <T extends Item> T registerI(String name, T item) {
        return Registry.register(Registry.ITEM, Identifier.of("minecraft",name), item);
    }
}
