package br.com.frameworksystem.marvelapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import br.com.frameworksystem.marvelapp.Mock;
import br.com.frameworksystem.marvelapp.R;
import br.com.frameworksystem.marvelapp.ui.adapters.EventAdapter;

/**
 * Created by john.souza on 13/09/2016.
 */
public class EventFragment extends Fragment {

    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState)
    {
        if(getResources().getBoolean(R.bool.is_tablet))
            return inflater.inflate(R.layout.eventos_tablet,container,false);
        else
            return inflater.inflate(R.layout.events,container,false);

    }

    public EventFragment()
    {

    }

    public void onViewCreated(View view,@Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);

        WebView webView = (WebView) view.findViewById(R.id.event_detail_tablet);

        eventAdapter = new EventAdapter(getActivity(), Mock.getEvents(),recyclerView,getResources().getBoolean(R.bool.is_tablet),webView);
        recyclerView.setAdapter(eventAdapter);

    }


}
