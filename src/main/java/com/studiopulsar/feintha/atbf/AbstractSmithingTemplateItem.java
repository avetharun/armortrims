package com.studiopulsar.feintha.atbf;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public abstract class AbstractSmithingTemplateItem extends Item {
    public AbstractSmithingTemplateItem() {
        super(new FabricItemSettings());
    }
    public abstract Identifier getTrimPattern();
    @Override
    public void postProcessNbt(NbtCompound nbt) {
        super.postProcessNbt(nbt);
    }
}
