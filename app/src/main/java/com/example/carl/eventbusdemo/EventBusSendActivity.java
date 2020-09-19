package com.example.carl.eventbusdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.carl.eventbusdemo.event.MessageEvent;
import com.example.carl.eventbusdemo.event.StickyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EventBusSendActivity extends AppCompatActivity {
    private TextView tv_send_content;
    private boolean isFirst = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_send);
        tv_send_content = findViewById(R.id.tv_send_content);
    }

    //5.接收消息
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void MessageEventBus(StickyEvent event){
        tv_send_content.setText(event.msg);
    }

    public void onSendClicked(View view) {
        //4.发送消息
        EventBus.getDefault().post(new MessageEvent("主线程发送过来的数据。"));
        finish();
    }

    public void onStickyClicked(View view) {
        if (isFirst){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
