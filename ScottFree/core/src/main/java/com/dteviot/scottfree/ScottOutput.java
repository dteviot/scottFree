package com.dteviot.scottfree;

public interface ScottOutput {
  void outputBottom(String s);

  void outputBottomNumber(int n);

  void topBegin();

  void topOutput(String s);

  String getTextToShow();

  void reset();
}
