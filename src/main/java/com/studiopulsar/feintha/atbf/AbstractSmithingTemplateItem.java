package com.studiopulsar.feintha.atbf;

import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;

public abstract class SmithingTemplateItem extends Item {
    public SmithingTemplateItem(Settings settings) {
        super(settings);
    }
    public abstract ATAPI.TrimPattern getTrimPattern();
    @Override
    public void postProcessNbt(NbtCompound nbt) {
        super.postProcessNbt(nbt);
    }
}
