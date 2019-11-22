package com.emmajerry2016.ebookkepingabc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class Contact extends AppCompatActivity {
    private TextView contactUs;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

       contactUs=findViewById(R.id.contactUs);
       contactUs.setMovementMethod(LinkMovementMethod.getInstance());

        toolbar=findViewById(R.id.contact_layout);
        setSupportActionBar(toolbar);

        String founder="Contact Us";
        getSupportActionBar().setTitle(founder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
    }

}
