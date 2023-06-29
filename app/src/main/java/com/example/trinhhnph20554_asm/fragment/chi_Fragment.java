package com.example.trinhhnph20554_asm.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trinhhnph20554_asm.Adapter.chiAdapter;
import com.example.trinhhnph20554_asm.Adapter.thuAdapter;
import com.example.trinhhnph20554_asm.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class chi_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi_, container, false);


        TabLayout tabchi = view.findViewById(R.id.tabchi);
        ViewPager2 viewchi = view.findViewById(R.id.viewchi);

        chiAdapter adapter = new chiAdapter(getActivity());
        viewchi.setAdapter(adapter);
        new TabLayoutMediator(tabchi, viewchi, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Loại chi");
                } else {
                    tab.setText("Khoản chi ");
                }
            }
        }).attach();
        return view;
    }
}