package com.emmajerry2016.ebookkepingabc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class EditBook extends AppCompatActivity {
    private Toolbar mToolbar;
    private EditText item1, item2, item3, item4, item5;
    private Button addButton;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mAuth;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();


        mToolbar = findViewById(R.id.add_book_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("Add Book");

        item1 = findViewById(R.id.edititem1);
        item2 = findViewById(R.id.edititem2);
        item3 = findViewById(R.id.edititem3);
        item4 = findViewById(R.id.edititem4);
        item5 = findViewById(R.id.edititem5);

        addButton = findViewById(R.id.editadd_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                collectBookInfo();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        retrieveBooksInfo();
    }

    //To retrieve book Info updated info
    private void retrieveBooksInfo() {
        mDatabaseReference.child("Users").child(currentUserId).child("Notes")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if((dataSnapshot.exists())&& (dataSnapshot.hasChild("Item1"))){
                            String retrieveItem1=dataSnapshot.child("Item1").getValue().toString();

                            item1.setText(retrieveItem1);
                        }
                        if((dataSnapshot.exists())&& (dataSnapshot.hasChild("Item2"))){
                            String retrieveItem2=dataSnapshot.child("Item2").getValue().toString();

                            item2.setText(retrieveItem2);
                        }
                        if((dataSnapshot.exists())&& (dataSnapshot.hasChild("Item3"))){
                            String retrieveItem3=dataSnapshot.child("Item3").getValue().toString();

                            item3.setText(retrieveItem3);
                        }
                        if((dataSnapshot.exists())&& (dataSnapshot.hasChild("Item4"))){
                            String retrieveItem4=dataSnapshot.child("Item4").getValue().toString();

                            item4.setText(retrieveItem4);
                        }
                        if((dataSnapshot.exists())&& (dataSnapshot.hasChild("Item5"))){
                            String retrieveItem5=dataSnapshot.child("Item5").getValue().toString();

                            item5.setText(retrieveItem5);
                        }

                        else if((dataSnapshot.exists())&& (dataSnapshot.hasChild("Item1"))&&(dataSnapshot.hasChild("Item2"))&&(dataSnapshot.hasChild("Item3"))&&(dataSnapshot.hasChild("Item4"))&&(dataSnapshot.hasChild("Item5"))){

                            String retrieveItem1=dataSnapshot.child("Item1").getValue().toString();
                            String retrieveItem2=dataSnapshot.child("Item2").getValue().toString();
                            String retrieveItem3=dataSnapshot.child("Item3").getValue().toString();
                            String retrieveItem4=dataSnapshot.child("Item4").getValue().toString();
                            String retrieveItem5=dataSnapshot.child("Item5").getValue().toString();

                            item1.setText(retrieveItem1);
                            item2.setText(retrieveItem2);
                            item3.setText(retrieveItem3);
                            item4.setText(retrieveItem4);
                            item5.setText(retrieveItem5);
                        }

                        else if(!dataSnapshot.exists()){

                            Toast.makeText(EditBook.this, "No note to edit", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }


    public void collectBookInfo() {
        //*********Now 2 proceed 2 allowing registered user to add books**********//
        String Item1 = item1.getText().toString();
        String Item2 = item2.getText().toString();
        String Item3 = item3.getText().toString();
        String Item4 = item4.getText().toString();
        String Item5 = item5.getText().toString();

        //**********Ensuring that all fields are well entered***********//
        if (!TextUtils.isEmpty(Item1)) {

            //*********To save data to firebase using Hashmap encryption********//
            HashMap<String, Object> bookMap = new HashMap<>();
            bookMap.put("uid", currentUserId);
            bookMap.put("Item1", Item1);

            mDatabaseReference.child("Users").child(currentUserId).child("Notes").updateChildren(bookMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(EditBook.this, ViewBook.class);
                                startActivity(intent);
                                Toast.makeText(EditBook.this, "Notes successfully added", Toast.LENGTH_SHORT).show();

                                item1.setText("");

                            } else {
                                String message = task.getException().toString();
                                Toast.makeText(EditBook.this, "Error:" + message, Toast.LENGTH_SHORT).show();
                            }
                        }

                    });

        }

        if (!TextUtils.isEmpty(Item2)) {
            //*********To save data to firebase using Hashmap encryption********//
            HashMap<String, Object> bookMap = new HashMap<>();
            bookMap.put("uid", currentUserId);
            bookMap.put("Item2", Item2);

            mDatabaseReference.child("Users").child(currentUserId).child("Notes").updateChildren(bookMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(EditBook.this, ViewBook.class);
                                startActivity(intent);
                                Toast.makeText(EditBook.this, "Notes successfully added", Toast.LENGTH_SHORT).show();

                                item2.setText("");

                            } else {
                                String message = task.getException().toString();
                                Toast.makeText(EditBook.this, "Error:" + message, Toast.LENGTH_SHORT).show();
                            }
                        }

                    });

        }

        if (!TextUtils.isEmpty(Item3)) {

            //*********To save data to firebase using Hashmap encryption********//
            HashMap<String, Object> bookMap = new HashMap<>();
            bookMap.put("uid", currentUserId);
            bookMap.put("Item3", Item3);

            mDatabaseReference.child("Users").child(currentUserId).child("Notes").updateChildren(bookMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(EditBook.this, ViewBook.class);
                                startActivity(intent);
                                Toast.makeText(EditBook.this, "Notes successfully added", Toast.LENGTH_SHORT).show();

                                item3.setText("");

                            } else {
                                String message = task.getException().toString();
                                Toast.makeText(EditBook.this, "Error:" + message, Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
        }

        if (!TextUtils.isEmpty(Item4)) {
            //*********To save data to firebase using Hashmap encryption********//
            HashMap<String, Object> bookMap = new HashMap<>();
            bookMap.put("uid", currentUserId);
            bookMap.put("Item4", Item4);

            mDatabaseReference.child("Users").child(currentUserId).child("Notes").updateChildren(bookMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(EditBook.this, ViewBook.class);
                                startActivity(intent);
                                Toast.makeText(EditBook.this, "Notes successfully added", Toast.LENGTH_SHORT).show();

                                item4.setText("");


                            } else {
                                String message = task.getException().toString();
                                Toast.makeText(EditBook.this, "Error:" + message, Toast.LENGTH_SHORT).show();
                            }
                        }

                    });

        }

        if (!TextUtils.isEmpty(Item5)) {

            //*********To save data to firebase using Hashmap encryption********//
            HashMap<String, Object> bookMap = new HashMap<>();
            bookMap.put("uid", currentUserId);
            bookMap.put("Item5", Item5);

            mDatabaseReference.child("Users").child(currentUserId).child("Notes").updateChildren(bookMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(EditBook.this, ViewBook.class);
                                startActivity(intent);
                                Toast.makeText(EditBook.this, "Notes successfully added", Toast.LENGTH_SHORT).show();

                                item5.setText("");

                            } else {
                                String message = task.getException().toString();
                                Toast.makeText(EditBook.this, "Error:" + message, Toast.LENGTH_SHORT).show();
                            }
                        }

                    });

        }

        else if(TextUtils.isEmpty(Item1) && TextUtils.isEmpty(Item2) && TextUtils.isEmpty(Item3) && TextUtils.isEmpty(Item4)
                && TextUtils.isEmpty(Item5)){
            Toast.makeText(EditBook.this, "all note fields can't be empty", Toast.LENGTH_SHORT).show();

        }

    }


}


