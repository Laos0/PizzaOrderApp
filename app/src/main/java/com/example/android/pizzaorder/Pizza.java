package com.example.android.pizzaorder;

import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class Pizza {

    public String name;
    public String pizzaSize;
    public String pizzaCrust;
    public ArrayList<String> toppings;

    public Pizza(String name, String pizzaSize, String pizzaCrust, ArrayList<String> toppings){
        this.name = name;
        this.pizzaSize = pizzaSize;
        this.pizzaCrust = pizzaCrust;
        this.toppings = toppings;
    }

    public String getToppings(){
        String finalToppings = "";
        for(int i = 0; i < this.toppings.size(); i++){
            finalToppings += this.toppings.get(i) + ", ";
        }
        return finalToppings;
    }

    public double getPrice(){

        double totalPrice = 0.0;
        double finalPrice = 0.0;
        if(pizzaSize.equals("10 Inch Pizza -- $11.79")){
            totalPrice += 11.79;
        }else if(pizzaSize.equals("12 Inch Pizza -- $12.79")){
            totalPrice += 12.79;
        }else if(pizzaSize.equals("14 Inch Pizza -- $13.79")){
            totalPrice += 13.79;
        }else if(pizzaSize.equals("16 Inch Pizza -- $14.79")){
            totalPrice += 14.79;
        }

        if(pizzaCrust.equals("Hand-Tossed")){
            totalPrice += 2.00;
        }else if(pizzaCrust.equals("Deep-Dish")){
            totalPrice += 3.00;
        }else if(pizzaCrust.equals("Thin Crust")){
            totalPrice += 1.00;
        }

        for(int i = 0; i < toppings.size(); i++){
            totalPrice += 1.25;
        }


        totalPrice = totalPrice + (totalPrice * .055);
        String priceStr = myFormat(totalPrice);
        finalPrice = parseDouble(priceStr);
        return finalPrice;
    }

    private double parseDouble(String priceStr) {
       return Double.parseDouble(priceStr);
    }

    public static String myFormat(double number) {
        DecimalFormat df = new DecimalFormat("#.00");
        String angleFormated = df.format(number);
        return angleFormated;
    }
}
