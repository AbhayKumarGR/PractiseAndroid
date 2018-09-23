package com.word.vocabularybuilder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WordDetailsActivity extends AppCompatActivity {
TextView txt, det;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_details);

        Bundle b = getIntent().getExtras();
        wordpojo w = b.getParcelable("Word_Details");

        txt = (TextView)findViewById(R.id.wrd_header);
        det = (TextView)findViewById(R.id.det_word);

        txt.setText(w.word);
        det.setText(w.meaning + "\n" + w.synonyms + "\n" + w.antonyms + "\n" + w.usages);
    }
}
