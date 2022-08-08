package sg.edu.rp.c346.mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Movie;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "NDSong.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MOVIE = "Movie";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_RATING = "ratings";

    public DBHelper(Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_MOVIE + "(" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT,"
                + COLUMN_GENRE + " TEXT, " + COLUMN_YEAR + " INTEGER, " + COLUMN_RATING
                + " TEXT " + ")";
        db.execSQL(CREATE_MOVIE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        //db.execSQL("ALTER TABLE " + TABLE_NOTE + " ADD COLUMN  module_name TEXT ");
        onCreate(db);

    }

    public ArrayList<Movies> getAllMovie() {
        ArrayList<Movies> songs = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
        Cursor cursor = db.query(TABLE_MOVIE, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movies song = new Movies(id, title, genre, year, rating);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public long insertMovie(String TITLE, String GENRE, int YEAR, String RATING ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, TITLE);
        values.put(COLUMN_GENRE, GENRE);
        values.put(COLUMN_YEAR, YEAR);
        values.put(COLUMN_RATING, RATING);
        long result = db.insert(TABLE_MOVIE, null, values);
        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1

        return result;
    }

    public int updateMovie(Movies data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_GENRE, data.getGenre());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_RATING, data.getRating());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_MOVIE, values, condition, args);
        db.close();
        return result;
    }

    public int deleteMovie(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_MOVIE, condition, args);
        db.close();
        return result;
    }

}

