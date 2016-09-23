package br.com.frameworksystem.marvelapp.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.frameworksystem.marvelapp.R;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.frameworksystem.marvelapp.model.Character;
import br.com.frameworksystem.marvelapp.model.Comic;
import br.com.frameworksystem.marvelapp.ui.activities.CharacterDetailActivity;
import br.com.frameworksystem.marvelapp.ui.activities.ComicActivity;
import br.com.frameworksystem.marvelapp.ui.activities.ComicDetailActivity;

/**
 * Created by john.souza on 13/09/2016.
 */
public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ViewHolder> {
    private Context context;
    private List<Comic> comics;
    private RecyclerView recyclerView;


    public ComicAdapter(Context context,List<Comic>comicList, RecyclerView recyclerView)
    {
        this.context = context;
        this.comics = comicList;
        this.recyclerView = recyclerView;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.comics,parent,false);
        ComicAdapter.ViewHolder viewHolder = new ComicAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comic comic = comics.get(position);
        holder.comicTitle.setText(comic.getTitle());
        Picasso.with(context).load(comic.getThumbnailUrl()).centerCrop().resize(250,250).into(holder.comicImg);
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView comicImg;
        TextView comicTitle;



        public ViewHolder(View itemView)
        {
            super(itemView);
            comicImg = (ImageView) itemView.findViewById(R.id.comic_image);
            comicTitle = (TextView) itemView.findViewById(R.id.comic_title);



            itemView.setOnClickListener(onclickListener);
        }

        View.OnClickListener onclickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                Comic comic = comics.get(position);
                Intent intent = new Intent(context,ComicDetailActivity.class);
                intent.putExtra("comic",comic);
                context.startActivity(intent);
            }
        };
    }
}
