package com.dteviot.scottfree.test;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

import com.dteviot.scottfree.AdventureFileReader;
import com.dteviot.scottfree.MainActivity;

import android.content.res.AssetManager;
import android.test.ActivityUnitTestCase;

public class TestAdventureFileReader extends ActivityUnitTestCase<MainActivity> {

    public TestAdventureFileReader() {
        super(MainActivity.class);
        // TODO Auto-generated constructor stub
    }

    public void testFetchMultiLineString() {
        try {
            AssetManager assetManager = getInstrumentation().getContext().getAssets();
            InputStream in = assetManager.open("testdata00.dat");
            AdventureFileReader afr = new AdventureFileReader(in);
            Assert.assertEquals("", afr.fetchString());
            Assert.assertEquals("\n\n", afr.fetchString());
            Assert.assertEquals("*I'm at the bottom of a very deep chasm. High above me is\na pair of ledges. One has a bricked up window across its face\nthe other faces a Throne-room", 
                    afr.fetchString());
            
            Assert.assertEquals("\nWelcome to Adventure number: 1 `ADVENTURELAND`.\nIn this Adventure you're to find *TREASURES* & store them away.\n\nTo see how well you're doing say: `SCORE`", 
                    afr.fetchString());
            Assert.assertEquals("\nCheck with your favorite computer dealer for the next Adventure\nprogram: PIRATE ADVENTURE. If they don't carry `ADVENTURE` have\nthem call: 1-305-862-6917 today!\n", 
                    afr.fetchString());
            
            Assert.assertEquals("*Thick PERSIAN RUG*/RUG/", afr.fetchString());
            Assert.assertEquals(17, afr.fetchInt());
            Assert.assertEquals("Sign: `magic word's AWAY! Look la...`\n(Rest of sign is missing!)", 
                    afr.fetchString());
            Assert.assertEquals(18, afr.fetchInt());

            Assert.assertEquals("*STAR/STAR/", afr.fetchString());
            Assert.assertEquals(12, afr.fetchInt());
            Assert.assertEquals("Moat", afr.fetchString());
            Assert.assertEquals(1, afr.fetchInt());
            
            in.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.fail("testFetchMultiLineString");
        }
    }

}
