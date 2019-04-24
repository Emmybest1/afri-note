package com.emmajerry2016.ebookkepingabc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewBook extends AppCompatActivity {
private TextView viewSn,viewTitle,viewAuthor,viewComment,viewContent;
private Button  enterButton,deleteButton;
private Toolbar toolBar;
private DatabaseReference mDatabaseReference,fetcherRef;
private String currentUserId;
private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);


        System.out.println("************");
        System.out.println("TextViews");
        System.out.println("************");
        viewSn=findViewById(R.id.view_books_sn);
        viewTitle=findViewById(R.id.view_title_books);
        viewAuthor=findViewById(R.id.view_author_books);
        viewComment=findViewById(R.id.view_comment_books);
        viewContent=findViewById(R.id.view_content_books);


        toolBar=findViewById(R.id.add_book_toolbar);
        setSupportActionBar(toolBar);
        //getSupportActionBar().setTitle("View Books");

        /////********Firebase Refs and connectors********/////
        mAuth=FirebaseAuth.getInstance();
        currentUserId=mAuth.getCurrentUser().getUid();
        mDatabaseReference= FirebaseDatabase.getInstance().getReference();

        System.out.println("************");
        System.out.println("Buttons");
        System.out.println("************");
        deleteButton=findViewById(R.id.delete_button);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseReference.child("Users").child(currentUserId).child("Books").removeValue();
            }
        });
        enterButton=findViewById(R.id.view_add_button);
        enterButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openAddBookActivity();

           }
       });
    }
    public void openAddBookActivity(){
        Intent intent=new Intent(ViewBook.this,addbook.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        retrieveBooksInfo();
    }


    //To retrieve book Info updated info
    private void retrieveBooksInfo() {
      mDatabaseReference.child("Users").child(currentUserId).child("Books")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if((dataSnapshot.exists())&& (dataSnapshot.hasChild("bookAuthor")&& (dataSnapshot.hasChild("bookComment")&& (dataSnapshot.hasChild("bookContent")&& (dataSnapshot.hasChild("bookSN")&& (dataSnapshot.hasChild("bookTitle"))))))){
                            String retrieveBookAuthor=dataSnapshot.child("bookAuthor").getValue().toString();
                            String retrieveBookComment=dataSnapshot.child("bookComment").getValue().toString();
                            String retrieveBookContent=dataSnapshot.child("bookContent").getValue().toString();
                            String retrieveBookSN=dataSnapshot.child("bookSN").getValue().toString();
                            String retrieveBookTitle=dataSnapshot.child("bookTitle").getValue().toString();

                            //*******to retrieve and display bookInfo*******//
                            viewSn.setText(retrieveBookSN);
                            viewTitle.setText(retrieveBookTitle);
                            viewAuthor.setText(retrieveBookAuthor);
                            viewComment.setText(retrieveBookComment);
                            viewContent.setText(retrieveBookContent);

                        }

                        else if((dataSnapshot.exists())&& (dataSnapshot.hasChild("BookTitle")&&(dataSnapshot.hasChild("bookAuthor")&&(dataSnapshot.hasChild("BookSN"))))){

                            String retrieveBookSN=dataSnapshot.child("bookSN").getValue().toString();
                            String retrieveBookTitle=dataSnapshot.child("bookTitle").getValue().toString();
                            String retrieveBookAuthor=dataSnapshot.child("bookAuthor").getValue().toString();


                            //*******to retrieve and display serialNumber,title and author*******//

                            viewSn.setText(retrieveBookSN);
                            viewTitle.setText(retrieveBookTitle);
                            viewAuthor.setText(retrieveBookAuthor);

                        }
                        else{

                            Toast.makeText(ViewBook.this, "No book details", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }


    }

