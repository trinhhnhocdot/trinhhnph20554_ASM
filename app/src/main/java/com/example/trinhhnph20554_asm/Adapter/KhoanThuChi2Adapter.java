package com.example.trinhhnph20554_asm.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trinhhnph20554_asm.DAO.KhoanThuChiDAO;
import com.example.trinhhnph20554_asm.R;
import com.example.trinhhnph20554_asm.modal.KhoanThuChi;

import java.util.ArrayList;
import java.util.HashMap;

public class KhoanThuChi2Adapter extends BaseAdapter {
    private ArrayList<KhoanThuChi> list;
    private Context context;
    private KhoanThuChiDAO thuChiDAO;
    private ArrayList<HashMap<String,Object>> listSpiner;

    public KhoanThuChi2Adapter(ArrayList<KhoanThuChi> list, Context context, KhoanThuChiDAO thuChiDAO, ArrayList<HashMap<String,Object>> listSpiner) {
        this.list = list;
        this.context = context;
        this.thuChiDAO = thuChiDAO;
        this.listSpiner = listSpiner;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewOfffItem{
                TextView txtten,txttien;
                ImageView ivSua,ivXoa;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        ViewOfffItem viewOffItem;
        if (view==null){
            view = inflater.inflate(R.layout.item_khoanchi,parent,false);
            viewOffItem = new ViewOfffItem();
            viewOffItem.txtten = view.findViewById(R.id.txtTen);
            viewOffItem.txttien = view.findViewById(R.id.txtTien);
            viewOffItem.ivSua = view.findViewById(R.id.ivSua);
            viewOffItem.ivXoa = view.findViewById(R.id.ivXoa);
            view.setTag(viewOffItem);
        }else {
            viewOffItem = (ViewOfffItem) view.getTag();

        }
        viewOffItem.txtten.setText(list.get(position).getTenloai());
        viewOffItem.txttien.setText(String.valueOf(list.get(position).getTien()));
        viewOffItem.ivSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDialoagsua(list.get(position));
            }
        });
        viewOffItem.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int makhoan = list.get(position) .getMakhoan();
               if (thuChiDAO.xoaKhoanThuChi(makhoan)){
                   Toast.makeText(context, "xóa thành công", Toast.LENGTH_SHORT).show();
               reloaddata();
               }else {
                   Toast.makeText(context, "xóa thất bại", Toast.LENGTH_SHORT).show();
               }
            }
        });
        return view;
    }
    private void  showDialoagsua(KhoanThuChi khoanThuChi){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_suakhoanchi,null);
        Spinner spnLoaithu = view.findViewById(R.id.spnloaichi);
        EditText edtTien = view.findViewById(R.id.edttien);
        builder.setView(view);
        SimpleAdapter adapter = new SimpleAdapter(context
                ,listSpiner, android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},new int[]{android.R.id.text1});
        spnLoaithu.setAdapter(adapter);
        edtTien.setText(String.valueOf(khoanThuChi.getTien()));
        int index  = 0;
        int vitri = -1;
        for(HashMap<String,Object>item :listSpiner){
            if ((int)item.get("maloai")==khoanThuChi.getMaloai()){
                vitri =index;
            }else {
                index++;
            }
        }
        spnLoaithu.setSelection(vitri);
        builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    String tien = edtTien.getText().toString();
                    HashMap<String,Object> selected =(HashMap<String, Object>) spnLoaithu.getSelectedItem();
           int maloai = (int) selected.get("maloai");
           khoanThuChi.setTien(Integer.parseInt(tien));
           khoanThuChi.setMaloai(maloai);
           if (thuChiDAO.capnhatKhoanThuChi(khoanThuChi)){
               Toast.makeText(context, "cập nhật thành công", Toast.LENGTH_SHORT).show();
        reloaddata();
           }else {
               Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
           }
            }
        });
        builder.setNegativeButton("Hủyy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void reloaddata(){
        list.clear();
        list = thuChiDAO.getDSKhoanThuChi("chi");
        notifyDataSetChanged();

    }
}
