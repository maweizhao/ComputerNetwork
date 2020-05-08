package com.example.computernetwork;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.computernetwork.db.WordDao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PractiseFragment extends Fragment {

    private LinearLayout answerA,answerB,answerC,answerD;
    private TextView question,eng1,eng2,eng3,eng4,chi1,chi2,chi3,chi4,abb1,abb2,abb3,abb4,cof;
    private Button answer,nextque;


    public List<TextView> chi=new ArrayList<>();
    public List<TextView> abb=new ArrayList<>();
    public List<TextView> eng=new ArrayList<>();
    public List<LinearLayout> choice=new ArrayList<>();
    public List<Word> questionwords;
    public List<Word> words;
    //初始化单例数据库
    SingleBase singleBase =SingleBase.getInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.practise_fragment,container,false);



        cof=(TextView)view.findViewById(R.id.cof);
        answer=(Button)view.findViewById(R.id.practise_set_answer_btn);
        nextque=(Button)view.findViewById(R.id.practise_next_question);
        question=(TextView)view.findViewById(R.id.practise_question);
        eng1=(TextView)view.findViewById(R.id.practise_answer_english_1);
        eng2=(TextView)view.findViewById(R.id.practise_answer_english_2);
        eng3=(TextView)view.findViewById(R.id.practise_answer_english_3);
        eng4=(TextView)view.findViewById(R.id.practise_answer_english_4);
        chi1=(TextView)view.findViewById(R.id.practise_answer_chinese_1);
        chi2=(TextView)view.findViewById(R.id.practise_answer_chinese_2);
        chi3=(TextView)view.findViewById(R.id.practise_answer_chinese_3);
        chi4=(TextView)view.findViewById(R.id.practise_answer_chinese_4);
        abb1=(TextView)view.findViewById(R.id.practise_answer_abb_1);
        abb2=(TextView)view.findViewById(R.id.practise_answer_abb_2);
        abb3=(TextView)view.findViewById(R.id.practise_answer_abb_3);
        abb4=(TextView)view.findViewById(R.id.practise_answer_abb_4);
        answerA=(LinearLayout) view.findViewById(R.id.answer_A);
        answerB=(LinearLayout) view.findViewById(R.id.answer_B);
        answerC=(LinearLayout) view.findViewById(R.id.answer_C);
        answerD=(LinearLayout) view.findViewById(R.id.answer_D);
        words=singleBase.setWorddata(getActivity());


        setQuestion();


        return view;
    }





    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //将Textview装入List中
        chi.add(chi1);chi.add(chi2);chi.add(chi3);chi.add(chi4);
        eng.add(eng1);eng.add(eng2);eng.add(eng3);eng.add(eng4);
        abb.add(abb1); abb.add(abb2); abb.add(abb3); abb.add(abb4);
        choice.add(answerA);choice.add(answerB);choice.add(answerC);choice.add(answerD);


        answerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getAnswerEng(chi1)==question.getText()){
                    cof.setText("正确！");
                    cof.setTextColor(Color.parseColor("#39d723"));
                    putAnswer();
                }else{
                    cof.setText("错误!");
                    cof.setTextColor(Color.parseColor("#d72d23"));
                }
            }
        });

        answerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getAnswerEng(chi2)==question.getText()){
                    cof.setText("正确！");
                    cof.setTextColor(Color.parseColor("#39d723"));
                    putAnswer();
                }else{
                    cof.setText("错误!");
                    cof.setTextColor(Color.parseColor("#d72d23"));
                }
            }
        });

        answerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getAnswerEng(chi3)==question.getText()){
                    cof.setText("正确！");
                    cof.setTextColor(Color.parseColor("#39d723"));
                    putAnswer();
                }else{
                    cof.setText("错误!");
                    cof.setTextColor(Color.parseColor("#d72d23"));
                }
            }
        });
        answerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getAnswerEng(chi4)==question.getText()){
                    cof.setText("正确！");
                    cof.setTextColor(Color.parseColor("#39d723"));
                    putAnswer();
                }else{
                    cof.setText("错误!");
                    cof.setTextColor(Color.parseColor("#d72d23"));
                }
            }
        });










        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                putAnswer();
            }
        });

        nextque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cof.setText("");
                for(TextView t1:eng){
                    t1.setText("");
                }
                for(TextView t1:abb){
                    t1.setText("");
                }
                setQuestion();
            }
        });



    }

    //根据英文输入答案的方法
    private void setAnswer(TextView settextView,TextView textView,String setstring){
        String searchword=textView.getText().toString();
        List<Word> searchwords=singleBase.getWordDao().queryBuilder().where(WordDao.Properties.Chinese.eq(searchword)).build().list();
        if(setstring=="chi"){
            settextView.setText(searchwords.get(0).getChinese());
        }else if(setstring=="abb"){
            settextView.setText(searchwords.get(0).getAbbreviations());
        }else if(setstring=="eng"){
            settextView.setText(searchwords.get(0).getEnglish());
        }
    }

    //输入中文获得选项的英文
    private String getAnswerEng(TextView textView){
        String searchword=textView.getText().toString();
        List<Word> searchwords=singleBase.getWordDao().queryBuilder().where(WordDao.Properties.Chinese.eq(searchword)).build().list();
        return searchwords.get(0).getEnglish();
    }





    private int getRandomWord(int number){
        Random random = new Random();
        int no =random.nextInt(number);
        return no;
    }



    //加载全部答案
    private void putAnswer(){
        //输入答案
        int i=0;
        for(TextView t1:eng){
            setAnswer(t1,chi.get(i),"eng");
            i++;
        }
        i=0;
        for(TextView t1:abb){
            setAnswer(t1,chi.get(i),"abb");
            i++;
        }

    }








    //加载问题
    private void addquestion(){
        questionwords = new ArrayList<>();
        questionwords.clear();
        //抽取四个word作为问题
        for(int i=0;i<=3;i++){
            questionwords.add(words.get(getRandomWord(325)));
        }
    }




    private void setQuestion(){
        //加载题目
        addquestion();
        question.setText(questionwords.get(getRandomWord(3)).getEnglish());

        int removeno=0;
        chi1.setText(questionwords.get(removeno=getRandomWord(3)).getChinese());
        questionwords.remove(removeno);
        chi2.setText(questionwords.get(removeno=getRandomWord(2)).getChinese());
        questionwords.remove(removeno);
        chi3.setText(questionwords.get(removeno=getRandomWord(1)).getChinese());
        questionwords.remove(removeno);
        chi4.setText(questionwords.get(0).getChinese());
        questionwords.remove(removeno);
    }



}
