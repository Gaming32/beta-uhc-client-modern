package io.github.gaming32.betauhcclientmodern.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.gaming32.betauhcclientmodern.UHCClientModernMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

@Mixin(Entity.class)
public class MixinEntity {
    @Inject(
        method = "getTeamColorValue()I",
        at = @At("HEAD"),
        cancellable = true
    )
    private void hasOutline(CallbackInfoReturnable<Integer> cir) {
        Integer color;
        if (
            (Object)this instanceof PlayerEntity player &&
            (color = UHCClientModernMod.glowingEffects.get(player.getName().asString())) != null
        ) {
            cir.setReturnValue(color);
        }
    }
}
