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

public class HowToUseActivity extends AppCompatActivity {


    private ImageView ivGTDCononical;
    private TextView tvHowToUseText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_use);

        //getSupportActionBar().setHomeButtonEnabled(true);
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

    private void init() {

        tvHowToUseText = (TextView) findViewById(R.id.tvHowToUseText);
        ivGTDCononical = (ImageView) findViewById(R.id.ivGTDCononical);

        String text = "At first add your task from the Collection tab.<br>" +
                "<strong><font color=\"#D32F2F\">In Collection Tab</font></strong><br><br>" +
                "First Write your task and suggesting solution for this task.<br>" +
                "<strong><font color=\"#D32F2F\">Buttons</font></strong><br><br>" +
                "<font color=\"#D32F2F\">1. ADD:</font> This is add your task and suggesting solution on ListView.<br>But can not insert it database for review.<br>" +
                "<font color=\"#D32F2F\">2. CLEAR:</font> This is clear your Edittext data.<br>But Listview added data can not clear.<br>" +
                "<font color=\"#D32F2F\">3. SAVE:</font> This is insert your given all data in database.<br><strong><font color=\"#D32F2F\">If you can not save data you can not show it on the Organize tab.</font></strong><br>" +
                "<font color=\"#D32F2F\">4. CLEARALL:</font> This is clear all Edittext,List data.<br>But can not delete database data.<br>" +
                "<strong><font color=\"#D32F2F\">Long Press:</font> </strong> if you Long press on listview that data are delete in Collection tab.<br>But in Organize tab it is complete the task so hide it.<br><br>" +
                "<strong><font color=\"#D32F2F\">In Organize Tab</font></strong><br><br>" +
                "<strong><font color=\"#D32F2F\">Long Press:</font></storng> if your task is complete then you can long press on that task. After long press that is hide from the list.<br>";

        tvHowToUseText.setText(Html.fromHtml(text));


    }


}
