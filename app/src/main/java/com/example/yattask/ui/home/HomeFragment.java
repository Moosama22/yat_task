package com.example.yattask.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yattask.MovieAdapter;
import com.example.yattask.R;
import com.example.yattask.movieitem;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
private ArrayList<movieitem>movieitems=new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView=root.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new MovieAdapter(movieitems,getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        movieitems.add(new movieitem(R.drawable.ant_man,"Ant man","0","0"));
        movieitems.add(new movieitem(R.drawable.aquaman,"Aquaman","1","0"));
        movieitems.add(new movieitem(R.drawable.avengers_age_of_ultron,"Avengers _aga of ultron","2","0"));
        movieitems.add(new movieitem(R.drawable.bloodshot,"BloodSHOT","3","0"));
        movieitems.add(new movieitem(R.drawable.doctor_strange,"Doctor Strange","4","0"));
        movieitems.add(new movieitem(R.drawable.john_wick_2,"John Wick 2","5","0"));
        movieitems.add(new movieitem(R.drawable.the_joker,"the joker","6","0"));
        movieitems.add(new movieitem(R.drawable.maleficent,"Maleficent","7","0"));
        movieitems.add(new movieitem(R.drawable.sherlock,"Sherlock","8","0"));



        return root;
    }
}