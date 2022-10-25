package com.example.NguyenCuong.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.NguyenCuong.model.PhieuMuon;
import com.example.NguyenCuong.sql.DbHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {

    private SQLiteDatabase db;

    public PhieuMuonDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    public long insert(PhieuMuon odj){
        ContentValues values = new ContentValues();
        values.put("maTT",odj.maTT);
        values.put("maTV",odj.maTV);
        values.put("maSach",odj.maSach);
        values.put("ngay",String.valueOf(odj.ngay));
        values.put("tienThue",odj.tienThue);
        values.put("traSach",odj.traSach);
        return db.insert("PhieuMuon",null,values);
    }

    public long update(PhieuMuon odj){
        ContentValues values = new ContentValues();
        values.put("maTT",odj.maTT);
        values.put("maTV",odj.maTV);
        values.put("maSach",odj.maSach);
        values.put("ngay",String.valueOf(odj.ngay));
        values.put("tienThue",odj.tienThue);
        values.put("traSach",odj.traSach);
        return db.update("PhieuMuon",values,"maPM=?",new String[]{String.valueOf(odj.maPM)});
    }

    public int delete(String id){
        return db.delete("PhieuMuon","maPM=?",new String[]{id});
    }

    public List<PhieuMuon> getAll(){
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }

    public PhieuMuon getID(String id){
        String sql = "SELECT * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> list = getData(sql,id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<PhieuMuon> getData(String sql, String...selectionArgs){
        List<PhieuMuon> list = new ArrayList<PhieuMuon>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            PhieuMuon obj = new PhieuMuon();
            obj.maPM = Integer.parseInt(c.getString(c.getColumnIndex("maPM")));
            obj.maTT = c.getString(c.getColumnIndex("maTT"));
            obj.maTV = Integer.parseInt(c.getString(c.getColumnIndex("maTV")));
            obj.maSach = Integer.parseInt(c.getString(c.getColumnIndex("maSach")));
            obj.tienThue = Integer.parseInt(c.getString(c.getColumnIndex("tienThue")));
            obj.ngay = Date.valueOf(c.getString(c.getColumnIndex("ngay")));
            obj.traSach = Integer.parseInt(c.getString(c.getColumnIndex("traSach")));
            list.add(obj);
        }
        return list;
    }
}
