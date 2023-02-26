package com.example.mysplash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DestinationActivity1 extends AppCompatActivity {
    TextView textView;
    Button button7;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_destination1));
        button7=(Button) findViewById(R.id.button7);
        textView = findViewById(R.id.textData);

        GetDateTime dateTime = new GetDateTime(this);
        dateTime.getDateTime(new GetDateTime.VolleyCallBack() {
            @Override
            public void onGetDateTime(String date, String time) {

                textView.setText(date +"\n" + time);

            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DestinationActivity1.this, menu.class);
                startActivity(intent);
            }
        });
    }
}
