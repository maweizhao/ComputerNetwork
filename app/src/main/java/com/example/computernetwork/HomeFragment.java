package com.example.computernetwork;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.computernetwork.db.GreendaoWord;
import com.example.computernetwork.db.WordDao;

import java.util.List;
import java.util.Random;

public class HomeFragment extends Fragment {



    private Button homesearchbutton;
    private Button homefreshbutton;
    private Button homeanswerbutton;
    private TextView eng1,eng2,eng3,eng4,chi1,chi2,chi3,chi4;


    public List<Word> words;
    //初始化单例数据库
    SingleBase singleBase =SingleBase.getInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        homesearchbutton = (Button) view.findViewById(R.id.home_search);
        homefreshbutton=(Button)view.findViewById(R.id.home_fresh_btn);
        homeanswerbutton=(Button)view.findViewById(R.id.home_answer_btn) ;
        eng1=(TextView)view.findViewById(R.id.home_english_1) ;
        eng2=(TextView)view.findViewById(R.id.home_english_2) ;
        eng3=(TextView)view.findViewById(R.id.home_english_3) ;
        eng4=(TextView)view.findViewById(R.id.home_english_4) ;
        chi1=(TextView)view.findViewById(R.id.home_chinese_1) ;
        chi2=(TextView)view.findViewById(R.id.home_chinese_2) ;
        chi3=(TextView)view.findViewById(R.id.home_chinese_3) ;
        chi4=(TextView)view.findViewById(R.id.home_chinese_4) ;
        LinearLayout homefragmentlayout = (LinearLayout) view.findViewById(R.id.home_fragment);



        //单例初始化数据库
        words=singleBase.setWorddata(getActivity());


            homefragmentlayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            eng1.setText(words.get(getRandomWord()).getEnglish());
            eng2.setText(words.get(getRandomWord()).getEnglish());
            eng3.setText(words.get(getRandomWord()).getEnglish());
            eng4.setText(words.get(getRandomWord()).getEnglish());



        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);





        //主页搜索按钮
        homesearchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),Search_Activity.class);
                startActivity(intent);
            }
        });


        homefreshbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eng1.setText(words.get(getRandomWord()).getEnglish());
                eng2.setText(words.get(getRandomWord()).getEnglish());
                eng3.setText(words.get(getRandomWord()).getEnglish());
                eng4.setText(words.get(getRandomWord()).getEnglish());

                //中文置空
                chi1.setText("");
                chi2.setText("");
                chi3.setText("");
                chi4.setText("");

            }
        });

        homeanswerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchword=eng1.getText().toString();
                List<Word> searchwords= singleBase.getWordDao().queryBuilder().where(WordDao.Properties.English.eq(searchword)).build().list();
                chi1.setText(searchwords.get(0).getChinese());

                searchword=eng2.getText().toString();
                searchwords= singleBase.getWordDao().queryBuilder().where(WordDao.Properties.English.eq(searchword)).build().list();
                chi2.setText(searchwords.get(0).getChinese());

                searchword=eng3.getText().toString();
                searchwords= singleBase.getWordDao().queryBuilder().where(WordDao.Properties.English.eq(searchword)).build().list();
                chi3.setText(searchwords.get(0).getChinese());

                searchword=eng4.getText().toString();
                searchwords= singleBase.getWordDao().queryBuilder().where(WordDao.Properties.English.eq(searchword)).build().list();
                chi4.setText(searchwords.get(0).getChinese());




            }
        });



    }

    private int getRandomWord(){
        Random random = new Random();
        int no =random.nextInt(325);
        return no;
    }



}


