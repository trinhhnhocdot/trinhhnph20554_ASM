package com.example.trinhhnph20554_asm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.example.trinhhnph20554_asm.fragment.chi_Fragment;
import com.example.trinhhnph20554_asm.fragment.gioithieu_Fragment;
import com.example.trinhhnph20554_asm.fragment.thongKe_Fragment;
import com.example.trinhhnph20554_asm.fragment.thu_Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.menu_24);
        actionBar.setDisplayHomeAsUpEnabled(true);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.mthu:
                        fragment = new thu_Fragment();
                        break;
                    case R.id.mchi:
                        fragment = new chi_Fragment();
                        break;
                    case R.id.mthongKe:
                        fragment = new thongKe_Fragment();
                        break;
                    case R.id.mgioithieu:
                        fragment = new gioithieu_Fragment();
                        break;
                    case R.id.mthoat:
                        onBackPressed();

                    default:
                        fragment = new thu_Fragment();
                        break;
                }

                fragmentManager.beginTransaction().replace(R.id.linearLayout,fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START );// click vào item xong đóng menu
                setTitle(item.getTitle());
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
                    drawerLayout.openDrawer(GravityCompat.START );
        }
        return super.onOptionsItemSelected(item);
    }
}