package com.studiopulsar.feintha.armortrims;

import com.studiopulsar.feintha.armortrims.client.ArmorTrimBackportFabricClient;
import it.unimi.dsi.fastutil.longs.Long2ReferenceLinkedOpenHashMap;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.Sprite;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.math.Vec3i;

import java.util.ArrayList;

public class ATAPI {
    public static class TrimPattern {
        public static String res_root = "textures/trims/models/armor/";
        public static Identifier getAssetForPattern(Identifier pattern){
            return Identifier.of(pattern.getNamespace(), res_root+pattern.getPath());
        }
    }
    public static class TrimmedMaterialPalette {
        public TrimmedMaterialPalette(Identifier id) {
            this.id = id;
        }
        public static String res_root = "trims/color_palettes/";
        public Identifier id;
        public Identifier getAsset() {
            return Identifier.of(id.getNamespace(), res_root+id.getPath());
        }
        public int averageColorPacked = 0;
        public Vec3f averageColor = Vec3f.ZERO;
    }
    public static class TrimmedModelData {
        public float red, green, blue;
        public int packed;
        public Identifier texture;
    }
    public static Long2ReferenceLinkedOpenHashMap<TrimmedMaterialPalette> cachedMaterials = new Long2ReferenceLinkedOpenHashMap<>();
//    public static Registry<TrimmedMaterialPalette> ARMOR_TRIM_PALETTES = FabricRegistryBuilder.createSimple(TrimmedMaterialPalette.class, new Identifier("armortrims", "trim_palettes")).buildAndRegister();
    private static TrimmedMaterialPalette cacheMaterial(Identifier id) {
        TrimmedMaterialPalette palette = new TrimmedMaterialPalette(id);
        Sprite sprite = ArmorTrimBackportFabricClient.TRIM_TEXTURES.getSprite(palette.getAsset());
        NativeImage img = alib.<NativeImage[], Sprite>getMixinField(sprite, "images")[0];
        int[] colorVals = new int[]{255, 0, 0, 0};
        int size = 0;
        for (int x = 0; x < sprite.getWidth(); x++) {
            for (int y = 0; y < sprite.getHeight(); y++) {
                colorVals[1] += img.getRed(x,y);
                colorVals[2] += img.getGreen(x,y);
                colorVals[3] += img.getBlue(x,y);
                size++;
            }
        }
        for (int i=0;i<colorVals.length;i++) {
            colorVals[i] = Math.min(255,Math.max(0,colorVals[i]/size));
        }
        palette.averageColorPacked = alib.packRGBi(new Vec3i(
                img.getRed((int) (sprite.getWidth() * 0.5), (int) (sprite.getHeight() * 0.5)),
                img.getGreen((int) (sprite.getWidth() * 0.5), (int) (sprite.getHeight() * 0.5)),
                img.getBlue((int) (sprite.getWidth()* 0.5), (int) (sprite.getHeight() * 0.5))
        ));
        palette.averageColor = alib.unpackRGB(palette.averageColorPacked);
        cachedMaterials.put(alib.getHash64(id.toString()), palette);
        return palette;
    }
    public static TrimmedMaterialPalette getCachedMaterial(Identifier id) {
        long l = alib.getHash64(id.toString());
        if (!cachedMaterials.containsKey(l)) {
            cacheMaterial(id);
        }
        return cachedMaterials.get(l);
    }
    public static NbtCompound getCForE(Identifier pattern, Identifier material, NbtCompound compound) {
        var c =  new NbtCompound();
        c.putString("material", material.toString());
        c.putString("pattern", pattern.toString());
        compound.put("Trim", c);
        return c;
    }
    public static TrimmedModelData getModelData(ItemStack stack) {
        NbtCompound c = stack.getOrCreateNbt();
        boolean bl1 = !c.contains("Trim");
        if (bl1) {
            return null;
        }
        TrimmedModelData mD = new TrimmedModelData();
        Identifier material = Identifier.tryParse(c.getCompound("Trim").getString("material"));
        Identifier pattern = Identifier.tryParse(c.getCompound("Trim").getString("pattern"));
        assert pattern != null;
        mD.texture = Identifier.of(pattern.getNamespace(), TrimPattern.res_root + pattern.getPath() + ".png");
        assert material != null;
        TrimmedMaterialPalette palette = getCachedMaterial(material);
        if (palette == null) {return null;}
        mD.blue = palette.averageColor.getZ();
        mD.green = palette.averageColor.getY();
        mD.red = palette.averageColor.getX();
        mD.packed = palette.averageColorPacked;
        return mD;
    }
}
