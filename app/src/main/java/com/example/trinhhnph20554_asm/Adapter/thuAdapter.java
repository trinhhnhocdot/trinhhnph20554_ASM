package com.example.trinhhnph20554_asm.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.trinhhnph20554_asm.fragment.khoanthu_Fragment;
import com.example.trinhhnph20554_asm.fragment.loaithu_Fragment;

public class thuAdapter extends FragmentStateAdapter {


    public thuAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position==0){
            return new loaithu_Fragment();
        }else {
            return new khoanthu_Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
