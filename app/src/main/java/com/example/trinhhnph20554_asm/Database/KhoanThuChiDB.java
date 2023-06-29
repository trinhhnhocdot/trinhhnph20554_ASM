package com.example.trinhhnph20554_asm.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class KhoanThuChiDB extends SQLiteOpenHelper {
   public KhoanThuChiDB(Context context){
            super(context ,"KHOANTHUCHIDB",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
            String qLoai = "CREATE TABLE LOAI(maloai integer primary key autoincrement, tenloai text,trangthai text)";
            db.execSQL(qLoai);
            String qKhoan ="CREATE TABLE KHOANTHUCHI(makhoan integer primary key autoincrement, tien integer,maloai integer)";
            db.execSQL(qKhoan);

            String dataLoai = "INSERT INTO LOAI VALUES(1,'tiền lương','thu'),(2,'tiền nhặt được không trả cho họ ','thu'),(3,'tiền xăng','chi')";
            db.execSQL(dataLoai);
             String dataKhoan = "INSERT INTO KHOANTHUCHI VALUES(1,1000,2),(2,9999,1),(3,6666,2)";
             db.execSQL(dataKhoan);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

       if(i!=i1){
           String dLoai ="DROP TABLE IF EXISTS LOAI";
           db.execSQL(dLoai);
           String dKhoan = "DROP TABLE KHOANTHUCHI";
           db.execSQL(dKhoan);
           onCreate(db);
       }

    }
}
