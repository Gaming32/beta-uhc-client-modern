package io.github.gaming32.betauhcclientmodern.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.network.PlayerListEntry;

@Mixin(PlayerListEntry.class)
public class MixinPlayerListEntry {
    // @Inject(
    //     method = "getGameMode()Lnet/minecraft/world/GameMode;",
    //     at = @At("HEAD"),
    //     cancellable = true
    // )
    // private void getGameMode(CallbackInfoReturnable<GameMode> cir) {
    //     final PlayerListEntry entry = (PlayerListEntry)(Object)this;
    //     Text displayName = entry.getDisplayName();
    //     if (displayName == null) return; // Interesting that this can be null
    //     if (UHCClientModernMod.spectatingPlayers.contains(entry.getDisplayName().getString())) {
    //         cir.setReturnValue(GameMode.SPECTATOR);
    //     }
    // }
}
