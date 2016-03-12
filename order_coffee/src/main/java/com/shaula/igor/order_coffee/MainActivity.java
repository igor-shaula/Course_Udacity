package com.shaula.igor.order_coffee;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private int numberOfCoffees = 2;
    int pricePerCup = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        numberOfCoffees++;
        displayQuantity(numberOfCoffees);
    }

    public void decrement(View view) {
        numberOfCoffees--;
        displayQuantity(numberOfCoffees);
    }

    private void displayQuantity(int number) {
        TextView tvQuantity = (TextView) findViewById(R.id.tvCount);
        tvQuantity.setText(String.valueOf(number));
    }

    public void submitOrder(View view) {
        displayQuantity(numberOfCoffees);
        TextView tvPrice = (TextView) findViewById(R.id.tvOrderSummary);
        tvPrice.setText(createOrderSummary());
    }

    private int calculatePrice() {
        return numberOfCoffees * pricePerCup;
    }

    public String createOrderSummary() {
        String localizedPrice = NumberFormat.getCurrencyInstance().format(calculatePrice());
        return "Name: Captain Kunal"
                + "\nQuantity: "
                + numberOfCoffees
                + "\nTotal: "
                + localizedPrice;
    }
}