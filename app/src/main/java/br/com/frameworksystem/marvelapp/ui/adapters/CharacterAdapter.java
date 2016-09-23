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
import br.com.frameworksystem.marvelapp.ui.activities.CharacterDetailActivity;
import br.com.frameworksystem.marvelapp.ui.activities.ComicActivity;

/**
 * Created by john.souza on 13/09/2016.
 */
public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {
    private Context context;
    private List<Character> characters;
    private RecyclerView recyclerView;
    private int menu;

    public CharacterAdapter(Context context,List<Character>characterList, RecyclerView recyclerView,int menu)
    {
        this.context = context;
        this.characters = characterList;
        this.recyclerView = recyclerView;
        this.menu = menu;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.itemcharacter,parent,false);
        CharacterAdapter.ViewHolder viewHolder = new CharacterAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Character character = characters.get(position);
        holder.characterName.setText(character.getName());
        holder.characterDescription.setText(character.getDescription());
        Picasso.with(context).load(character.getThumbnailUrl()).centerCrop().resize(400,400).into(holder.characterImg);
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView characterImg;
        TextView characterName;
        TextView characterDescription;

        public ViewHolder(View itemView)
        {
            super(itemView);
            characterImg = (ImageView) itemView.findViewById(R.id.imageCharacter2);
            characterName = (TextView) itemView.findViewById(R.id.textName2);
            characterDescription = (TextView) itemView.findViewById(R.id.textDescription2);

            itemView.setOnClickListener(onclickListener);
        }

        View.OnClickListener onclickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                Character character = characters.get(position);
                Intent intent = null;
                if(menu==0)
                {
                    intent = new Intent(context,CharacterDetailActivity.class);
                }
                else
                {
                    intent = new Intent(context,ComicActivity.class);
                }
                intent.putExtra("character",character);
                context.startActivity(intent);
            }
        };
    }
}
