package com.shaula.igor.order_coffee;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends Activity {

    private int numberOfCoffees = 2;

    // for localization \
    private String toastMore;
    private String toastLess;
    private String toastNo;
    private String coffeeFor;
    private String clientsName;
    private String hasCream;
    private String hasChocolate;
    private String quantity;
    private String total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toastMore = getString(R.string.toastMore);
        toastLess = getString(R.string.toastLess);
        toastNo = getString(R.string.toastNo);
        coffeeFor = getString(R.string.coffeeFor);
        clientsName = getString(R.string.clientsName);
        hasCream = getString(R.string.hasCream);
        hasChocolate = getString(R.string.hasChocolate);
        quantity = getString(R.string.quantity);
        total = getString(R.string.total);
//        total = getString(R.string.total, NumberFormat.getCurrencyInstance().format(price));
    }

    public void incrementByOne(View view) {
        if (numberOfCoffees < 100)
            numberOfCoffees++;
        else
            Toast.makeText(this, toastMore, Toast.LENGTH_SHORT).show();
        displayQuantity(numberOfCoffees);
    }

    public void incrementByTen(View view) {
        if (numberOfCoffees < 90)
            numberOfCoffees += 10;
        else
            Toast.makeText(this, toastMore, Toast.LENGTH_SHORT).show();
        displayQuantity(numberOfCoffees);
    }

    public void decrementByOne(View view) {
        if (numberOfCoffees > 1)
            numberOfCoffees--;
        else
            Toast.makeText(this, toastLess, Toast.LENGTH_SHORT).show();
        displayQuantity(numberOfCoffees);
    }

    public void decrementByTen(View view) {
        if (numberOfCoffees > 10)
            numberOfCoffees -= 10;
        else
            Toast.makeText(this, toastLess, Toast.LENGTH_SHORT).show();
        displayQuantity(numberOfCoffees);
    }

    private void displayQuantity(int number) {
        TextView tvQuantity = (TextView) findViewById(R.id.tvCount);
        tvQuantity.setText(String.valueOf(number));
    }

    public void submitOrder(View view) {

        EditText etName = (EditText) findViewById(R.id.etName);
        String name = etName.getText().toString();
/*
        TextView tvOrderSummary = (TextView) findViewById(R.id.tvOrderSummary);
        tvOrderSummary.setText(createOrderSummary(name));
*/
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
//                .setType("text/plain");
//                .setType("*/*")
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, coffeeFor + name);
        emailIntent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(name));

        // this line prevents from crash if nobody can handle this intent \
        if (emailIntent.resolveActivity(getPackageManager()) != null)
            startActivity(emailIntent);
        else Toast.makeText(this, toastNo, Toast.LENGTH_SHORT).show();
    }

    /**
     * Create summary string of the order.
     *
     * @param name of the order
     * @return text summary
     */
    public String createOrderSummary(String name) {

        CheckBox cbHasCream = (CheckBox) findViewById(R.id.cbHasCream);
        CheckBox cbHasChocolate = (CheckBox) findViewById(R.id.cbHasChocolate);

        boolean hasCream = cbHasCream.isChecked();
        boolean hasChocolate = cbHasChocolate.isChecked();

        String localizedPrice = NumberFormat
                .getCurrencyInstance()
                .format(calculatePrice(hasCream, hasChocolate));
        return ""
                + clientsName + ": " + name + "\n"
                + this.hasCream + ": " + hasCream + "\n"
                + this.hasChocolate + ": " + hasChocolate + "\n"
                + quantity + numberOfCoffees + "\n"
                + total + localizedPrice
                + "";
    }

    private int calculatePrice(boolean hasCream, boolean hasChocolate) {

        int pricePerCup = 5;
        if (hasCream) pricePerCup += 1;
        if (hasChocolate) pricePerCup += 2;

        return numberOfCoffees * pricePerCup;
    }
}