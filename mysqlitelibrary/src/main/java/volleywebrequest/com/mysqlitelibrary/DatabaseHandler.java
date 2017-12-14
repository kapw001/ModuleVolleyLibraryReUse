package volleywebrequest.com.mysqlitelibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by yasar on 6/10/17.
 */

public class DatabaseHandler {

    private FeedReaderDbHelper feedReaderDbHelper;

    private static DatabaseHandler databaseHandler;

    private SQLiteDatabase db;

    private DatabaseHandler(Context context) {
        feedReaderDbHelper = new FeedReaderDbHelper(context);
        getWritableDatabase();
    }

    public static DatabaseHandler getInstace(Context context) {
        if (databaseHandler == null) {
            databaseHandler = new DatabaseHandler(context);
        }

        return databaseHandler;
    }


    public void getWritableDatabase() {
        db = feedReaderDbHelper.getWritableDatabase();
    }

    public void closeDatabase() {
        db.close();
    }

    public void addData(List<Contact> list) {

        Log.e(TAG, "addData: called ");

        if (db.isOpen()) {
            // Create a new map of values, where column names are the keys

            db.beginTransaction();
            for (int i = 0; i < list.size(); i++) {
                Contact contact = list.get(i);
                ContentValues values = new ContentValues();
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NAME, contact.getName());
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NUMBER, contact.getNumber());

// Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
//            Log.e(TAG, "addData: newRowId " + newRowId);
            }

            db.setTransactionSuccessful();
            db.endTransaction();
        } else {
            Log.e(TAG, "database not open ");
        }


    }

    public void updateData(Contact contact) {

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NAME, contact.getName());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NUMBER, contact.getNumber());

        // updating row
        long updateId = db.update(FeedReaderContract.FeedEntry.TABLE_NAME, values, FeedReaderContract.FeedEntry.COLUMN_NAME_NUMBER + " = ?",
                new String[]{String.valueOf(contact.getNumber())});
    }

    public List<Contact> getAllDatas() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setName(cursor.getString(1));
                contact.setNumber(cursor.getInt(2));
                // Adding contact to list
                contactList.add(contact);
//                Log.e(TAG, "getAllDatas: "+contact.toString() );
            } while (cursor.moveToNext());
        }

        cursor.close();

        // return contact list
        return contactList;
    }

    public int getDataCount() {
        String countQuery = "SELECT  * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public void deleteData(Contact contact) {

        db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, FeedReaderContract.FeedEntry.COLUMN_NAME_NUMBER + " = ?",
                new String[]{String.valueOf(contact.getNumber())});

    }

    public void deleteAllData() {
        db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, null, null);
    }

}
