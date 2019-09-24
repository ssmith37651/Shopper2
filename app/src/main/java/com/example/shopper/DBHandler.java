package com.example.shopper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "shopper.db";

    private static final String TABLE_SHOPPING_LIST = "shoppinglist";
    private static final String COLUMN_LIST_ID = "_id";
    private static final String COLUMN_LIST_NAME = "name";
    private static final String COLUMN_LIST_STORE = "store";
    private static final String COLUMN_LIST_DATE = "date";

    /**
     * Initializes the Database
     * @param context
     * @param cursorFactory
     */
    public DBHandler (Context context, SQLiteDatabase.CursorFactory cursorFactory){
        super(context, DATABASE_NAME, cursorFactory, DATABASE_VERSION);
    }

    /**
     * Creates the table
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_SHOPPING_LIST + "(" +
                COLUMN_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LIST_NAME + " TEXT, " +
                COLUMN_LIST_STORE + " TEXT, " +
                COLUMN_LIST_DATE + " TEXT" +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    /**
     * Any time there is a change of the database, this method deletes the existing
     * database and recreate it to reflect the change
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPING_LIST);
        onCreate(sqLiteDatabase);
    }

    public void addShoppingList (String name, String store, String date){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_LIST_NAME, name);
        values.put(COLUMN_LIST_STORE, store);
        values.put(COLUMN_LIST_DATE, date);

        db.insert(TABLE_SHOPPING_LIST, null, values);
        db.close();
    }

    public Cursor getShoppingLists(){

        // get writable reference to shopper database
        SQLiteDatabase db = getWritableDatabase();

        // select all data from shoppinglist table and return it as a cursor
        return db.rawQuery("SELECT * FROM " + TABLE_SHOPPING_LIST, null);
    }

    public String getShoppingListName(int id){

        // get writable reference to shopper database
        SQLiteDatabase db = getWritableDatabase();

        String dbString = "";

        // create SQL String that will get the shopping list name
        String query = "SELECT * FROM " + TABLE_SHOPPING_LIST +
                " WHERE " + COLUMN_LIST_ID + " = " + id;

        // execute the SELECT statement and store the result in a cursor
        Cursor cursor = db.rawQuery(query, null);

        // move to first row in the cursor
        cursor.moveToFirst();

        // check to make sure there's a shopping list in name value
        if (cursor.getString(cursor.getColumnIndex("name")) != null){
            // store it in the String that will be returned by the method
            dbString = cursor.getString(cursor.getColumnIndex("name"));
        }

        return dbString;
    }
}
