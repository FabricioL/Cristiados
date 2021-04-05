package cristiados.triviaquiz;


import android.content.ContentValues;
import android.content.Context;
import android.content.pm.FeatureGroupInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class UserInfoHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "User.db";
    //If you want to add more questions or wanna update table values
    //or any kind of modification in db just increment version no.
    private static final int DB_VERSION = 1;
    //Table name
    private static final String TABLE_NAME = "TU";
    //Id of question
    private static final String UID = "_UID";
    //Question
    private static final String RESP_CORRECT = "RESP_CORRECT";
    //Option A
    private static final String FECHA = "FECHA";
    //So basically we are now creating table with first column-id , sec column-question , third column -option A, fourth column -option B , Fifth column -option C , sixth column -option D , seventh column - answer(i.e ans of  question)
    private static final String CREATE_TABLE = "CREATE TABLE  IF NOT EXISTS " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + RESP_CORRECT + " INTEGER , " + FECHA + " VARCHAR(255) ) "+" ;";
    //Drop table query
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private Context context;

    UserInfoHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //OnCreate is called only once
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //OnUpgrade is called when ever we upgrade or increment our database version no
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }



    public void addScore(Pair<Integer,String> entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(RESP_CORRECT, entry.first);
            values.put(FECHA, entry.second);
            db.insert(TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    public List< Pair<Integer,String> > getAllScores() {

        List< Pair<Integer,String> > score = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String column[] = {UID, RESP_CORRECT, FECHA};
        Cursor cursor = db.query(TABLE_NAME, column, null, null, null, null, RESP_CORRECT+" DESC LIMIT 10");


        while (cursor.moveToNext()) {
            Pair<Integer, String> p;
            p = new Pair<Integer, String>(Integer.valueOf(cursor.getString(1)),cursor.getString(2));
            score.add(p);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return score;
    }
}
