package io.github.gaming32.betauhcclientmodern;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

@SuppressWarnings("resource")
public final class CustomPacketManager {
    public CustomPacketManager() {
    }

    private static ClientPlayerEntity getClientPlayer() {
        return MinecraftClient.getInstance().player;
    }

    public void sendPacket(String packetType) {
        getClientPlayer().sendChatMessage("canyonuhc:" + packetType);
    }

    public void sendPacket(String packetType, String data) {
        getClientPlayer().sendChatMessage("canyonuhc:" + packetType + ' ' + data);
    }

    public boolean handleMessage(String message) {
        if (message.startsWith("canyonuhc:")) {
            int endIndex = message.indexOf(' ', 10);
            String data;
            if (endIndex == -1) {
                endIndex = message.length();
                data = null;
            } else {
                data = message.substring(endIndex + 1);
            }
            String packetType = message.substring(10, endIndex);
            UHCClientModernMod.handleCustomPacket(packetType, data);
            return true;
        }
        return false;
    }

    public static String doubleToString(double d) {
        return Long.toHexString(Double.doubleToRawLongBits(d));
    }

    public static double stringToDouble(String s) {
        return Double.longBitsToDouble(Long.parseUnsignedLong(s, 16));
    }
}
