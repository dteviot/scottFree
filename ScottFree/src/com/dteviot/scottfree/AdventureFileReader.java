package com.dteviot.scottfree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/*
 * Loads an adventure file
 */
public class AdventureFileReader {
    BufferedReader mBuffer;

    public AdventureFileReader(InputStream in) throws UnsupportedEncodingException {
        InputStreamReader isr = new InputStreamReader(in, "UTF-8");
        mBuffer = new BufferedReader(isr);
    }
    
    public AdventureStaticData loadDatabase() throws IOException {
        AdventureStaticData staticData = loadDatabaseHeader();
        loadActions(staticData);
        loadWords(staticData);
        loadRooms(staticData);
        loadMessages(staticData);
        loadItems(staticData);
        return staticData;
    }

    public AdventureStaticData loadDatabaseHeader() throws IOException {
        fetchInt();  // I don't know what first value is
        int numObjects = fetchInt() + 1;
        int numActions = fetchInt() + 1;
        int numWords = fetchInt() + 1;
        int numRooms = fetchInt() + 1;
        int maxCarry = fetchInt();
        int playerStartRoom = fetchInt();
        int numTreasures = fetchInt();
        fetchInt();                  // wordLength, not needed
        int lightRefill = fetchInt();
        int numMessages = fetchInt() + 1;
        int treasureRoom = fetchInt();
        
        return new AdventureStaticData(numObjects, numActions, numWords,
                numRooms, numMessages, maxCarry, playerStartRoom,
                numTreasures, lightRefill, treasureRoom);        
    }
    
    public void loadActions(AdventureStaticData staticData) throws IOException {
        int[] gameCode = staticData.getGameCode();
        for(int i = 0; i < gameCode.length; ++i) {
            gameCode[i] = fetchInt();    
        }
    }

    public void loadWords(AdventureStaticData staticData) throws IOException {
        for(int i = 0; i < staticData.numWords(); ++i) {
            staticData.getVerbs()[i] = fetchString();
            staticData.getNouns()[i] = fetchString();
        }
    }

    public void loadRooms(AdventureStaticData staticData) throws IOException {
        Room[] rooms = staticData.getRooms();
        for(int i = 0; i < rooms.length; ++i) {
            rooms[i] = new Room(this);
        }
    }

    public void loadMessages(AdventureStaticData staticData) throws IOException {
        String[] messages =  staticData.getMessages();
        for(int i = 0; i < messages.length; ++i) {
            messages[i] = fetchString();
        }
    }

    public void loadItems(AdventureStaticData staticData) throws IOException {
        for(int i = 0; i < staticData.numItems(); ++i) {
            staticData.addItem(new ItemStaticData(this, staticData), i);
        }
    }
    
    /*
     * @return the text that was enclosed in quotes
     */
    public String fetchString() throws IOException {
        // scan for first quote
        int ch;
        do {
            ch = mBuffer.read();  
        } while (ch != '"');
        
        // read up to next quote
        StringBuffer sb = new StringBuffer();
        while ((ch = mBuffer.read()) != '"') {
            if (ch != '\r') {
                sb.append((char)ch);
            }
        }
        return sb.toString();
    }
    
    public int fetchInt() throws IOException {
        // scan for first digit
        StringBuffer sb = new StringBuffer();
        int ch;
        do {
            ch = mBuffer.read();  
        } while (!Character.isDigit(ch));

        while(Character.isDigit(ch)) {
            sb.append((char)ch);
            ch = mBuffer.read();  
        }
        return Integer.parseInt(sb.toString(), 10);
    }
}
