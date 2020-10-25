package com.example.yattask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class favdb extends SQLiteOpenHelper {
    private static final int Db_Version =1;
    private static final String Database_name ="Ant Man";
    private static final String List_name ="favoritmove";
    public static String Key_id ="id";
    public static String Item_title ="itemtitle";
    public static String Item_image ="item_image";
    public static String Favorit_movie ="fmovie";
    private static final String Creat_table="Create table "+ List_name +
            "("+ Key_id +" text,"+ Item_title +" text,"
            + Item_image +" text,"+ Favorit_movie +" text)";
    public favdb(Context Context){super(Context, Database_name,null, Db_Version);}
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Creat_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertempty(){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        for (int x=1 ; x<11 ; x++){
            cv.put(Key_id,x);
            cv.put(Favorit_movie,"0");
            db.insert(List_name,null,cv);
        }

    }
    public void insertintodatabase(String item_title ,int item_image , String id , String favorit_movie){
        SQLiteDatabase db ;
        db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Item_title,item_title);
        cv.put(Item_image,item_image);
        cv.put(Key_id,id);
        cv.put(Favorit_movie,favorit_movie);
        db.insert(List_name,null,cv);
        Log.d("favdb status",item_title+", favstatus - "+favorit_movie+"- ." +cv);

    }

    public Cursor read_all_data (String id){
        SQLiteDatabase db =this.getReadableDatabase();
        String sql ="select * from "+List_name+"where"+Key_id+"="+id+"";
        return db.rawQuery(sql,null,null);

    }

    public void remove_fav(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql="update"+List_name+"set "+Favorit_movie+" ='0' WHERE"+Key_id+"="+id+"";


    }
    public Cursor select_all_favorite(String id){
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="select * from "+List_name+" WHERE "+Favorit_movie+"='1'";
        return db.rawQuery(sql,null,null);


    }


}
