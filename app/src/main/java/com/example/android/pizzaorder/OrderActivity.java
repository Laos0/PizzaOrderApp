package com.example.android.pizzaorder;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public Pizza orderPizza = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        // THIS IS FOR THE SPINNER----------------------------------------------------------------
        final Spinner spinner = (Spinner) findViewById(R.id.pizzaSize);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pizzaSizes, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        //----------------------------------------------------------------------------------------


        //// INTENT coding
       /*
       -------------------- How to do Intent -----------------------------

       Intent intent = new Intent(OrderActivity.this, ReceiptActivity.class);
       intent.putExtra("text", receiptText);
       start
        */

        final Intent intent = new Intent(OrderActivity.this, ReceiptActivity.class);



        // ------------------ BUTTONS IMPLMENTATION ----------------------------------------
        final Button clearBtn = findViewById(R.id.clearBtn);
        final Button submitBtn = findViewById(R.id.submitBtn);
        final RadioGroup radioGroup = findViewById(R.id.radioGroup);
        final CheckBox checkBox1 = findViewById(R.id.sausage);
        final CheckBox checkBox2 = findViewById(R.id.pepperoni);
        final CheckBox checkBox3 = findViewById(R.id.bellPepper);
        final CheckBox checkBox4 = findViewById(R.id.onions);
        final CheckBox checkBox5 = findViewById(R.id.tomatoes);
        final EditText userName = findViewById(R.id.nameInput);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Data to store in Intent and display them
                String customerName = userName.getText().toString();
                String pizzaSize = spinner.getSelectedItem().toString();

                // get pizza crust
                int pizzaCrustId = radioGroup.getCheckedRadioButtonId();
                RadioButton rBtn = (RadioButton) findViewById(pizzaCrustId);
                String pizzaCrust = rBtn.getText().toString();


                // get all the selected toppings
                ArrayList<String> toppings = new ArrayList<String>();
                if(checkBox1.isChecked()){
                    toppings.add(checkBox1.getText().toString());
                }
                if(checkBox2.isChecked()){
                    toppings.add(checkBox2.getText().toString());
                }
                if(checkBox3.isChecked()){
                    toppings.add(checkBox3.getText().toString());
                }
                if(checkBox4.isChecked()){
                    toppings.add(checkBox4.getText().toString());
                }
                if(checkBox5.isChecked()){
                    toppings.add(checkBox5.getText().toString());
                }

                int numOfTop;
                if(toppings.size() == 1){
                    numOfTop = 1;
                }else if(toppings.size() == 2){
                    numOfTop = 2;
                }else if(toppings.size() == 3){
                    numOfTop = 3;
                }else if(toppings.size() == 4){
                    numOfTop = 4;
                }else{
                    numOfTop = 5;
                }

                // The whole calculation of the pizza

                // Creating a pizza instance
                Pizza orderPizza = new Pizza(customerName, pizzaSize , pizzaCrust, toppings);

                // String pizzaCrust = radioGroup.getChildAt(pizzaCrustId);
                // Store the name of the user
                intent.putExtra("customerName", orderPizza.name);
                // Store the pizza Size
                intent.putExtra("pizzaSize", orderPizza.pizzaSize);
                // Store the selected crust from the radioGroup
                intent.putExtra("pizzaCrust", orderPizza.pizzaCrust);
                // Store the selected toppings
                intent.putExtra("pizzaToppings", orderPizza.getToppings());
                //Store the amount of toppings selected
                intent.putExtra("totalTop",numOfTop);
                // Store the total selected Price from all selections
                intent.putExtra("totalPrice", orderPizza.getPrice());
                startActivityForResult(intent, 1);
            }
        });


        clearBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                radioGroup.clearCheck();

                if(checkBox1.isChecked()){
                    checkBox1.setChecked(false);
                }

                if(checkBox2.isChecked()){
                    checkBox2.setChecked(false);
                }

                if(checkBox3.isChecked()){
                    checkBox3.setChecked(false);
                }

                if(checkBox4.isChecked()){
                    checkBox4.setChecked(false);
                }

                if(checkBox5.isChecked()){
                    checkBox5.setChecked(false);
                }

                userName.setText("");
                spinner.setSelection(0);
            }
        });

        //-------------------------------------------------------------------------------------

        //------------------------------ CHECK BOXES -----------------------------------------

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
