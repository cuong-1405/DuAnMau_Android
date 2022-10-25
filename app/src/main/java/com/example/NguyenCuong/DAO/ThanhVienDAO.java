package com.example.NguyenCuong.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
        values.put("gioiTinh",odj.gioiTinh);
        return db.insert("ThanhVien",null,values);
    }

    public long update(ThanhVien odj){
        ContentValues values = new ContentValues();
        values.put("hoTen",odj.hoTen);
        values.put("namSinh",odj.namSinh);
        values.put("gioiTinh",odj.gioiTinh);
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

    @SuppressLint("Range")
    public List<ThanhVien> getData(String sql, String...selectionArgs) {
        List<ThanhVien> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            ThanhVien obj = new ThanhVien();
            obj.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setNamSinh(c.getString(c.getColumnIndex("namSinh")));
            obj.setGioiTinh(Integer.parseInt(c.getString(c.getColumnIndex("gioiTinh"))));
            list.add(obj);
        }
        return list;
    }
}
