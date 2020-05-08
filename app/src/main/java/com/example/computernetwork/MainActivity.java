package com.example.computernetwork;

import androidx.appcompat.app.AppCompatActivity;



import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.example.computernetwork.db.DaoMaster;
import com.example.computernetwork.db.DaoSession;
import com.example.computernetwork.db.GreendaoWord;
import com.example.computernetwork.db.WordDao;
import com.roughike.bottombar.BottomBar;

import com.roughike.bottombar.OnTabReselectListener;


//更新github
public class MainActivity extends AppCompatActivity {

    private BottomBar bottomBar;
    private Fragment homefragment;
    private Fragment practisefragment;
    private Fragment contentfragment;

    private Button homesearchbutton;

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public  static AppCompatActivity instances;

    public static AppCompatActivity getInstances(){
        return instances;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instances=this;







        //主页搜索按钮
        homesearchbutton = findViewById(R.id.home_search);
        homesearchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,Search_Activity.class);
                startActivity(intent);
            }
        });





        //实例Fragment
        homefragment = new HomeFragment();
        practisefragment = new PractiseFragment();
        contentfragment = new ContentFragment();

        //设置底部切换栏
        bottomBar = (BottomBar)findViewById(R.id.bottomBar);
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(int tabId) {
                if(tabId==R.id.tab_home){
                    repalceFragment(homefragment);
                }else if(tabId==R.id.tab_Practise){
                    repalceFragment(practisefragment);
                    Log.d("Mainactivity111", "onTabReSelected: ");
                }
                else if(tabId==R.id.tab_Content){
                    repalceFragment(contentfragment);
                    Log.d("Mainactivity111", "onTabReSelected: content");
                }
            }
        });


    }

    //切换fragment
    private void repalceFragment(Fragment fragment){
        FragmentManager fm1 = getFragmentManager();
        FragmentTransaction ft1 = fm1.beginTransaction();
        ft1.replace(R.id.home_framelayout,fragment).commit();
    }







}
