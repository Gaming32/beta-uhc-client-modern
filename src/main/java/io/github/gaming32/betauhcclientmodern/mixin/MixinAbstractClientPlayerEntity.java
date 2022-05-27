package io.github.gaming32.betauhcclientmodern.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.gaming32.betauhcclientmodern.UHCClientModernMod;
import net.minecraft.client.network.AbstractClientPlayerEntity;

@Mixin(AbstractClientPlayerEntity.class)
public class MixinAbstractClientPlayerEntity {
    @Inject(
        method = "isSpectator()Z",
        at = @At("HEAD"),
        cancellable = true
    )
    private void isSpectator(CallbackInfoReturnable<Boolean> cir) {
        if (UHCClientModernMod.spectatingPlayers.contains(
            ((AbstractClientPlayerEntity)(Object)this).getName().getString()
        )) {
            cir.setReturnValue(true);
        }
    }
}
