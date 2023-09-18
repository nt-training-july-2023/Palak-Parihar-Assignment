package com.grievance.service;

import java.util.Base64;

/**
 * A service class for decoding base64-encoded data.
 */
public final class Base64DecodeService {

  /**
   * Decodes a base64-encoded string and returns it as a UTF-8 encoded string.
   *
   * @param base64EncodedPassword The base64-encoded string to decode.
   * @return The decoded string.
   * @throws IllegalArgumentException
   * If the input is not a valid base64-encoded string.
   */
  public static String decodeBase64ToString(
         final String base64EncodedPassword) {
    try {
      return new String(Base64.getDecoder().decode(base64EncodedPassword));
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid base64-encoded input", e);
    }
  }

  /**
   * private default constructor.
   */
  private Base64DecodeService() {
  }
}
