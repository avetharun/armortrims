package com.studiopulsar.feintha.armortrims.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.studiopulsar.feintha.armortrims.HandledSmithingScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.entity.ArmorStandEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class SmithingScreen extends HandledScreen<HandledSmithingScreen>  implements ScreenHandlerListener {

    private static final Identifier EMPTY_SLOT_SMITHING_TEMPLATE_ARMOR_TRIM_TEXTURE = new Identifier("textures/item/empty_slot_smithing_template_armor_trim.png");
    private static final Identifier EMPTY_SLOT_SMITHING_TEMPLATE_NETHERITE_UPGRADE_TEXTURE = new Identifier("textures/item/empty_slot_smithing_template_netherite_upgrade.png");

    public SmithingScreen(HandledSmithingScreen handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.titleX = 40;
        this.titleY = 10;
        e = new ArmorStandEntity(MinecraftClient.getInstance().world, 0,0,0);
        NbtCompound c = new NbtCompound();
        c.putBoolean("ShowArms", true);
        c.putBoolean("NoBasePlate", true);
        e.readCustomDataFromNbt(c);
        this.r = (ArmorStandEntityRenderer) MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(e);
    }
    void updateArmorStand(ItemStack stack) {
        var pref_slot = ArmorStandEntity.getPreferredEquipmentSlot(stack);
        EquipmentSlot[] eSA = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET, EquipmentSlot.OFFHAND};
        for (EquipmentSlot eS : eSA) {
            e.equipStack(eS, ItemStack.EMPTY);
        }
        e.equipStack(pref_slot, stack);
    }
    private static final Identifier TEXTURE = new Identifier("minecraft","textures/gui/container/smithing.png");

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        this.drawInvalidRecipeArrow(matrices);
        this.drawMissingTemplate(matrices);
    }
    ArmorStandEntity e;
    ArmorStandEntityRenderer r;
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        var projM = RenderSystem.getProjectionMatrix();
        super.render(matrices, mouseX, mouseY, delta);
        updateArmorStand(handler.result.getStack());
        InventoryScreen.drawEntity(this.x+145, this.y+60, 28, 50, -50, e);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
    float timeSinceLastSwapTemplate = 0f;
    boolean useTrimTextureForMissing = false;
    Identifier currentTex = EMPTY_SLOT_SMITHING_TEMPLATE_ARMOR_TRIM_TEXTURE;
    protected  void drawMissingTemplate(MatrixStack matrixStack) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, EMPTY_SLOT_SMITHING_TEMPLATE_ARMOR_TRIM_TEXTURE);
        this.drawTexture(matrixStack, this.x+8, this.y+48, 0, 0, 16, 16);
        timeSinceLastSwapTemplate += MinecraftClient.getInstance().getTickDelta();
    }
    protected void drawInvalidRecipeArrow(MatrixStack matrixStack) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        this.drawTexture(matrixStack, this.x + 65, this.y + 46, this.backgroundWidth, 0, 28, 21);
    }
    @Override
    public void onSlotUpdate(ScreenHandler handler, int slotId, ItemStack stack) {

    }

    @Override
    public void onPropertyUpdate(ScreenHandler handler, int property, int value) {

    }
}
