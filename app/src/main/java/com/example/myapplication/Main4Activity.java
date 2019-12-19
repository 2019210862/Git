package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Arrays;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {

    private Button button33;
    private Button button44;
     Button button4;
    private TextView textView_top_bar;
    private FrameLayout ly_content;
    private MyFragment f1,f2;
    private FragmentManager fragmentManager;
    private int[] scores = new int[]{89, -23, 64, 91, 119, 52, 73};//创建scores数组
    private int[] arr = new int[3];//创建一个容量为3的数组用来接收返回的三个数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main4);

        button4=findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main4Activity.this,Main5Activity.class);
                startActivity(intent);
            }
        });


        fragmentManager = getSupportFragmentManager();
        bindView();
        button33.performClick();
    }

    //UI组件初始化与事件绑定
    private void bindView() {
        button33 = findViewById(R.id.button33);
        button44 = findViewById(R.id.button44);
      //  button4 = findViewById(R.id.button4);
        textView_top_bar = findViewById(R.id.text_top_bar);
        ly_content = findViewById(R.id.ly_content);

        button33.setOnClickListener(this);
        button44.setOnClickListener(this);
       // button4.setOnClickListener(this);
        textView_top_bar.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    private void setSelected() {
        button33.setSelected(false);
        button44.setSelected(false);
      //  button4.setSelected(false);
        textView_top_bar.setSelected(false);
    }
    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(f1!=null)fragmentTransaction.hide(f1);
        if(f2!=null)fragmentTransaction.hide(f2);
    }



        private String doubleNine () {
            String doublenine = "";
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j <= i; j++) {
                    doublenine = doublenine + i + "*" + j + "=" + i * j + "\t";
                }
                doublenine = doublenine + "\n";
            }
            return doublenine;
        }
        /**
         * ClassNo.2 level_3*/
        //编写一个方法对数组进行排序返回前三个
        private int[] mysort ( int[] inarr){
            int a = 0;
            Arrays.sort(inarr);
            for (int i = inarr.length - 1; i >= 0; i--) {
                if (inarr[i] > 0 && inarr[i] < 100) {
                    arr[a] = inarr[i];
                    a++;
                    if (a == 3)
                        return arr;
                }
            }
            return arr;
        }


        @Override
        public void onClick (View view){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            hideAllFragment(fragmentTransaction);
            switch (view.getId()) {
                case R.id.button33:
                    setSelected();
                    button33.setSelected(true);
                    textView_top_bar.setText("九九乘法表");
                    if (f1 == null) {
                        f1 = new MyFragment(doubleNine());//调用方法
                        fragmentTransaction.add(R.id.ly_content, f1);
                    } else {
                        fragmentTransaction.show(f1);
                    }
                    break;
                case R.id.button44:
                    setSelected();
                    button44.setSelected(true);
                    textView_top_bar.setText("排序输出");
                    if (f2 == null) {
                        f2 = new MyFragment("考试成绩的前三名为\n" + mysort(scores)[0] + "\n" + mysort(scores)[1] + "\n" + mysort(scores)[2]);//调用mysort
                        fragmentTransaction.add(R.id.ly_content, f2);
                    } else {
                        fragmentTransaction.show(f2);
                    }
                    break;

            }
            fragmentTransaction.commit();

        }
    public void setLy_content(FrameLayout ly_content) {
        this.ly_content = ly_content;
    }

    public FrameLayout getLy_content() {

            return ly_content;
    }
    }
