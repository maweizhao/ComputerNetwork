package com.example.computernetwork;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;


import com.example.computernetwork.db.GreendaoWord;
import com.example.computernetwork.db.WordDao;

import java.util.List;



public class ContentFragment extends Fragment {


    SingleBase singleBase =SingleBase.getInstance();
    public List<Word> words;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_fragment,container,false);



        //单例初始化数据库
        words=singleBase.setWorddata(getActivity());
        WordDao wordDao = singleBase.getWordDao();

        List<Word> words =wordDao.loadAll();



        RecyclerView recyclerView = view.findViewById(R.id.recycler_word) ;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        WordAdapter adapter = new WordAdapter(words);
        recyclerView.setAdapter(adapter);
        return view;
    }



}
