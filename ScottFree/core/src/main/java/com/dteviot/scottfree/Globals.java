package com.dteviot.scottfree;

public class Globals {
  public static final String TAG = "ScottFree";

  /*
   *  CLDC 1.1 helpers. Some idiot decided that case independent
   *  string compare could go away even though they kept all the
   *  other related functions... and people wonder why I think
   *  Java is badly designed! Instead we do it ourselves and in
   *  doing so end up creating lots of temporary objects
   *
   *  To minimise the pain we "know" that the second string is
   *  already upper case.
   */

  public static boolean StringEqual(String a, String b) {
    int len = b.length();
    if ((1 <= len) && (len < a.length())) {
      a = a.substring(0, len);
    }
    return (a.toUpperCase().equals(b));
  }
}
