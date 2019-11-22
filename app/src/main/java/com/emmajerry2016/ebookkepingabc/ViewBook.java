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
private TextView item1View,item2View,item3View,item4View,item5View;
private Button  addButton,deleteAllButton,deleteItem1,deleteItem2,editButton,
        deleteItem3,deleteItem4,deleteItem5;
private Toolbar toolBar;
private DatabaseReference mDatabaseReference,fetcherRef;
private String currentUserId;
private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        toolBar=findViewById(R.id.view_book_toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("View Book");

        /********Firebase Refs and connectors********/
        mAuth=FirebaseAuth.getInstance();
        currentUserId=mAuth.getCurrentUser().getUid();
        mDatabaseReference= FirebaseDatabase.getInstance().getReference();

        item1View=findViewById(R.id.item1View);
        item2View=findViewById(R.id.item2View);
        item3View=findViewById(R.id.item3View);
        item4View=findViewById(R.id.item4View);
        item5View=findViewById(R.id.item5View);

        deleteItem1=findViewById(R.id.item1deletebtn);
        deleteItem2=findViewById(R.id.item2deletebtn);
        deleteItem3=findViewById(R.id.item3deletebtn);
        deleteItem4=findViewById(R.id.item4deletebtn);
        deleteItem5=findViewById(R.id.item5deletebtn);

        deleteAllButton=findViewById(R.id.delete_button);
        editButton=findViewById(R.id.editbtn);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewBook.this,EditBook.class);
                startActivity(intent);
            }
        });
        addButton=findViewById(R.id.view_add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddBookActivity();
            }
        });


        deleteItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseReference.child("Users").child(currentUserId).child("Notes").child("Item1").removeValue();

                item1View.setText("");

            }
        });

        deleteItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseReference.child("Users").child(currentUserId).child("Notes").child("Item2").removeValue();

                item2View.setText("");

            }
        });

        deleteItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseReference.child("Users").child(currentUserId).child("Notes").child("Item3").removeValue();

                item3View.setText("");

            }
        });

        deleteItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseReference.child("Users").child(currentUserId).child("Notes").child("Item4").removeValue();

                item4View.setText("");

            }
        });

        deleteItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseReference.child("Users").child(currentUserId).child("Notes").child("Item5").removeValue();

                item5View.setText("");

            }
        });


        deleteAllButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDatabaseReference.child("Users").child(currentUserId).child("Notes").removeValue();

                    item1View.setText("");
                    item2View.setText("");
                    item3View.setText("");
                    item4View.setText("");
                    item5View.setText("");
                }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        retrieveBooksInfo();
    }

    public void openAddBookActivity(){
        Intent intent=new Intent(ViewBook.this,addbook.class);
        startActivity(intent);
    }

    //To retrieve book Info updated info
    private void retrieveBooksInfo() {
        mDatabaseReference.child("Users").child(currentUserId).child("Notes")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if((dataSnapshot.exists())&& (dataSnapshot.hasChild("Item1"))){
                            String retrieveItem1=dataSnapshot.child("Item1").getValue().toString();

                            item1View.setText(retrieveItem1);
                        }
                        if((dataSnapshot.exists())&& (dataSnapshot.hasChild("Item2"))){
                            String retrieveItem2=dataSnapshot.child("Item2").getValue().toString();

                            item2View.setText(retrieveItem2);
                        }
                        if((dataSnapshot.exists())&& (dataSnapshot.hasChild("Item3"))){
                            String retrieveItem3=dataSnapshot.child("Item3").getValue().toString();

                            item3View.setText(retrieveItem3);
                        }
                        if((dataSnapshot.exists())&& (dataSnapshot.hasChild("Item4"))){
                            String retrieveItem4=dataSnapshot.child("Item4").getValue().toString();

                            item4View.setText(retrieveItem4);
                        }
                        if((dataSnapshot.exists())&& (dataSnapshot.hasChild("Item5"))){
                            String retrieveItem5=dataSnapshot.child("Item5").getValue().toString();

                            item5View.setText(retrieveItem5);
                        }

                        else if((dataSnapshot.exists())&& (dataSnapshot.hasChild("Item1"))&&(dataSnapshot.hasChild("Item2"))&&(dataSnapshot.hasChild("Item3"))&&(dataSnapshot.hasChild("Item4"))&&(dataSnapshot.hasChild("Item5"))){

                            String retrieveItem1=dataSnapshot.child("Item1").getValue().toString();
                            String retrieveItem2=dataSnapshot.child("Item2").getValue().toString();
                            String retrieveItem3=dataSnapshot.child("Item3").getValue().toString();
                            String retrieveItem4=dataSnapshot.child("Item4").getValue().toString();
                            String retrieveItem5=dataSnapshot.child("Item5").getValue().toString();

                            item1View.setText(retrieveItem1);
                            item2View.setText(retrieveItem2);
                            item3View.setText(retrieveItem3);
                            item4View.setText(retrieveItem4);
                            item5View.setText(retrieveItem5);
                        }

                        else if(!dataSnapshot.exists()){

                            Toast.makeText(ViewBook.this, "No note to view", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }






}




