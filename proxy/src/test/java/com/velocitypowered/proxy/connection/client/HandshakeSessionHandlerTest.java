package com.velocitypowered.proxy.connection.client;

import static com.velocitypowered.proxy.connection.client.HandshakeSessionHandler.cleanVhost;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.velocitypowered.proxy.connection.forge.ForgeConstants;
import org.junit.jupiter.api.Test;

class HandshakeSessionHandlerTest {

  @Test
  void cleanVhostHandlesGoodHostname() {
    assertEquals("localhost", cleanVhost("localhost"));
    assertEquals("mc.example.com", cleanVhost("mc.example.com"));
  }

  @Test
  void cleanVhostHandlesTrailingOctet() {
    assertEquals("localhost", cleanVhost("localhost."));
    assertEquals("mc.example.com", cleanVhost("mc.example.com."));
  }

  @Test
  void cleanVhostHandlesForge() {
    // We want to test both modern and legacy forge
    assertEquals("localhost", cleanVhost("localhost" + ForgeConstants.HANDSHAKE_HOSTNAME_TOKEN));
    assertEquals("mc.example.com",
            cleanVhost("mc.example.com" + ForgeConstants.HANDSHAKE_HOSTNAME_TOKEN));
    assertEquals("localhost",
            cleanVhost("localhost" + ForgeConstants.LEGACY_HANDSHAKE_HOSTNAME_TOKEN));
    assertEquals("mc.example.com",
            cleanVhost("mc.example.com" + ForgeConstants.LEGACY_HANDSHAKE_HOSTNAME_TOKEN));
  }

  @Test
  void cleanVhostHandlesOctetsAndForge() {
    // We want to test both modern and legacy forge
    assertEquals("localhost", cleanVhost("localhost." + ForgeConstants.HANDSHAKE_HOSTNAME_TOKEN));
    assertEquals("mc.example.com",
            cleanVhost("mc.example.com." + ForgeConstants.HANDSHAKE_HOSTNAME_TOKEN));
    assertEquals("localhost",
            cleanVhost("localhost." + ForgeConstants.LEGACY_HANDSHAKE_HOSTNAME_TOKEN));
    assertEquals("mc.example.com",
            cleanVhost("mc.example.com." + ForgeConstants.LEGACY_HANDSHAKE_HOSTNAME_TOKEN));
  }

  @Test
  void cleanVhostHandlesEmptyHostnames() {
    // We want to test both modern and legacy forge
    assertEquals("", cleanVhost(""));
    assertEquals("", cleanVhost(ForgeConstants.HANDSHAKE_HOSTNAME_TOKEN));
    assertEquals("", cleanVhost(ForgeConstants.LEGACY_HANDSHAKE_HOSTNAME_TOKEN));
    assertEquals("", cleanVhost("."));
    assertEquals("", cleanVhost("." + ForgeConstants.HANDSHAKE_HOSTNAME_TOKEN));
    assertEquals("", cleanVhost("." + ForgeConstants.LEGACY_HANDSHAKE_HOSTNAME_TOKEN));
  }
}