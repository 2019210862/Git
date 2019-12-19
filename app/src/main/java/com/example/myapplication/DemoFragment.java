package com.example.myapplication;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoFragment extends Fragment implements View.OnClickListener {

    private Button button_1;
    private Button button_2;

    public DemoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_demo,container,false);
        button_1=view.findViewById(R.id.button_1);
        button_2=view.findViewById(R.id.button_2);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Date date=new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年mm月dd日 hh:mm:ss");
        switch (view.getId()){
            case R.id.button_1:
                Toast.makeText(getContext(),"这是一个简单的toast",Toast.LENGTH_LONG).show();
                break;
            case R.id.button_2:
                Toast.makeText(getContext(),"当前系统时间是：\n"+simpleDateFormat.format(date),Toast.LENGTH_LONG).show();
                break;
        }
    }
}
