/**
 * Created by Pierre-Henry Soria on 16/10/2016.
 */
package ph7.me.ph2notetaking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ph2note.db";
    private static final int DB_VER = 1;

    // Constants that identifies table / columns
    public static final String TABLE_NOTE = "note";
    public static final String NOTE_ID = "_id";
    public static final String NOTE_TEXT = "noteText";
    public static final String NOTE_CREATED = "noteCreated";

    // SQL to create table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NOTE + " (" +
                    NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOTE_TEXT + " TEXT, " +
                    NOTE_CREATED + " TEXT default CURRENT_TIMESTAMP" +
                    ")";


    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        onCreate(db); // Then recreate the Note table
    }
}
