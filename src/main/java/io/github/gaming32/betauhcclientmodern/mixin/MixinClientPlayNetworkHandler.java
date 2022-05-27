package io.github.gaming32.betauhcclientmodern.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.gaming32.betauhcclientmodern.UHCClientModernMod;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler {
    @Inject(
        method = "onGameMessage(Lnet/minecraft/network/packet/s2c/play/GameMessageS2CPacket;)V",
        at = @At("HEAD"),
        cancellable = true
    )
    private void onGameMessage(GameMessageS2CPacket packet, CallbackInfo ci) {
        if (UHCClientModernMod.getPacketManager().handleMessage(packet.getMessage().asString())) {
            ci.cancel();
        }
    }
}
