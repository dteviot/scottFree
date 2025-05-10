package com.dteviot.scottfree;

import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
  private final static int LIST_ADVENTURE_ACTIVITY_ID = 0;
  public static final String PREFS_NAME = "EpubViewerPrefsFile";

  AdventureGame mAdventureGame;
  TextView mTextView;
  EditText mCommandEdit;
  ScottCanvas mScottCanvas;

  /*
   * Games are saved to preferences with this name
   */
  String mCurrentAdventure;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mTextView = (TextView) findViewById(R.id.text_view);
    mCommandEdit = (EditText) findViewById(R.id.command_text);
    Button goButton = (Button) (findViewById(R.id.go_button));
    goButton.setOnClickListener(mOnGoButtonClicked);
    mCommandEdit.addTextChangedListener(mTextWatcher);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle item selection
    switch (item.getItemId()) {
      case R.id.menu_start_adventure:
        launchAdventuresList();
        return true;
      case R.id.menu_load_adventure:
        loadSaveFile();
        return true;
      case R.id.menu_save_adventure:
        saveAdventure();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private void launchAdventuresList() {
    Intent listChaptersIntent = new Intent(this, ListAdventuresActivity.class);
    startActivityForResult(listChaptersIntent, LIST_ADVENTURE_ACTIVITY_ID);
  }

  /*
   * Should return with adventure to load
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK) {
      switch (requestCode) {
        case LIST_ADVENTURE_ACTIVITY_ID:
          onListAdventuresResult(data);
          break;

        default:
          Utility.showToast(this, R.string.something_is_badly_broken);
      }
    } else if (resultCode == RESULT_CANCELED) {
      Utility.showErrorToast(this, data);
    }
  }

  private void onListAdventuresResult(Intent data) {
    String fileName = data.getStringExtra(ListAdventuresActivity.ADVENTURE_EXTRA);
    loadAdventure(fileName);
  }

  private void loadAdventure(String fileName) {
    mScottCanvas = new ScottCanvas();
    mCurrentAdventure = fileName;
    mAdventureGame = new AdventureGame(mScottCanvas);

    try {
      InputStream in = getAssets().open(fileName);
      AdventureFileReader reader = new AdventureFileReader(in);
      mAdventureGame.setStaticData(reader.loadDatabase());
      in.close();
    } catch (IOException e) {
      Utility.showToast(this, R.string.failed_to_load_adventure);
    }

    mAdventureGame.FirstGameCycle();
    mTextView.setText(mScottCanvas.getTextToShow());
  }

  private void saveAdventure() {
    if (mAdventureGame != null) {
      String state = mAdventureGame.serializeState();
      SharedPreferences settings = getSharedPreferences(PREFS_NAME,
          Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = settings.edit();
      editor.putString(mCurrentAdventure, state);
      editor.commit();
    }
  }

  private void loadSaveFile() {
    if (mAdventureGame == null) {
      Utility.showToast(this, R.string.error_no_game_loaded);
    } else {
      SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
      String savedState = settings.getString(mCurrentAdventure, "");

      if (savedState.equals("")) {
        Utility.showToast(this, R.string.error_no_saved_game);
      } else {
        mAdventureGame.reset();
        mAdventureGame.deserializeState(savedState);
        mAdventureGame.Look();
        mTextView.setText(mScottCanvas.getTextToShow());
        mCommandEdit.setText("");
      }
    }
  }

  private OnClickListener mOnGoButtonClicked = new OnClickListener() {
    public void onClick(View view) {
      processCommand();
    }
  };

  private void processCommand() {
    if (mAdventureGame == null) {
      Utility.showToast(this, R.string.error_no_game_loaded);
    } else {
      String cmd = mCommandEdit.getText().toString();
      mAdventureGame.GameCycle(cmd);
      mTextView.setText(mScottCanvas.getTextToShow());
      mCommandEdit.setText("");
    }
  }

  private final TextWatcher mTextWatcher = new TextWatcher() {

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    public void afterTextChanged(Editable s) {
      // if enter pressed, process input so far
      if (0 < s.length() && s.charAt(s.length() - 1) == '\n') {
        processCommand();
      }
    }
  };
}
