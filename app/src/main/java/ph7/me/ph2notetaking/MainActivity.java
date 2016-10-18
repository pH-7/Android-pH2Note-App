/**
 * Created by Pierre-Henry Soria on 16/10/2016.
 */
package ph7.me.ph2notetaking;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public static final String DEFAULT_NOTE_TITLE = "New Note";

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNote(DEFAULT_NOTE_TITLE);

        Cursor cursor = getContentResolver().query(NoteProvider.CONTENT_URI, DBOpenHelper.ALL_COLUMNS, null, null, null, null);
        String[] from = {DBOpenHelper.NOTE_TEXT};
        int[] to  = {android.R.id.text1};
        CursorAdapter cursorAdapter = new SimpleCursorAdapter(this, android.R.simple_list_item1, cursor, from, to, 0);

        ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(cursorAdapter);
    }

    private void addNote(String title) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.NOTE_TEXT, title);
        Uri noteUri = getContentResolver().insert(NoteProvider.CONTENT_URI, values);

        Log.d("MainActivity", "Note Added, ID " + noteUri.getLastPathSegment());
    }

}
