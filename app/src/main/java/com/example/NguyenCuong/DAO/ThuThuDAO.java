package com.example.NguyenCuong.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.NguyenCuong.model.ThuThu;
import com.example.NguyenCuong.sql.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {

    private SQLiteDatabase db;

    public ThuThuDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    public long insert(ThuThu odj){
        ContentValues values = new ContentValues();
        values.put("maTT",odj.maTT);
        values.put("hoTen",odj.hoTen);
        values.put("matKhau",odj.matKhau);
        return db.insert("ThuThu",null,values);
    }
    public long insertadmin(){
        ContentValues values = new ContentValues();
        values.put("maTT","admin");
        values.put("hoTen","ADMIN");
        values.put("matKhau","admin");
        return db.insert("ThuThu",null,values);
    }

    public long update(ThuThu odj){
        ContentValues values = new ContentValues();
        values.put("maTT",odj.maTT);
        values.put("hoTen",odj.hoTen);
        values.put("matKhau",odj.matKhau);
        return db.update("ThuThu",values,"maTT=?",new String[]{String.valueOf(odj.maTT)});
    }

    public int delete(String id){
        return db.delete("ThuThu","maTT=?",new String[]{id});
    }

    public List<ThuThu> getAll(){
        String sql = "SELECT * FROM ThuThu";
        return getData(sql);
    }

    public ThuThu getID(String id){
        String sql = "SELECT * FROM ThuThu WHERE maTT=?";
        List<ThuThu> list = getData(sql,id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<ThuThu> getData(String sql, String...selectionArgs){
        List<ThuThu> list = new ArrayList<ThuThu>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            ThuThu obj = new ThuThu();
            obj.maTT = c.getString(c.getColumnIndex("maTT"));
            obj.hoTen = c.getString(c.getColumnIndex("hoTen"));
            obj.matKhau = c.getString(c.getColumnIndex("matKhau"));
            list.add(obj);
        }
        return list;
    }

    public int checkLogin(String id, String password){
        String sql = "SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?";
        List<ThuThu> list = getData(sql,id,password);
        if (list.size()==0){
            return -1;
        }
        return 1;
    }
}
