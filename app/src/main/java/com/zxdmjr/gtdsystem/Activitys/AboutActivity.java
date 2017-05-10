package com.zxdmjr.gtdsystem.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxdmjr.gtdsystem.MainActivity;
import com.zxdmjr.gtdsystem.R;

public class AboutActivity extends AppCompatActivity {

    private TextView tvDeveloperEmail;
    private TextView tvDeveloperFb;
    private ImageView ivDeveloper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){

            case android.R.id.home:

                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                finish();

                break;
        }

        return (super.onOptionsItemSelected(item));

    }

    private void init(){

        tvDeveloperEmail = (TextView) findViewById(R.id.tvDeveloperEmail);
        tvDeveloperFb = (TextView) findViewById(R.id.tvDeveloperFb);
        ivDeveloper = (ImageView) findViewById(R.id.ivDeveloper);

        String email = "<font color=\"#D32F2F\"Email:</font> zxdmjr@gmail.com<br>jewelms007@gmail.com<br><br>";

        String fb = "<font color=\"#303F9F\"Facebook:</font> https://www.facebook.com/dutchman.bd<br>";

        tvDeveloperEmail.setText(Html.fromHtml(email));

        tvDeveloperFb.setText(Html.fromHtml(fb));

    }
}
