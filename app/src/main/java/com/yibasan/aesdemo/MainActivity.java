package com.yibasan.aesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    //String key = AesUtil.generateKey(128);
                    String key = "BA7372A153EBF8FF921F1ED4DB76099B";
                    String test = "test";
                    System.out.println("key=" + key);
                    System.out.println("encode=" + Base64.encodeToString(AesUtil.encode(key, test), Base64.DEFAULT));
                    System.out.println("decode=" + new String(AesUtil.decode(key, Base64.decode("2g8pNUVF+rpsnBrGKuaNug==", Base64.DEFAULT))));

                    System.out.println("encode2=" + AesUtil.encode2(key, test));
                    System.out.println("decode2=" + AesUtil.decode2(key, "2g8pNUVF+rpsnBrGKuaNug=="));


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
