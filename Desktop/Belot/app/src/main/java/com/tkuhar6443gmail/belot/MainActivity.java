package com.tkuhar6443gmail.belot;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    Bellot bellot=new Bellot();
    infoActivity Info=new infoActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bellot.nPL = (NumberPicker) findViewById(R.id.nPickerL);
        bellot.nPL.setMinValue(0);
        bellot.nPL.setMaxValue(382);
        bellot.nPR = (NumberPicker) findViewById(R.id.nPickerR);
        bellot.nPR.setMinValue(0);
        bellot.nPR.setMaxValue(382);
        bellot.nPR.setOnLongPressUpdateInterval(100);
        bellot.inicijalizacijaListe();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent launchActivity2 = new Intent(MainActivity.this, infoActivity.class);
            startActivity(launchActivity2);
            //setContentView(R.layout.activity_info);
        }
        //resetiranje vrijednosti varijabli, ukoliko se odabere ista opcija iz izbornika
        if(id==R.id.action_reset){
            bellot.setSumaD(0);
            bellot.setSumaL(0);
            TextView temp=(TextView) findViewById(R.id.gem_lijevi);
            temp.setText(""+0);
            temp=(TextView)findViewById(R.id.gem_desni);
            temp.setText(""+0);
            temp=(TextView)findViewById(R.id.suma_lijeva);
            temp.setText(""+0);
            temp=(TextView)findViewById(R.id.suma_desna);
            temp.setText(""+0);
            bellot.clearLists();
            bellot.inicijalizacijaListe();
        }
        return super.onOptionsItemSelected(item);

    }
    //dinamicna promijena izbornika prilikom izvođenja aplikacije
    /*
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(Build.VERSION.SDK_INT > 11) {
            invalidateOptionsMenu();
            if(f1==true)menu.findItem(R.id.action_reset).setVisible(false);
            if(f1==false) menu.findItem(R.id.action_reset).setVisible(true);

        }

        return super.onPrepareOptionsMenu(menu);
    }
    */
    //metoda kojom se mijenjaju vrijednosti rezultata, pritiskom na gumb +, vrijednosti iz number pickera se dodaju ukupnom rezultatu pozivom Display metode koje ujedno isti vraca na ekran;
    //U Listi se nalaze sva predhodna stanja rezultata; radi UNDO operacije; reset briše listu
    public void povecaj(View view){
        if(view.getId()==R.id.button_lijevi){

            bellot.displayL(bellot.nPL.getValue(),(TextView) findViewById(R.id.suma_lijeva),(TextView)findViewById(R.id.gem_lijevi));
            bellot.linkedL.add(bellot.getSumaL());
            bellot.setCounterListeL(bellot.getCounterListeL()+1);
        }
        else {

            bellot.displayR(bellot.nPR.getValue(),(TextView) findViewById(R.id.suma_desna),(TextView)findViewById(R.id.gem_desni));
            bellot.linkedD.add(bellot.getSumaD());
            bellot.setCounterListeD(bellot.getCounterListeD()+1);
        }

    }
    //undo mehanika dohvaća stare vrijednosti rezultata iz liste ukoliko je korisnik unio kojim slučajem krivi iznos
    public void undo(View view){
        TextView suma;
        if(view.getId()==R.id.undo_lijevi) {
            if(bellot.linkedL.size()<=1||bellot.linkedL.isEmpty())return;;
            suma = (TextView) findViewById(R.id.suma_lijeva);
            suma.setText("" + (bellot.linkedL.get(bellot.getCounterListeL()-1)));
            bellot.setSumaL(bellot.linkedL.get(bellot.getCounterListeL()-1));
            bellot.setCounterListeL(bellot.getCounterListeL()-1);
            bellot.linkedL.removeLast();
        }
        else{
            if(bellot.linkedD.size()<=1||bellot.linkedD.isEmpty())return;
            suma = (TextView) findViewById(R.id.suma_desna);
            suma.setText("" + (bellot.linkedD.get(bellot.getCounterListeD()-1)));
            bellot.setSumaD(bellot.linkedD.get(bellot.getCounterListeD()-1));
            bellot.setCounterListeD(bellot.getCounterListeD()-1);
            bellot.linkedD.removeLast();
        }
    }


}
