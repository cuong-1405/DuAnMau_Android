package com.example.NguyenCuong.model;

public class ThanhVien {
    public int maTV;
    public String hoTen;
    public String namSinh;
    public int gioiTinh;

    public ThanhVien() {
    }

    public ThanhVien(int maTV, String hoTen, String namSinh, int gioiTinh) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Override
    public String toString() {
        return "ThanhVien{" +
                "maTV=" + maTV +
                ", hoTen='" + hoTen + '\'' +
                ", namSinh='" + namSinh + '\'' +
                ", gioiTinh=" + gioiTinh +
                '}';
    }
//    public String htGioiTinh(){
//        if(this.gioiTinh==0){
//            return "nam";
//        }
//        return "nữ";
//    }

}
