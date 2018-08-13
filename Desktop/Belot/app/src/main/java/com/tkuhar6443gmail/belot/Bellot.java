package com.tkuhar6443gmail.belot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.LinkedList;

class Bellot extends AppCompatActivity {
    NumberPicker nPL=null;
    NumberPicker nPR=null;
    LinkedList<Integer> linkedL=new LinkedList<Integer>();
    LinkedList<Integer> linkedD=new LinkedList<Integer>();
    private int sumaL=0;
    private int sumaD=0;
    private int counterL=0;
    private int counterD=0;
    private int counterListeL=-1;
    private int counterListeD=-1;

    public int getCounterD() {
        return counterD;
    }

    public void setCounterD(int counterD) {
        this.counterD = counterD;
    }

    public int getCounterListeL() {
        return counterListeL;
    }

    public void setCounterListeL(int counterListeL) {
        this.counterListeL = counterListeL;
    }

    public int getCounterL() {
        return counterL;
    }

    public void setCounterL(int counterL) {
        this.counterL = counterL;
    }

    public int getCounterListeD() {
        return counterListeD;
    }

    public void setCounterListeD(int counterListeD) {
        this.counterListeD = counterListeD;
    }

    public int getSumaD() {
        return sumaD;
    }

    public void setSumaD(int sumaD) {
        this.sumaD = sumaD;
    }

    public int getSumaL() {
        return sumaL;
    }

    public void setSumaL(int sumaL) {
        this.sumaL = sumaL;
    }
    //poziv iz MainActivity.povecaj() mijenja vrijednost trenutnog rezultata isto vrijedi i za DisplayR
    public void displayL(int sum, TextView view, TextView gemL) {
        sumaL+=sum;
        view.setText("" + sumaL);
        ProvjeraIReset(view,gemL);
    }
    public void displayR(int sum, TextView view, TextView gemD) {
        sumaD+=sum;
        view.setText("" + sumaD);
        ProvjeraIReset(view,gemD);
    }
    //metoda brise listu te postavlja brojač na -1
    void clearLists(){
        while(!linkedL.isEmpty())linkedL.removeFirst();
        while(!linkedD.isEmpty())linkedD.removeFirst();
        setCounterListeL(-1);
        setCounterListeD(-1);
    }

    private void ProvjeraIReset(TextView view,TextView gem){
        if(sumaL>999){
            //TextView gemL=(TextView) findViewById(R.id.gem_lijevi);
            counterL++;
            //gem kao u tenisu predstavlja jednu dobivenu igru;
            gem.setText(""+counterL);
            sumaD=0;
            sumaL=0;
            clearLists();
            inicijalizacijaListe();
            //  return;
        }
        if (sumaD > 999) {
            //TextView gemR=(TextView) findViewById(R.id.gem_desni);
            counterD++;
            //gem kao u tenisu predstavlja jednu dobivenu igru;
            gem.setText(""+counterD);
            sumaD=0;
            sumaL=0;
            clearLists();
            inicijalizacijaListe();
            //return;
        }
    }
    //metoda za postavljanje prve vrjednosti liste na 0; ujedno se brojač elemenata uvečava za broj trenutnih elementata + 1; ista tehnika kao i u metodi undo()
    void inicijalizacijaListe(){
        linkedL.add(0);
        setCounterListeL(getCounterListeL()+1);
        linkedD.add(0);
        setCounterListeD(getCounterListeD()+1);
    }
}
