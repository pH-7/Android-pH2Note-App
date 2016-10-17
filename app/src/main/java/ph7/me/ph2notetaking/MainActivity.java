/**
 * Created by Pierre-Henry Soria on 16/10/2016.
 */
package ph7.me.ph2notetaking;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String DEFAULT_NOTE_TITLE = "New Note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNote(DEFAULT_NOTE_TITLE);
    }

    private void addNote(String title) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.NOTE_TEXT, title);
        Uri noteUri = getContentResolver().insert(NoteProvider.CONTENT_URI, values);

        Log.d("MainActivity", "Note Added, ID " + noteUri.getLastPathSegment());
    }

}
