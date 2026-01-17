package com.caio.chespirito.utils;

public final class Utils {

  private Utils() {}

  public static String normalize(String value) {
    if (value == null) {
      return null;
    }
    return value.trim();
  }
}
