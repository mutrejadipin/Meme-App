package com.example.mymeme;

import android.annotation.SuppressLint;
import android.content.Intent;
//import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
//import android.widget.ProgressBar;
import android.widget.Toast;

//import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.DataSource;
//import com.bumptech.glide.load.engine.GlideException;
//import com.bumptech.glide.request.RequestListener;
//import com.bumptech.glide.request.target.Target;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
ImageView imageMeme;
Button sharebutton, nextbutton;
//ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageMeme=findViewById(R.id.imageMeme);
        sharebutton=findViewById(R.id.sharebutton);
        nextbutton=findViewById(R.id.nextbutton);
       //progressBar=findViewById(R.id.progressBar);


    }
    @SuppressLint("CheckResult")
    public void loadMeme()
    {
        //progressBar.setVisibility(ProgressBar.VISIBLE);
        String url="https://meme-api.herokuapp.com/gimme";
        //String url="https://finnhub.io/register";
        //String url="c43ag2qad3iaavqomf30";
        JsonObjectRequest jsonObjectRequest;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                String url1 = response.getString("url");
                Glide.with(MainActivity.this).load(url1).into(imageMeme);
                /*Glide.with(MainActivity.this).load(url1).listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.INVISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.INVISIBLE);
                        return false;
                    }
                });*/
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(MainActivity.this,"error occurred",Toast.LENGTH_SHORT).show());
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    public void sharefun(View view) {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"this is my app");
        startActivity(intent);
    }

    public void nextfun(View view) {
        loadMeme();
    }

}