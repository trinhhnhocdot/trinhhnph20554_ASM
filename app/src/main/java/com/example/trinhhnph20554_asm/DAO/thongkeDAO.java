package com.example.trinhhnph20554_asm.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trinhhnph20554_asm.Database.KhoanThuChiDB;

public class thongkeDAO {
    private KhoanThuChiDB khoanThuChiDB;

    public thongkeDAO(Context context) {
        khoanThuChiDB = new KhoanThuChiDB(context);
    }

    public float[] getThongTinThuChi() {
        SQLiteDatabase sqLiteDatabase = khoanThuChiDB.getReadableDatabase();
        int thu = 0, chi = 0;
        //select sum(tien)
        //from khoan
        //where idLoai in (select idLoai from Loai where trangthai = 0)
        Cursor cursorThu = sqLiteDatabase.rawQuery("select sum(tien) from KHOANTHUCHI where maloai in (select maloai from LOAI where trangthai='thu' ) ", null);
        if (cursorThu.getCount() != 0) {
            cursorThu.moveToFirst();
            thu = cursorThu.getInt(0);
        }
        //select sum(tien)
        //from khoan
        //where idLoai in (select idLoai from Loai where trangthai = 1)
        Cursor cursorChi = sqLiteDatabase.rawQuery("select sum(tien) from KHOANTHUCHI where maloai in (select maloai from LOAI where trangthai = 'chi')", null);
        if (cursorChi.getCount() != 0) {
            cursorChi.moveToFirst();
            chi = cursorChi.getInt(0);
        }
        float[] ketQua = new float[]{thu, chi};
        return ketQua;
    }
}
