package com.emmajerry2016.ebookkepingabc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;

public class Option extends AppCompatActivity {
private Button  viewBooks,addBooks;
private TextView powered1;
private Toolbar mToolbar;
private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        viewBooks=findViewById(R.id.view_books);
        addBooks=findViewById(R.id.add_books);
        powered1=findViewById(R.id.powered1);
        powered1.setMovementMethod(LinkMovementMethod.getInstance());

        mToolbar=findViewById(R.id.option_toobar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("Options");

        viewBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             openViewBooksActivity();
            }
        });
        addBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddBookClass();
            }
        });
    }


    public void openAddBookClass(){
        Intent intent=new Intent(Option.this,addbook.class);
        startActivity(intent);
    }
    public void openViewBooksActivity(){
        Intent intent=new Intent(Option.this,ViewBook.class);
        startActivity(intent);
    }

}
