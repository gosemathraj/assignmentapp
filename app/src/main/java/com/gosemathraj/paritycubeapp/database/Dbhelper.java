package com.gosemathraj.paritycubeapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gosemathraj.paritycubeapp.model.Deals;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iamsparsh on 15/6/16.
 */
public class Dbhelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "paritycube";
    private static final String TABLE_NAME = "deals";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CURRENTPRICE = "current_price";
    private static final String KEY_ORIGINALPRICE = "original_price";
    private static final String KEY_OFFPERCENT = "off_percent";
    private static final String KEY_LIKECOUNTS = "like_count";
    private static final String KEY_COMMENTSCOUNT  = "comments_count";
    private static final String KEY_DEALER = "dealer";

    public Dbhelper(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_DEALS_TABLE = "CREATE TABLE "+TABLE_NAME +"("+KEY_ID+" TEXT PRIMARY KEY,"+KEY_TITLE+" TEXT,"+
                KEY_CURRENTPRICE+" TEXT,"+KEY_ORIGINALPRICE+" TEXT,"+KEY_OFFPERCENT+" TEXT,"+KEY_LIKECOUNTS+" TEXT,"+
                KEY_COMMENTSCOUNT+" TEXT,"+KEY_DEALER+" TEXT"+")";
        db.execSQL(CREATE_DEALS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void addDeals(List<Deals> deallist){

        for(int i = 0;i < deallist.size();i++){

            insertDeal(deallist.get(i));
        }
    }

    private void insertDeal(Deals deals) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID,deals.getId());
        values.put(KEY_TITLE,deals.getTitle());
        values.put(KEY_CURRENTPRICE,deals.getCurrent_price());
        values.put(KEY_ORIGINALPRICE,deals.getOriginal_price());
        values.put(KEY_OFFPERCENT,deals.getOff_percent());
        values.put(KEY_LIKECOUNTS,deals.getLike_count());
        values.put(KEY_COMMENTSCOUNT,deals.getComments_count());
        values.put(KEY_DEALER,deals.getDealer());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Deals> getAllDeals(){

        List<Deals> deallists = new ArrayList<>();

        String query = "SELECT * FROM "+TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{

                Deals m = new Deals();
                m.setId(cursor.getString(0));
                m.setTitle(cursor.getString(1));
                m.setCurrent_price(cursor.getString(2));
                m.setOriginal_price(cursor.getString(3));
                m.setOff_percent(cursor.getString(4));
                m.setLike_count(cursor.getString(5));
                m.setComments_count(cursor.getString(6));
                m.setDealer(cursor.getString(7));

                deallists.add(m);
            }while(cursor.moveToNext());
        }

        return deallists;

    }
}
