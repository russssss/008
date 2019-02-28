package com.example.t_008.feature_storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.t_008.feature_app.IContext;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private DBHelper dbHelper;

    public Storage(IContext iContext) {
        if (iContext instanceof Context) {
            dbHelper = new DBHelper(iContext.getContext());
        }
    }

    public void saveData(List<CurrencyDbModel> currencyDbModelList) {

        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.beginTransaction();
        for (CurrencyDbModel currencyDbModel : currencyDbModelList) {
            contentValues.put("numCode", currencyDbModel.getNumCode());
            contentValues.put("charCode", currencyDbModel.getCharCode());
            contentValues.put("nominal", currencyDbModel.getNominal());
            contentValues.put("name", currencyDbModel.getName());
            contentValues.put("value", currencyDbModel.getValue());

            int rowID = db.update(ConstDB.DB_NAME, contentValues, "charCode=?", new String[]{""});
            if (rowID == 0) {
                db.insertWithOnConflict(ConstDB.DB_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
            }

        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    public List<CurrencyDbModel> readData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(ConstDB.DB_NAME, null, null, null, null, null, null);

        List<CurrencyDbModel> currencyDbModelList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            int numCode = cursor.getColumnIndex("numCode");
            int charCode = cursor.getColumnIndex("charCode");
            int nominal = cursor.getColumnIndex("nominal");
            int name = cursor.getColumnIndex("name");
            int value = cursor.getColumnIndex("value");

            do {
                currencyDbModelList.add(new CurrencyDbModel.Builder()
                        .numCode(cursor.getInt(numCode))
                        .charCode(cursor.getString(charCode))
                        .nominal(cursor.getInt(nominal))
                        .name(cursor.getString(name))
                        .value(cursor.getString(value))
                        .build());

            } while (cursor.moveToNext());
        }
        cursor.close();

        return currencyDbModelList;
    }
}
