package com.example.trinhhnph20554_asm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trinhhnph20554_asm.Database.KhoanThuChiDB;
import com.example.trinhhnph20554_asm.modal.KhoanThuChi;
import com.example.trinhhnph20554_asm.modal.Loai;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class KhoanThuChiDAO {
    KhoanThuChiDB khoanThuChiDB;
    public KhoanThuChiDAO(Context context){
        khoanThuChiDB = new KhoanThuChiDB(context);
    }

    // lấy danh sách Loại thu/chi
    public ArrayList<Loai> getDSLoai(String loai){
        ArrayList<Loai> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = khoanThuChiDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAI",null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                String trangthai = cursor.getString(2);
                if (trangthai.equals(loai)){
                    list.add(new Loai(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));

                }
            }while (cursor.moveToNext());
        }
        return list;
    }

    // thêm loai thu/chi
    public boolean themMoiLoaiThuChi(Loai loai){
        SQLiteDatabase sqLiteDatabase = khoanThuChiDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put("maloai",loai.getMaLoai());
        contentValues.put("tenloai",loai.getTenLoai());
        contentValues.put("trangthai",loai.getTrangthai());

        long check = sqLiteDatabase.insert("LOAI",null,contentValues);
        if(check==-1)
            return false;
            return true;

    }

    public boolean capNhapLoaiThuChi(Loai loai){
        SQLiteDatabase sqLiteDatabase = khoanThuChiDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maloai",loai.getMaLoai());
        contentValues.put("tenloai",loai.getTenLoai());
        contentValues.put("trangthai",loai.getTrangthai());
        long check = sqLiteDatabase.update("LOAI",contentValues,"maloai=?",new String[]{String.valueOf(loai.getMaLoai())});
    if (check==-1)
        return false;
    return true;
    }
    public boolean xoaLoaiThuChi(int maloai){
        SQLiteDatabase sqLiteDatabase = khoanThuChiDB.getWritableDatabase();
        long check = sqLiteDatabase.delete("LOAI","maloai=?",new String[]{String.valueOf(maloai)});
        if (check==-1)
            return false;
        return true;

    }

    public ArrayList<KhoanThuChi> getDSKhoanThuChi(String loai){
            ArrayList<KhoanThuChi> list = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = khoanThuChiDB.getReadableDatabase();
              String query = "select k.makhoan,k.tien,k.maloai,l.tenloai from khoanthuchi k,loai l where k.maloai = l.maloai and l.trangthai =?";
            Cursor cursor = sqLiteDatabase.rawQuery(query,new String[]{loai});
            if(cursor.getCount()>0){
                cursor.moveToFirst();
                do {
                    list.add(new KhoanThuChi(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3)));
                }while (cursor.moveToNext());
            }
                    return list;
    }
    public boolean themMoiKhoanThuChi(KhoanThuChi khoanThuChi){
        SQLiteDatabase sqLiteDatabase = khoanThuChiDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tien",khoanThuChi.getTien());
        contentValues.put("maloai",khoanThuChi.getMaloai());
        long check = sqLiteDatabase.insert("khoanthuchi",null,contentValues);
        if (check==-1){
            return false;
        }
        return  true;
    }
    public boolean capnhatKhoanThuChi(KhoanThuChi khoanThuChi){
        SQLiteDatabase sqLiteDatabase = khoanThuChiDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tien",khoanThuChi.getTien());
        contentValues.put("maloai",khoanThuChi.getMaloai());
        long check = sqLiteDatabase.update("KHOANTHUCHI",contentValues,"makhoan=?",new String[]{String.valueOf(khoanThuChi.getMakhoan())});
        if (check==-1){
            return false;
        }
        return  true;
    }
    public boolean xoaKhoanThuChi(int makhoan){
        SQLiteDatabase sqLiteDatabase = khoanThuChiDB.getWritableDatabase();
        long check = sqLiteDatabase.delete("KHOANTHUCHI","makhoan=?",new String[]{String.valueOf(makhoan)});
        if (check==-1){
            return false;
        }
        return  true;
    }

}
