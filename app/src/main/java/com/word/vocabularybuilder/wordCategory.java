package com.word.vocabularybuilder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;

public class wordCategory extends AppCompatActivity {
    ListView mainListView;
    private ArrayAdapter<String> listAdapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("WordList");
    ArrayList<String> wordlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_category);
        mainListView = (ListView) findViewById(R.id.text_list_view);
        Bundle bundle = getIntent().getExtras();
        ArrayList<String> Categories = bundle.getStringArrayList("CatList");
        HashSet<String> CatSet = new HashSet<>();
        CatSet.addAll(Categories);
        final ArrayList<String> catListSet = new ArrayList<String>();
        catListSet.addAll(CatSet);

        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, catListSet);
        mainListView.setAdapter(listAdapter);
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(wordCategory.this, "word at " + position
                        + " is " + catListSet.get(position), Toast.LENGTH_SHORT).show();
                String Cate_gory = catListSet.get(position);
                getWordlist(Cate_gory);
            }


        });
    }

    private void getWordlist(final String cate_gory) {
        DatabaseReference dref = myRef.child(cate_gory).getRef();
        wordlist.clear();
        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String s = ds.getKey();
                    wordlist.add(s);
                }

                Intent intent = new Intent(getApplicationContext(), CategoryList.class);
                Bundle b = new Bundle();
                b.putStringArrayList("WordList",wordlist);
                b.putString("Category",cate_gory);
                intent.putExtras(b);
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
