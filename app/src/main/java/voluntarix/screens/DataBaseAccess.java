package voluntarix.screens;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseAccess extends SQLiteOpenHelper {

    private static final String DB_NAME = "voluntarix";

    public DataBaseAccess(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS voluntaries (title VARCHAR(64), description VARCHAR(512), publisher VARCHAR(64))");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tags (tag VARCHAR(32), voluntary INTEGER, FOREIGN KEY (voluntary) REFERENCES voluntaries(rowid))");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (user VARCHAR(32) PRIMARY KEY, password VARCHAR(32) NOT NULL, name VARCHAR(32), last_name VARCHAR(32), email VARCHAR(64), location VARCHAR(64), description VARCHAR(512))");

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
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery("SELECT password FROM users WHERE user = " + username, null);
        if (cr.getCount() != 1) {
            db.close();
            return null;
        }
        String pass = cr.getString(cr.getColumnIndex("password"));
        if (!password.equals(pass)) {
            db.close();
            return null;
        }
        cr = db.rawQuery("SELECT * FROM users WHERE user = " + username, null);
        return new User(cr.getString(cr.getColumnIndex("name")),
                cr.getString(cr.getColumnIndex("last_name")),
                cr.getString(cr.getColumnIndex("email")),
                cr.getString(cr.getColumnIndex("user")),
                cr.getString(cr.getColumnIndex("password")),
                cr.getString(cr.getColumnIndex("location")),
                cr.getString(cr.getColumnIndex("description")));
    }
}
