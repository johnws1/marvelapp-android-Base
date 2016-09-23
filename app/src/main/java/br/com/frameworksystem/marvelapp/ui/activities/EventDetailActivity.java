package br.com.frameworksystem.marvelapp.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import br.com.frameworksystem.marvelapp.R;
import br.com.frameworksystem.marvelapp.model.Event;

/**
 * Created by john.souza on 14/09/2016.
 */
public class EventDetailActivity extends BaseActivity {
    private Event event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventdetail);

        if(getIntent().hasExtra("event"))
        {
            event = (Event)getIntent().getSerializableExtra("event");
        }
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(event.getUrl());

    }


}
