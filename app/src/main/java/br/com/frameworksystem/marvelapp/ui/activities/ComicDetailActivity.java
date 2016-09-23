package br.com.frameworksystem.marvelapp.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.com.frameworksystem.marvelapp.R;
import br.com.frameworksystem.marvelapp.model.Character;
import br.com.frameworksystem.marvelapp.model.Comic;

/**
 * Created by john.souza on 14/09/2016.
 */
public class ComicDetailActivity extends BaseActivity {
    private Comic comic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comicdetail);

        if(getIntent().hasExtra("comic"))
        {
            comic = (Comic) getIntent().getSerializableExtra("comic");
        }

        TextView textView = (TextView) findViewById(R.id.textcomicdetail);
        textView.setText("Description: "+comic.getDescription());

        TextView textViewPrice = (TextView) findViewById(R.id.textprice);
        textViewPrice.setText("Price: "+comic.getPrice());

        TextView textViewLanguage = (TextView) findViewById(R.id.textlanguage);
        String languages = "";
        for (String lingua : comic.getLanguages()) {
            languages+="\n"+lingua;
        }
        textViewLanguage.setText("Language: "+languages);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(comic.getTitle());

        ImageView imageView = (ImageView) findViewById(R.id.imagecomicdetail);
        Picasso.with(this).load(comic.getThumbnailUrl()).centerCrop().resize(600,600).into(imageView);


        Button b = (Button)findViewById(R.id.toast);
        //fazer o set list do button recuperado
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Toast.makeText(getApplicationContext(),"Botão pressionado",Toast.LENGTH_SHORT).show();

            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        getMenuInflater().inflate(R.menu.character_detail, menu);

        //criando a intent de share com provider
        Intent intent = share(comic.getDetailUrl());
        ShareActionProvider actionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(
                menu.findItem(R.id.action_share));
        actionProvider.setShareIntent(intent);

        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        if(item.getItemId() == R.id.action_share)
        {
            share(comic.getDetailUrl());
        }
        return super.onOptionsItemSelected(item);
    }


    //maneira manual
    private Intent share(String url)
    {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_VIEW);
        shareIntent.setData(Uri.parse(url));
        return shareIntent;
    }




}
