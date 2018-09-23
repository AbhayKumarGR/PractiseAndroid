package com.word.vocabularybuilder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;

public class CategoryList extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("WordList");

    ArrayList<String> wrdlist = new ArrayList<>();
    ArrayAdapter<String > wrdadapter;
    String Category_w;
    TextView textView;
    private ListView categoryList;
    wordpojo wp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        textView = (TextView)findViewById(R.id.txt_Category);
        categoryList = (ListView)findViewById(R.id.text_list_view2);

       Bundle bndl = getIntent().getExtras();
       wrdlist = bndl.getStringArrayList("WordList");
       Category_w = bndl.getString("Category");
        wrdadapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, wrdlist);
        categoryList.setAdapter(wrdadapter);
       categoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               String word = wrdlist.get(position);
                getWordDetails(Category_w, word);

                          }

           private void getWordDetails(String Category, String word) {
               wp=null;
               DatabaseReference dref = myRef.child(Category).child(word).getRef();
                dref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        wp = dataSnapshot.getValue(wordpojo.class);
                        Intent intent = new Intent(getApplicationContext(), WordDetailsActivity.class);
                        Bundle b = new Bundle();
                        b.putParcelable("Word_Details", wp);
                        intent.putExtras(b);
                        startActivity(intent);
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                           }
       });

    }


}
