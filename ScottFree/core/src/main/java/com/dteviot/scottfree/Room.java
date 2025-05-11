package com.dteviot.scottfree;

import java.io.IOException;

/*
 * A "location" in the adventure
 */
public class Room {
  public final static int EXITS_PER_ROOM = 6;

  private String mDescription;

  /*
   * Links to other rooms
   */
  private int[] mExits;

  public Room(AdventureFileReader reader) throws IOException {
    mExits = new int[EXITS_PER_ROOM];
    for (int i = 0; i < EXITS_PER_ROOM; ++i) {
      mExits[i] = reader.fetchInt();
    }
    mDescription = reader.fetchString();
  }

  public String getDescription() {
    return mDescription;
  }

  public int getExit(int index) {
    return mExits[index];
  }
}
