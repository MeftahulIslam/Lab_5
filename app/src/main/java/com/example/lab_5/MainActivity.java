package com.example.lab_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayList<String> currencies = new ArrayList<>();
    static ArrayAdapter adapter;
    ListView currencyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currencyList = (ListView) findViewById(R.id.currencyList);
        Button generateList = findViewById(R.id.btnGenerateList);
        TextView tv = findViewById(R.id.currencyTitle);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, currencies);
        currencyList.setAdapter(adapter);
    }

    public void btnGenerateList(View view) {
        new DataLoader(){
            @Override
            protected void onPostExecute(ArrayList<String> strings) {
                super.onPostExecute(strings);
                int size = strings.size();
                currencies = new ArrayList<>(strings);
                for(int i = 0; i < size; i++){
                    adapter.add(currencies.get(i));
                    adapter.notifyDataSetChanged();
                }
            }
        }.execute("USD");
    }

}