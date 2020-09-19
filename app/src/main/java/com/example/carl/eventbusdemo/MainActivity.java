package com.example.carl.eventbusdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.carl.eventbusdemo.event.MessageEvent;
import com.example.carl.eventbusdemo.event.StickyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private TextView tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvContent = findViewById(R.id.tvContent);
        initData();
    }

    public void onClickSend(View view) {
        Intent i = new Intent(this,EventBusSendActivity.class);
        startActivity(i);
    }
    private void initData(){
        //1.注册广播
        EventBus.getDefault().register(this);
    }
    //5.接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(MessageEvent event){
        tvContent.setText(event.name);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //2.解注册广播
        EventBus.getDefault().unregister(this);
    }

    public void onClickSticky(View view) {
        EventBus.getDefault().postSticky(new StickyEvent("这是黏性事件"));
        Intent i = new Intent(this,EventBusSendActivity.class);
        startActivity(i);
    }
}
