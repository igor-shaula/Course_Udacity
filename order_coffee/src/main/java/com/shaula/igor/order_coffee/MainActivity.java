package com.shaula.igor.order_coffee;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        int numberOfCoffees = 2;
        int price = 5;
        display(numberOfCoffees);
        displayPrice(numberOfCoffees * price);
    }

    private void display(int number) {
        TextView textView = (TextView) findViewById(R.id.tvCount);
        textView.setText(String.valueOf(number));
    }

    public void displayPrice(int number) {
        TextView textView = (TextView) findViewById(R.id.tvPrice);
        textView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}