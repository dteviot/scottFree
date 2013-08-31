package com.dteviot.scottfree;

/*
 * A "thing" in adventure that can be picked up, looked at, manipulated, etc.
 */
public class Item {
    /*
     * location code that indicates object is carried
     */
    public final static int Carried = 255;
    
    
    /*
     * location code that indicates object is destroyed
     */
    public final static int Destroyed = 0;

    
    private ItemStaticData mStaticData;
    
    /*
     * "room" item is currently in
     */
    int            mLocation;
    
    public Item(ItemStaticData staticData) {
        mStaticData = staticData;
        mLocation = staticData.getStartLocation();
    }
    
    public String getName() { return mStaticData.getName(); }
    public int getStartLocation() { return mStaticData.getStartLocation(); }
    public int getNounCode() { return mStaticData.getNounCode(); }
    public boolean isTreasure() { return getName().charAt(0) == '*'; }
    
    public int getLocation() { return mLocation; }
    public void setLocation(int location) { mLocation = location; }
    public boolean isInInventory() { return mLocation == Carried; } 
    public void addToInventory() { mLocation = Carried; }
    public boolean isIn(int location) { return mLocation == location; }
    public boolean isDestroyed() { return mLocation == Destroyed; }
    public boolean isAtStartLocation() { return mLocation == getStartLocation(); }
}
