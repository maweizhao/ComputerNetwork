package com.example.computernetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.List;

public class Search_Activity extends AppCompatActivity {

    private EditText searchEdittext;
    private Button searchbtn;
    private Fragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        //保存输入数据
        final String[] searchstring = new String[1];
        searchEdittext = (EditText)findViewById(R.id.search_edit) ;
        searchbtn=(Button)findViewById(R.id.search_btn);

        //实例SearchFragment
        searchFragment = new SearchFragment();




        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获得editt的数据
                searchstring[0] =searchEdittext.getText().toString();
                SearchFragment fragment = new SearchFragment();

                Bundle bundle=new Bundle();

                //bundle输入要传输的数据
                bundle.putString("one",searchstring[0]);
                fragment.setArguments(bundle);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.search_frame,fragment).commit();

            }
        });


        //实现回车监听
        searchEdittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    searchstring[0] = searchEdittext.getText().toString();
                    SearchFragment fragment = new SearchFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("one", searchstring[0]);
                    fragment.setArguments(bundle);
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.search_frame, fragment).commit();


                }
                return false;
            }
        });


    }


}
