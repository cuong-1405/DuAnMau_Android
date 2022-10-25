package com.example.NguyenCuong.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.NguyenCuong.model.ThanhVien;
import com.example.NguyenCuong.sql.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {

    private SQLiteDatabase db;

    public ThanhVienDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    public long insert(ThanhVien odj){
        ContentValues values = new ContentValues();
        values.put("hoTen",odj.hoTen);
        values.put("namSinh",odj.namSinh);
        return db.insert("ThanhVien",null,values);
    }

    public long update(ThanhVien odj){
        ContentValues values = new ContentValues();
        values.put("hoTen",odj.hoTen);
        values.put("namSinh",odj.namSinh);
        return db.update("ThanhVien",values,"maTV=?",new String[]{String.valueOf(odj.maTV)});
    }

    public int delete(String id){
        return db.delete("ThanhVien","maTV=?",new String[]{id});
    }

    public List<ThanhVien> getAll(){
        String sql = "SELECT * FROM ThanhVien";
        return getData(sql);
    }

    public ThanhVien getID(String id){
        String sql = "SELECT * FROM ThanhVien WHERE maTV=?";
        List<ThanhVien> list = getData(sql,id);
        return list.get(0);
    }

    private List<ThanhVien> getData(String sql, String...selectionArgs){
        List<ThanhVien> list = new ArrayList<ThanhVien>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            ThanhVien obj = new ThanhVien();
            obj.maTV = Integer.parseInt(c.getString(0));
            obj.hoTen = c.getString(1);
            obj.namSinh = c.getString(2);
            list.add(obj);
        }
        return list;
    }
}
