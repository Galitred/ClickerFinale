package com.example.clickerproject;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    static public float codeLines = 0.0f;
    static public float codeLinesAdd = 1.0f;
    static public float fonSum = 0.0F;
    static public float coinBalance = 0.0f;

    public static Button buttonAdd;

    Button openShop, exchangeButton;
    public static TextView balanceText, moneyBalanceText;

    SharedPreferences balanceSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        balanceSave = getPreferences(MODE_PRIVATE);
        loadBalance();

        if(codeLinesAdd == 0){
            codeLinesAdd = 1;
        }

        buttonAdd = findViewById(R.id.buttonAdd);
        openShop = findViewById(R.id.openShop);
        exchangeButton = findViewById(R.id.openExchange);
        balanceText = findViewById(R.id.balanceText);
        moneyBalanceText = findViewById(R.id.moneyBalanceText);

        buttonAdd.setText("+" + (int)codeLinesAdd);



        Thread CodeLinesThread = new CodeLinesThread();
        CodeLinesThread.start();

        if(CodeLinesThread.isAlive()){
            Log.d("THREAD_CHECK", "Thread alive");
        }
        allMainOperations();

    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    class CodeLinesThread extends Thread {
        @Override
        public void run() {
            Log.d("THREAD_CHECK", "Thread running");
            balanceText.setText("Code Lines: " + String.format("%.2f", codeLines) + "");
            moneyBalanceText.setText("Money: " + String.format("%.2f", coinBalance) + "");

            while (true) {

                try {
                    Thread.sleep(1000);
                    codeLines += fonSum;


                    Log.d("THREAD_CHECK", "Coins added");
                } catch (InterruptedException e) {
                }

                runOnUiThread(() -> {
                    balanceText.setText("Code Lines: " + String.format("%.2f", codeLines) + "");
                    moneyBalanceText.setText("Money: " + String.format("%.2f", coinBalance) + "");

                });
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveBalance();
    }


    @Override
    protected void onPause() {
        super.onPause();
        saveBalance();
    }


    void allMainOperations() {
        buttonAdd.setOnClickListener(v -> {
            /*codeLines = 0;
            codeLinesAdd = 1;
            fonSum = 0;
            coinBalance = 0;*/
            codeLines += codeLinesAdd;
            balanceText.setText("Code Lines: " + String.format("%.2f", codeLines) + "");
        });


        openShop.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ShopActivity.class);
            startActivityForResult(intent, 1);
        });

        exchangeButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ExchangeActivity.class);
            startActivityForResult(intent, 1);
        });
    }


    void saveBalance() {
        SharedPreferences.Editor ed = balanceSave.edit();
        ed.putFloat("CodeLinesSave", codeLines);
        ed.putFloat("FonSumSave", fonSum);
        ed.putFloat("MoneyBalanceSave", coinBalance);
        ed.putFloat("CodeLinesAdd", codeLinesAdd);
        ed.commit();
    }

    void loadBalance(){
        codeLines = balanceSave.getFloat("CodeLinesSave", 0.0f);
        fonSum = balanceSave.getFloat("FonSumSave", 0.0f);
        coinBalance = balanceSave.getFloat("MoneyBalanceSave", 0.0f);
        codeLinesAdd = balanceSave.getFloat("CodeLinesAdd", 0.0f);
        Log.d("SaveLoadTest", "Loaded Successfully");
    }
}