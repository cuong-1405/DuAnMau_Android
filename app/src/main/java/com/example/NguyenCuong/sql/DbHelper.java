package com.example.NguyenCuong.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {

    static final String dbName="PNLIB";
    static final int dbVersion=1;

    public DbHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            String createTableThuThu="create table ThuThu (" +
                    "maTT text PRIMARY KEY, " +
                    "hoTen text NOT NULL, " +
                    "matKhau text NOT NULL)";
            db.execSQL(createTableThuThu);

        String createTableThanhVien="create table ThanhVien (" +
                "maTV INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "hoTen text NOT NULL, " +
                "namSinh text NOT NULL)";
        db.execSQL(createTableThanhVien);

        String createTableLoaiSach="create table LoaiSach (" +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nhaSX text NOT NULL, " +
                "tenLoai text NOT NULL)";
        db.execSQL(createTableLoaiSach);

        String createTableSach="create table Sach (" +
                "maSach INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenSach text NOT NULL, " +
                "giaThue INTEGER NOT NULL, " +
                "maLoai INTEGER REFERENCES LoaiSach(maLoai))";
        db.execSQL(createTableSach);

        String createTablePhieuMuon="create table PhieuMuon (" +
                "maPM INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maTT text REFERENCES ThuThu(maTT), " +
                "maTV INTEGER REFERENCES ThanhVien(maTV), " +
                "maSach INTEGER REFERENCES Sach(maSach), " +
                "tienThue INTEGER NOT NULL, " +
                "ngay DATE NOT NULL, " +
                "traSach INTEGER NOT NULL )";
        db.execSQL(createTablePhieuMuon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String dropTableThuThu = "drop table if exists ThuThu";
        db.execSQL(dropTableThuThu);
        String dropTableThanhVien = "drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);

        onCreate(db);
    }
}
