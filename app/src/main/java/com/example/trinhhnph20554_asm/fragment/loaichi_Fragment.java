package com.example.trinhhnph20554_asm.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.trinhhnph20554_asm.Adapter.loaichiAdapter;
import com.example.trinhhnph20554_asm.Adapter.loaithuAdapter;
import com.example.trinhhnph20554_asm.DAO.KhoanThuChiDAO;
import com.example.trinhhnph20554_asm.R;
import com.example.trinhhnph20554_asm.modal.Loai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class loaichi_Fragment extends Fragment {
    ListView listViewloaichi;
    FloatingActionButton floatAdd;
    loaichiAdapter adapter;
    ArrayList<Loai> list;
    KhoanThuChiDAO khoanThuChiDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View view =  inflater.inflate(R.layout.fragment_loaichi_, container, false);
        listViewloaichi = view.findViewById(R.id.listViewLoaichi);
        floatAdd = view.findViewById(R.id.floatAdd);

        khoanThuChiDAO = new KhoanThuChiDAO(getContext());
        loadData();

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaLogThem();
            }
        });
        return view ;
    }
    private void loadData(){
        list = khoanThuChiDAO.getDSLoai("chi");
        adapter = new loaichiAdapter(list,getContext(),khoanThuChiDAO);
        listViewloaichi.setAdapter(adapter);
    }
    public void showDiaLogThem(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themloaichi,null);
        EditText edtInput = view.findViewById(R.id.edtInput);
        builder.setView(view);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                String tenloai = edtInput.getText().toString();
                if (tenloai.equals("")){
                    Toast.makeText(getContext(), "vui lòng điển đầy đủ ", Toast.LENGTH_SHORT).show();
                }else {
                    Loai loaithem = new Loai(tenloai,"chi");
                    if (khoanThuChiDAO.themMoiLoaiThuChi(loaithem)){
                        Toast.makeText(getContext(), "Thêm thành công ", Toast.LENGTH_SHORT).show();
                        loadData();
                    }else {
                        Toast.makeText(getContext(), "Thêm thất bại ", Toast.LENGTH_SHORT).show();
                    }
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
}