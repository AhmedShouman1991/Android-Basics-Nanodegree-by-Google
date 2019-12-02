package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.os.Bundle;
//import android.support.v4.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private int quantity = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","abc@gmail.com", null));
        //intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.msg_subject));

        EditText nameText = findViewById(R.id.Name);
        String name = nameText.getText().toString();
        CheckBox whippedCreamBox = findViewById(R.id.check1);
        boolean hasWhipped = whippedCreamBox.isChecked();
        CheckBox chocBox = findViewById(R.id.check2);
        boolean hasChoc = chocBox.isChecked();
        int price = calculatePrice(hasChoc, hasWhipped);

        String message = orderSummary(name, price, hasWhipped, hasChoc );
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(intent, "Send Email"));
        //displayMessage(message);
    }

    /**
     *
     * @param price unite price
     * @return order for one person summary
     */
    private String orderSummary(String name, int price, boolean hasCream, boolean hasChocolate) {
        String msg =  getString(R.string.customerName, name);
        if (hasCream) {
            msg = msg + "\n" + getString(R.string.whippedCreemAdd);
        }
        if (hasChocolate) {
            msg = msg + "\n" + getString(R.string.chocoAdd);
        }
        msg = msg +
                "\n" + getString(R.string.quantity) + " " + quantity +
                "\n" + getString(R.string.price) +" $" + price +
                "\n" + getString(R.string.thankyou);

        return msg;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * Calculates the price of the order.
     *
     *
     */
    private int calculatePrice(boolean hasChoc, boolean hasWhipped) {
        int price = 5 * quantity;
        if (hasChoc) {
            price += 2 * quantity;
        }
        if (hasWhipped) {
            price += quantity;
        }
        return price;
    }

    /**
     * This method displays the given price on the screen.
     */
//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }

    public void increment(View view) {
        quantity++;
        if (quantity > 100) {
            Toast.makeText(this, "You Cann't Order More Then 100 Cups", Toast.LENGTH_SHORT).show();
            quantity = 100;
        }
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        quantity--;
        if (quantity < 1) {
            Toast.makeText(this, "You Cann't Order Less Then 1 Cup", Toast.LENGTH_SHORT).show();
            quantity = 1;
        };
        displayQuantity(quantity);
    }



    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}