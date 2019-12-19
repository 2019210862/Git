package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {
    private Button button11;
    private Button button22;
     Button button3;
    private TextView textView_top_bar;
    private DemoFragment f1;
    private BlankFragment f2;
    private FrameLayout ly_content;


    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main3);

        button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main3Activity.this,Main4Activity.class);
                startActivity(intent);
            }
        });


        fragmentManager=getSupportFragmentManager();
        bindView();
        button11.performClick();
    }

    //UI组件初始化与事件绑定
    private void bindView() {
        button11= findViewById(R.id.button11);
        button22= findViewById(R.id.button22);
       // button3= findViewById(R.id.button3);
        textView_top_bar=findViewById(R.id.text_top_bar);
        ly_content=findViewById(R.id.ly_content);

        button11.setOnClickListener(this);
        button22.setOnClickListener(this);
        //button3.setOnClickListener(this);
        textView_top_bar.setOnClickListener(this);
    }
    //重置所有文本的选中状态
    private void setSelected(){
        button11.setSelected(false);
        button22.setSelected(false);
       // button3.setSelected(false);
        textView_top_bar.setSelected(false);
    }
    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(f1!=null)fragmentTransaction.hide(f1);
        if(f2!=null)fragmentTransaction.hide(f2);
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (view.getId()){
            case R.id.button11:
                setSelected();
                button11.setSelected(true);
                textView_top_bar.setText("...");
                if(f1==null){
                    f1=new DemoFragment();
                    fragmentTransaction.add(R.id.ly_content,f1);
                }else {
                    fragmentTransaction.show(f1);
                }
                break;
            case R.id.button2:
                setSelected();
                button22.setSelected(true);
                textView_top_bar.setText("。。。");
                if(f2==null){
                    f2=new BlankFragment("。。。");
                    fragmentTransaction.add(R.id.ly_content,f2);
                }else {
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
