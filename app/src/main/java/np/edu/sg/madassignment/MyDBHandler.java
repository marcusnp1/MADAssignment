package np.edu.sg.madassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Admin on 04/06/2018.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Exercises.db";
    public static final String TABLE_COMMENTS = "comments";
    public static final String TABLE_COMMENTS_2 = "comments2";
    public static final String TABLE_COMMENTS_3 = "comments3";
    public static final String TABLE_COMMENTS_4 = "comments4";

    public static final String COLUMN_ID = "UserID";
    public static final String COLUMN_NAME = "UserComments";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override//creates 4 tables on create to store data for each exercise
    public void onCreate(SQLiteDatabase db) {

        String CREATE_STUDENT_TABLE = "CREATE TABLE " +
                TABLE_COMMENTS + "(" + COLUMN_ID + " ," + COLUMN_NAME
                + " TEXT " + ")";
        db.execSQL(CREATE_STUDENT_TABLE);

        String CREATE_STUDENT_TABLE_2 = "CREATE TABLE comments2 " +
                "(" + COLUMN_ID + " ," + COLUMN_NAME
                + " TEXT " + ")";
        db.execSQL(CREATE_STUDENT_TABLE_2);
        String CREATE_STUDENT_TABLE_3 = "CREATE TABLE comments3 " +
                "(" + COLUMN_ID + " ," + COLUMN_NAME
                + " TEXT " + ")";
        db.execSQL(CREATE_STUDENT_TABLE_3);
        String CREATE_STUDENT_TABLE_4 = "CREATE TABLE comments4 " +
                "(" + COLUMN_ID + " ," + COLUMN_NAME
                + " TEXT " + ")";
        db.execSQL(CREATE_STUDENT_TABLE_4);

    }

    @Override// Updates the database
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS_2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS_3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS_4);
        onCreate(db);

    }
    //Method to add comments into the database table to store.
    public void addHandler(Comments comment, int type) {
        ContentValues values = new ContentValues();
        String query;
        if(type == 1)//checks for which table to use
        {
            query = "Select * FROM " + TABLE_COMMENTS;
        }
        else if(type == 2)
        {
            query = "Select * FROM " + TABLE_COMMENTS_2;
        }
        else if(type == 3)
        {
            query = "Select * FROM " + TABLE_COMMENTS_3;
        }
        else if(type == 4)
        {
            query = "Select * FROM " + TABLE_COMMENTS_4;
        }
        else{
            query = "Select * FROM " + TABLE_COMMENTS;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToLast();
        Comments commen = new Comments(); //create a new comment
        commen.setUserID(Integer.parseInt(cursor.getString(0)));
        cursor.close();
        int id = commen.getUserID();//obtain the ID for the comment

        id += 1;
        values.put(COLUMN_ID, id);
        values.put(COLUMN_NAME, comment.getUserComments());
        if(type == 1)//detemines which table to add the comment
        {
            db.insert(TABLE_COMMENTS, null, values);
        }
        else if(type == 2)
        {
            db.insert(TABLE_COMMENTS_2, null, values);
        }
        else if(type == 3)
        {
            db.insert(TABLE_COMMENTS_3, null, values);
        }
        else if(type == 4)
        {
            db.insert(TABLE_COMMENTS_4, null, values);
        }

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
    //Method to load the comments from the database to the app.
    public ArrayList<String> loadHandler(int type) {
        ArrayList<String> result = new ArrayList<>();
        String query;
        //detemines which table to load
        if(type == 1){
            query = "Select*FROM " + TABLE_COMMENTS ;
        }
        else if(type == 2) {
            query = "Select*FROM " + TABLE_COMMENTS_2 ;
        }
        else if(type == 3){
            query = "Select*FROM " + TABLE_COMMENTS_3 ;
        }
        else if(type == 4){
            query = "Select*FROM " + TABLE_COMMENTS_4 ;
        }
        else{
            query = "Select*FROM " + TABLE_COMMENTS ;
        }


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result.add( String.valueOf(result_0) + "\n" + result_1 +
                    System.getProperty("line.separator"));
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