package com.example.computernetwork;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;


import com.example.computernetwork.db.GreendaoWord;
import com.example.computernetwork.db.WordDao;

import java.util.List;

public class SearchFragment extends Fragment{


    SingleBase singleBase =SingleBase.getInstance();
    public List<Word> words;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment,container,false);
        TextView search_textview_id= (TextView) view.findViewById(R.id.search_set_id);
        TextView search_textview_english = (TextView) view.findViewById(R.id.search_set_English);
        TextView search_textview_chinese= (TextView) view.findViewById(R.id.search_set_Chiese);
        TextView search_textview_abb= (TextView) view.findViewById(R.id.search_set_abb);


        //从Search_Activity获得EditText数据
        Bundle bundle=getArguments();
        String searchword = bundle.getString("one").toUpperCase();




        //单例初始化数据库
        words=singleBase.setWorddata(getActivity());
        WordDao wordDao = singleBase.getWordDao();





        //查询数据库
        List<Word> searchwords = wordDao.queryBuilder().whereOr(WordDao.Properties.English.eq(searchword),
                WordDao.Properties.ID.eq(searchword),
                WordDao.Properties.Chinese.eq(searchword),
                WordDao.Properties.Abbreviations.eq(searchword)).build().list();


        if(searchwords!=null&&searchwords.size()>0) {
            search_textview_id.setText(String.valueOf(searchwords.get(0).getID()));
            search_textview_english.setText(searchwords.get(0).getEnglish());
            search_textview_chinese.setText(searchwords.get(0).getChinese());
            search_textview_abb.setText(searchwords.get(0).getAbbreviations());
        }else {
            Toast.makeText(getActivity(),"输入的数据不存在",Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}
