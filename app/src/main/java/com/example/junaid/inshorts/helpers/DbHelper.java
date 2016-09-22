package com.example.junaid.inshorts.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.junaid.inshorts.Card;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junaid on 29/01/16.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "InShorts.db";

    public static final String TABLE_CARD = "card";

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table card" +
                        "(image_url text,body text)"
        );
    }
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {}

    public boolean insertFragmentData(String imageUrl, String body){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("image_url", imageUrl);
        contentValues.put("body", body);
        db.insert("card", null, contentValues);
        return true;
    }

    public List<Card> getCards( JSONArray mJsonArray){
        List<Card> cards = new ArrayList<>();

        Card card = null;
        try {
        for(int i = 0; i < mJsonArray.length(); i++){
            card = new Card(mJsonArray.getJSONObject(i).getString("image_url"),
                    mJsonArray.getJSONObject(i).getString("body"),
                    mJsonArray.getJSONObject(i).getString("shortLine"),
                    mJsonArray.getJSONObject(i).getString("likes"),
                    mJsonArray.getJSONObject(i).getString("more"),
                    mJsonArray.getJSONObject(i).getString("reportby"));

            cards.add(card);
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*
        String query = "SELECT  * FROM " + TABLE_CARD;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                card = new Card(cursor.getString(0), cursor.getString(1));
                cards.add(card);
            } while (cursor.moveToNext());
        }
        */
        return cards;
    }

}
