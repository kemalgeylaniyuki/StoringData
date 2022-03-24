package com.kemalgeylaniyuki.storingdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences; // mini veri saklamak

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextNumber);
        textView = findViewById(R.id.textView);

        // MODE PRIVATE : Sadece benim uygulamamda ulaşılsın.
        sharedPreferences = this.getSharedPreferences("com.kemalgeylaniyuki.storingdata", Context.MODE_PRIVATE);

        // veriyi çekmek
        int storedAge = sharedPreferences.getInt("storedAge", 0);

        if (storedAge == 0){
            textView.setText("Your Age : ");
        }
        else {
            textView.setText("Your Age : " + storedAge);
        }



    }

    public void save(View view){ // onClick ismi save yaptık. // görünüm tarafından çağrılacağı için View view kullanıyoruz.

        if (!editText.getText().toString().matches("")){
            int userAge = Integer.parseInt(editText.getText().toString()); // string i tamsayıya çeviriyor.
            textView.setText("Your Age : " + userAge); // textview e kullanıcıdan aldığımız değeri yazdırıyoruz.

            // veriyi depolamak
            sharedPreferences.edit().putInt("storedAge", userAge).apply(); // uygulama kapansada veri içinde kalacak.
        }

    }

    public void delete(View view){

        int storedData = sharedPreferences.getInt("storedAge", 0);

        if (storedData != 0){
            sharedPreferences.edit().remove("storedAge").apply(); // key deki kaıylı veriyi silmek için remove kullanırız.
            textView.setText("Your Age : ");
        }


    }


}