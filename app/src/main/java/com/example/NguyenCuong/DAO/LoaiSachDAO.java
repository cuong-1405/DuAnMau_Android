package com.example.NguyenCuong.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.NguyenCuong.model.LoaiSach;
import com.example.NguyenCuong.sql.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {

    private SQLiteDatabase db;

    public LoaiSachDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    public long insert(LoaiSach odj){
        ContentValues values = new ContentValues();
        values.put("nhaSX",odj.nhaSX);
        values.put("tenLoai",odj.tenLoai);
        return db.insert("LoaiSach",null,values);
    }

    public long update(LoaiSach odj){
        ContentValues values = new ContentValues();
        values.put("tenLoai",odj.tenLoai);
        values.put("nhaSX",odj.nhaSX);
        return db.update("LoaiSach",values,"maLoai=?",new String[]{String.valueOf(odj.maLoai)});
    }

    public int delete(String id){
        return db.delete("LoaiSach","maLoai=?",new String[]{id});
    }

    public List<LoaiSach> getAll(){
        String sql = "SELECT * FROM LoaiSach";
        return getData(sql);
    }

    public LoaiSach getID(String id){
        String sql = "SELECT * FROM LoaiSach WHERE maLoai=?";
        List<LoaiSach> list = getData(sql,id);
        return list.get(0);
    }

    private List<LoaiSach> getData(String sql, String...selectionArgs){
        List<LoaiSach> list = new ArrayList<LoaiSach>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            LoaiSach obj = new LoaiSach();
            obj.maLoai = Integer.parseInt(c.getString(0));
            obj.nhaSX = c.getString(1);
            obj.tenLoai = c.getString(2);
            list.add(obj);
        }
        return list;
    }

    public List<Integer> getAllMaLoai(){
        List<Integer> list = new ArrayList<>();
        Cursor cursor = db.query("LoaiSach",null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            list.add(cursor.getInt(0));
            cursor.moveToNext();
        }cursor.close();
        return list;
    }

}
