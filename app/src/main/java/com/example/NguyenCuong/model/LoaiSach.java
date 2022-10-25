package com.example.NguyenCuong.model;

public class LoaiSach {

    public int maLoai;
    public String nhaSX;
    public String tenLoai;

    public LoaiSach() {
    }

    public LoaiSach(int maLoai, String nhaSX, String tenLoai) {
        this.maLoai = maLoai;
        this.nhaSX = nhaSX;
        this.tenLoai = tenLoai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getNhaSX() {
        return nhaSX;
    }

    public void setNhaSX(String nhaSX) {
        this.nhaSX = nhaSX;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
