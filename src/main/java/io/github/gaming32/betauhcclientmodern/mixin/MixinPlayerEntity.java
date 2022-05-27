package io.github.gaming32.betauhcclientmodern.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.gaming32.betauhcclientmodern.UHCClientModernMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;

@Mixin(PlayerEntity.class)
@SuppressWarnings("resource")
public class MixinPlayerEntity {
    private boolean uhc$wasSpectator = false;

    @Inject(
        method = "tick()V",
        at = @At("HEAD")
    )
    private void tick(CallbackInfo ci) {
        final PlayerEntity player = (PlayerEntity)(Object)this;
        if (player != MinecraftClient.getInstance().player) {
            return;
        }
        boolean spectator = UHCClientModernMod.spectatingPlayers.contains(player.getName().getString());
        if (spectator != uhc$wasSpectator) {
            MinecraftClient.getInstance().interactionManager.setGameMode(
                spectator ? GameMode.SPECTATOR : GameMode.SURVIVAL
            );
            uhc$wasSpectator = spectator;
        }
    }

    @Overwrite
    public Text getDisplayName() {
        final PlayerEntity player = (PlayerEntity)(Object)this;
        String displayName = UHCClientModernMod.displayNames.get(player.getName().asString());
        return displayName != null ? Text.of(displayName) : player.getName();
    }
}
