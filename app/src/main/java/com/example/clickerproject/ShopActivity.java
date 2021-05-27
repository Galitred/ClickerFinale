package com.example.clickerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.clickerproject.MainActivity.codeLinesAdd;

public class ShopActivity extends AppCompatActivity {

    float localeBalance;
    float fonSum;
    float defaultPrice;

    float firstPrice = 10.0f;
    float secondPrice = 30.0f;
    float thirdPrice = 150.0f;
    float fourthPrice = 1000.0f;

    float firstClickPrice = 150.0f;
    float secondClickPrice = 500.0f;
    float thirdClickPrice = 1000.0f;
    float fourthClickPrice = 2500.0f;


    float value;

    boolean threadActive = true;

    int counters[] = new int[3];

    int firstCounter, secondCounter, thirdCounter, fourthCounter = 0;
    int firstClickCounter, secondClickCounter, thirdClickCounter, fourthClickCounter = 0;

    String newPrice;

    TextView mCurrentbalance;

    Button back, firstUpgrade, secondUpgrade, thirdUpgrade, fourthUpgrade, firstClickUpgrade, secondClickUpgrade, thirdClickUpgrade, fourthClickUpgrade;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        sharedPref = getPreferences(MODE_PRIVATE);

        Thread CurrentBalance = new CurrentBalance();
        CurrentBalance.start();




        mCurrentbalance = findViewById(R.id.currentBalance);
        mCurrentbalance.setText(String.format("Current Balance: " + "%.2f", MainActivity.coinBalance));

        firstUpgrade = findViewById(R.id.firstUpgrade);
        secondUpgrade = findViewById(R.id.secondUpgrade);
        thirdUpgrade = findViewById(R.id.thirdUpgrade);
        fourthUpgrade = findViewById(R.id.fourthUpgrade);

        firstClickUpgrade = findViewById(R.id.firstClickUpgrade);
        secondClickUpgrade = findViewById(R.id.secondClickUpgrade);
        thirdClickUpgrade = findViewById(R.id.thirdClickUpgrade);
        fourthClickUpgrade = findViewById(R.id.fourthClickUpgrade);


        back = findViewById(R.id.backFromShop);
        loadButton();
        Log.d("FIRST_TEST", String.valueOf(firstPrice));
        priceChecker();
        Log.d("SECOND_TEST", String.valueOf(firstPrice));
        mainShop();
        Log.d("THIRD_TEST", String.valueOf(firstPrice));

        firstUpgrade.setText(String.format("Speed + 0.3. Price: " + "%.2f", firstPrice));
        secondUpgrade.setText(String.format("Speed + 1. Price: " + "%.2f", secondPrice));
        thirdUpgrade.setText(String.format("Speed + 10. Price: " + "%.2f", thirdPrice));
        fourthUpgrade.setText(String.format("Speed + 100. Price: " + "%.2f", fourthPrice));

        firstClickUpgrade.setText(String.format("Click + 1. Price: " + "%.2f", firstClickPrice));
        secondClickUpgrade.setText(String.format("Click + 5. Price: " + "%.2f", secondClickPrice));
        thirdClickUpgrade.setText(String.format("Click + 20. Price: " + "%.2f", thirdClickPrice));
        fourthClickUpgrade.setText(String.format("Click + 50. Price: " + "%.2f", fourthClickPrice));

    }

    class CurrentBalance extends Thread {
        @Override
        public void run() {
            super.run();
            while(threadActive) {
                try {
                    CurrentBalance.sleep(1000);
                    mCurrentbalance.setText(String.format("Current Balance: " + "%.2f", MainActivity.coinBalance));


                } catch (InterruptedException e) {
                }

            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveButton();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveButton();
        threadActive = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadButton();
        priceChecker();
    }



    void mainShop(){
        firstUpgrade.setOnClickListener(v -> {
            defaultPrice = 10;
            value = 0.3f;
            Log.d("BackTest", String.valueOf(firstPrice));
            if(MainActivity.coinBalance >= firstPrice){
                MainActivity.coinBalance -= firstPrice;
                MainActivity.fonSum += value;
                firstCounter++;
                firstPrice = (float) (defaultPrice * Math.pow(1.15, firstCounter));
                firstUpgrade.setText(String.format("Speed + 0.3. Price: " + "%.2f", firstPrice));

            } else {
                firstUpgrade.setText(String.format("Speed + 0.3. Price: " + "%.2f", firstPrice));
                Toast alertToast = Toast.makeText(getApplicationContext(), "Недостаточно средств!", Toast.LENGTH_SHORT);
                alertToast.show();
            }
        });

        secondUpgrade.setOnClickListener(v -> {
            defaultPrice = 30;
            value = 1.0f;
            Log.d("BackTest", "CLICKED");
            if(MainActivity.coinBalance >= secondPrice){
                MainActivity.coinBalance -= secondPrice;
                MainActivity.fonSum += value;
                secondCounter++;
                secondPrice = (float) (defaultPrice * Math.pow(1.15, secondCounter));
                secondUpgrade.setText(String.format("Speed + 1. Price: " + "%.2f", secondPrice));


            } else {
                secondUpgrade.setText(String.format("Speed + 1. Price: " + "%.2f", secondPrice));
                Toast alertToast = Toast.makeText(getApplicationContext(), "Недостаточно средств!", Toast.LENGTH_SHORT);
                alertToast.show();
            }
        });

        thirdUpgrade.setOnClickListener(v -> {
            defaultPrice = 150;
            value = 10.0f;
            Log.d("BackTest", "CLICKED");
            if(MainActivity.coinBalance >= thirdPrice){
                MainActivity.coinBalance -= thirdPrice;
                MainActivity.fonSum += value;
                thirdCounter++;
                thirdPrice = (float) (defaultPrice * Math.pow(1.15, thirdCounter));
                thirdUpgrade.setText(String.format("Speed + 10. Price: " + "%.2f", thirdPrice));


            } else {
                thirdUpgrade.setText(String.format("Speed + 10. Price: " + "%.2f", thirdPrice));
                Toast alertToast = Toast.makeText(getApplicationContext(), "Недостаточно средств!", Toast.LENGTH_SHORT);
                alertToast.show();
            }
        });

        fourthUpgrade.setOnClickListener(v -> {
            defaultPrice = 1000;
            value = 100.0f;
            Log.d("BackTest", "CLICKED");
            if(MainActivity.coinBalance >= fourthPrice){
                MainActivity.coinBalance -= fourthPrice;
                MainActivity.fonSum += value;
                fourthCounter++;
                fourthPrice = (float) (defaultPrice * Math.pow(1.15, fourthCounter));
                fourthUpgrade.setText(String.format("Speed + 100. Price: " + "%.2f", fourthPrice));


            } else {
                fourthUpgrade.setText(String.format("Speed + 100. Price: " + "%.2f", fourthPrice));
                Toast alertToast = Toast.makeText(getApplicationContext(), "Недостаточно средств!", Toast.LENGTH_SHORT);
                alertToast.show();
            }
        });

        firstClickUpgrade.setOnClickListener(v -> {
            defaultPrice = 150;
            value = 1.0f;
            Log.d("BackTest", "CLICKED");
            if(MainActivity.coinBalance >= firstClickPrice){
                MainActivity.coinBalance -= firstClickPrice;
                codeLinesAdd += value;
                MainActivity.buttonAdd.setText("+" + (int)codeLinesAdd);
                firstClickCounter++;
                firstClickPrice = (float) (defaultPrice * Math.pow(1.15, firstClickCounter));
                firstClickUpgrade.setText(String.format("Click + 1. Price: " + "%.2f", firstClickPrice));


            } else {
                firstClickUpgrade.setText(String.format("Click + 1. Price: " + "%.2f", firstClickPrice));
                Toast alertToast = Toast.makeText(getApplicationContext(), "Недостаточно средств!", Toast.LENGTH_SHORT);
                alertToast.show();
            }
        });

        secondClickUpgrade.setOnClickListener(v -> {
            defaultPrice = 500;
            value = 5.0f;
            Log.d("BackTest", "CLICKED");
            if(MainActivity.coinBalance >= secondClickPrice){
                MainActivity.coinBalance -= secondClickPrice;
                codeLinesAdd += value;
                MainActivity.buttonAdd.setText("+" + (int)codeLinesAdd);
                secondClickCounter++;
                secondClickPrice = (float) (defaultPrice * Math.pow(1.15, secondClickCounter));
                secondClickUpgrade.setText(String.format("Click + 5. Price: " + "%.2f", secondClickPrice));


            } else {
                secondClickUpgrade.setText(String.format("Click + 5. Price: " + "%.2f", secondClickPrice));
                Toast alertToast = Toast.makeText(getApplicationContext(), "Недостаточно средств!", Toast.LENGTH_SHORT);
                alertToast.show();
            }
        });

        thirdClickUpgrade.setOnClickListener(v -> {
            defaultPrice = 1000;
            value = 20.0f;
            Log.d("BackTest", "CLICKED");
            if(MainActivity.coinBalance >= thirdClickPrice){
                MainActivity.coinBalance -= thirdClickPrice;
                codeLinesAdd += value;
                MainActivity.buttonAdd.setText("+" + (int)codeLinesAdd);
                thirdClickCounter++;
                thirdClickPrice = (float) (defaultPrice * Math.pow(1.15, thirdClickCounter));
                thirdClickUpgrade.setText(String.format("Click + 20. Price: " + "%.2f", thirdClickPrice));


            } else {
                thirdClickUpgrade.setText(String.format("Click + 20. Price: " + "%.2f", thirdClickPrice));
                Toast alertToast = Toast.makeText(getApplicationContext(), "Недостаточно средств!", Toast.LENGTH_SHORT);
                alertToast.show();
            }
        });

        fourthClickUpgrade.setOnClickListener(v -> {
            defaultPrice = 2500;
            value = 50.0f;
            Log.d("BackTest", "CLICKED");
            if(MainActivity.coinBalance >= fourthClickPrice){
                MainActivity.coinBalance -= fourthClickPrice;
                codeLinesAdd += value;
                MainActivity.buttonAdd.setText("+" + (int)codeLinesAdd);
                fourthClickCounter++;
                fourthClickPrice = (float) (defaultPrice * Math.pow(1.15, fourthClickCounter));
                fourthClickUpgrade.setText(String.format("Click + 50. Price: " + "%.2f", fourthClickPrice));


            } else {
                fourthClickUpgrade.setText(String.format("Click + 50. Price: " + "%.2f", fourthClickPrice));
                Toast alertToast = Toast.makeText(getApplicationContext(), "Недостаточно средств!", Toast.LENGTH_SHORT);
                alertToast.show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    void saveButton() {
        SharedPreferences.Editor ed = sharedPref.edit();
        ed.putFloat("firstPrice", firstPrice);
        ed.putFloat("secondPrice", secondPrice);
        ed.putFloat("thirdPrice", thirdPrice);
        ed.putFloat("fourthPrice", fourthPrice);

        ed.putFloat("firstClickPrice", firstClickPrice);
        ed.putFloat("secondClickPrice", secondClickPrice);
        ed.putFloat("thirdClickPrice", thirdClickPrice);
        ed.putFloat("fourthClickPrice", fourthClickPrice);


        ed.putInt("firstCounter", firstCounter);
        ed.putInt("secondCounter", secondCounter);
        ed.putInt("thirdCounter", thirdCounter);
        ed.putInt("fourthCounter", fourthCounter);

        ed.putInt("firstClickCounter", firstClickCounter);
        ed.putInt("secondClickCounter", secondClickCounter);
        ed.putInt("thirdClickCounter", thirdClickCounter);
        ed.putInt("fourthClickCounter", fourthClickCounter);
        ed.apply();
    }

    void loadButton(){
        firstPrice = sharedPref.getFloat("firstPrice", 0f);
        secondPrice = sharedPref.getFloat("secondPrice", 0f);
        thirdPrice = sharedPref.getFloat("thirdPrice", 0f);
        fourthPrice = sharedPref.getFloat("fourthPrice", 0f);

        firstClickPrice = sharedPref.getFloat("firstClickPrice", 0f);
        secondClickPrice = sharedPref.getFloat("secondClickPrice", 0f);
        thirdClickPrice = sharedPref.getFloat("thirdClickPrice", 0f);
        fourthClickPrice = sharedPref.getFloat("fourthClickPrice", 0f);

        firstCounter = sharedPref.getInt("firstCounter", 0);
        secondCounter = sharedPref.getInt("secondCounter", 0);
        thirdCounter = sharedPref.getInt("thirdCounter", 0);
        fourthCounter = sharedPref.getInt("fourthCounter", 0);

        firstClickCounter = sharedPref.getInt("firstClickCounter", 0);
        secondClickCounter = sharedPref.getInt("secondClickCounter", 0);
        thirdClickCounter = sharedPref.getInt("thirdClickCounter", 0);
        fourthClickCounter = sharedPref.getInt("fourthClickCounter", 0);

        Log.d("SaveLoadTest", "Loaded Successfully");
    }

    void priceChecker(){
        float[] priceArr = new float[8];
        priceArr[0] = firstPrice;
        priceArr[1] = secondPrice;
        priceArr[2] = thirdPrice;
        priceArr[3] = fourthPrice;

        priceArr[4] = firstClickPrice;
        priceArr[5] = secondClickPrice;
        priceArr[6] = thirdClickPrice;
        priceArr[7] = fourthClickPrice;

        for (int i = 0; i < priceArr.length; i++) {
            if(priceArr[i] == 0){
                firstPrice = 10.0f;
                secondPrice = 30.0f;
                thirdPrice = 150.0f;
                fourthPrice = 1000.0f;

                firstClickPrice = 150.0f;
                secondClickPrice = 500.0f;
                thirdClickPrice = 1000.0f;
                fourthClickPrice = 2500.0f;
                break;
            }
        }
    }
}