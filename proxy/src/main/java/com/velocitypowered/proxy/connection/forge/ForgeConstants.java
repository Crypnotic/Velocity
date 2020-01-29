package com.velocitypowered.proxy.connection.forge;

import com.velocitypowered.api.network.ProtocolVersion;
import com.velocitypowered.proxy.connection.MinecraftConnection;

public class ForgeConstants {

  /**
   * Clients attempting to connect to 1.8-1.12.2 Forge servers will have
   * this token appended to the hostname in the initial handshake
   * packet.
   */
  public static final String LEGACY_HANDSHAKE_HOSTNAME_TOKEN = "\0FML\0";

  /**
   * The channel for legacy forge handshakes.
   */
  public static final String LEGACY_FORGE_HANDSHAKE_CHANNEL = "FML|HS";

  /**
   * Clients attempting to connect to 1.13+ Forge servers will have
   * this token appended to the hostname in the initial handshake
   * packet.
   */
  public static final String HANDSHAKE_HOSTNAME_TOKEN = "\0FML2\0";

  /**
   * The channel for forge handshakes.
   */
  public static final String FORGE_HANDSHAKE_CHANNEL = "fml:handshake";

  /**
   * The channel for forge login messages.
   */
  public static final String FORGE_LOGIN_MESSAGE_CHANNEL = "fml:loginwrapper";

  /**
   * The channel for forge play messages.
   */
  public static final String FORGE_PLAY_MESSAGE_CHANNEL = "fml:play";

  /**
   * The reset packet discriminator.
   */
  private static final int RESET_DATA_DISCRIMINATOR = -2;

  /**
   * The acknowledgement packet discriminator.
   */
  static final int ACK_DISCRIMINATOR = -1;

  /**
   * The Server -> Client Hello discriminator.
   */
  static final int SERVER_HELLO_DISCRIMINATOR = 0;

  /**
   * The Client -> Server Hello discriminator.
   */
  static final int CLIENT_HELLO_DISCRIMINATOR = 1;

  /**
   * The Mod List discriminator.
   */
  static final int MOD_LIST_DISCRIMINATOR = 2;

  /**
   * The Registry discriminator.
   */
  static final int REGISTRY_DISCRIMINATOR = 3;

  /**
   * The payload for the reset packet.
   */
  static final byte[] FORGE_HANDSHAKE_RESET_DATA = new byte[]{RESET_DATA_DISCRIMINATOR, 0};

  private ForgeConstants() {
    throw new AssertionError();
  }

  /**
   * Get the handshake hostname token for the protocol
   * version the {@link MinecraftConnection} is using.
   *
   * @param mc the connection
   *
   * @return the handshake hostname token
   */
  public static String getHandshakeHostnameToken(MinecraftConnection mc) {
    if (mc.getProtocolVersion().compareTo(ProtocolVersion.MINECRAFT_1_13) < 0) {
      return LEGACY_HANDSHAKE_HOSTNAME_TOKEN;
    } else {
      return HANDSHAKE_HOSTNAME_TOKEN;
    }
  }

  /**
   * Determines if a handshake address was sent by forge.
   *
   * @param address the handshake address to be checked
   *
   * @return {@code true} if the address was sent by forge, {@code false} if not
   */
  public static boolean isForgeHandshakeAddress(String address) {
    if (address.endsWith(LEGACY_HANDSHAKE_HOSTNAME_TOKEN)
            || address.endsWith(HANDSHAKE_HOSTNAME_TOKEN)) {
      return true;
    }
    return false;
  }

  /**
   * Determines if this is either a modern or legacy channel.
   *
   * @param channel the channel to be checked
   *
   * @return {@code true} if this is the forge channel, {@code false} if not
   */
  public static boolean isForgeHandshakeChannel(String channel) {
    if (channel == null) {
      return false;
    }
    return channel == FORGE_HANDSHAKE_CHANNEL || channel == LEGACY_FORGE_HANDSHAKE_CHANNEL;
  }
}
