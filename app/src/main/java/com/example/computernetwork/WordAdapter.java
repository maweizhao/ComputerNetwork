package com.example.computernetwork;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

    private List<Word> mWordList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView wordEnglish, wordChinese, wordID, wordabb;


        public ViewHolder(View view){
            super(view);
            wordEnglish = (TextView)view.findViewById(R.id.word_English);
            wordChinese = (TextView)view.findViewById(R.id.word_Chinese);
            wordID=(TextView)view.findViewById(R.id.word_number);
            wordabb = (TextView) view.findViewById(R.id.word_abb);
        }
    }

    public WordAdapter(List<Word> wordList){
        mWordList = wordList;
    }


    @NonNull
    @Override
    public WordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WordAdapter.ViewHolder holder, int position) {
        Word word = mWordList.get(position);
        holder.wordEnglish.setText(word.getEnglish());
        holder.wordChinese.setText(word.getChinese());
        holder.wordID.setText(String.valueOf(word.getID()));
        holder.wordabb.setText(word.getAbbreviations());
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }
}
