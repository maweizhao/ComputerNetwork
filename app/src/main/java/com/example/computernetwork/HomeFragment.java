package com.example.computernetwork;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import android.app.Fragment;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.computernetwork.db.GreendaoWord;
import com.example.computernetwork.db.WordDao;

import java.util.List;
import java.util.Random;

public class HomeFragment extends Fragment {



    private Button homesearchbutton;
    private Button homefreshbutton;
    private Button homeanswerbutton;
    private TextView eng1, eng2, eng3, eng4, chi1, chi2, chi3, chi4, popwordchi, popwordeng, popwordabb;
    private LinearLayout homes1, homes2, homes3, homes4;


    public List<Word> words;
    //初始化单例数据库
    SingleBase singleBase =SingleBase.getInstance();


    @RequiresApi(api = Build.VERSION_CODES.O)
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
        homes1 = (LinearLayout) view.findViewById(R.id.home_select_1);
        homes2 = (LinearLayout) view.findViewById(R.id.home_select_2);
        homes3 = (LinearLayout) view.findViewById(R.id.home_select_3);
        homes4 = (LinearLayout) view.findViewById(R.id.home_select_4);


        //单例初始化数据库
        words=singleBase.setWorddata(getActivity());
        homefragmentlayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        eng1.setText(words.get(getRandomWord()).getEnglish());
        eng2.setText(words.get(getRandomWord()).getEnglish());
        eng3.setText(words.get(getRandomWord()).getEnglish());
        eng4.setText(words.get(getRandomWord()).getEnglish());


        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
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

        //主页刷新按钮
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
        //主页答案按钮
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


        //点击选项然后popword
        homes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询
                String searchword = eng1.getText().toString();
                List<Word> searchwords = singleBase.getWordDao().queryBuilder().where(WordDao.Properties.English.eq(searchword)).build().list();
                popwordwindow(searchwords, homes1);
            }
        });

        //点击选项然后popword
        homes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询
                String searchword = eng2.getText().toString();
                List<Word> searchwords = singleBase.getWordDao().queryBuilder().where(WordDao.Properties.English.eq(searchword)).build().list();

                popwordwindow(searchwords, homes2);

            }
        });

        //点击选项然后popword
        homes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询
                String searchword = eng3.getText().toString();
                List<Word> searchwords = singleBase.getWordDao().queryBuilder().where(WordDao.Properties.English.eq(searchword)).build().list();
                popwordwindow(searchwords, homes3);

            }
        });

        //点击选项然后popword
        homes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询
                String searchword = eng4.getText().toString();
                List<Word> searchwords = singleBase.getWordDao().queryBuilder().where(WordDao.Properties.English.eq(searchword)).build().list();
                popwordwindow(searchwords, homes4);

            }
        });


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void popwordwindow(List<Word> searchwords, final View view) {


        //加载wiev，用于popwindow
        final View viewWordPop = getLayoutInflater().inflate(R.layout.word_pop, null);
        final LinearLayout linearlayout = (LinearLayout) viewWordPop.findViewById(R.id.word_pop_lin);
        popwordabb = (TextView) viewWordPop.findViewById(R.id.pop_word_abb);
        popwordeng = (TextView) viewWordPop.findViewById(R.id.pop_word_english);
        popwordchi = (TextView) viewWordPop.findViewById(R.id.pop_word_chinese);

        //setpopword
        popwordchi.setText(searchwords.get(0).getChinese());
        popwordabb.setText(searchwords.get(0).getAbbreviations());
        popwordeng.setText(searchwords.get(0).getEnglish());


        //measure方法的参数值都设为0即可
        viewWordPop.measure(0, 0);
        //获取组件的宽度
        int width = 0;
        if (linearlayout.getMeasuredWidth() > view.getWidth()) {
            width = view.getWidth();

        } else {
            width = linearlayout.getMeasuredWidth();
        }


        //获取组件的高度


        final PopupWindow popword = new PopupWindow(viewWordPop, width, 0);
        //popwindow高度匹配内容
        popword.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //触摸window外取消window
        popword.setOutsideTouchable(true);
        popword.showAsDropDown(view);


    }




    private int getRandomWord(){
        Random random = new Random();
        int no =random.nextInt(325);
        return no;
    }



}


