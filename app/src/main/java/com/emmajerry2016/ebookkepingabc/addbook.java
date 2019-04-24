package com.emmajerry2016.ebookkepingabc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class addbook extends AppCompatActivity {

    private EditText bookSN, bookTitle, bookAuthor, bookComment, bookContent, email, password;
    private Button addButton, registerButton;
    private Toolbar mToolbar;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mAuth;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);

        mAuth = FirebaseAuth.getInstance();
        currentUserId=mAuth.getCurrentUser().getUid();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        System.out.println("************");
        System.out.println("Views");
        System.out.println("************");
        bookSN = findViewById(R.id.add_books);
        bookTitle = findViewById(R.id.title_books);
        bookAuthor = findViewById(R.id.author_books);
        bookComment = findViewById(R.id.comment_books);
        bookContent = findViewById(R.id.content_books);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);


        mToolbar = findViewById(R.id.add_book_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Add Book");

        System.out.println("************");
        System.out.println("Buttons");
        System.out.println("************");

        registerButton = findViewById(R.id.register_button);
        addButton = findViewById(R.id.add_button);

        //******collecting email and password by clicking registerButton******//
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        //*******collecting book info by clicking addButton********//
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectBookInfo();
            }
        });




    }
    // System.out.println("************");
    //System.out.println("At starting point checks if user is signed ");
    //System.out.println("************");


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        setConditions(currentUser);
    }

    public void registerUser() {

        String email1 = email.getText().toString();
        String password1 = password.getText().toString();

        if (TextUtils.isEmpty(email1)) {
            Toast.makeText(addbook.this, "input email", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password1)) {
            Toast.makeText(addbook.this, "input password", Toast.LENGTH_SHORT).show();
        } else {

            mAuth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {


                        // adding users id to my firebase acc
                        String currentUser = mAuth.getCurrentUser().getUid();
                        mDatabaseReference.child("Users").child(currentUser).setValue("");


                        Toast.makeText(addbook.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        setConditions(user);
                    } else {
                        String message = task.getException().toString();
                        Toast.makeText(addbook.this, "error: " + message,
                                Toast.LENGTH_SHORT).show();
                        setConditions(null);
                    }
                }
            });

            mAuth.signInWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(addbook.this, "Signed in successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        String message = task.getException().toString();
                        Toast.makeText(addbook.this, "error: " + message, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    public void setConditions(FirebaseUser user) {
        if (user != null) {
            findViewById(R.id.register_button).setVisibility(View.INVISIBLE);

        } else {

            findViewById(R.id.register_button).setVisibility(View.VISIBLE);
        }

    }
    public void collectBookInfo() {
        //*********Now 2 proceed 2 allowing registered user to add books**********//

        String title = bookTitle.getText().toString();
        String author = bookAuthor.getText().toString();
        String comment = bookComment.getText().toString();
        String content = bookContent.getText().toString();
        String serialNumber = bookSN.getText().toString();

        //**********Ensuring that all fields are well entered***********//


        if (TextUtils.isEmpty(serialNumber)) {
            Toast.makeText(addbook.this, "input content", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(addbook.this, "input title", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(author)) {
            Toast.makeText(addbook.this, "input author", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(comment)) {
            Toast.makeText(addbook.this, "input comment", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(addbook.this, "input content", Toast.LENGTH_SHORT).show();
        } else {


            //*********To save data to firebase using Hashmap encryption********//
            HashMap<String, Object> bookMap = new HashMap<>();
            bookMap.put("uid", currentUserId);
            bookMap.put("bookSN", serialNumber);
            bookMap.put("bookAuthor", author);
            bookMap.put("bookTitle", title);
            bookMap.put("bookComment", comment);
            bookMap.put("bookContent", content);

            mDatabaseReference.child("Users").child(currentUserId).child("Books").updateChildren(bookMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(addbook.this, ViewBook.class);
                                startActivity(intent);
                                Toast.makeText(addbook.this, "Book added successfully updated", Toast.LENGTH_SHORT).show();

                                bookTitle.setText("");
                                bookAuthor.setText("");
                                bookContent.setText("");
                                bookComment.setText("");
                                bookSN.setText("");

                            } else {
                                String message = task.getException().toString();
                                Toast.makeText(addbook.this, "Error:" + message, Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
        }
    }}


