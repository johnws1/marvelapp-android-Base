package br.com.frameworksystem.marvelapp.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.frameworksystem.marvelapp.R;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.frameworksystem.marvelapp.model.Event;
import br.com.frameworksystem.marvelapp.ui.activities.EventDetailActivity;

/**
 * Created by john.souza on 13/09/2016.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private Context context;
    private List<Event> events;
    private RecyclerView recyclerView;
    private boolean isTablet = false;
    private WebView webView;

    public EventAdapter(Context context,List<Event>eventList, RecyclerView recyclerView,boolean isTablet,WebView webView)
    {
        this.context = context;
        this.events = eventList;
        this.recyclerView = recyclerView;
        this.isTablet = isTablet;
        this.webView = webView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        EventAdapter.ViewHolder viewHolder = new EventAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Event event = events.get(position);
        holder.eventTitle.setText(event.getTitle());
        Picasso.with(context).load(event.getImgUrl()).centerCrop().resize(400,400).into(holder.eventImg);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView eventImg;
        TextView eventTitle;

        public ViewHolder(View itemView)
        {
            super(itemView);
            eventImg = (ImageView) itemView.findViewById(R.id.imageView2);
            eventTitle = (TextView) itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(onclickListener);
        }

        View.OnClickListener onclickListener = new View.OnClickListener()
        {
            //webview

            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                Event event = events.get(position);
                if(isTablet)
                {
                    webView.loadUrl(event.getUrl());
                }
                else
                {
                    Intent intent = new Intent(context,EventDetailActivity.class);
                    intent.putExtra("event",event);
                    context.startActivity(intent);
                }




            }
        };
    }
}
