package com.tkuhar6443gmail.belot;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class infoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

    }
    //metoda kojom se kreira intent kojim se poziva sistemska mail aplikacija
    public void saljiMail(View view){
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:tkuhar6443@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Bellot Aplikacija, upit!");
        //provjera dali postoji sistemska aplikacija koja podrzava ovaj intent (e-mail)
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }
    //metoda kreira intent kojim se poziva MainActivity
    public void goBack(View view){
        Intent launchActivity1 = new Intent(infoActivity.this, MainActivity.class);
        startActivity(launchActivity1);
    }
}
