package com.chien.mysharedpreference002;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;

import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    SharedPreferences shared;
    SharedPreferences.Editor editor;

    TextView mytext, show;
    SeekBar seekBar;
    int size;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("設定顏色");

        //偏好設定檔的檔案名，與存取權 都是 PRIVATE 比較好
        shared = getSharedPreferences("myColor",MODE_PRIVATE);
        int cc = shared.getInt("color", getResources().getColor(R.color.myyellow));

        toolbar.setBackgroundColor(cc); //工具頭顏色
        getWindow().setStatusBarColor(cc); //狀態列顏色

        int cc2 = shared.getInt("color", getResources().getColor(R.color.mybg));
        LinearLayout layout = findViewById(R.id.layout);
        layout.setBackgroundColor(cc2);
        //----------------------------------------------------------------------------

        mytext = findViewById(R.id.mytext);
        show = findViewById(R.id.show);
        seekBar = findViewById(R.id.seekBar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekBar.setMin(14);
            seekBar.setMax(50);
        }
        seekBar.setProgress(24);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //當捲動時立即觸發
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                show.setText(progress);
                size = progress;
            }
            //按住seekBar觸發
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //show.setText("按住seekBar");
            }
            //按住seekBar放開時，觸發
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //show.setText("按住seekBar放開時");
            }
        });

        int ss = shared.getInt("size", 14);
        mytext.setTextSize(TypedValue.COMPLEX_UNIT_SP, ss);
        show.setText(""+ss);
    }

    //儲存顏色
    void saveColor(int c){
        shared = getSharedPreferences("myColor", MODE_PRIVATE); //呼叫
        editor = shared.edit(); //給 editor 去 送 edit() 方法
        editor.putInt("color", c); //放進去
        editor.commit();  //跟 submit 是一樣的意思
    }

    //監聽
    public void onclick(View view) {
        switch(view.getId()){
            case R.id.button:
                int a = getResources().getColor(R.color.myred);
                toolbar.setBackgroundColor(a);
                getWindow().setStatusBarColor(a);
                saveColor(a);
                break;
            case R.id.button2:
                int b = getResources().getColor(R.color.myyellow);
                toolbar.setBackgroundColor(b);
                getWindow().setStatusBarColor(b);
                saveColor(b);
                break;
            case R.id.button3:
                int c = getResources().getColor(R.color.myblue);
                toolbar.setBackgroundColor(c);
                getWindow().setStatusBarColor(c);
                saveColor(c);
                break;
        }
    }

    //儲存Size
    void savesize(int c) {
        shared = getSharedPreferences("myColor", MODE_PRIVATE);
        editor = shared.edit();
        editor.putInt("size", c);
        editor.commit();
    }

    //監聽2
    public void setSize(View view) {
        switch(view.getId()){
            case R.id.txtSize_s://16
                size = 16;
                mytext.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
                savesize(size);
                show.setText(""+size);
                break;
            case R.id.txtSize_m://24
                size = 24;
                mytext.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
                savesize(size);
                show.setText(""+size);
                break;
            case R.id.txtSize_l://32
                size = 32;
                mytext.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
                savesize(size);
                show.setText(""+size);
                break;
        }
    }
}