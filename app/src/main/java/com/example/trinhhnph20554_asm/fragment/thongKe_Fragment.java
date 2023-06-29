package com.example.trinhhnph20554_asm.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trinhhnph20554_asm.DAO.thongkeDAO;
import com.example.trinhhnph20554_asm.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;


public class thongKe_Fragment extends Fragment implements OnChartValueSelectedListener {
        PieChart mChart;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_thong_ke_, container, false);
        mChart = view.findViewById(R.id.piechart);
        mChart.setRotationEnabled(true);
        mChart.setDescription(new Description());
        mChart.setHoleRadius(35f);
        mChart.setTransparentCircleAlpha(0);
        mChart.setCenterText("Thống kê");
        mChart.setCenterTextSize(10);
        mChart.setDrawEntryLabels(true);
        addDataSet(mChart);
        mChart.setOnChartValueSelectedListener(this);
        return view;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    private void addDataSet(PieChart pieChart) {
        ArrayList<PieEntry> entrys = new ArrayList<>();
        thongkeDAO thongkeDAO = new thongkeDAO(getContext());

        float[] yData = thongkeDAO.getThongTinThuChi();
        String[] xData = { "Khoản thu", "Khoản chi" };
        for (int i = 0; i < yData.length;i++){
            entrys.add(new PieEntry(yData[i], xData[i]));
        }

        PieDataSet pieDataSet=new PieDataSet(entrys," ");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);
        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.GRAY);
        pieDataSet.setColors(colors);
        Legend legend=pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}