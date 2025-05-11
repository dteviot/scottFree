package com.dteviot.scottfree;

import java.util.ArrayList;

/*
 * Converts text output from Adventure Game into string
 * to display on Text View
 */
public class ScottCanvas implements ScottOutput {
  static final int MAX_HISTORY_LINES = 10;

  /*
   * Top part of screen, what user is viewing
   */
  ArrayList<String> mLookText;

  /*
   * Bottom part of screen, what user has done
   */
  ArrayList<String> mHistoryText;

  public ScottCanvas() {
    mLookText = new ArrayList<String>();
    mHistoryText = new ArrayList<String>();
  }

  @Override public void outputBottom(String s) {
    if (mHistoryText.size() == MAX_HISTORY_LINES) {
      mHistoryText.remove(0);
    }
    mHistoryText.add(s);
  }

  @Override public void outputBottomNumber(int n) {
    outputBottom("" + n);
  }

  @Override public void topBegin() {
    mLookText.clear();
  }

  @Override public void topOutput(String s) {
    mLookText.add(s);
  }

  @Override public String getTextToShow() {
    StringBuilder sb = new StringBuilder();
    for (String s : mLookText) {
      sb.append(s);
    }
    sb.append("\n===========================\n");
    for (String s : mHistoryText) {
      sb.append(s);
    }
    return sb.toString();
  }

  @Override public void reset() {
    mLookText.clear();
    mHistoryText.clear();
  }
}
