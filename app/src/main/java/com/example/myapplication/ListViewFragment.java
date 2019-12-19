package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.fragment.app.ListFragment;
import com.example.myapplication.R;

public class ListViewFragment extends ListFragment {
    private ListView mListView; //字符串组合
    private String [] data = {"item_One","item_Two","item_Three","item_Four", "item_Five","item_Six","item_Seven",
            "item_Eight","item_Nine","item_Ten","1","1","1","1","1","1","1","1"};
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list_view,container,false); //找到listview 组件
        mListView = view.findViewById(android.R.id.list);
        /**
         * 通过ArrayAdapter 将数据和布局联系起来
         * * 参数1 当前上下文环境
         * 参数2 当前引用的布局 一般系统默认
         * * 参数3 当前绑定的数据
         * */
        ArrayAdapter<String> array = new ArrayAdapter<String>( getActivity(), android.R.layout.simple_list_item_1, data); //将数据和布局 显示到列表
         mListView.setAdapter(array);
         mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 onListItemClick(mListView,view,position,id); } }); return view; }
             public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l,v,position,id);
        Toast.makeText(getActivity(),"你点击了第"+position+"个",Toast.LENGTH_LONG).show(); }}