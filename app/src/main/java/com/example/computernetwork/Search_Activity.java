package com.example.computernetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



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
                searchstring[0] =searchEdittext.getText().toString();
                Log.d("search111", searchEdittext.getText().toString());





                SearchFragment fragment = new SearchFragment();
                Bundle bundle=new Bundle();
                bundle.putString("one",searchstring[0]);
                fragment.setArguments(bundle);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.search_frame,fragment).commit();

            }
        });


    }





    private void repalceFragment(Fragment fragment){
        FragmentManager fm1 = getFragmentManager();
        FragmentTransaction ft1 = fm1.beginTransaction();
        ft1.replace(R.id.search_frame,fragment).commit();
    }
}
