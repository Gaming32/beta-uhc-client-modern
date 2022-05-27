package io.github.gaming32.betauhcclientmodern.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import io.github.gaming32.betauhcclientmodern.UHCClientModernMod;
import net.minecraft.client.gui.hud.spectator.SpectatorMenu;
import net.minecraft.client.gui.hud.spectator.TeleportToSpecificPlayerSpectatorCommand;

@Mixin(TeleportToSpecificPlayerSpectatorCommand.class)
public class MixinTeleportToSpecificPlayerSpectatorCommand {
    @Overwrite
    public void use(SpectatorMenu menu) {
        UHCClientModernMod.getPacketManager().sendPacket(
            "teleport",
            ((TeleportToSpecificPlayerSpectatorCommand)(Object)this).getName().asString()
        );
    }
}
