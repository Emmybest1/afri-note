package com.emmajerry2016.ebookkepingabc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
private Button button1;
private TextView powered_textView;
private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);

        powered_textView = findViewById(R.id.powered_textView);
        powered_textView.setMovementMethod(LinkMovementMethod.getInstance());

        toolbar = findViewById(R.id.app_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ABC eBookkeeping");

        initializeField();

    }

    private void initializeField() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOptionActivity();
            }
        });
    }

    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            super.onCreateOptionsMenu(menu);
            getMenuInflater().inflate(R.menu.menus, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item){
            super.onOptionsItemSelected(item);
            if(item.getItemId()==R.id.about){
                Intent intent=new Intent(MainActivity.this,About.class);
                startActivity(intent);

            }
            if(item.getItemId()==R.id.contact){
                Intent intent=new Intent(MainActivity.this,Contact.class);
                startActivity(intent);

            }
            return true;
        }
        public void openOptionActivity(){
        Intent intent=new Intent(this,Option.class);
        startActivity(intent);
        }
        }

