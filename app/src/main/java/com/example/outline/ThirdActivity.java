package com.example.outline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private Button btn_start;
    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdActivity.this , TimerActivity.class);
                startActivity(intent); //진행 단계 화면 이동
            }
        });

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdActivity.this ,SecondActivity.class);
                startActivity(intent); // 그림 선택 화면 이동
            }
        });
    }
}
