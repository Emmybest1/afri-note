package com.emmajerry2016.ebookkepingabc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Contact extends AppCompatActivity {

    private TextView homepage,igniteGroup;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        homepage=findViewById(R.id.homepage);
        homepage.setMovementMethod(LinkMovementMethod.getInstance());

        igniteGroup=findViewById(R.id.textView);
        igniteGroup.setMovementMethod(LinkMovementMethod.getInstance());

        mButton=findViewById(R.id.returnButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToHomepage();
            }
        });

    }
    public void returnToHomepage(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
