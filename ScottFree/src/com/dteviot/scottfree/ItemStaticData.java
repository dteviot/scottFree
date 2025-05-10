package com.dteviot.scottfree;

import java.io.IOException;

/*
 * The unchanging properties of a "thing" in adventure
 * that can be picked up, looked at, manipulated, etc.
 */
public class ItemStaticData {
  private int mStartLocation;
  private String mName;
  private int mNounCode;

  public ItemStaticData(int startLocation, String name, int nounCode) {
    mStartLocation = startLocation;
    mName = name;
    mNounCode = nounCode;
  }

  /*
   * Split up Item line of form "<name/noun/" startLocation"
   */
  public ItemStaticData(AdventureFileReader reader, AdventureStaticData staticData)
      throws IOException {
    String itemString = reader.fetchString();
    mStartLocation = reader.fetchInt();

    // set to default value
    mNounCode = AdventureGame.NoWord;

    //.. split name from noun
    int slashIndex = itemString.indexOf('/');
    if (slashIndex == -1) {
      // no slash, there's no noun
      mName = itemString;
    } else {
      mName = itemString.substring(0, slashIndex);
      itemString = itemString.substring(slashIndex + 1);

      // Some games use // to mean no auto get/drop word!
      slashIndex = itemString.indexOf('/');
      if (0 < slashIndex) {
        itemString = itemString.substring(0, slashIndex);
        int temp = staticData.MatchNoun(itemString);
        if (temp != AdventureGame.AnyWord) {
          mNounCode = temp;
        }
      }
    }
  }

  public String getName() {
    return mName;
  }

  public int getStartLocation() {
    return mStartLocation;
  }

  public int getNounCode() {
    return mNounCode;
  }
}
