package com.example.computernetwork;

import android.content.Context;

import com.example.computernetwork.db.GreendaoWord;
import com.example.computernetwork.db.WordDao;

import java.util.List;

public class SingleBase {

    private static SingleBase instance = new SingleBase();
    private SingleBase(){}


    public static SingleBase getInstance(){
        return instance;
    }


    Context mcontext;
    WordDao wordDao;

    public List<Word> setWorddata(Context mcontext){

        GreendaoWord greendaoWord = new GreendaoWord(mcontext);
        wordDao=greendaoWord.initGreenDao();
        List<Word> mList =wordDao.loadAll();
        return mList;
    }

    public WordDao getWordDao() {
        return wordDao;
    }

}
