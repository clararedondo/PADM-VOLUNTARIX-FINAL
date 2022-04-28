package voluntarix.screens;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseAccess extends SQLiteOpenHelper {

    private static final String DB_NAME = "voluntarix";

    public DataBaseAccess(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS voluntaries (title VARCHAR(64), description VARCHAR(512), publisher VARCHAR(64), day INTEGER, month INTEGER, year INTEGER, location VARCHAR(128));");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tags (tag VARCHAR(32), voluntary INTEGER, FOREIGN KEY (voluntary) REFERENCES voluntaries(rowid));");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (user VARCHAR(32) PRIMARY KEY, password VARCHAR(32) NOT NULL, name VARCHAR(32), last_name VARCHAR(32), email VARCHAR(64), location VARCHAR(64), description VARCHAR(512));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void storeUser(User user) {
        ContentValues content = new ContentValues();
        content.put("user", user.username);
        content.put("password", user.password);
        content.put("name", user.name);
        content.put("last_name", user.lastName);
        content.put("email", user.email);
        content.put("location", user.location);
        content.put("description", user.description);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("users", null, content);
        db.close();
    }

    @SuppressLint("Range")
    public User log_in(String username, String password) {
        if (username.equals(""))
            return null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT * FROM users WHERE user = ?;", new String[]{username});
        db.close();
        if (cr.getCount() != 1) {
            return null;
        }
        String pass = cr.getString(cr.getColumnIndex("password"));
        if (!password.equals(pass)) {
            return null;
        }

        return new User(cr.getString(cr.getColumnIndex("name")),
                cr.getString(cr.getColumnIndex("last_name")),
                cr.getString(cr.getColumnIndex("email")),
                cr.getString(cr.getColumnIndex("user")),
                cr.getString(cr.getColumnIndex("password")),
                cr.getString(cr.getColumnIndex("location")),
                cr.getString(cr.getColumnIndex("description")));
    }

    @SuppressLint("Range")
    public List<VoluntaryEvent> getEventsOn(int day, int month, int year) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT * FROM voluntaries WHERE day = ? AND month = ? AND year = ?;", new String[]{Integer.toString(day), Integer.toString(month), Integer.toString(year)});
        List<VoluntaryEvent> retrieval = new ArrayList<VoluntaryEvent>();
        for (int i = 0; i < cr.getCount(); i++) {
            cr.moveToPosition(i);
            Cursor tag_cr = db.rawQuery("SELECT tag from tags WHERE voluntary = ?;", new String[]{Integer.toString(cr.getInt(cr.getColumnIndex("rowid")))});
            List<String> tags = new ArrayList<String>();
            for (int j = 0; j < tag_cr.getCount(); j++) {
                tag_cr.moveToPosition(j);
                tags.add(tag_cr.getString(tag_cr.getColumnIndex("tag")));
            }
            retrieval.add(new VoluntaryEvent(cr.getString(cr.getColumnIndex("title")), cr.getString(cr.getColumnIndex("description")),
                    cr.getString(cr.getColumnIndex("publisher")), tags, cr.getInt(cr.getColumnIndex("day")),
                    cr.getInt(cr.getColumnIndex("month")), cr.getInt(cr.getColumnIndex("year")), cr.getString(cr.getColumnIndex("location"))));
        }
        db.close();
        return retrieval;
    }
}
