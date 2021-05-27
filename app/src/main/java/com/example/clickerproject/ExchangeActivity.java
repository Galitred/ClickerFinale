package com.example.clickerproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ExchangeActivity extends AppCompatActivity {

    private final List<ExchangeStat> exchangeStatList = new ArrayList<>();
    static public TextView mCurrentBalance;
    Button back;
    ExchangeAdapter adapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        recyclerView = findViewById(R.id.exchangeList);
        /*ExchangeAdapter */
        adapter = new ExchangeAdapter(this, exchangeStatList);
        recyclerView.setAdapter(adapter);
        Thread CurrentBalance = new CurrentBalance();
        CurrentBalance.start();
        Thread RandomGenerator = new RandomGenerator();
        RandomGenerator.start();


        mCurrentBalance = findViewById(R.id.currentBalanceEx);
        mCurrentBalance.setText(String.format("Current balance: " + "%.2f", MainActivity.codeLines));
        back = findViewById(R.id.backToMain);
        returnToMain();
        fillExchange();


        recyclerView.setItemAnimator(null);

        for (int i = 0; i < exchangeStatList.size(); i++) {
            float k = (float) (Math.random() * 25) + 0;
            int signInt = (int) (Math.random() * 1000) + 0;
            float currentPrice;
            float defPrice = exchangeStatList.get(i).getDefExchangePrice();
            currentPrice = exchangeStatList.get(i).getFloatExchangePrice();
            if (signInt <= 500) {
                exchangeStatList.get(i).setExchangePrice(currentPrice + (currentPrice * k / 100));

                if (exchangeStatList.get(i).getFloatExchangePrice() <= defPrice / 3) {
                    exchangeStatList.get(i).setExchangePrice(currentPrice * 2);
                } else if (exchangeStatList.get(i).getFloatExchangePrice() >= defPrice * 3) {
                    exchangeStatList.get(i).setExchangePrice(currentPrice / 2);
                }
                adapter.notifyItemChanged(i);
            } else {
                exchangeStatList.get(i).setExchangePrice(currentPrice - (currentPrice * k / 100));

                if (exchangeStatList.get(i).getFloatExchangePrice() <= defPrice / 3) {
                    exchangeStatList.get(i).setExchangePrice(currentPrice * 2);
                } else if (exchangeStatList.get(i).getFloatExchangePrice() >= defPrice * 3) {
                    exchangeStatList.get(i).setExchangePrice(currentPrice / 2);
                }
                adapter.notifyItemChanged(i);
            }
        }
    }

    class RandomGenerator extends Thread {
        @Override
        public void run() {
            super.run();
            synchronized (this) {
                while (true) {
                    try {
                        wait(5000);

                    } catch (InterruptedException e) {
                    }
                    runOnUiThread(() -> {
                        for (int i = 0; i < exchangeStatList.size(); i++) {
                            float k = (float) (Math.random() * 25) + 0;
                            int signInt = (int) (Math.random() * 1000) + 0;
                            float currentPrice;
                            float defPrice = exchangeStatList.get(i).getDefExchangePrice();
                            currentPrice = exchangeStatList.get(i).getFloatExchangePrice();
                            if (signInt <= 500) {
                                exchangeStatList.get(i).setExchangePrice(currentPrice + (currentPrice * k / 100));

                                if(exchangeStatList.get(i).getFloatExchangePrice() <= defPrice/3){
                                    exchangeStatList.get(i).setExchangePrice(currentPrice*2);
                                } else if(exchangeStatList.get(i).getFloatExchangePrice() >= defPrice*3){
                                    exchangeStatList.get(i).setExchangePrice(currentPrice/2);
                                }
                                adapter.notifyItemChanged(i);
                            } else {
                                exchangeStatList.get(i).setExchangePrice(currentPrice - (currentPrice * k / 100));

                                if(exchangeStatList.get(i).getFloatExchangePrice() <= defPrice/3){
                                    exchangeStatList.get(i).setExchangePrice(currentPrice*2);
                                } else if(exchangeStatList.get(i).getFloatExchangePrice() >= defPrice*3){
                                    exchangeStatList.get(i).setExchangePrice(currentPrice/2);
                                }
                                adapter.notifyItemChanged(i);
                            }
                        }
                    });

                }
            }
        }
    }




    class CurrentBalance extends Thread {
        @Override
        public void run() {
            super.run();
            while(true) {
                try {

                    ShopActivity.CurrentBalance.sleep(1000);
                    mCurrentBalance.setText("Current balance: " + String.format("%.2f", MainActivity.codeLines));


                } catch (InterruptedException e) {
                }

            }
        }
    }


    void returnToMain(){
        back.setOnClickListener(v -> finish());
    }




    private void fillExchange() {
        exchangeStatList.add(new ExchangeStat(1, "Print 2 numbers", 10.0f,2.0f,2.0f, R.drawable.app_icon));
        exchangeStatList.add(new ExchangeStat(2, "Print sum of 2 numbers", 50.0f,12.0f,12.0f, R.drawable.app_icon));
        exchangeStatList.add(new ExchangeStat(3, "Calculator", 100.0f,25.0f,25.0f, R.drawable.app_icon));
        exchangeStatList.add(new ExchangeStat(4, "MusicPlayer", 200.0f,51.0f,51.0f, R.drawable.app_icon));
        exchangeStatList.add(new ExchangeStat(5, "VideoPlayer", 500.0f,130.0f,130.0f, R.drawable.app_icon));
        exchangeStatList.add(new ExchangeStat(6, "8-bit game", 700.0f,195.0f,195.0f, R.drawable.app_icon));
        exchangeStatList.add(new ExchangeStat(7, "2D platformer", 1000.0f,280.0f,280.0f, R.drawable.app_icon));
        exchangeStatList.add(new ExchangeStat(8, "3D Indie Game", 2000.0f,600.0f,600.0f, R.drawable.app_icon));
        exchangeStatList.add(new ExchangeStat(9, "BIOS", 5000.0f,1500.0f,1500.0f, R.drawable.app_icon));
    }


}