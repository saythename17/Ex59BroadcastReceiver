package com.icandothisallday2020.ex59broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        //명시적 인텐트로 리시버 실행
        Intent intent=new Intent(this,MyReceiver.class);
        sendBroadcast(intent);
    }

    //Oreo version~ Broadcast or service component 사용 제한
    //(백그라운드에서 너무 리소스를 많이 사용하면 배터리가 빨리 닳기 때문)
    //-운영체제의 시스템 브로드캐스트는 정상동작
    //-개발자 임의 방송은 [동적 리시버 등록] 필요
    //∴ Manifest.xml 에 리시버를 등록X → Java 코드로 리시버 등록
    //앱이 켜져있을때만 묵시적(암시적) 방송을 들을수 있도록 제약됨
    public void click2(View view) {
        //묵시적 인텐트로 방송보내기 : 디바이스에 설치된 모든 앱에게 방송
        Intent intent=new Intent();
        intent.setAction("tvN");//방송의 액션값(실별값) 지정
        sendBroadcast(intent);
    }
    //액티비티가 화면에 보여질때 자동으로 발동하는 콜백 메소드
    //[onCreate()->onStart()->onResume()]---[lifecycle method]
    @Override
    protected void onResume() {
        super.onResume();
        //tvN 액션을 듣는 동적 리시버 등록
        receiver=new MyReceiver();
        IntentFilter filter=new IntentFilter("tvN");
        registerReceiver(receiver,filter);
    }
    //화면에 보이지 않을때 자동발동메소드
    @Override
    protected void onPause() {
        super.onPause();
        //등록했던 리시버 등록해제
        unregisterReceiver(receiver);
    }
}






/*Manifest.xml ----(Manifest 에 등록하는)
<application>안에 놓을 수 있는 4가지
        Android 4대 Component
        1.Activity :화면 담당
        -화면이 보이지 않으면 MainThread 가 동작 을 멈춤
        -Activity 안의 그 어떤 코드도 돌아가지 않음

        2.Service : Background 작업
        운영체제 안에는 이미 굉장히 많은 서비스들이 돌아가고 있음
        ex)음악 재생,화면에 안보여도 메세지를 받을 수 있음
        백그라운드에서 실행중인 앱

        3.Broadcast Reciever :핸드폰 상태 인지
        핸드폰의 상태를 모든 앱들에게 방송함
        수신기가 있는 앱들은 방송을 캐치할 수 있음

        4.Content Provider
        내 앱의 정보(DB)를 다른 앱에 줄 수 있음
        -갤러리, 연락처.
        다른 앱이 providing 하면 데이터를 가져올 수 있음

        Intent:컴포넌트(1~3)를 실행 시키기 위함*/




//