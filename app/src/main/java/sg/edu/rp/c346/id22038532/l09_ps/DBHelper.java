package sg.edu.rp.c346.id22038532.l09_ps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    //Start version with 1
    //increment by 1 whenever db schema changes
    private static final int DATABASE_VER = 1;
    //Filename of the database
    private static final String DATABASE_NAME = "MySong.db";

    private static final String TABLE_SONG = "Song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGER = "singer";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }


    public void insertSong(String title, String singer, String year, String stars) {
        //Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        //Use ContentValues object to store the values for the db operation
        ContentValues values = new ContentValues();
        //Store the column name as key and the description as value
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGER, singer);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STARS, stars);
        //Insert the row into the TABLES_TASK
        db.insert(TABLE_SONG, null, values);
        //Close database connection
        db.close();
    }

    public ArrayList<String> getSongContent()
    {
        //Create an ArrayList that holds String objects
        ArrayList<String> songs = new ArrayList<String>();
        //Get the instance of the database to read
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID,COLUMN_TITLE,COLUMN_SINGER,COLUMN_YEAR,COLUMN_STARS};
        //Run the query and get back the Cursor object
        Cursor cursor = db.query(TABLE_SONG, columns, null,null,null,null,null);

        //moveToFirst() moves to first row, null if no records
        if(cursor.moveToFirst())
        {
            //Loop while moveToNext() points to next row
            //and retruns true; moveToNext() return false
            // when no more rows to move to
            do {
                //Add the task content to the ArrayList object
                //getString(0) retrieves first column data
                //  getString(1) return second column data
                //  getInt(0) if data is an integer value
                songs.add(cursor.getString(1));
                songs.add(cursor.getString(2));
                songs.add(cursor.getString(3));
                songs.add(cursor.getString(4));
            }
            while (cursor.moveToNext());
        }
        //close connection
        cursor.close();
        db.close();
        return songs;
    }

    public int updateSong(Songs data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_SINGER, data.getSingers());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_SONG, values,condition,args);
        db.close();
        return result;
    }

    public int deleteSong(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition,args);
        db.close();
        return result;
    }

    public ArrayList <Songs> getSong() {
        ArrayList<Songs> songs = new ArrayList<Songs>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_STARS};
        Cursor cursor = db.query(TABLE_SONG, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int years = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Songs obj = new Songs(id,title,singers,years,stars);
                songs.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_SONG + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TITLE + " TEXT," + COLUMN_SINGER + " TEXT," + COLUMN_YEAR + " TEXT," + COLUMN_STARS + " TEXT )";
        db.execSQL(createTableSql);
        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        // Create table(s) again
        onCreate(db);

    }
}
