package com.studiopulsar.feintha.armortrims.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.studiopulsar.feintha.armortrims.ATAPI;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
public class ArmorFeatureRendererMixin<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> {
    @Inject(method="renderArmor",
        at=@At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/entity/feature/ArmorFeatureRenderer;renderArmorParts(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/item/ArmorItem;ZLnet/minecraft/client/render/entity/model/BipedEntityModel;ZFFFLjava/lang/String;)V",
            shift = At.Shift.AFTER
        )
    )
    void renderArmorParts_CutoutTrim(MatrixStack matrices, VertexConsumerProvider vertexConsumers, T entity, EquipmentSlot armorSlot, int light, A model, CallbackInfo ci){
        ItemStack itemStack = entity.getEquippedStack(armorSlot);
        var mD = ATAPI.getModelData(itemStack);
        // This check also functions as a check for if the item is trimmed at all.
        if (mD != null) {
            boolean bl2 = itemStack.hasGlint();
            RenderLayer cutoutLayer = RenderLayer.getArmorCutoutNoCull(mD.texture);
            var vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, cutoutLayer, false, bl2);
            model.render(matrices, vertexConsumer.color(mD.red,mD.blue,mD.green,1), light, OverlayTexture.DEFAULT_UV, mD.red, mD.green, mD.blue, 1.0F);
        }
    }
}
