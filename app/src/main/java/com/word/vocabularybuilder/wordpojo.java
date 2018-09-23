package com.word.vocabularybuilder;

import android.os.Parcel;
import android.os.Parcelable;

public class wordpojo implements Parcelable {
    String word, meaning, synonyms, antonyms, category, usages;

    public wordpojo() {
    }

    public wordpojo(String word, String meaning, String synonyms, String antonyms, String category, String usages) {
        this.word = word;
        this.meaning = meaning;
        this.synonyms = synonyms;
        this.antonyms = antonyms;
        this.category = category;
        this.usages = usages;
    }

    protected wordpojo(Parcel in) {
        word = in.readString();
        meaning = in.readString();
        synonyms = in.readString();
        antonyms = in.readString();
        category = in.readString();
        usages = in.readString();
    }

    public static final Creator<wordpojo> CREATOR = new Creator<wordpojo>() {
        @Override
        public wordpojo createFromParcel(Parcel in) {
            return new wordpojo(in);
        }

        @Override
        public wordpojo[] newArray(int size) {
            return new wordpojo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(word);
        dest.writeString(meaning);
        dest.writeString(synonyms);
        dest.writeString(antonyms);
        dest.writeString(category);
        dest.writeString(usages);
    }
}
