package com.dteviot.scottfree;

import java.util.ArrayList;

/*
 * Converts text output from Adventure Game into string
 * to display on Text View
 */
public class ScottCanvas {
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

    public void outputBottom(String s) { 
        if (mHistoryText.size() == MAX_HISTORY_LINES) {
            mHistoryText.remove(0);
        }
        mHistoryText.add(s);
    }
    
    public void outputBottomNumber(int n) {
        outputBottom(""+n);
    }
    
    public void topBegin() {
        mLookText.clear();
    }
    
    public void topOutput(String s) {
        mLookText.add(s);
    }
    
    public String getTextToShow() {
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

    public void reset() {
        mLookText.clear();
        mHistoryText.clear();
    }
}
