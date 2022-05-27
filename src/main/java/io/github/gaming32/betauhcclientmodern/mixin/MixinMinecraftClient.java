package io.github.gaming32.betauhcclientmodern.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.gaming32.betauhcclientmodern.UHCClientModernMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Inject(
        method = "hasOutline(Lnet/minecraft/entity/Entity;)Z",
        at = @At("HEAD"),
        cancellable = true
    )
    private void hasOutline(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (
            entity instanceof PlayerEntity player &&
            UHCClientModernMod.glowingEffects.containsKey(player.getName().asString())
        ) {
            cir.setReturnValue(true);
        }
    }
}
