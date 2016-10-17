/**
 * Created by Pierre-Henry Soria on 17/10/2016.
 */
package ph7.me.ph2notetaking;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

public class NoteProvider extends ContentProvider {

    // Authority is the unique name for the app
    private static final String AUTHORITY = "ph7.me.ph2notetaking.noteprovider";
    private static final String BASE_PATH = "note";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH );

    // Constant to identify the requested operation
    private static final int NOTE = 1;
    private static final int NOTE_ID = 2;

    private SQLiteDatabase database;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, NOTE);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", NOTE_ID);
    }

    @Override
    public boolean onCreate() {
        DBOpenHelper dbHelper = new DBOpenHelper(getContext());
        database = dbHelper.getWritableDatabase();

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return database.query(
                DBOpenHelper.TABLE_NOTE, DBOpenHelper.ALL_COLUMNS, selection, null, null, null, DBOpenHelper.NOTE_CREATED + " " + DBOpenHelper.DESC
        );
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = database.insert(DBOpenHelper.TABLE_NOTE, null, values);

        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return database.delete(DBOpenHelper.TABLE_NOTE, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return database.update(DBOpenHelper.TABLE_NOTE, values, selection, selectionArgs);
    }
}
