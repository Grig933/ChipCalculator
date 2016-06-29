package com.example.chipcalculator;
//Вычисление количества чипов на пластине

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private static final NumberFormat chipFormat = NumberFormat.getIntegerInstance();
    private double a_chip = 10.0; //Длина чипа
    private double b_chip = 10.0; //Ширина чипа
    private double d_waf = 4.0; //Диаметр структуры
    private double pi = 3.1416; //Число
    private double n_chip; //Количество чипов
    private double wafer_prise = 0; //стоимость структуры
    private double chip_prise = 0; //стоимость 1k chip

    TextView a_TextView;
    TextView b_TextView;
    TextView amountTextView;
    TextView wafer_ackount;
    CheckBox ml_mm;
    Button button1;
    Button button2;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        amountTextView = (TextView) findViewById(R.id.amountTextView);
        wafer_ackount =  (TextView) findViewById(R.id.wafer_ackount);
        a_TextView = (TextView) findViewById(R.id.a_textView);
        b_TextView = (TextView) findViewById(R.id.b_textView);
        ml_mm = (CheckBox) findViewById(R.id.ml_mm);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (ml_mm.isChecked()){
                   chipAcuont_mm();
                   wafer ();
               }
                else {
                   chipAcuont();
                   wafer();
               }

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountTextView.setText("Прошу");
                ml_mmSise();
                wafer_ackount.setText("");
            }
        });

        //Назначения для EditText
        EditText a_editText = (EditText) findViewById(R.id.a_editText);
        EditText b_editText = (EditText) findViewById(R.id.b_editText);
        EditText d_editText = (EditText) findViewById(R.id.d_editText);
        EditText chip_prise = (EditText) findViewById(R.id.chip_prise);

        amountTextView.setText("Введи данные");

        a_editText.addTextChangedListener(a_editTextWatcher);
        b_editText.addTextChangedListener(b_editTextWatcher);
        d_editText.addTextChangedListener(d_editTextWatcher);
        chip_prise.addTextChangedListener(chip_priseWatcher);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //Конец onCreate
    private void ml_mmSise(){
        if (ml_mm.isChecked()){
            a_TextView.setText("Длина чипа, mkm");
            b_TextView.setText("Ширина чипа, mkm");

        }
        else {
            a_TextView.setText("Длина чипа, mil");
            b_TextView.setText("Ширина чипа, mil");
        }
    }
    private void chipAcuont() {
        double s_chip = (a_chip + 1.5748) * (b_chip + 1.5748);
        double s_wafer = (pi * (d_waf - 0.0394) * (d_waf - 0.0394)) / 4;
        n_chip = (s_wafer / s_chip) * 1000000 * 0.98;
        amountTextView.setText(chipFormat.format(n_chip));

    }
    private void chipAcuont_mm(){
        double s_chip = (a_chip + 40) * (b_chip + 40);
        double s_wafer = (pi * (d_waf - 0.0394) * (d_waf - 0.0394)) / 4;
        n_chip = ((s_wafer*25.4*25.4) / (s_chip/1000000));
        amountTextView.setText(chipFormat.format(n_chip));
    }
    // конец расчета количества чипов
    private  void wafer (){
        wafer_prise = (chip_prise * n_chip/1000);
        wafer_ackount.setText(chipFormat.format(wafer_prise));
    }

      //конец расчета стоимости структуры

    private TextWatcher a_editTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                a_chip = Double.parseDouble(s.toString());

            } catch (NumberFormatException e) {
                a_chip = 55;
            }

        }


        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    //Конец ввода  a
    private TextWatcher b_editTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                b_chip = Double.parseDouble(s.toString());

            } catch (NumberFormatException e) {
                b_chip = 55;
            }
        }


        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    //Конец ввода  b
    private TextWatcher d_editTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try {
                d_waf = Double.parseDouble(s.toString());

            } catch (NumberFormatException e) {
                d_waf = 4;
            }

        }
        @Override
        public void afterTextChanged(Editable s) {

            }

    };
    //Конец ввода  d
    private TextWatcher chip_priseWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                chip_prise = Double.parseDouble(s.toString());
            } catch (NumberFormatException e){
                wafer_prise = 100;
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.chipcalculator/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.chipcalculator/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
