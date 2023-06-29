package com.example.trinhhnph20554_asm.modal;

public class Loai {
    private int maLoai;
    private String tenLoai;
    private String trangthai;

    public Loai(int maLoai, String tenLoai, String trangthai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.trangthai = trangthai;
    }

    public Loai(String tenLoai, String trangthai) {
        this.tenLoai = tenLoai;
        this.trangthai = trangthai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
