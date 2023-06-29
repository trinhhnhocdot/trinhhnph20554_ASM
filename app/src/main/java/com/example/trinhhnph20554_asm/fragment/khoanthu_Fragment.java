package com.example.trinhhnph20554_asm.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.trinhhnph20554_asm.Adapter.KhoanThuChiAdapter;
import com.example.trinhhnph20554_asm.DAO.KhoanThuChiDAO;
import com.example.trinhhnph20554_asm.R;
import com.example.trinhhnph20554_asm.modal.KhoanThuChi;
import com.example.trinhhnph20554_asm.modal.Loai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;


public class khoanthu_Fragment extends Fragment {

    ListView listViewKhoanthu;
    KhoanThuChiAdapter adapter;
    ArrayList<KhoanThuChi> list;
    KhoanThuChiDAO khoanThuChiDAO;
    ArrayList<HashMap<String,Object>> listSpiner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khoanthu_, container, false);
        listViewKhoanthu= view.findViewById(R.id.listViewKhoanthu);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);

        khoanThuChiDAO = new KhoanThuChiDAO(getContext());

        getData();
        // showw dialog
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogthem();
            }
        });

        return view ;
    }
    private  void getData(){
        list = khoanThuChiDAO.getDSKhoanThuChi("thu");
        KhoanThuChiAdapter adapter = new KhoanThuChiAdapter(list,getContext(),khoanThuChiDAO,getdataSpiner());
        listViewKhoanthu.setAdapter(adapter);
    }
    private void showDialogthem(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layinflater = getLayoutInflater();
        View view = layinflater.inflate(R.layout.dialog_themkhoanthu,null);
        Spinner spnLoaithu = view.findViewById(R.id.spnloaithu);
        EditText edttien = view.findViewById(R.id.edttien);
        builder.setView(view);
        SimpleAdapter adapter = new SimpleAdapter(getContext()
                ,getdataSpiner(), android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},new int[]{android.R.id.text1});
        spnLoaithu.setAdapter(adapter);

        builder.setPositiveButton("thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tien = edttien.getText().toString();
                HashMap<String,Object> selected = (HashMap<String, Object>) spnLoaithu.getSelectedItem();
                int maloai = (int) selected.get("maloai");
                KhoanThuChi khoanThuChi = new KhoanThuChi(Integer.parseInt(tien),maloai);

                if (khoanThuChiDAO.themMoiKhoanThuChi(khoanThuChi)){
                    Toast.makeText(getContext(), "thêm thành công", Toast.LENGTH_SHORT).show();
                    getData();
                }else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    private ArrayList<HashMap<String,Object>> getdataSpiner (){
        ArrayList<Loai> listLoai = khoanThuChiDAO.getDSLoai("thu");
        listSpiner = new ArrayList<>();
        for (Loai loai : listLoai){
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("maloai",loai.getMaLoai());
            hashMap.put("tenloai",loai.getTenLoai());
            listSpiner.add(hashMap);
        }
        return listSpiner;
    }
}