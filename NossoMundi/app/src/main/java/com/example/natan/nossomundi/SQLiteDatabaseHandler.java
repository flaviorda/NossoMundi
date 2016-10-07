package com.example.natan.nossomundi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by natan on 9/15/2016.
 */
public class SQLiteDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UsersDB";
    private static final String TABLE_NAME = "Users";
    private static final String KEY_ID = "id";
    //private static final String KEY_ID_SELECTION = "idselection";
    private static final String KEY_NAME = "username";
    private static final String KEY_PASSWORD = "userpassword";

    private static final String[] COLUMNS = { KEY_ID , KEY_NAME, KEY_PASSWORD };

    public SQLiteDatabaseHandler(Context context) {
        super(context,DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE Users( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT , "+ "userpassword TEXT )";
        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void deleteOne(User user) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?",
                new String[] { String.valueOf(user.getId()) });
        db.close();

    }

    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        User user = new User();
        user.setId(Integer.parseInt(cursor.getString(0)));
        user.setName(cursor.getString(1));
        user.setPassword(cursor.getString(2));
        return user;

    }

    public List<User> allUsers() {

        List<User> users = new LinkedList<User>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = null;

        if (cursor.moveToFirst()) {
            do {
                user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                users.add(user);
            } while (cursor.moveToNext());
        }

        return users;
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_PASSWORD, user.getPassword());
        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_PASSWORD, user.getPassword());

        int i = db.update(TABLE_NAME, // table
                values, // column/value
                "id = ?", // selections
                new String[] { String.valueOf(user.getId()) });

        db.close();

        return i;
    }
}
