package br.com.frameworksystem.marvelapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.frameworksystem.marvelapp.Mock;
import br.com.frameworksystem.marvelapp.R;
import br.com.frameworksystem.marvelapp.model.Character;
import br.com.frameworksystem.marvelapp.ui.adapters.ComicAdapter;

/**
 * Created by john.souza on 13/09/2016.
 */
public class ComicFragment extends Fragment {

    private RecyclerView recyclerView;
    private ComicAdapter comicAdapter;
    private Character character;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.events,container,false);

    }

    public ComicFragment(Character character)
    {
        this.character = character;
    }

    public void onViewCreated(View view,@Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);

        comicAdapter = new ComicAdapter(getActivity(), Mock.getCommics(character.getName()),recyclerView);
        recyclerView.setAdapter(comicAdapter);
    }


}
