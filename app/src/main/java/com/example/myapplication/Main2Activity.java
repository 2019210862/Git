package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import org.jetbrains.annotations.NotNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    public ListView lv;
    Button button2;
    public ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    private Context context;

    @Override
    protected void onCreate(@NotNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(intent);
            }
        });
        init();
    }

    private Mybaseadapter list_item;

    private void init() {
        list.clear();
        lv = (ListView) findViewById(R.id.lv_contact);
        list_item = new Mybaseadapter();
        lv.setAdapter(list_item);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    //服务端访问地址
                    Request request = new Request
                            .Builder()
                            .url("http://120.36.153.174:9080/CargolistApi/select").build();
                    Response response = client.newCall(request).execute();
                    //得到服务器返回的数据后，调用parseJSONWithJSONObject进行解析
                    String responseData = response.body().string();
                    parseJSONWithJSONObject(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithJSONObject(String jsonData) {
        if (jsonData != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonData);

                //获取数据中的code值，如果是0则正确
                String resultCode = jsonObject.getString("code");
                if (resultCode.equals("0")) {
                    //获取到json数据中里的data内容
                    JSONArray resultJsonArray = jsonObject.getJSONArray("data");
                    Log.d("MainActivity", "data+++" + resultJsonArray);
                    for (int i = 0; i < resultJsonArray.length(); i++) {
                        //循环遍历，获取json数据中data数组里的内容
                        JSONObject Object = resultJsonArray.getJSONObject(i);
                        Map<String, Object> map = new HashMap<String, Object>();
                        try {
                            String cargono = Object.getString("cargono");
                            String variety = Object.getString("variety");
                            String markno = Object.getString("markno");
                            String spec = Object.getString("spec");
                            String kgs = Object.getString("kgs");
                            String net = Object.getString("net");

                            map.put("cargono", cargono);
                            map.put("variety", variety);
                            map.put("markno", markno);
                            map.put("spec", spec);
                            map.put("kgs", kgs);
                            map.put("net", net);

                            //保存到ArrayList集合中
                            list.add(map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    handler.sendEmptyMessageDelayed(1, 100);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    list_item.notifyDataSetChanged();
                    break;
            }
        }
    };


    //listview适配器
    public class Mybaseadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = new ViewHolder();

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.fragment_main2, null);
                viewHolder.Cargono = (TextView) convertView.findViewById(R.id.tvCargono);
                viewHolder.Variety = (TextView) convertView.findViewById(R.id.tvVariety);
                viewHolder.Markno = (TextView) convertView.findViewById(R.id.tvMarkno);
                viewHolder.Spec = (TextView) convertView.findViewById(R.id.tvSpec);
                viewHolder.Kgs = (TextView) convertView.findViewById(R.id.tvKgs);
                viewHolder.Net = (TextView) convertView.findViewById(R.id.tvNet);

                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.Cargono.setText("钢卷号：" + list.get(position).get("cargono").toString());
            viewHolder.Variety.setText("品种：" + list.get(position).get("variety").toString());
            viewHolder.Markno.setText("牌号：" + list.get(position).get("markno").toString());
            viewHolder.Spec.setText("规格型号：" + list.get(position).get("spec").toString());
            viewHolder.Kgs.setText("毛重：" + list.get(position).get("kgs").toString());
            viewHolder.Net.setText("净重：" + list.get(position).get("net").toString());

            return convertView;
        }
    }

    final static class ViewHolder {
        TextView Cargono;
        TextView Variety;
        TextView Markno;
        TextView Spec;
        TextView Kgs;
        TextView Net;
    }

    @Override
    public void onClick(View view) {

    }

}