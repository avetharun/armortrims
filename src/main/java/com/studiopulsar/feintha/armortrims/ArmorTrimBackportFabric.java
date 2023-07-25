package com.studiopulsar.feintha.armortrims;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ArmorTrimBackportFabric implements ModInitializer {

    public static final ScreenHandlerType<HandledSmithingScreen> SMITHING = Registry.register(Registry.SCREEN_HANDLER, new Identifier("armortrims", "smithing"), new ScreenHandlerType(HandledSmithingScreen::new));;

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

    @Override
    public void onInitialize() {
    }
    public static <T extends AbstractSmithingTemplateItem> T register(String name, T item) {
        return Registry.register(Registry.ITEM, Identifier.of("minecraft",name), item);
    }
    public static <T extends Item> T registerI(String name, T item) {
        return Registry.register(Registry.ITEM, Identifier.of("minecraft",name), item);
    }
}
