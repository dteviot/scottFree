package com.dteviot.scottfree;

/*
 * The static data that describes an adventure.
 * Is mostly a POJO.
 */
public class AdventureStaticData {
  private ItemStaticData[] mItemStaticData;
  private String[] mMessages;
  private Room[] mRooms;
  private String[] mVerbs;
  private String[] mNouns;
  private int[] mGameCode;

  private int mMaxCarry;
  private int mPlayerStartRoom;
  private int mNumTreasures;
  private int mLightRefill;
  private int mTreasureRoom;

  public AdventureStaticData(int numObjects, int numActions,
      int numWords, int numRooms, int numMessages,
      int maxCarry, int playerStartRoom,
      int numTreasures, int lightRefill, int treasureRoom) {
    mItemStaticData = new ItemStaticData[numObjects];
    mGameCode = new int[numActions * 8];
    mVerbs = new String[numWords];
    mNouns = new String[numWords];
    mMessages = new String[numMessages];
    mRooms = new Room[numRooms];
    mMaxCarry = maxCarry;
    mPlayerStartRoom = playerStartRoom;
    mNumTreasures = numTreasures;
    mLightRefill = lightRefill;
    mTreasureRoom = treasureRoom;
  }

  /*
   * Details of an object
   */
  public void addItem(ItemStaticData item, int index) {
    mItemStaticData[index] = item;
  }

  public Item[] createItems() {
    Item[] items = new Item[numItems()];
    for (int i = 0; i < numItems(); ++i) {
      items[i] = new Item(mItemStaticData[i]);
    }
    return items;
  }

  public int MatchWord(String s, String[] words) {
    int len = s.length();
    char num = 0;

      if (len == 0) {
          return AdventureGame.NoWord;
      }

    for (int i = 0; i < words.length; i++) {
      if (words[i].length() > 1 && words[i].charAt(0) == '*') {
          if (Globals.StringEqual(s, words[i].substring(1))) {
              return num;
          }
      } else {
        num = (char) i;
          if (Globals.StringEqual(s, words[i])) {
              return num;
          }
      }
    }
    return AdventureGame.AnyWord;
  }

  public int MatchVerb(String s) {
    return MatchWord(s, mVerbs);
  }

  public int MatchNoun(String s) {
    return MatchWord(s, mNouns);
  }

  public int numItems() {
    return mItemStaticData.length;
  }

  public int numActions() {
    return mGameCode.length / 8;
  }

  public int numWords() {
    return mVerbs.length;
  }

  public String[] getMessages() {
    return mMessages;
  }

  public Room[] getRooms() {
    return mRooms;
  }

  public String[] getVerbs() {
    return mVerbs;
  }

  public String[] getNouns() {
    return mNouns;
  }

  public int[] getGameCode() {
    return mGameCode;
  }

  public void setMessages(String[] messages) {
    mMessages = messages;
  }

  public int getMaxCarry() {
    return mMaxCarry;
  }

  public int getPlayerStartRoom() {
    return mPlayerStartRoom;
  }

  public int getNumTreasures() {
    return mNumTreasures;
  }

  public int getLightRefill() {
    return mLightRefill;
  }

  public int getTreasureRoom() {
    return mTreasureRoom;
  }
}
