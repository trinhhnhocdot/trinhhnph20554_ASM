package com.example.trinhhnph20554_asm.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trinhhnph20554_asm.Adapter.thuAdapter;
import com.example.trinhhnph20554_asm.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class thu_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_thu_, container, false);

        TabLayout tabthu = view.findViewById(R.id.tabthu);
        ViewPager2 viewthu = view.findViewById(R.id.viewthu);

        thuAdapter adapter = new thuAdapter(getActivity());
        viewthu.setAdapter(adapter);
        new TabLayoutMediator(tabthu, viewthu, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0){

                    tab.setText("Loại thu");
                }else {
                    tab.setText("Khoản thu ");
                }
            }
        }).attach();
        return view;
    }
}