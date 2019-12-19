package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.ListFragment;

import com.example.myapplication.R;
import com.example.myapplication.StudentData;
import com.example.myapplication.MyListAdapter;

import java.util.ArrayList;
import java.util.List;
//在fragment中显示ListView要继承ListFragment
public class ListViewFragment3 extends ListFragment {

    private ListView listView;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list_view3,container,false);
        //找到listview 组件
        listView=view.findViewById(android.R.id.list);

        List<StudentData> mStudentDataList = new ArrayList<>();  //创建studentData 对象集合
        for( int i = 1; i <= 18 ; i++) {
            StudentData mStudentData = new StudentData();      //循环创建studentData 对象
            mStudentData.setName("这是第"+i+"个");
            mStudentData.setAge(i++);
            mStudentData.setPhoto(R.drawable.ic_launcher_background);
            mStudentDataList.add(mStudentData);
        }
        //创建Adapter 实例化对象， 调用构造函数传参，将数据和adapter  绑定
        MyListAdapter mMyListAdapter = new MyListAdapter(mStudentDataList,getContext());
        listView.setAdapter(mMyListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onListItemClick(listView,view,position,id);
            }
        });//将定义的adapter 和 listView 绑定

        return view;
    }
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l,v,position,id);
        Toast.makeText(getActivity(),"你点击了第"+position+"个",Toast.LENGTH_LONG).show();
    }
}
