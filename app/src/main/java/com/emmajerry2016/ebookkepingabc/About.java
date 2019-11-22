package com.emmajerry2016.ebookkepingabc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;



public class About extends AppCompatActivity {
private Toolbar mToolbar;
private Button forward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mToolbar=findViewById(R.id.about_layout);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("About");

        forward=findViewById(R.id.forward);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOptionActivity();
            }
        });
    }
    public void openOptionActivity(){
        Intent intent=new Intent(this,Option.class);
        startActivity(intent);
    }
}
