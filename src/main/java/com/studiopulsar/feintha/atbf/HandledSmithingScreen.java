package com.studiopulsar.feintha.atbf;

import com.studiopulsar.feintha.atbf.client.SmithingScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.SmithingScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class HandledSmithingScreen extends ScreenHandler {
    public SimpleInventory mInventory;
    public Slot template, baseItem, addtLItem, result;
    public HandledSmithingScreen(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory) {
        super(type, syncId);
        mInventory =new SimpleInventory(4);
        template = this.addSlot(new Slot(mInventory, 0, 8,48));
        baseItem = this.addSlot(new Slot(mInventory, 1, 26,48));
        addtLItem = this.addSlot(new Slot(mInventory, 2, 44, 48));
        result = this.addSlot(new Slot(mInventory, 3, 98, 48){
            @Override
            public boolean canInsert(ItemStack stack) {
                return true;
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                super.onTakeItem(player, stack);
            }
        });



        int i;
        for(i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public HandledSmithingScreen(int i, PlayerInventory playerInventory) {
        this(ArmorTrimBackportFabric.SMITHING, i, playerInventory);

    }

    @Override
    public void close(PlayerEntity player) {
        for (ItemStack s : mInventory.stacks) {
            player.giveItemStack(s);
        }
        super.close(player);
    }

    public HandledSmithingScreen(int syncId, PlayerInventory inventory, Text title) {
        this(syncId, inventory);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        return null;
    }

    @Override
    public void onSlotClick(int slotIndex, int button, SlotActionType actionType, PlayerEntity player) {
        super.onSlotClick(slotIndex, button, actionType, player);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
