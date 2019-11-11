package com.yibasan.aesdemo;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                try {
                    String test = "test";
                    String string = AESCipher.aesEncryptString(test, "BA7372A153EBF8FF");//BA7372A153EBF8FF//16BytesLengthKey
                    System.out.println(string);
                    System.out.println(AESCipher.aesDecryptString(string, "BA7372A153EBF8FF"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
