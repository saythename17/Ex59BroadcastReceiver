package com.icandothisallday2020.ex59broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //명시적 인텐트로 리시버 실행: 갑은 앱안에 있는 리시버만 실행가능
        // [Manifest 에 등록 필수]
        //이 리시버가 방송을 수신하면 실행되는 메소드

        //만약 묵시적 방송이면 ->인텐트의 액션값 확인
        String action=intent.getAction();
        if(action!=null/*null 이 아니면 묵시적*/){
            switch (action){
                case "tvN":
                    break;
                case Intent.ACTION_BATTERY_LOW:
                    break;
            }
        }
        Toast.makeText(context, "Received!", Toast.LENGTH_SHORT).show();
    }
}
