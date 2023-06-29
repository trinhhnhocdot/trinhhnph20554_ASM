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
import android.widget.TextView;
import android.widget.Toast;

import com.example.trinhhnph20554_asm.DAO.KhoanThuChiDAO;
import com.example.trinhhnph20554_asm.R;
import com.example.trinhhnph20554_asm.modal.Loai;

import java.util.ArrayList;

public class loaichiAdapter extends BaseAdapter {
    private ArrayList<Loai> list;
    private Context context;
    private KhoanThuChiDAO khoanThuChiDAO;
    public loaichiAdapter(ArrayList<Loai> list, Context context,KhoanThuChiDAO khoanThuChiDAO) {
        this.list = list;
        this.khoanThuChiDAO = khoanThuChiDAO;
        this.context = context;
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
    public static class ViewOffItem{
        TextView txtTen;
        ImageView ivSua,ivXoa;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        loaichiAdapter.ViewOffItem viewOffItem;


        if (view == null ){
            viewOffItem = new loaichiAdapter.ViewOffItem();
            view = inflater.inflate(R.layout.item_loaichi,parent,false);
            viewOffItem.txtTen = view.findViewById(R.id.txtTen);
            viewOffItem.ivXoa = view.findViewById(R.id.ivXoa);
            viewOffItem.ivSua = view.findViewById(R.id.ivSua);
            view.setTag(viewOffItem);
        }else {
            viewOffItem = (loaichiAdapter.ViewOffItem) view.getTag();
        }
        viewOffItem.txtTen.setText(list.get(position).getTenLoai());



        viewOffItem.ivSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaLogSuaLoaichi(list.get(position));
            }
        });

        viewOffItem.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idcanxoa = list.get(position).getMaLoai();
                if (khoanThuChiDAO.xoaLoaiThuChi(idcanxoa)){
                    Toast.makeText(context, "xóa thành công ", Toast.LENGTH_SHORT).show();
                    reLoadData();
                }else {
                    Toast.makeText(context, "xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    private void showDiaLogSuaLoaichi(Loai loai){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialoa_sualoaichi,null);
        EditText edtInput = view.findViewById(R.id.edtInput);
        edtInput.setText(loai.getTenLoai());
        builder.setView(view);



        builder.setPositiveButton("Cập nhật ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String tenloai = edtInput.getText().toString();
                loai.setTenLoai(tenloai);
                if (khoanThuChiDAO.capNhapLoaiThuChi(loai)){
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    reLoadData();
                }else {
                    Toast.makeText(context, "Cập nhật thất bại ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void reLoadData(){
        list.clear();
        list = khoanThuChiDAO.getDSLoai("chi");
        notifyDataSetChanged();
    }

}
