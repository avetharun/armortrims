package com.studiopulsar.feintha.atbf;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ATAPI {
    public static class TrimPattern {
        public static String res_root = "textures/trims/models/armor";
        public static Identifier getAssetForPattern(Identifier pattern){
            return Identifier.of(pattern.getNamespace(), res_root+pattern.getPath());
        }
    }
    public static class TrimmedMaterialPalette {
        public TrimmedMaterialPalette(Identifier id) {
            this.id = id;
        }
        public static String res_root = "textures/trims/color_palettes";
        public Identifier id;
        public Identifier getAsset() {
            return Identifier.of(id.getNamespace(), res_root+id.getPath());
        }
    }
    public static Registry<TrimmedMaterialPalette> ARMOR_TRIM_PALETTES = FabricRegistryBuilder.createSimple(TrimmedMaterialPalette.class, new Identifier("atbf", "trim_palettes")).buildAndRegister();
    public static NbtCompound getCForE(Identifier pattern, Identifier material, NbtCompound compound) {
        var c =  new NbtCompound();
        c.putString("material", material.toString());
        c.putString("pattern", pattern.toString());
        compound.put("Trim", c);
        return c;
    }
}
