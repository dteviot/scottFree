package com.dteviot.scottfree.test;


import junit.framework.Assert;

import com.dteviot.scottfree.AdventureGame;
import com.dteviot.scottfree.AdventureStaticData;
import com.dteviot.scottfree.MainActivity;

import android.test.ActivityUnitTestCase;

public class TestAdventureStaticData extends ActivityUnitTestCase<MainActivity> {

    public TestAdventureStaticData() {
        super(MainActivity.class);
        // TODO Auto-generated constructor stub
    }

    public void testMatchWord() {
        String[] words = { "ANY", "NORTH", "SOUTH", "EAST", "WEST", "UP", "DOWN" }; 

        AdventureStaticData data = new AdventureStaticData(1, 1,
                7, 1, 1,
                1, 1,
                1, 1, 1);
        
        Assert.assertEquals(1, data.MatchWord("North", words));
        Assert.assertEquals(3, data.MatchWord("east", words));
        Assert.assertEquals(5, data.MatchWord("UP", words));
        Assert.assertEquals(AdventureGame.AnyWord, data.MatchWord("unknown", words));
    }
}
