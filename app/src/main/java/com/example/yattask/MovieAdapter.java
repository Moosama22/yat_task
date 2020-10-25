package com.example.yattask;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<movieitem>movieitems;
    private Context context;
    private favdb favdb;

    public MovieAdapter(ArrayList<movieitem>movieitems , Context context){
        this.movieitems=movieitems;
        this.context=context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favdb=new favdb(context);
        SharedPreferences prefs =context.getSharedPreferences("prefs",Context.MODE_PRIVATE);
        boolean firstStart =prefs.getBoolean("firstStart",true);
        if (firstStart){
            createtableonFirstStart();

        }

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        final movieitem movieitem= movieitems.get(position);

        readCursorData(movieitem,holder);
        holder.imageView.setImageResource(movieitem.getImageResource());
        holder.titleTextView.setText(movieitem.getTitle());
    }



    @Override
    public int getItemCount() {
        return movieitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        Button favBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageview);
            titleTextView=itemView.findViewById(R.id.titletextview);
            favBtn=itemView.findViewById(R.id.favBtn);
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    movieitem Movieitem= movieitems.get(position);

                    if (Movieitem.getFavStatus().equals("0")){
                        Movieitem.setFavStatus("1");
                        favdb.insertintodatabase(Movieitem.getTitle(),Movieitem.getImageResource(),
                                Movieitem.getKey_id(),Movieitem.getFavStatus());
                        favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24);

                    }
                    else {
                        Movieitem.setFavStatus("0");
                        favdb.remove_fav(Movieitem.getKey_id());
                        favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24);

                    }
                }
            });



        }
    }
    private void createtableonFirstStart() {
        favdb.insertempty();
        SharedPreferences prefs=context.getSharedPreferences("prefs",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();
    }
    private void readCursorData(movieitem movieitem , ViewHolder viewHolder) {
        Cursor cursor =favdb.read_all_data(movieitem.getKey_id());
        SQLiteDatabase db=favdb.getReadableDatabase();
        try {
            while (cursor.moveToNext()){
                String item_fav_status=cursor.getString(cursor.getColumnIndex(com.example.yattask.favdb.Favorit_movie));
                movieitem.setFavStatus(item_fav_status);

                if (item_fav_status!=null && item_fav_status.equals("1")){
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24);

                }
                else if(item_fav_status!=null && item_fav_status.equals("0")){
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24);
                }
            }


        }
        finally {
            if (cursor !=null && cursor.isClosed()){
                cursor.close();
                db.close();

            }


        }


    }
}
