package com.example.computernetwork.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;

import com.example.computernetwork.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class GreendaoWord {


    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private Context mContext;

    //用于传输数据的常量
    private final int BUFFER_SIZE = 400000;
    public static final String PACKAGE_NAME = "com.example.computernetwork";//包名
    public static final String DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() +
            "/" + PACKAGE_NAME+"/databases";   //数据库的绝对路径( /data/data/com.*.*(package name))
    public static final String DB_NAME = "WordBaseIn.db"; //数据库名字



    public GreendaoWord(Context context) {
        mContext = context;
    }

    public  static AppCompatActivity instances;

    public static AppCompatActivity getInstances(){
        return instances;
    }

    //初始化数据库
    public WordDao initGreenDao() {
        mHelper = new DaoMaster.DevOpenHelper(mContext, DB_NAME);
        db = mHelper.getWritableDatabase();
        //在初始化greenDao的地方加上这一行
        db.disableWriteAheadLogging();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
        WordDao wordDao = mDaoSession.getWordDao();

        try {
            //复制外部程序
            InputStream is = mContext.getResources().openRawResource(R.raw.wordbasein);
            BufferedInputStream bis = new BufferedInputStream(is);
            OutputStream fos = new FileOutputStream(DB_PATH + "/" + DB_NAME);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;
            while ((len=bis.read(buffer))!=-1){
                bos.write(buffer,0,len);
            }
            bis.close();
            bos.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return wordDao;
    }
    public DaoSession getmDaoSession(){
        return mDaoSession;
    }

    public SQLiteDatabase getDb(){
        return db;
    }





}
