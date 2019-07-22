package np.edu.sg.madassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 04/06/2018.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Exercises.db";
    public static final String TABLE_COMMENTS = "comments";
    public static final String COLUMN_ID = "UserID";
    public static final String COLUMN_NAME = "UserComments";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " +
                TABLE_COMMENTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME
                + " TEXT " + ")";
        db.execSQL(CREATE_STUDENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);

    }

    public void addHandler(Comments comment) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, comment.getUserID());
        values.put(COLUMN_NAME, comment.getUserComments());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_COMMENTS, null, values);
        db.close();
    }

    public Comments findHandler(String studentname) {
        String query = "Select * FROM " + TABLE_COMMENTS + " WHERE " +
                COLUMN_NAME + " = '" + studentname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Comments comments = new Comments();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            comments.setUserID(Integer.parseInt(cursor.getString(0)));
            comments.setUserComments(cursor.getString(1));
            cursor.close();
        } else {
            comments = null;
        }
        db.close();
        return comments;
    }

    public String loadHandler() {
        String result = "";
        String query = "Select*FROM " + TABLE_COMMENTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public boolean deleteHandler(int ID) {
        boolean result = false;
        String query = "Select*FROM " + TABLE_COMMENTS + " WHERE " + COLUMN_ID + " = '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Comments comment = new Comments();
        if (cursor.moveToFirst()) {
            comment.setUserID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_COMMENTS, COLUMN_ID + "=?",
                    new String[] {
                            String.valueOf(comment.getUserID())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateHandler(int ID, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, name);
        return db.update(TABLE_COMMENTS, args, COLUMN_ID + "=" + ID, null) > 0;
    }
}