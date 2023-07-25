package com.studiopulsar.feintha.atbf.mixin;

import com.studiopulsar.feintha.atbf.HandledSmithingScreen;
import com.studiopulsar.feintha.atbf.client.SmithingScreen;
import net.minecraft.block.BlockState;
import net.minecraft.block.SmithingTableBlock;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.*;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(ScreenHandlerType.class)
public class HandledScreensMixin {
    @Mixin(SmithingTableBlock.class)
    public static class SmithingTableScreenMixin {
        @Shadow @Final private static Text SCREEN_TITLE;

        @Inject(method="createScreenHandlerFactory", at=@At("HEAD"), cancellable = true)
        void screenHandlerFactoryMixin(BlockState state, World world, BlockPos pos, CallbackInfoReturnable<NamedScreenHandlerFactory> cir){
            cir.setReturnValue(new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> {
                return new HandledSmithingScreen(syncId, inventory);
            }, SCREEN_TITLE));
            cir.cancel();
            return;
        }
    }
}
