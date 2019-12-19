package com.example.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MyFragment extends Fragment {

    private String text;
    public MyFragment(String text) {
        this.text=text;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my,container,false);
        TextView textView=(TextView)view.findViewById(R.id.fragment_text);
        textView.setText(text);
        return view;
    }
}