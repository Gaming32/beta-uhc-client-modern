package io.github.gaming32.betauhcclientmodern.mixin;

import java.util.List;

import com.mojang.authlib.GameProfile;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.gaming32.betauhcclientmodern.UHCClientModernMod;
import net.minecraft.client.gui.hud.spectator.SpectatorMenuCommand;
import net.minecraft.client.gui.hud.spectator.TeleportSpectatorMenu;
import net.minecraft.client.gui.hud.spectator.TeleportToSpecificPlayerSpectatorCommand;

@Mixin(TeleportSpectatorMenu.class)
public abstract class MixinTeleportSpectatorMenu {
    @Shadow
    private List<SpectatorMenuCommand> elements;

    @Inject(
        method = "<init>(Ljava/util/Collection;)V",
        at = @At("TAIL")
    )
    private void init(CallbackInfo ci) {
        elements.clear();
        for (var player : UHCClientModernMod.displayNames.entrySet()) {
            GameProfile profile = new GameProfile(null, player.getKey());
            elements.add(new TeleportToSpecificPlayerSpectatorCommand(profile));
        }
    }
}
