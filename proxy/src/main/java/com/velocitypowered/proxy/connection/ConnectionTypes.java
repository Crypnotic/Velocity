package com.velocitypowered.proxy.connection;

import com.velocitypowered.proxy.connection.backend.BackendConnectionPhases;
import com.velocitypowered.proxy.connection.client.ClientConnectionPhases;
import com.velocitypowered.proxy.connection.forge.ForgeConnectionType;
import com.velocitypowered.proxy.connection.forge.ForgeHandshakeClientPhase;
import com.velocitypowered.proxy.connection.util.ConnectionTypeImpl;

/**
 * The connection types supported by Velocity.
 */
public final class ConnectionTypes {

  /**
   * Indicates that the connection has yet to reach the
   * point where we have a definitive answer as to what
   * type of connection we have.
   */
  public static final ConnectionType UNDETERMINED =
      new ConnectionTypeImpl(ClientConnectionPhases.VANILLA, BackendConnectionPhases.UNKNOWN);

  /**
   * Indicates that the connection is a Vanilla connection.
   */
  public static final ConnectionType VANILLA =
      new ConnectionTypeImpl(ClientConnectionPhases.VANILLA, BackendConnectionPhases.VANILLA);

  public static final ConnectionType UNDETERMINED_17 = new ConnectionTypeImpl(
      ForgeHandshakeClientPhase.NOT_STARTED, BackendConnectionPhases.UNKNOWN);

  /**
   * Indicates that the connection is a Forge connection.
   */
  public static final ConnectionType FORGE = new ForgeConnectionType();

  private ConnectionTypes() {
    throw new AssertionError();
  }
}
