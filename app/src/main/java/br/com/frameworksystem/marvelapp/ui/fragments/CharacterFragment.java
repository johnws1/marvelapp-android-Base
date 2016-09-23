package br.com.frameworksystem.marvelapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.frameworksystem.marvelapp.Mock;
import br.com.frameworksystem.marvelapp.R;
import br.com.frameworksystem.marvelapp.model.Character;
import br.com.frameworksystem.marvelapp.ui.adapters.CharacterAdapter;
import br.com.frameworksystem.marvelapp.ui.adapters.EventAdapter;

/**
 * Created by john.souza on 13/09/2016.
 */
public class CharacterFragment extends Fragment {

    private RecyclerView recyclerView;
    private CharacterAdapter characterAdpater;
    private int menuOption;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.events,container,false);

    }

    public CharacterFragment(){}

    public CharacterFragment(int id){
        menuOption = id;
    }

    public void onViewCreated(View view,@Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);

        characterAdpater = new CharacterAdapter(getActivity(), Mock.getCharacters(),recyclerView,menuOption);
        recyclerView.setAdapter(characterAdpater);


    }


}
