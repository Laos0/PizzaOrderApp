package com.example.android.pizzaorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ReceiptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
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


        // When clicking on cancel button, return to OrderActivity
        final Intent intent = new Intent(ReceiptActivity.this, OrderActivity.class);

        final Button cancelBtn = findViewById(R.id.cancelBtn);
        final Button confirmBtn = findViewById(R.id.confirmBtn);

        final TextView customerName = findViewById(R.id.userName);
        final TextView sizePrice = findViewById(R.id.pizzaSizePrice);
        final TextView testText = findViewById(R.id.testText);



        // Fetching the stored data from MainActivity
        String personName = getIntent().getExtras().getString("customerName");
        String sizeOfPizza = getIntent().getExtras().getString("pizzaSize");
        String crustOfPizza = getIntent().getExtras().getString("pizzaCrust");
        String pizzaToppings = getIntent().getExtras().getString("pizzaToppings");
        int totalTops = getIntent().getExtras().getInt("totalTop");
        double totalPrice = getIntent().getExtras().getDouble("totalPrice");

        // Displaying the cost of the crust, and toppings
        String crustPrice;
        if(crustOfPizza.equals("Hand-Tossed")){
            crustPrice = "-- $2.00";
        }else if(crustOfPizza.equals("Thin Crust")){
            crustPrice = "-- $1.00";
        }else{
            // if crustOfPizza is Deep-Dish
            crustPrice = "-- $3.00";
        }

        String toppingsPrice = "";
        if(totalTops == 1){
            toppingsPrice = "-- $1.25";
        }else if(totalTops == 2){
            toppingsPrice = "-- $2.50";
        }else if(totalTops == 3){
            toppingsPrice = "-- $3.75";
        }else if(totalTops == 4){
            toppingsPrice = "-- $5.00";
        }else if(totalTops == 5){
            toppingsPrice = "-- $6.25";
        }


        String receipt = "Name: " + personName + "\n " + "Pizza Size: " + sizeOfPizza + "\n " + "Pizza Crust: " + crustOfPizza + " " + crustPrice +
                "\n " + "Toppings: " + pizzaToppings + " " + toppingsPrice + "\n" + "Total: " + "$" + totalPrice;

        testText.setText(receipt);
        //customerName.setText(personName);
        //sizePrice.setText(sizeOfPizza);
        // Return to MainActivity with all the selected options
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(RESULT_OK);
                finish();
            }
        });

        // Return to MainActivity with all the default options
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });



    }

}
