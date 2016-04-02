package com.example.beknur.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;






/**
 * Created by Dr.h3cker on 14/03/2015.
 */
public class Tab1 extends Fragment {
    private String[] monthsArray = {    "Главные новости\nЧитают\tОбсуждают\n" +
            "вчера, 15:16\n" +
            "Экипаж аварийно севшего самолета. Фото пресс-службы \n" +
            "Асет Исекешев беседует с экипажем аварийно севшего самолета. Фото МИР РК\n" +
            "Материал с видео\n" +
            "Командир экипажа рассказал, как посадил самолет Bek Air без передней стойки шасси\n" +
            "Любое использование материалов допускается только при наличии гиперссылки на Tengrinews.kz", "FEB", "MAR", "APR", "MAY", "JUNE", "JULY",
            "AUG", "SEPT", "OCT", "NOV", "DEC" };

    private ListView monthsListView;
    private ArrayAdapter arrayAdapter;


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_tab1, container, false);
        monthsListView = (ListView) view.findViewById(R.id.months_list);

        monthsListView.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1 , monthsArray));
        return view;
    }

}
