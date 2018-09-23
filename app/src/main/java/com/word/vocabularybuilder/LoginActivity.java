package com.word.vocabularybuilder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("WordList");
    ArrayList<String> lstcat = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login_authenticate(View view) {
        getallCategoriesFromWordDb();
    }

    private void getallCategoriesFromWordDb() {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            //ArrayList<String> catList = new ArrayList<>();
            HashSet<String> categSet = new HashSet<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d :dataSnapshot.getChildren()){
                    String cat = d.getKey();
                    categSet.add(cat);
                }
                lstcat.addAll(categSet);
                Intent intent = new Intent(getApplicationContext(), wordCategory.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("CatList",lstcat);
                intent.putExtras(bundle);
                startActivity(intent,bundle);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
    }
}
