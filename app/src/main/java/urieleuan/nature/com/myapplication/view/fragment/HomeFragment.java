package urieleuan.nature.com.myapplication.view.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import urieleuan.nature.com.myapplication.R;
import urieleuan.nature.com.myapplication.adapter.PictureAdapterRecyclerView;
import urieleuan.nature.com.myapplication.model.Picture;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar("Home", false, view);
        RecyclerView picturesRecycler = (RecyclerView) view.findViewById(R.id.pictureRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturesRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView =
                new PictureAdapterRecyclerView(buildPictures(), R.layout.cardview_picture, getActivity());
        picturesRecycler.setAdapter(pictureAdapterRecyclerView);


        //FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewPostFragment newPostFragment = new NewPostFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, newPostFragment)
                        .addToBackStack (null).commit();
            }
        });

        return view;
    }

    public ArrayList<Picture> buildPictures() {
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("http://d2on3k3hzjn2sy.cloudfront.net/wp-content/uploads/2011/12/serpiente-cascabel1.jpg", "Vibora de Cascabel", "4 días", "3 Me gusta"));
        pictures.add(new Picture("http://www.anipedia.net/imagenes/cocodrilos-800x375.jpg", "Cocodrilo", "7 días", "12 Me gusta"));
        pictures.add(new Picture("http://www.planetaazul.com.mx/site/wp-content/uploads/2011/02/arana_nota.jpg", "Araña africana", "5 días", "3 Me gusta"));
        return pictures;

    }

    public void showToolbar(String tittle, boolean upButton, View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}