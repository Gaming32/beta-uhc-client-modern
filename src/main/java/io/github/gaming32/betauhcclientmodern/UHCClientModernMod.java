package io.github.gaming32.betauhcclientmodern;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.border.WorldBorder;

@SuppressWarnings("resource")
public class UHCClientModernMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("beta-uhc-client-modern");

    public static final Set<String> spectatingPlayers = new HashSet<>();
    public static final Map<String, Integer> glowingEffects = new HashMap<>();
    public static final Map<Integer, String> mapIdToPlayerName = new HashMap<>();
    public static final Map<String, String> displayNames = new HashMap<>();

    private static CustomPacketManager packetManager;

    @Override
    public void onInitialize() {
        packetManager = new CustomPacketManager();
        LOGGER.info("Loaded uhc-client");
    }

    public static void handleCustomPacket(String packetType, String data) {
        LOGGER.info(packetType + ' ' + data);
        switch (packetType) {
            case "ping": {
                packetManager.sendPacket("pong");
                break;
            }
            case "spectator": {
                spectatingPlayers.add(data);
                break;
            }
            case "reset-spectators": {
                spectatingPlayers.clear();
                break;
            }
            case "worldborder": {
                WorldBorder border = MinecraftClient.getInstance().world.getWorldBorder();
                border.setCenter(0, 0);
                border.setSize(CustomPacketManager.stringToDouble(data) * 2);
                break;
            }
            case "worldborderinterp": {
                WorldBorder border = MinecraftClient.getInstance().world.getWorldBorder();
                border.setCenter(0, 0);
                int midIndex = data.indexOf(' ');
                double worldBorderDest = CustomPacketManager.stringToDouble(data.substring(0, midIndex));
                long worldBorderTicksRemaining = Long.parseUnsignedLong(data.substring(midIndex + 1), 16);
                border.interpolateSize(border.getSize(), worldBorderDest * 2, worldBorderTicksRemaining * 50);
                break;
            }
            case "mapplayer": {
                int midIndex = data.indexOf(' ');
                mapIdToPlayerName.put(
                    Integer.valueOf(data.substring(0, midIndex), 16),
                    data.substring(midIndex + 1)
                );
                break;
            }
            case "glowing": {
                int midIndex = data.indexOf(' ');
                glowingEffects.put(
                    data.substring(midIndex + 1),
                    Integer.valueOf(data.substring(0, midIndex), 16)
                );
                break;
            }
            case "noglowing": {
                if (data == null) {
                    glowingEffects.clear();
                } else {
                    glowingEffects.remove(data);
                }
                break;
            }
            case "displayname": {
                int midIndex = data.indexOf(' ');
                if (midIndex == -1) {
                    displayNames.put(data, data);
                } else {
                    displayNames.put(data.substring(0, midIndex), data.substring(midIndex + 1));
                }
                break;
            }
            case "cleardisplaynames": {
                displayNames.clear();
                break;
            }
            case "removeplayer": {
                displayNames.remove(data);
                break;
            }
            default:
                LOGGER.warn("Unknown packet type: " + packetType);
                break;
        }
    }

    public static CustomPacketManager getPacketManager() {
        return packetManager;
    }
}
