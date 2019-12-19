package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.fragment.app.ListFragment;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListViewFragment2 extends ListFragment {
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view2, container, false);
        //找到listview 组件
        listView = view.findViewById(android.R.id.list);
        //创建一个含有hanshMap 的列表集合
        List<HashMap<String, String>> data = new ArrayList<>();
        //创建 18  个 map 数据对象 ，每个map 对象 有两个键值数据
        for (int i = 1; i <= 18; i++) {
            //创建HashMap 对象,添加键值数据
            HashMap<String, String> map = new HashMap<>();
            //向map 对象添加两组键值对数据
            map.put("key_one", "dataOne_" + i);
            map.put("key_two", "dataTwo_" + i);
            //将 map 对象添加到data  集合
            data.add(map);
        }

        SimpleAdapter mSimpleAdapter = new SimpleAdapter(
                getContext(),
                data,
                R.layout.list_item_1,
                new String[]{"key_one", "key_two"},
                new int[]{R.id.text_one, R.id.text_two});
        //将数据通过适配器显示到lv
        listView.setAdapter(mSimpleAdapter);
        listView.setFocusable(false);
        listView.setFocusableInTouchMode(false);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onListItemClick(listView, view, position, id);
            }
        });
        return view;
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(getActivity(), "你点击了第" + position + "个", Toast.LENGTH_LONG).show();
    }
}