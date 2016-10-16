package ph7.me.ph2notetaking;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDatabase();
    }

    protected void createDatabase() {
        DBOpenHelper dbHelper = new DBOpenHelper(this);
        dbHelper.getWritableDatabase();
    }
}
