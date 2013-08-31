package com.dteviot.scottfree;


import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListAdventuresActivity extends ListActivity {
    public static final String ADVENTURE_EXTRA = "ADVENTURE_EXTRA";

    private static class AdventureFile {
        private String mTitle;
        private String mFile;
        
        public AdventureFile(String file, String title) {
            mTitle = title;
            mFile = file;
        }
        
        public String getTitle() { return mTitle; }
        public String getFile() { return mFile; }
    }

    private ArrayList<AdventureFile> mAdventuresList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildAdventuresList();
        getListView().setAdapter(new AdventureAdapter(this));
    }

    private void buildAdventuresList() {
        mAdventuresList = new ArrayList<AdventureFile>();
        mAdventuresList.add(new AdventureFile("adv01.dat", "Adventureland"));
        mAdventuresList.add(new AdventureFile("adv02.dat", "Pirate Adventure"));
        mAdventuresList.add(new AdventureFile("adv03.dat", "Mission Impossible"));
        mAdventuresList.add(new AdventureFile("adv04.dat", "Voodoo Castle"));
        mAdventuresList.add(new AdventureFile("adv05.dat", "The Count"));
        mAdventuresList.add(new AdventureFile("adv06.dat", "Strange Odyssey"));
        mAdventuresList.add(new AdventureFile("adv07.dat", "Mystery Fun House"));
        mAdventuresList.add(new AdventureFile("adv08.dat", "Pyramid of Doom"));
        mAdventuresList.add(new AdventureFile("adv09.dat", "Ghost Town"));
        mAdventuresList.add(new AdventureFile("adv10.dat", "Savage Island, Part I"));
        mAdventuresList.add(new AdventureFile("adv11.dat", "Savage Island, Part II"));
        mAdventuresList.add(new AdventureFile("adv12.dat", "The Golden Voyage"));
        mAdventuresList.add(new AdventureFile("adv13.dat", "Sorcerer of Claymorgue Castle"));
        mAdventuresList.add(new AdventureFile("adv14a.dat", "Return to Pirate's Isle"));
        mAdventuresList.add(new AdventureFile("quest1.dat", "The Hulk"));
        mAdventuresList.add(new AdventureFile("sampler1.dat", "Adventureland (sampler version)"));
    }
    
    /*
     * Class implementing the "ViewHolder pattern", for better ListView
     * performance
     */
    private static class ViewHolder {
        public TextView textView; // refers to ListView item's TextView
        public AdventureFile adventureFile;
    }

    /*
     * Populates entries on the list
     */
    private class AdventureAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public AdventureAdapter(Context context) {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder; // holds references to current item's GUI

            // if convertView is null, inflate GUI and create ViewHolder;
            // otherwise, get existing ViewHolder
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.adventure_list_item, null);
                viewHolder = new ViewHolder();

                viewHolder.textView = (TextView) convertView.findViewById(R.id.adventure_title);
                convertView.setTag(viewHolder); // store as View's tag
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.adventureFile = (AdventureFile)getItem(position);
            String title = viewHolder.adventureFile.getTitle();
            viewHolder.textView.setText(title);

            return convertView;
        }

        @Override
        public int getCount() {
            return mAdventuresList.size();
        }

        @Override
        public Object getItem(int position) {
            return mAdventuresList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent();
        String fileName = ((ViewHolder)v.getTag()).adventureFile.getFile(); 
        intent.putExtra(ADVENTURE_EXTRA, fileName);
        setResult(RESULT_OK, intent);
        finish();
    }    
}
