package br.com.frameworksystem.marvelapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.frameworksystem.marvelapp.R;
import br.com.frameworksystem.marvelapp.model.Character;

/**
 * Created by john.souza on 14/09/2016.
 */
public class CharacterDetailActivity extends BaseActivity {
    private Character character;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.characterdetail);

        if(getIntent().hasExtra("character"))
        {
            character = (Character) getIntent().getSerializableExtra("character");
        }

        TextView textView = (TextView) findViewById(R.id.textNameDetail2);
        textView.setText(character.getDescription());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(character.getName());

        ImageView imageView = (ImageView) findViewById(R.id.imageCharacterDetail2);
        Picasso.with(this).load(character.getThumbnailUrl()).centerCrop().resize(600,600).into(imageView);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        getMenuInflater().inflate(R.menu.character_detail, menu);

        //criando a intent de share com provider
        ShareCompat.IntentBuilder intent = ShareCompat.IntentBuilder.from(this).
                setText(character.getDescription()).setType("text/plain");
        ShareActionProvider actionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(
                menu.findItem(R.id.action_share));
        actionProvider.setShareIntent(intent.getIntent());

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        /*if(item.getItemId() == R.id.action_share)
        {
            share();
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }


//maneira manual
    private void share()
    {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, character.getName());
        shareIntent.putExtra(Intent.EXTRA_TITLE, character.getDescription());
        shareIntent.setType("text/plain");

        startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));
    }




}
